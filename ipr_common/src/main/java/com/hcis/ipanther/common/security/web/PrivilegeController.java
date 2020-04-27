package com.hcis.ipanther.common.security.web;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import com.hcis.ipanther.common.utils.ApiCode;
import com.hcis.ipanther.common.utils.BaseApi;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.hcis.ipanther.common.login.vo.LoginUser;
import com.hcis.ipanther.common.security.entity.Module;
import com.hcis.ipanther.common.security.entity.Privilege;
import com.hcis.ipanther.common.security.service.IModuleService;
import com.hcis.ipanther.common.security.service.IPrivilegeService;
import com.hcis.ipanther.common.security.utils.SecurityConstants;
import com.hcis.ipanther.core.spring.mvc.bind.annotation.FormModel;
import com.hcis.ipanther.core.utils.CommonConfig;
import com.hcis.ipanther.core.utils.JsonUtil;
import com.hcis.ipanther.core.web.controller.BaseController;
import com.hcis.ipanther.core.web.controller.IBaseFormController;
import com.hcis.ipanther.core.web.vo.AjaxReturnObject;
import com.hcis.ipanther.core.web.vo.BaseListForm;
import com.hcis.ipanther.core.web.vo.SearchParam;

@Controller
@RequestMapping("/common/security/privilege")
public class PrivilegeController extends BaseController implements IBaseFormController<Privilege> {
	@Resource
	private IPrivilegeService privilegeService;
	@Autowired
	private IModuleService moduleService;
	//功能列表
	@Override
	@RequestMapping("/list")
	public ModelAndView list(SearchParam searchParam) {
		ModelAndView mv =new ModelAndView();
		return mv;
	}
	
	//根据模板ID列出功能列表
		@RequestMapping("/listJson")
		@ResponseBody
		public String listJson(SearchParam searchParam,HttpServletResponse response){
			String returnJson=null;
			//获取配置的模块id判断是否是子系统
			String moduleId=CommonConfig.getProperty("app.module.id");
			String[] moduleIds=moduleId.split(";");
			if(null!=moduleIds){
				Module module=moduleService.read(moduleIds[moduleIds.length-1]);
				if(null!=module){
					if(StringUtils.equals(module.getType(), SecurityConstants.MODULE_TYPE_MASTER)){
						List<Module> list= moduleService.list(searchParam);
						//mv.addObject("moduleList", list);
						try {
							returnJson=JsonUtil.toJson(list);
						} catch (IOException e) {
							e.printStackTrace();
						}
					///	mv.addObject("moduleId" , moduleIds[moduleIds.length-1]);
					}
				}
			}
			return this.renderText(returnJson,response);
			//	return returnJson;
		}
	
	
	
	//根据模板ID列出功能列表
	@RequestMapping("/priByModule")
	@ResponseBody
	public String priByModule(@FormModel("module")Module module,HttpServletResponse response){
		String treeView=null;
/*		SearchParam searchParam=new SearchParam();
		module=moduleService.read(module.getId());*/
		List<Map<String, Object>>  list =privilegeService.treeView(new SearchParam(),module);
		try {
			treeView=JsonUtil.toJson(list);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return this.renderText(treeView,response);
	}
	//查看
	@Override
	@RequestMapping("/view")
	public ModelAndView view(@FormModel("privilege")Privilege privilege) {
		ModelAndView mv = this.newModelAndView();
		mv.setViewName("common/security/privilege/view");
		mv.addObject("privilege", privilegeService.read(privilege.getId()));
		return mv;
	}
	//新增页面跳转
	@Override
	@RequestMapping("/add")
	public String add() {
		return "common/security/privilege/add";
	}
	//修改页面跳转
	@Override
	@RequestMapping("/edit")
	public ModelAndView edit(@FormModel("privilege")Privilege privilege) {
		ModelAndView mv = this.newModelAndView();
		mv.setViewName("common/security/privilege/edit");
		mv.addObject("privilege", privilegeService.read(privilege.getId()));
		return mv;
	}
	//保存
	@RequestMapping("/save")
	public @ResponseBody AjaxReturnObject savePrivilege(@FormModel("privilege")Privilege privilege) {
		int count=privilegeService.save(privilege);
		return AjaxReturnObject.newDefaultAjaxReturnObject(count);
	}
	
	//保存
	@RequestMapping("/aadPrivilege")
	public @ResponseBody AjaxReturnObject aadPrivilege(@FormModel("privilege")Privilege privilege) {
		int count=privilegeService.save(privilege);
		return AjaxReturnObject.newDefaultAjaxReturnObject(count);
	}
	
	//删除
	@Override
	@RequestMapping("/delete")
	public @ResponseBody AjaxReturnObject delete(@FormModel("privilege")Privilege privilege) {
		int count=privilegeService.delete(privilege, LoginUser.loginUser(request).getId());
		return AjaxReturnObject.newDefaultAjaxReturnObject(count);
	}

	@Override
	@RequestMapping("/batchDelete")
	public AjaxReturnObject batchDelete(BaseListForm<Privilege> baseListForm) {
		return AjaxReturnObject.newDefaultAjaxReturnObject(privilegeService.batchDelete(baseListForm.getBaseForms(),"")).forwardUrl("/security/privilege/list.do");
	}
	
	//检测方法是否重复
	@RequestMapping("/checkPrivilegeName")
	public @ResponseBody boolean checkPrivilegeName(String privilegeName,String oldPrivilegeName) {
		return privilegeService.checkPrivilegeName(privilegeName, oldPrivilegeName);
	}

	@Override
	public AjaxReturnObject save(Privilege t, Errors errors) {
		return null;
	}

	/**
	 * 获取登录用户菜单
	 * @return
	 */
	@RequestMapping("/menus")
	@ResponseBody
	public BaseApi getLoginMenuRole(String moduleId){
		BaseApi api = new BaseApi();
		try {
 			api.setData(privilegeService.getUserPrivileges(moduleId));
		}catch (Exception e){
			api.setCode(ApiCode.ERROR);
			api.setMsg(e.getMessage());
		}
		return api;
	}
}
