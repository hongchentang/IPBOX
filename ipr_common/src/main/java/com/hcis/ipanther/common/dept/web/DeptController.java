package com.hcis.ipanther.common.dept.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.security.auth.Subject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.hcis.ipanther.common.attachment.entity.Attachment;
import com.hcis.ipanther.common.attachment.utils.AttachmentUtils;
import com.hcis.ipanther.common.dept.entity.Department;
import com.hcis.ipanther.common.dept.entity.UnitLegal;
import com.hcis.ipanther.common.dept.entity.UnitFund;
import com.hcis.ipanther.common.dept.entity.UnitIntellectual;

import com.hcis.ipanther.common.dept.service.IDepartmentService;
import com.hcis.ipanther.common.dept.service.IUnitLegalService;
import com.hcis.ipanther.common.dept.service.IUnitQualificationService;
import com.hcis.ipanther.common.dept.service.IUnitIntellectualService;

import com.hcis.ipanther.common.dept.service.IUnitFundService;
import com.hcis.ipanther.common.dict.utils.DictionaryUtils;
import com.hcis.ipanther.common.login.vo.LoginUser;
import com.hcis.ipanther.common.regions.service.IRegionsService;
import com.hcis.ipanther.common.regions.utils.RegionsUtil;
import com.hcis.ipanther.common.user.entity.User;
import com.hcis.ipanther.common.user.entity.UserRegister;
import com.hcis.ipr.core.entity.Response;
import com.hcis.ipanther.common.json.JsonForMap;
import net.sf.json.JSONObject;

import com.hcis.ipanther.core.spring.mvc.bind.annotation.FormModel;
import com.hcis.ipanther.core.utils.ExportUtils;
import com.hcis.ipanther.core.web.controller.BaseController;
import com.hcis.ipanther.core.web.vo.AjaxReturnObject;
import com.hcis.ipanther.core.web.vo.SearchParam;

@Controller
@RequestMapping("/common/dept")
public class DeptController extends BaseController{
	
	private final static Log log = LogFactory.getLog(DeptController.class);

	@Autowired
	private IDepartmentService departmentService;
	
	@Autowired
	private IRegionsService regionsService;
	
	@Autowired
	private IUnitLegalService unitLegalService;
	
	@Autowired
	private IUnitFundService unitFundService;
	
	@Autowired
	private IUnitQualificationService unitQualificationService;
	
	@Autowired
	private IUnitIntellectualService unitIntellectualService;
	
	/**
	 * 展示当前人所属及辖下的区域树
	 * @param searchParam
	 * @return
	 */
	@RequestMapping("areaTree")
	public ModelAndView areaTree(@ModelAttribute("searchParam")SearchParam searchParam){
		ModelAndView mv = this.newModelAndView();
		mv.setViewName("/common/dept/areaTree");
		String treeStr=regionsService.getAreaTree(searchParam);
		mv.addObject("treeStr", treeStr);
		return mv;
	}
	
	/**
	 * 展示当前机构及其下属组织机构
	 * @param searchParam
	 * @return
	 */
	@RequestMapping("deptTree")
	public ModelAndView deptTree(@ModelAttribute("searchParam")SearchParam searchParam){
		ModelAndView mv = this.newModelAndView();
		mv.setViewName("/common/dept/deptTree");
		//String treeStr=departmentService.getDeptTree(searchParam);
		String treeStr = departmentService.getAllDeptTree(searchParam);
		mv.addObject("treeStr", treeStr);
		return mv;
	}


	@RequestMapping("/getDepartmentList")
	@ResponseBody
	public List<Department> getDepartmentList(String unitId){
		return departmentService.getDepartmentList(unitId);
	}

	/**
	 * 机构列表
	 * action为expoert时为导出，不分页
	 * @param searchParam
	 * @return
	 */
	@RequestMapping("/list")
	public ModelAndView list(@ModelAttribute("searchParam")SearchParam searchParam,HttpServletResponse response) {
		ModelAndView mv = this.newModelAndView();
		String regionsCode = (String) searchParam.getParamMap().get("regionsCode");
		if(null!=regionsCode){
			String action = (String) searchParam.getParamMap().get("action");
			if("export".equals(action)) {//导出
				searchParam.getPagination().setAvailable(false);
				List<Department> list=departmentService.list(searchParam);
				//转码
				for (Department dept : list) {
					dept.setRegionsName(RegionsUtil.getRegions(regionsCode).getRegionsName());
					dept.setDeptType(DictionaryUtils.getEntryName("DEPT_TYPEDEPT_TYPE", dept.getDeptType()));
					dept.setDeptCategory(DictionaryUtils.getEntryName("DEPT_CATEGORY", dept.getDeptCategory()));
					dept.setDeptNature(DictionaryUtils.getEntryName("DEPT_NATURE_TYPE", dept.getDeptNature()));
					dept.setTrainOrgLevel(DictionaryUtils.getEntryName("TRAIN_ORG_LEVEL", dept.getTrainOrgLevel()));
				}
				try {
					Map<String,Object> beans = new HashMap<String,Object>();
					beans.put("jigou", list);
					ExportUtils.exportExcel(response, "/excel/deptExport.xls", beans, "机构导出");
				} catch (Exception e) {
					e.printStackTrace();
					log.error("导出出错",e);
				}
				return null;
			} else {//正常查询
				List<Department> list=departmentService.list(searchParam);
				mv.addObject("list", list);
				mv.addObject("areaName", RegionsUtil.getRegions(regionsCode).getRegionsName());
			}
		}
		return mv;
	}
	
	/**
	 * 根据区域码输出区域下所有的机构
	 * @param dept
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping("/getDeptByRegionsCode")
	public void getDeptByRegionsCode(Department dept,HttpServletResponse response) throws IOException {
		List<Department> depts = new ArrayList<Department>();
		if(StringUtils.isNotEmpty(dept.getRegionsCode())) {
			depts= departmentService.getDeptByRegionsCode(dept);
		}
		this.renderJson(depts, response);
	}
	
	/**
	 * 以json格式返回机构信息
	 * @param dept
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping("/getDeptByDeptId")
	public void getDeptByDeptId(Department dept,HttpServletResponse response) throws IOException {
		dept = departmentService.read(dept.getId());
		this.renderJson(dept, response);
	}
	
	/**
	 * 跳转到新增页面
	 * @param searchParam
	 * @return
	 */
	@RequestMapping("/goAdd")
	public ModelAndView goAdd(SearchParam searchParam, String id) {
		ModelAndView mv = this.newModelAndView();
		String regionsCode = (String) searchParam.getParamMap().get("regionsCode");
		if(StringUtils.isNotBlank(id)){
			mv.addObject("dept", departmentService.read(id));
		}
		mv.addObject("regions", RegionsUtil.getRegions(regionsCode));
		mv.setViewName("/common/dept/add");
		return mv;
	}
	
	/**
	 * 跳转到编辑页面
	 * @param dept
	 * @return
	 */
	@RequestMapping({"/edit","view"})
	public ModelAndView edit(@FormModel("dept")Department dept) {
		ModelAndView mv = this.newModelAndView();
		mv.setViewName("/common/dept/detail");
		dept = departmentService.read(dept.getId());
		mv.addObject("dept",dept);
		String regionsCode = dept.getRegionsCode();
		mv.addObject("regions", RegionsUtil.getRegions(regionsCode));
		return mv;
	}

	/**
	 * 详情
	 * @param id
	 * @return
	 */
	@RequestMapping("/detail")
	public ModelAndView detail(String id){

		ModelAndView view = new ModelAndView();
		Department department = departmentService.read(id);
		view.addObject("dept", department);
		return view;
	}

	/**
	 * 保存
	 * @param dept
	 * @param response
	 * @return
	 */
	@RequestMapping("/save")
	@ResponseBody
	public AjaxReturnObject save(Department dept,@RequestParam(value="upload",required=false) MultipartFile upload,HttpServletResponse response) {
		int count = 0;
		String id = dept.getId();
		/*
		 * 上传logo
		 */
		String info = AttachmentUtils.updateAvatar(dept, upload, "logo");
		if(StringUtils.isNotEmpty(info)){
			return AjaxReturnObject.newDefaultAjaxReturnObject(0);
		}
		LoginUser loginUser = LoginUser.loginUser(request);
		dept.setUnitId(loginUser.getCompanyId());
		//临时值
		dept.setRegionsCode("231005");
		if(StringUtils.isEmpty(id)) {
			//新增
			count = departmentService.create(dept, loginUser.getId());
		} else {
			//修改
			count= departmentService.update(dept, loginUser.getId());
		}
		return AjaxReturnObject.newDefaultAjaxReturnObject(count);
	}
	
	/**
	 * 删除
	 * @param
	 * @param response
	 * @return
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public AjaxReturnObject delete(Department dept,HttpServletResponse response) {
		int count = departmentService.delete(dept, LoginUser.loginUser(request).getId());
		return AjaxReturnObject.newDefaultAjaxReturnObject(count);
	}
	
	/**
	 * 跳转到打印页面
	 * @param dept
	 * @return
	 */
	@RequestMapping("/print")
	public ModelAndView print(Department dept) {
		ModelAndView mv = this.newModelAndView();
		dept = departmentService.read(dept.getId());
		mv.addObject("dept",dept);
		String regionsCode = dept.getRegionsCode();
		mv.addObject("regions", RegionsUtil.getRegions(regionsCode));
		mv.setViewName("/common/dept/print");
		return mv;
	}
	
	@RequestMapping("selectDept")
	public ModelAndView selectDept(@ModelAttribute("searchParam")SearchParam searchParam){
		ModelAndView modelAndView=new ModelAndView();
		modelAndView.addObject("deptList",departmentService.list(searchParam));
		modelAndView.addObject("paramMap", searchParam.getParamMap());
		return modelAndView;
	}
	/**
	 * 机构基本信息列表
	 * @param
	 * @param searchParam
	 * @param
	 * @return
	 */
	@RequestMapping("/tabListDetails")
	public ModelAndView tabListDetails(@FormModel("dept")Department dept,SearchParam searchParam) {
		ModelAndView modelAndView = new ModelAndView("/common/dept/tabListDetails");
		dept = departmentService.read((String) searchParam.getParamMap().get("departmentId"));
		modelAndView.addObject("department",dept);
		return modelAndView;
	}
	/**
	 * 基本信息完善
	 * @param searchParam
	 * @return
	 */
	@RequestMapping("/listDept")
	public ModelAndView listDept(@FormModel("department")Department department,SearchParam searchParam,String id){
		ModelAndView modelAndView=new ModelAndView();
		modelAndView.addObject("department",departmentService.read(id));
		String regionsCode = department.getRegionsCode();
		modelAndView.addObject("regions", RegionsUtil.getRegions(regionsCode));
		return modelAndView;
	}
	
	@RequestMapping("/departmentSave")
	@ResponseBody
	public AjaxReturnObject departmentSave(Department department,SearchParam searchParam){
		int statusCode=200;
		String msg="操作成功！";
		if(!department.getProvince().isEmpty() && department.getRegionsCode().isEmpty()){
			String regionsCode = department.getProvince();
			department.setRegionsCode(regionsCode);
		}
		departmentService.update(department, LoginUser.loginUser(request).getId());
		return new AjaxReturnObject(statusCode,msg);
	}
	/**
	 * 机构财务信息完善
	 * @param searchParam
	 * @return
	 */
	@RequestMapping("/listFinancial")
	public ModelAndView listFinancial(SearchParam searchParam){
		ModelAndView modelAndView=new ModelAndView();
		return modelAndView;
	}
	/**
	 * 机构管理人员信息完善
	 * @param searchParam
	 * @return
	 */
	@RequestMapping("/listManagers")
	public ModelAndView listManagers(SearchParam searchParam){
		ModelAndView modelAndView=new ModelAndView();
		//modelAndView.addObject("deptManagers",departmentService.list(searchParam));
		return modelAndView;
	}
	
	@RequestMapping("/listManagersGrid")
	@ResponseBody
	public JSONObject listManagersGrid(SearchParam searchParam,HttpServletRequest request){
		//modelAndView.addObject("deptManagers",departmentService.list(searchParam));
		JSONObject object = new JSONObject();
		ArrayList<UnitLegal> list = (ArrayList)unitLegalService.list(searchParam);
		object = JsonForMap.JsonFormartMap(list);
		return object;
	}
	/**
	 * 受资助情况完善
	 * @param searchParam
	 * @return
	 */
	@RequestMapping("/listFunds")
	public ModelAndView listFunds(SearchParam searchParam){
		ModelAndView modelAndView=new ModelAndView();
		return modelAndView;
	}
	
	
	
	@RequestMapping("/listFundsGrid")
	@ResponseBody
	public JSONObject listFundsGrid(SearchParam searchParam,HttpServletRequest request){
		//modelAndView.addObject("deptManagers",departmentService.list(searchParam));
		JSONObject object = new JSONObject();
		ArrayList<UnitFund> list = (ArrayList)unitFundService.list(searchParam);
		object = JsonForMap.JsonFormartMap(list);
		return object;
	}
	/**
	 * 机构企业资质
	 * @param searchParam
	 * @return
	 */
	@RequestMapping("/listProperties")
	public ModelAndView listProperties(SearchParam searchParam){
		ModelAndView modelAndView=new ModelAndView();
		String id=searchParam.getParamMap().get("departmentId").toString();
		modelAndView.addObject("unitQualification",unitQualificationService.read(id));
				
		return modelAndView;
	}
	/**
	 * 员工信息列表
	 * @param searchParam
	 * @return
	 */
	@RequestMapping("/listEmployee")
	public ModelAndView listEmployee(SearchParam searchParam,String id){
		ModelAndView modelAndView=new ModelAndView();
		//searchParam.getParamMap().put("projectStatus", "2");
		//modelAndView.addObject("employee",unitEmployeeService.read(id));
		return modelAndView;
	}
	/**
	 * 知识产权信息
	 * @param searchParam
	 * @return
	 */
	@RequestMapping("/listIntellectual")
	public ModelAndView listIntellectual(SearchParam searchParam){
		ModelAndView modelAndView=new ModelAndView();
		//searchParam.getParamMap().put("projectStatus", "2");
		return modelAndView;
	}
	
	@RequestMapping("/listIntelltualsGrid")
	@ResponseBody
	public JSONObject listIntelltualsGrid(SearchParam searchParam,HttpServletRequest request){
		//modelAndView.addObject("deptManagers",departmentService.list(searchParam));
		JSONObject object = new JSONObject();
		ArrayList<UnitIntellectual> list = (ArrayList)unitIntellectualService.list(searchParam);
		object = JsonForMap.JsonFormartMap(list);
		return object;
	}


	@RequestMapping("/tree")
	public ModelAndView tree(@ModelAttribute("searchParam") SearchParam searchParam) {
		ModelAndView view = new ModelAndView("/common/dept/tree");
		try {
			//String treeStr = departmentService.getDeptTree(searchParam);
			String treeStr = departmentService.getAllDeptTree(searchParam);
			view.addObject("treeStr", treeStr);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return view;
	}
}
