package com.hcis.items.web;

import com.hcis.ipanther.common.dept.entity.Department;
import com.hcis.ipanther.common.login.vo.LoginUser;
import com.hcis.ipanther.common.regions.utils.RegionsUtil;
import com.hcis.ipanther.common.user.service.IUserService;
import com.hcis.ipanther.core.web.controller.BaseController;
import com.hcis.ipanther.core.web.vo.AjaxReturnObject;
import com.hcis.ipanther.core.web.vo.SearchParam;
import com.hcis.items.entity.ItemsManager;
import com.hcis.items.entity.ItemsLibrary;
import com.hcis.items.entity.ItemsExpert;
import com.hcis.items.service.ItemsManagerService;
import com.hcis.items.service.ItemsLibraryService;
import com.hcis.items.service.ItemsExpertService;
import com.hcis.ipr.train.train.service.ITrainCourseService;
import com.hcis.ipanther.common.dept.service.IDepartmentService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.support.DefaultMultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;



@Controller
@RequestMapping("/itemsManager/apply")
public class ItemsManagerController extends BaseController{

	@Autowired
	private ItemsManagerService itemsManagerService;

	@Autowired
	private ItemsLibraryService itemsLibraryService;
	
	@Autowired
	private ItemsExpertService itemsExpertService;
	
	@Autowired IUserService userService;
	
	@Autowired
	private IDepartmentService departmentService;
	
	/****************************项目库操作*****************/
	/**
	 * 获取新建的项目库列表
	 * @param searchParam
	 * @return
	 */
	@RequestMapping("tabItemsLibrary")
	public ModelAndView tabItemsLibrary(SearchParam searchParam) {
		ModelAndView modelAndView=new ModelAndView();
		itemsLibraryService.listLibrary(searchParam,request);
		return modelAndView;
	}
	/**
	 * 查询项目库，根据isPass查询结果。0未通过，1未通过,这里为所有项目列表
	 * @param searchParam
	 * @return
	 */
	@RequestMapping("libraryList")
	public ModelAndView libraryList(SearchParam searchParam){
			ModelAndView modelAndView=new ModelAndView();
//			searchParam.getParamMap().put("isPass", "0");
			modelAndView.addObject("listProject", itemsLibraryService.listLibrary(searchParam,request));
			return modelAndView;
	}
		
	/**
	 * 添加项目库数据，返回视图addLibrary.jsp
	 * @param itemsLibrary
	 * @param searchParam
	 * @return
	 */
	@RequestMapping("goAddLibrary")
	public ModelAndView goAddLibrary(ItemsLibrary itemsLibrary,SearchParam searchParam) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/itemsManager/apply/addLibrary");
		if(itemsLibrary!=null&&StringUtils.isNotBlank(itemsLibrary.getId())){
			modelAndView.addObject("itemsLibrary", itemsLibraryService.read(itemsLibrary.getId()));
		}
		return modelAndView;
	}
	
	@RequestMapping("goCheckLibrary")
	public ModelAndView goCheckLibrary(ItemsLibrary itemsLibrary,SearchParam searchParam) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/itemsManager/apply/checkLibrary");
		if(itemsLibrary!=null&&StringUtils.isNotBlank(itemsLibrary.getId())){
			modelAndView.addObject("itemsLibrary", itemsLibraryService.read(itemsLibrary.getId()));
		}
		return modelAndView;
	}
	
	
	/**
	 * 保存项目库内容，有附件上传附件
	 * @param itemsLibrary
	 * @param request
	 * @return
	 */
	@RequestMapping("saveLibrary")
	@ResponseBody
	public AjaxReturnObject saveLibrary(@ModelAttribute("itemsLibrary")ItemsLibrary itemsLibrary,DefaultMultipartHttpServletRequest request){
		int statusCode=200;
		String msg="操作成功！";
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;  
		MultipartFile file = multipartRequest.getFile("uploadFile");
		//项目附件
		if(file!=null&&!file.isEmpty()){
			String fileInfo=itemsLibraryService.uploadFile(itemsLibrary, file);
			if(StringUtils.isNotBlank(fileInfo)){
				return new AjaxReturnObject(300, fileInfo);
			}
		}
		
		if(StringUtils.isNotBlank( itemsLibrary.getId())){
			itemsLibraryService.update(itemsLibrary, LoginUser.loginUser(request).getId());
		}else{
			int createCount=itemsLibraryService.create(itemsLibrary, LoginUser.loginUser(request).getId());
		}
		
		return new AjaxReturnObject(statusCode, msg);
	}
	
	/**
	 * 删除项目库中的数据，只做逻辑删除
	 * @param itemsLibrary
	 * @return
	 */
	
	@RequestMapping("deleteLibrary")
	@ResponseBody
	public AjaxReturnObject deleteProject(@ModelAttribute("itemsLibrary")ItemsLibrary itemsLibrary){
		SearchParam se=new SearchParam();
		se.getParamMap().put("id",itemsLibrary.getId());
		int statusCode=200;
		String msg="操作成功！";		
		itemsLibraryService.delete(itemsLibrary, LoginUser.loginUser(request).getId());
		return new AjaxReturnObject(statusCode, msg);
	}
	
	/**
	 * 确认新增的项目可进行结题，流转到下一个User使用环节
	 * @param itemsLibrary
	 * @return
	 */
	@RequestMapping("submitLibrary")
	@ResponseBody
	public AjaxReturnObject submitLibrary(ItemsLibrary itemsLibrary){
		int statusCode=200;
		String msg="操作成功！";
		itemsLibrary=itemsLibraryService.read(itemsLibrary.getId());
		itemsLibraryService.submitLibrary(itemsLibrary, request);
		return new AjaxReturnObject(statusCode, msg);
	}
	
	
	/**
	 * 可申请结题，但还没有结题在申请中的项目列表：0，未申请，1 申请........10 结题
	 * @param searchParam
	 * @return
	 */
	@RequestMapping("libraryListUnPass")
	public ModelAndView libraryListUnPass(SearchParam searchParam){
		ModelAndView modelAndView=new ModelAndView();
		searchParam.getParamMap().put("isPass","1");
		searchParam.getParamMap().put("isApply","1");
//		searchParam.getParamMap().put("status","10");
		modelAndView.addObject("listProject", itemsLibraryService.listLibrary(searchParam,request));
		return modelAndView;
	}	
	
	/**
	 * 已经完成项目结题的项目status 0，未申请，1 申请........10 结题
	 * @param searchParam
	 * @return
	 */
	@RequestMapping("libraryListPass")
	public ModelAndView libraryListPass(SearchParam searchParam){
		ModelAndView modelAndView=new ModelAndView();
		searchParam.getParamMap().put("isPass","1");
		searchParam.getParamMap().put("isApply","1");
		searchParam.getParamMap().put("status","10");
		modelAndView.addObject("listProject", itemsLibraryService.listLibrary(searchParam,request));
		return modelAndView;
	}
	
	/****************************项目结题申请操作*****************/
	/**
	 * 用户进行结题申请列表，从项目库中选取项目进行结题
	 * @param searchParam
	 * @return
	 */
	@RequestMapping("tabList")
	public ModelAndView tabList(SearchParam searchParam){
		ModelAndView modelAndView=new ModelAndView();
		return modelAndView;
	}
	
	/**
	 * 用户进行结题申请列表，已经确认可以结题的，但是还木有进去申请结题的列表项进行申请
	 * @param searchParam
	 * @return
	 */
	@RequestMapping("listEdit")
	public ModelAndView listEdit(SearchParam searchParam){
		ModelAndView modelAndView=new ModelAndView();	
		searchParam.getParamMap().put("isPass", "1");
		searchParam.getParamMap().put("isApply","0");
		modelAndView.addObject("listProject", itemsLibraryService.listLibrary(searchParam,request));
		return modelAndView;
	}
	

	
	/**
	 * 已经申请结题的所有项目进度情况列表
	 * @param searchParam
	 * @return
	 */
	@RequestMapping("listPass")
	public ModelAndView listPass(SearchParam searchParam){
		ModelAndView modelAndView=new ModelAndView();
		//searchParam.getParamMap().put("projectStatus", "2");
		searchParam.getParamMap().put("creator", LoginUser.loginUser(request).getId());
		modelAndView.addObject("listProject", itemsManagerService.listDone(searchParam, request));
		return modelAndView;
	}
	
	/**
	 * 项目结题列表界面，已申请但没有结题的列表
	 * @param searchParam
	 * @return
	 */
	@RequestMapping("listUnPass")
	public ModelAndView listUnPass(SearchParam searchParam){
		ModelAndView modelAndView=new ModelAndView();
		searchParam.getParamMap().put("projectStatus", "0");
		searchParam.getParamMap().put("creator", LoginUser.loginUser(request).getId());
		modelAndView.addObject("listProject", itemsManagerService.listDone(searchParam, request));
		return modelAndView;
	}
	
	/**
	 * 
	 * @param itemsManager
	 * @param searchParam
	 * @return addProject.jsp
	 */
	@RequestMapping("goAddProject")
	public ModelAndView goAddProject(ItemsManager itemsManager,SearchParam searchParam){
		ModelAndView modelAndView=new ModelAndView();
		modelAndView.setViewName("/itemsManager/apply/addProject");
		if(itemsManager!=null&&StringUtils.isNotBlank(itemsManager.getId())){
			modelAndView.addObject("projectItem", itemsManagerService.read(itemsManager.getId()));
		}
		return modelAndView;
	}
	
	
	/**
	 * user 进行查看并上传附件进行保存
	 */
	@RequestMapping("goSubProject")
	public ModelAndView goSubProject(ItemsManager itemsManager,SearchParam searchParam){
		ModelAndView modelAndView=new ModelAndView();
		modelAndView.setViewName("/itemsManager/apply/addProject");
		if(itemsManager!=null&&StringUtils.isNotBlank(itemsManager.getId())){
			modelAndView.addObject("projectItem", itemsManagerService.read(itemsManager.getId()));
		}
		return modelAndView;
	}
	//保存项目
	@RequestMapping("saveProject")
	@ResponseBody
	public AjaxReturnObject saveProject(@ModelAttribute("itemsManager")ItemsManager itemsManager,DefaultMultipartHttpServletRequest request){
		int statusCode=200;
		String msg="操作成功！";
		/**
		 * 使用的是界面上的参数，可能会导致一些数据无法更新，id不为空时，故而先读取数据库中的数据，然后进行更新作业。
		 */
		if(StringUtils.isNotBlank( itemsManager.getId())){
			itemsManager =itemsManagerService.read(itemsManager.getId());
		}
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;  
		MultipartFile file = multipartRequest.getFile("uploadFile");
		//项目附件
		if(file!=null&&!file.isEmpty()){
			String fileInfo=itemsManagerService.uploadFile(itemsManager, file);
			if(StringUtils.isNotBlank(fileInfo)){
				return new AjaxReturnObject(300, fileInfo);
			}
		}
		if(StringUtils.isNotBlank( itemsManager.getId())){

			itemsManagerService.update(itemsManager, LoginUser.loginUser(request).getId());
		}else{
			int createCount=itemsManagerService.create(itemsManager, LoginUser.loginUser(request).getId());
		}
		
		return new AjaxReturnObject(statusCode, msg);
	}
	
	/**
	 * 项目结题明细
	 */
	@RequestMapping("goDetailProject")
	public ModelAndView goDetailProject(ItemsManager itemsManager,SearchParam searchParam){
		ModelAndView modelAndView=new ModelAndView();
		modelAndView.setViewName("/itemsManager/apply/detailProject");
		if(itemsManager!=null&&StringUtils.isNotBlank(itemsManager.getId())){
			modelAndView.addObject("projectItem", itemsManagerService.read(itemsManager.getId()));
		}
		return modelAndView;
	}
	
	/**
	 * 逻辑删除已申请但没有提交的项目，同时更新项目库数据。通过ItemsManager.projectSourceCode进行和ItemsLibrary.id
	 * @param itemsManager
	 * @return
	 */
	@RequestMapping("deleteProject")
	@ResponseBody
	public AjaxReturnObject deleteProject(@ModelAttribute("itemsManager")ItemsManager itemsManager){
		SearchParam se=new SearchParam();
		se.getParamMap().put("id",itemsManager.getId());
		System.out.println(itemsManager.getId());
		
		itemsManager = itemsManagerService.read(itemsManager.getId());
		System.out.println(itemsManager.getProjectSourceCode());
		ItemsLibrary itemsLibrary = new ItemsLibrary();
		itemsLibrary = itemsLibraryService.read(itemsManager.getProjectSourceCode());
		itemsLibrary.setIsApply("0");
		int statusCode=200;
		String msg="操作成功！";		
		itemsManagerService.delete(itemsManager, LoginUser.loginUser(request).getId());
		itemsLibraryService.update(itemsLibrary, LoginUser.loginUser(request).getId());
		
		
		return new AjaxReturnObject(statusCode, msg);
	}
	
	//申报提交项目
	@RequestMapping("submitProject")
	public @ResponseBody AjaxReturnObject submitProject(ItemsLibrary itemsLibrary ){
		int statusCode=200;
		String msg="操作成功！";
		itemsLibrary=itemsLibraryService.read(itemsLibrary.getId());
		ItemsManager itemsManager = new ItemsManager();
		itemsManager.setProjectSourceCode(itemsLibrary.getId());
		itemsManager.setProjectCode(itemsLibrary.getProjectCode());
		itemsManager.setProjectName(itemsLibrary.getProjectName());
		itemsManager.setProjectDeadline(itemsLibrary.getProjectDeadline());
		itemsManager.setProjectType(itemsLibrary.getProjectType());
		itemsManager.setProjectExplain(itemsLibrary.getProjectExplain());
		itemsManager.setProjectNotes(itemsLibrary.getProjectNotes());
		itemsManager.setProjectRuleFile(itemsLibrary.getProjectRuleFile());
		int createCount=itemsManagerService.create(itemsManager, LoginUser.loginUser(request).getId());
		if (createCount == 0) {
			statusCode = 301;
			msg ="申请失败，请检查操作，谢谢！";
		}else {
			itemsLibrary.setIsApply("1");
			itemsLibraryService.update(itemsLibrary, LoginUser.loginUser(request).getId());
		}
		return new AjaxReturnObject(statusCode, msg);
	}
	
	//提交结题申请,IsApply由0变为1,projectStatus由0变为2一次审核,flowstatus也由0变为2
	@RequestMapping("applyProject")
	public @ResponseBody AjaxReturnObject applyList(ItemsManager itemsManager,SearchParam searchparam){
		int statusCode=200;
		String msg="操作成功！";
		itemsManager.setProjectStatus("2");
		itemsManager.setFlowStatus("2");
		itemsManagerService.update(itemsManager, LoginUser.loginUser(request).getId());
		
		/**
		 * 更新项目库状态，由未申请转化成以一次申请审查状态，即status由0变为0
		 */
//		ItemsLibrary itemsLibrary = new ItemsLibrary();
//		itemsLibrary = itemsLibraryService.read(itemsManager.getProjectSourceCode());
//		itemsLibrary.setStatus("2");
//		itemsLibraryService.update(itemsLibrary, LoginUser.loginUser(request).getId());

		
		return new AjaxReturnObject(statusCode, msg);
		
		
		
	}
	//申请页面
	@RequestMapping("applyList")
	public ModelAndView applyList(SearchParam searchParam){
		ModelAndView modelAndView=new ModelAndView();
		searchParam.getParamMap().put("projectStatus", "1");
		searchParam.getParamMap().put("flowStatus", "1");
		searchParam.getParamMap().put("creator", LoginUser.loginUser(request).getId());
		modelAndView.addObject("listProject", itemsManagerService.applyListDone(searchParam, request));
		return modelAndView;
	}
	
	/**
	 * 
	 * @param searchParam
	 * @return
	 */
	@RequestMapping("tabCheckList")
	public ModelAndView tabCheckList(SearchParam searchParam) {
		ModelAndView modelAndView=new ModelAndView();
		itemsManagerService.applyListCheck(searchParam, request);
		return modelAndView;
	}
	//中心第一次审查检查页面
	@RequestMapping("checkList")
	public ModelAndView checkList(SearchParam searchParam){
		ModelAndView modelAndView=new ModelAndView();
		searchParam.getParamMap().put("projectStatus", "2");
		searchParam.getParamMap().put("flowStatus", "2");
//		searchParam.getParamMap().put("creator", LoginUser.loginUser(request).getId());
		modelAndView.addObject("checkProject", itemsManagerService.applyListCheck(searchParam, request));
		return modelAndView;
	}
	
	@RequestMapping("checkListTwo")
	public ModelAndView checkListTwo(SearchParam searchParam){
		ModelAndView modelAndView=new ModelAndView();
		searchParam.getParamMap().put("projectStatus", "3");
		searchParam.getParamMap().put("flowStatus", "3");
//		searchParam.getParamMap().put("creator", LoginUser.loginUser(request).getId());
		modelAndView.addObject("checkProject", itemsManagerService.applyListCheck(searchParam, request));
		return modelAndView;
	}
	
	/**
	 * 中心进行第一材料审查，分为两种，查看详情后继续审查或者一键审查
	 * 此处是一键审查，点击一次审查确认即可 project Status 由2转换成3，从而进入资料复查
	 */
	@RequestMapping("submitCheckList")
	public @ResponseBody  AjaxReturnObject submitCheckList(ItemsManager itemsManager){
		int statusCode=200;
		String msg="操作成功！";
		itemsManager=itemsManagerService.read(itemsManager.getId());
		if (itemsManager.getProjectStatus().equals("2")) {
			itemsManager.setProjectStatus("3");
			itemsManager.setFlowStatus("3");
			itemsManager.setFirstCheckDate(new Date());
			itemsManager.setFirstChecker(LoginUser.loginUser(request).getId());
		}else
		{
			itemsManager.setProjectStatus("4");
			itemsManager.setFlowStatus("4");
			itemsManager.setFirstAgainCheckDate(new Date());
			itemsManager.setFirstAgainChecker(LoginUser.loginUser(request).getId());
		}
		itemsManager.setFirstCheckDate(new Date());
		itemsManager.setFirstChecker(LoginUser.loginUser(request).getId());
		itemsManagerService.update(itemsManager, LoginUser.loginUser(request).getId());
		return new AjaxReturnObject(statusCode, msg);
	}
	
	/**
	 * 中心一次资料审查查看详情，然后进行确认后保存，并表示确认提交，关闭表示暂时不提交，拒绝，返回申请。
	 */
	@RequestMapping("checkView")
	public ModelAndView checkView(ItemsManager itemsManager,SearchParam searchParam){
		ModelAndView modelAndView=new ModelAndView();
		modelAndView.setViewName("/itemsManager/apply/checkView");
		String id =itemsManager.getId();
		itemsManager = itemsManagerService.read(id); 
		if(itemsManager!=null&&StringUtils.isNotBlank(itemsManager.getId())){
			modelAndView.addObject("itemsCheck", itemsManager);
		}
		return modelAndView;
	}
	
	/**
	 * 中心一次资料审查查看详情，然后进行确认后保存，并表示确认提交，转让一次复审
	 * 
	 */
	@RequestMapping("saveCheck")
	public @ResponseBody AjaxReturnObject saveCheck(@ModelAttribute("itemsManager")ItemsManager itemsManager){
		int statusCode=200;
		String msg="操作成功！";
		/* 不能重新读取数据库中的数据，因为可能存在通过是由通过意见或者拒绝意见*/
		//itemsManager=itemsManagerService.read(itemsManager.getId());
		

		if(itemsManager.getProjectStatus()=="2") {
			itemsManager.setFlowStatus("3");
			itemsManager.setProjectStatus("3");
			itemsManager.setFirstCheckStatus("1");
			itemsManager.setFirstCheckDate(new Date());
			itemsManager.setFirstChecker(LoginUser.loginUser(request).getId());
			
		}else {
			itemsManager.setFlowStatus("4");
			itemsManager.setProjectStatus("4");
			itemsManager.setFirstAgainCheckStatus("1");
			itemsManager.setFirstAgainCheckDate(new Date());
			itemsManager.setFirstAgainChecker(LoginUser.loginUser(request).getId());
		}
		itemsManagerService.update(itemsManager, LoginUser.loginUser(request).getId());
		return new AjaxReturnObject(statusCode, msg);
	}

	/**
	 * 提交结题申请,IsApply由0变为1,projectStatus由0变为2一次审核,flowstatus也由0变为2
	 * 提交结题申请时,IsApply由0变为1,projectStatus由0变为2一次审核,flowstatus也由0变为2。
	 * 此处是拒绝动作，故应该将此刻的状态将IsApply由1变为0,projectStatus由2变为0一次审核,flowstatus也由2变为0
	 */
	@RequestMapping("saveDenialCheck")
	public @ResponseBody AjaxReturnObject saveDenialCheck(@ModelAttribute("itemsManager")ItemsManager itemsManager){
		int statusCode=200;
		String msg="操作成功！";
		//itemsManager=itemsManagerService.read(itemsManager.getId());		
		itemsManager.setFirstAgainCheckDate(new Date());
		itemsManager.setFirstAgainChecker(LoginUser.loginUser(request).getId());
		itemsManager.setProjectStatus("0");
		itemsManager.setFlowStatus("0");
		itemsManagerService.update(itemsManager, LoginUser.loginUser(request).getId());
		itemsManagerService.delete(itemsManager, LoginUser.loginUser(request).getId());
		return new AjaxReturnObject(statusCode, msg);
	}
	

	
	//分配专家及机构		 
	@RequestMapping("tabAssignList")
	public ModelAndView tabAssignList(SearchParam searchParam){
		ModelAndView modelAndView=new ModelAndView();
		itemsManagerService.listTodo(searchParam,request);
		return modelAndView;
	}
	
	//专家及机构分配页面
	@RequestMapping("assignListEidt")
	public ModelAndView assignList(SearchParam searchParam){
		ModelAndView modelAndView=new ModelAndView();
		searchParam.getParamMap().put("flowStatus", "4");
		searchParam.getParamMap().put("projectStatus", "4");
		modelAndView.addObject("assignList", itemsManagerService.assignList(searchParam, request));
		return modelAndView;
	}
	
	/**
	 * 已分配分配页面
	 * @param searchParam
	 * @return
	 */
	@RequestMapping("assignExpert")
	public ModelAndView assignExpert(SearchParam searchParam){
		ModelAndView modelAndView=new ModelAndView();
		//searchParam.getParamMap().put("creator", LoginUser.loginUser(request).getId());
		searchParam.getParamMap().put("projectIsExport", "1");
		modelAndView.addObject("auditLists", itemsManagerService.listExpert(searchParam, request));
		return modelAndView;
	}
	/**
	 * 已分配机构页面
	 * @param searchParam
	 * @return
	 */
	@RequestMapping("assignAgency")
	public ModelAndView assignAgency(SearchParam searchParam){
		ModelAndView modelAndView=new ModelAndView();
//		searchParam.getParamMap().put("creator", LoginUser.loginUser(request).getId());
		searchParam.getParamMap().put("projectIsOthers", "1");
		modelAndView.addObject("agencyLists", itemsManagerService.listAssignAgency(searchParam));
		return modelAndView;
	}
	/**
	 * 专家分配界面
	 * @param searchParam
	 * @return
	 */
	@RequestMapping("divideExpertView")
	public ModelAndView divideExpertView(SearchParam searchParam){
		ModelAndView modelAndView=new ModelAndView();
		modelAndView.setViewName("itemsManager/apply/divideExpertView");
		Object idObj=searchParam.getParamMap().get("id");
		if(null!=idObj&&StringUtils.isNotBlank(idObj.toString())){
			modelAndView.addObject("listExperts", itemsManagerService.listAssignExpert(searchParam));
		}
		return modelAndView;
	}
	/**
	 * 专家列表
	 */
	@RequestMapping("divideExperts")
	public ModelAndView divideExperts(SearchParam searchParam){
		ModelAndView modelAndView=new ModelAndView();
		modelAndView.setViewName("itemsManager/apply/divideExperts");
		Object teacherIdsObj=searchParam.getParamMap().get("teacherIds");
		if(null!=teacherIdsObj&&StringUtils.isNotBlank(teacherIdsObj.toString())){
			//String teacherIds="'"+teacherIdsObj.toString().replaceAll(",", "','")+"'";
			searchParam.getParamMap().put("excludeId", teacherIdsObj.toString());
		}
		//角色为教师类型的人
		searchParam.getParamMap().put("roleCode", "expert");
		modelAndView.addObject("listExerts", userService.selectExperts(searchParam));
		return modelAndView;
	}

	
	/**
	 * 保存的专家到ItemsExpert entity 中
	 * @param searchParam
	 * @return孵化
	 */
	@RequestMapping("saveExpertDevide")
	@ResponseBody
	public AjaxReturnObject saveExpertDevide(SearchParam searchParam){	
		int statusCode =200;
		String msg ="操作成功";
		String id = searchParam.getParamMap().get("id").toString();
		ItemsManager itemsManager = itemsManagerService.read(id);
		String projectCode = itemsManager.getProjectCode().toString();
		String projectName = itemsManager.getProjectName().toString();
		String ids= searchParam.getParamMap().get("teacherIds").toString();
												   
		System.out.println(ids);
		if(ids.length() !=0){

			String[] expertids= searchParam.getParamMap().get("teacherIds").toString().split(",");
			int flag = 0;//0表示成功，1表示失败
			try {
				flag = itemsExpertService.deleteExperts(searchParam);
			}catch(Exception e) {
				e.printStackTrace();
				statusCode=301;
				msg ="重新分配专家失败";
				return new AjaxReturnObject(statusCode, msg);	
			}
			for(int i=0;i<expertids.length;i++) {
//				if(flag ==1) {
					ItemsExpert itemsExpert = new ItemsExpert();
					itemsExpert.setProjectSourceCode(id);
					itemsExpert.setProjectCode(projectCode);
					itemsExpert.setProjectName(projectName);
					itemsExpertService.create(itemsExpert, expertids[i]);
			
//				}
			}
			itemsManager.setProjectIsExport("1");
			itemsManagerService.update(itemsManager, LoginUser.loginUser(request).getId());
		}
		
		//itemsManagerService.saveExpertDevide(searchParam, LoginUser.loginUser(request));
		return new AjaxReturnObject(statusCode, msg);	
		
	}
	
	
	/**
	 * 机构分配页面
	 * @param searchParam
	 * @return
	 */
	@RequestMapping("divideAgencyView")
	public ModelAndView divideAgencyView(SearchParam searchParam){
		ModelAndView modelAndView=new ModelAndView();
		modelAndView.setViewName("itemsManager/apply/divideAgencyView");
		Object idObj=searchParam.getParamMap().get("id");
		if(null!=idObj&&StringUtils.isNotBlank(idObj.toString())){
			modelAndView.addObject("listAgency", itemsManagerService.listAssignAgency(searchParam));
		}
		return modelAndView;
	}
	
	/**
	 * 机构列表
	 * @param searchParam
	 * @return
	 */
	@RequestMapping("divideAgency")
	public ModelAndView divideAgency(SearchParam searchParam){
		ModelAndView modelAndView=new ModelAndView();
		modelAndView.setViewName("itemsManager/apply/divideAgency");
		Object teacherIdsObj=searchParam.getParamMap().get("teacherIds");
		if(null!=teacherIdsObj&&StringUtils.isNotBlank(teacherIdsObj.toString())){
			//String teacherIds="'"+teacherIdsObj.toString().replaceAll(",", "','")+"'";
			searchParam.getParamMap().put("excludeId", teacherIdsObj.toString());
		}
//		String regionsCode = (String) searchParam.getParamMap().get("regionsCode");
		List<Department> list=departmentService.list(searchParam);
//		mv.addObject("list", list);
//		mv.addObject("areaName", RegionsUtil.getRegions(regionsCode).getRegionsName());
		modelAndView.addObject("listAgency", list);
		return modelAndView;
	}
	
	/**
	 * 几机构保存，即更新ItemnsManager 单元
	 * @param searchParam
	 * @return
	 */	
	@RequestMapping("saveAgencyDevide")
	@ResponseBody
	public AjaxReturnObject saveAgencyDevide(ItemsManager itemsManager,SearchParam searchParam){	
		int statusCode =200;
		String msg ="操作成功";
		String id = searchParam.getParamMap().get("id").toString();
		String otherChecker = searchParam.getParamMap().get("othersChecker").toString();
	    itemsManager = itemsManagerService.read(id);
		
		Date time = new Date();
		itemsManager.setOthersCheckDate(time);
		itemsManager.setOthersChecker(otherChecker);
		itemsManagerService.update(itemsManager, LoginUser.loginUser(request).getId());
		
		//itemsManagerService.saveExpertDevide(searchParam, LoginUser.loginUser(request));
		return new AjaxReturnObject(statusCode, msg);	
		
	}
	/**
	 * 提交 分配数据
	 * @param searchParam
	 * @return
	 */
	@RequestMapping("saveAssignDone")
	@ResponseBody
	public AjaxReturnObject saveAssignDone(ItemsManager itemsManager){
		
		int statusCode =200;
		String msg ="操作成功";
		SearchParam se=new SearchParam();
		//itemsManager = itemsManagerService.read(itemsManager.getId());
		
		itemsManager.setProjectIsExport("1");
		itemsManager.setProjectIsOthers("1");
		
		//itemsManager.setProjectIsOthers("1");
		//专家审核
		itemsManager.setProjectStatus("6");
		itemsManager.setFlowStatus("6");
		itemsManagerService.update(itemsManager, LoginUser.loginUser(request).getId());
		return new AjaxReturnObject(statusCode, msg);
	}
	
	
	/**
	 * 专家审查页面及上传附件，保存附件
	 * @param searchParam
	 * @return
	 */
	@RequestMapping("expertList")
	public ModelAndView exportList(SearchParam searchParam){
		ModelAndView modelAndView=new ModelAndView();
		searchParam.getParamMap().put("projectStatus", "6");
		searchParam.getParamMap().put("flowStatus", "6");
		searchParam.getParamMap().put("creator", LoginUser.loginUser(request).getId());
		modelAndView.addObject("listExpert", itemsManagerService.listExpert(searchParam, request));
		return modelAndView;
	}
	
	//专家上传附件
	@RequestMapping("auditExpertView")
	public ModelAndView auditExpertView(ItemsExpert itemsExpert,SearchParam searchParam) {
		ModelAndView modelAndView=new ModelAndView();
		modelAndView.setViewName("itemsManager/apply/auditExpertView");
//		String idObj=searchParam.getParamMap().get("id").toString();
		searchParam.getParamMap().put("projectStatus", "6");
		searchParam.getParamMap().put("flowStatus", "6");
		searchParam.getParamMap().put("creator", LoginUser.loginUser(request).getId());
		searchParam.getParamMap().put("id", itemsExpert.getId());
//		if(null!=idObj&&StringUtils.isNotBlank(idObj.toString())){
		if(itemsExpert!=null&&StringUtils.isNotBlank(itemsExpert.getId())){
			modelAndView.addObject("projectExpert", itemsManagerService.listExpertView(searchParam, request));
//			}
		}
		return modelAndView;
	}
	
	//保存专家附件
	
	@RequestMapping("saveExpertView")
	public @ResponseBody AjaxReturnObject saveExpertView(@ModelAttribute("itemsExpert")ItemsExpert itemsExpert,DefaultMultipartHttpServletRequest request){
		int statusCode=200;
		String msg="操作成功！";
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;  
		MultipartFile file = multipartRequest.getFile("uploadFile");
		//项目附件
		if(file!=null&&!file.isEmpty()){
			String fileInfo=itemsExpertService.uploadFile(itemsExpert, file);
			if(StringUtils.isNotBlank(fileInfo)){
				return new AjaxReturnObject(300, fileInfo);
			}
		}		
		itemsExpert.setExpertCheckDate(new Date());
		itemsExpert.setCreator(LoginUser.loginUser(request).getId());
		itemsExpertService.update(itemsExpert, LoginUser.loginUser(request).getId());

		
		return new AjaxReturnObject(statusCode, msg);
	}
	//专家提交审稿
	@RequestMapping("submitExpert")
	@ResponseBody
	public AjaxReturnObject submitExpert(ItemsExpert itemsExpert){
		int statusCode=200;
		String msg="操作成功！";
		
		itemsExpert = itemsExpertService.read(itemsExpert.getId());
		ItemsManager itemsManager = new ItemsManager();
		itemsManager=itemsManagerService.read(itemsExpert.getProjectSourceCode());
		itemsManager.setProjectStatus("7");
		itemsManager.setFlowStatus("7");
		itemsManagerService.update(itemsManager, LoginUser.loginUser(request).getId());
		
//		SearchParam searchParam = new SearchParam();
//		searchParam.getParamMap().put("creator",LoginUser.loginUser(request).getId());
//		searchParam.getParamMap().put("projectSourceCode",itemsManager.getId());
		itemsExpertService.update(itemsExpert, LoginUser.loginUser(request).getId());
		
		return new AjaxReturnObject(statusCode, msg);
	}
	
	@RequestMapping("assignedExpertList")
	public ModelAndView assignedExpertList(SearchParam searchParam) {
		ModelAndView modelAndView = new ModelAndView();
		//searchParam.getParamMap().put("creator", LoginUser.loginUser(request).getId());
		searchParam.getParamMap().put("projectIsExport", "1");
		modelAndView.addObject("auditLists", itemsManagerService.listExpert(searchParam, request));
		return modelAndView;
	}
	
	//第三方机构审查页面
	@RequestMapping("auditList")
	public ModelAndView auditList(SearchParam searchParam){
		ModelAndView modelAndView=new ModelAndView();
		searchParam.getParamMap().put("projectStatus", "7");
		searchParam.getParamMap().put("flowStatus", "7");
		searchParam.getParamMap().put("creator", LoginUser.loginUser(request).getId());
		modelAndView.addObject("auditLists", itemsManagerService.auditAgencyList(searchParam));
		return modelAndView;
	}
	
	/**
	 * 第三方机构,添加审计文件，并返回值
	 * @param searchParam
	 * @return
	 */
	@RequestMapping("auditAgencyView")
	public ModelAndView auditAgencyView(ItemsManager itemsManager,SearchParam searchParam){
		ModelAndView modelAndView=new ModelAndView();
		String id = itemsManager.getId();
		itemsManager = itemsManagerService.read(id);
		modelAndView.addObject("auditAgency",itemsManager);
		return modelAndView;
	}
	
	@RequestMapping("saveAgencytView")
	public @ResponseBody AjaxReturnObject saveAgencytView(@ModelAttribute("itemsManager")ItemsManager itemsManager,DefaultMultipartHttpServletRequest request){
		int statusCode=200;
		String msg="操作成功！";
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;  
		MultipartFile file = multipartRequest.getFile("uploadFile");
		//项目附件
		String id=itemsManager.getId();
		itemsManager =itemsManagerService.read(id);
		if(file!=null&&!file.isEmpty()){
			String fileInfo=itemsManagerService.uploadFile(itemsManager, file);
			if(StringUtils.isNotBlank(fileInfo)){
				return new AjaxReturnObject(300, fileInfo);
			}
		}		
		// itemsManager.setExpertCheckDate(new Date());
		itemsManager.setCreator(LoginUser.loginUser(request).getId());
		itemsManagerService.update(itemsManager, LoginUser.loginUser(request).getId());

		
		return new AjaxReturnObject(statusCode, msg);
	}
	
	@RequestMapping("submitAgency")
	@ResponseBody
	public AjaxReturnObject submitAgency(ItemsManager itemsManager){
		int statusCode=200;
		String msg="操作成功！";
		itemsManager.setProjectStatus("8");
		itemsManager.setFlowStatus("8");
		itemsManagerService.update(itemsManager, LoginUser.loginUser(request).getId());
		return new AjaxReturnObject(statusCode, msg);
	}
	//中心二次检查页面
	@RequestMapping("auditListTwo")
	public ModelAndView auditListTwo(SearchParam searchParam, HttpServletRequest request){
		ModelAndView modelAndView=new ModelAndView();
		searchParam.getParamMap().put("projectStatus", "8");
		searchParam.getParamMap().put("flowStatus", "8");
		modelAndView.addObject("auditLists", itemsManagerService.listExpert(searchParam, request));
		return modelAndView;
	}
	/**
	 * 中心复查的详细内容
	 * @param itemsManager
	 * @param searchParam
	 * @return
	 */
	@RequestMapping("auditTwoView")
	public ModelAndView auditTwoView(ItemsManager itemsManager,SearchParam searchParam,HttpServletRequest request){
		ModelAndView modelAndView=new ModelAndView();
		modelAndView.setViewName("itemsManager/apply/auditTwoView");
		String id=searchParam.getParamMap().get("projectSourceCode").toString();
		String projectSourceCode=searchParam.getParamMap().get("projectSourceCode").toString();
//		itemsManager = itemsManagerService.read(id);
//		Object aa =itemsManagerService.readbyProjectSourceCode(searchParam);
		itemsManager =itemsManagerService.readbyProjectSourceCode(searchParam);
		if(itemsManager!=null&&StringUtils.isNotBlank(itemsManager.getId())){
			modelAndView.addObject("auditItems", itemsManager);
//			}
		}
		
		return modelAndView;
		
		
//		ModelAndView modelAndView=new ModelAndView();
//		
////		String idObj=searchParam.getParamMap().get("id").toString();
//		searchParam.getParamMap().put("projectStatus", "6");
//		searchParam.getParamMap().put("flowStatus", "6");
//		searchParam.getParamMap().put("creator", LoginUser.loginUser(request).getId());
//		searchParam.getParamMap().put("sourceId", itemsExpert.getId());
////		if(null!=idObj&&StringUtils.isNotBlank(idObj.toString())){
//		if(itemsExpert!=null&&StringUtils.isNotBlank(itemsExpert.getId())){
//			modelAndView.addObject("projectExpert", itemsManagerService.listExpert(searchParam, request));
////			}
//		}
		
	}
	
	
	//中心二次检查页面复查
	@RequestMapping("auditListTwoAgain")
	public ModelAndView auditListTwoAgain(SearchParam searchParam){
		ModelAndView modelAndView=new ModelAndView();
		searchParam.getParamMap().put("projectStatus", "9");
		searchParam.getParamMap().put("flowStatus", "9");
		modelAndView.addObject("auditTwoLists", itemsManagerService.listExpert(searchParam, request));
		return modelAndView;
	}
	
	@RequestMapping("auditFinishList")
	public ModelAndView auditFinishList(SearchParam searchParam) {
		ModelAndView modelAndView = new ModelAndView();
		searchParam.getParamMap().put("projectStatus", "10");
		searchParam.getParamMap().put("flowStatus", "10");
		modelAndView.addObject("finishList",itemsManagerService.auditAgencyList(searchParam));
		return modelAndView;
	}
	
	@RequestMapping("subLastAudit")
	@ResponseBody
	public AjaxReturnObject subLastAudit(ItemsExpert itemsExpert) {
		int statusCode=200;
		String id = itemsExpert.getId();
		itemsExpert = itemsExpertService.read(id);
		ItemsManager itemsManager = new ItemsManager();
		itemsManager = itemsManagerService.read(itemsExpert.getProjectSourceCode());
		String msg="操作成功！";
		if("8".equals(itemsManager.getProjectStatus())){
			itemsManager.setProjectStatus("9");
			itemsManager.setFlowStatus("9");	
		}else {
			itemsManager.setProjectStatus("10");
			itemsManager.setFlowStatus("10");
		}

		itemsManagerService.update(itemsManager, LoginUser.loginUser(request).getId());		
		return new AjaxReturnObject(statusCode, msg);
	}
	
	/**
	 * 将ItemsManager 数据退回到申请状态补充资料，同时添加times 添加1
	 * @param itemsManager
	 * @return
	 */
	@RequestMapping("submitRollback")
	@ResponseBody
	public AjaxReturnObject submitRollback(ItemsManager itemsManager) {
		int statusCode=200;
		String msg="操作成功！";
		String id = itemsManager.getId();
		itemsManager = itemsManagerService.read(id);
		if(itemsManager.getProjectStatus().equals("2")){
			itemsManager.setProjectStatus("0");
			itemsManager.setFlowStatus("0");
		}else if(itemsManager.getProjectStatus().equals("3")){
			itemsManager.setProjectStatus("2");
			itemsManager.setFlowStatus("2");
		}else if(itemsManager.getProjectStatus().equals("8")){
			itemsManager.setProjectStatus("7");
			itemsManager.setFlowStatus("7");
		}else{
			itemsManager.setProjectStatus("8");
			itemsManager.setFlowStatus("8");
		}	
		itemsManagerService.update(itemsManager, LoginUser.loginUser(request).getId());
		//itemsManagerService.delete(itemsManager, LoginUser.loginUser(request).getId());
		return new AjaxReturnObject(statusCode, msg);
	}
	
	
	//跳往专题专家上传附件页面
	@RequestMapping("goUploadFile")
	public ModelAndView goUploadFile(SearchParam searchParam){
		ModelAndView modelAndView=new ModelAndView();
		modelAndView.setViewName("/space/archive/upload");
		return modelAndView;
	}
	
}
