package com.hcis.ipanther.common.user.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.hcis.ipanther.common.dept.service.IDepartmentService;
import com.hcis.ipanther.common.security.entity.UserRole;
import com.hcis.ipanther.common.security.service.IUserRoleService;
import com.hcis.ipanther.common.user.entity.User;
import com.hcis.ipanther.common.user.service.IUserDeptService;
import com.hcis.ipanther.common.user.service.IUserService;
import com.hcis.ipanther.core.spring.mvc.bind.annotation.FormModel;
import com.hcis.ipanther.core.utils.JsonUtil;
import com.hcis.ipanther.core.web.controller.BaseController;
import com.hcis.ipanther.core.web.vo.AjaxReturnObject;
import com.hcis.ipanther.core.web.vo.SearchParam;

@Controller("common_userRoleController")
@RequestMapping("/common/user")
public class UserRoleController  extends BaseController {

	@Autowired
	private IDepartmentService departmentService;
	@Autowired
	private IUserRoleService userRoleService;
	@Autowired
	private IUserService  userService;
	@Autowired
	private IUserDeptService userDeptService;
	
	//角色分配用户列表
	@RequestMapping("/roleConfigList")
	public ModelAndView roleConfigList(@ModelAttribute("searchParam")SearchParam searchParam) {
		ModelAndView mv = this.newModelAndView();
		mv.addObject("listUser", userService.listUser(searchParam));
		mv.addObject("paramMap", searchParam.getParamMap());
		return mv;
	}
	
	//跳转配置角色页面
	@RequestMapping("/roleConfig")
	public ModelAndView goRoleConfig(@FormModel("user")User user){
		ModelAndView mv = new ModelAndView();
		//mv.addObject("checkboxStr", userRoleService.getAllRolesCheckBox(user,"userView.roleId"));
		//mv.addObject("checkboxList", userRoleService.getModuleCheckBox(user, "user.roleId"));
		mv.addObject("user", user);
		return mv;
	}
	
	//跳转配置角色页面
	@RequestMapping("/roleConfigTree")
	public String roleConfigTrre(@FormModel("user")User user,HttpServletResponse response){
		//ModelAndView mv = new ModelAndView();
		//mv.addObject("checkboxStr", userRoleService.getAllRolesCheckBox(user,"userView.roleId"));
		//mv.addObject("checkboxList", );
		//mv.addObject("user", user);
		String returnJson=null;
		List<?> list= userRoleService.getModuleCheckBox(user, "user.roleId");
		try {
			returnJson=JsonUtil.toJson(list);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return this.renderText(returnJson, response);
	}
	
	//保存角色配置
	@RequestMapping("/saveRoleConfig")
	@ResponseBody
	public String saveRoleConfig(@FormModel("user") User user,@FormModel("userRole")UserRole userRole,HttpServletResponse response) throws Exception{
		//ModelAndView mv = new ModelAndView();
		int count=userService.saveRoleConfig(user,userRole);
		return this.renderJson(AjaxReturnObject.newDefaultAjaxReturnObject(count),response); 
	}
	
	//跳转批量配置角色页面
	@RequestMapping("batchRoleConfig")
	public ModelAndView goBatchRoleConfig(@ModelAttribute("searchParam")SearchParam searchParam){
		ModelAndView mv = new ModelAndView();
		//mv.addObject("checkboxStr", userRoleService.getAllRolesCheckBox(null,"roleId"));
		mv.addObject("checkboxList", userRoleService.getModuleCheckBox(null, "roleId"));
		mv.addObject("userIds", searchParam.getParamMap().get("hiddenId"));
		return mv;
	}
	
	//保存批量角色配置
	@RequestMapping("saveBatchRoleConfig")
	@ResponseBody
	public String saveBatchRoleConfig(@ModelAttribute("searchParam")SearchParam searchParam,String roleId,HttpServletResponse response) throws Exception{
		int count=0;
		count=userService.saveBatchRoleConfig(searchParam, roleId);
		return this.renderJson(AjaxReturnObject.newDefaultAjaxReturnObject(count),response); 
	}
	
}
