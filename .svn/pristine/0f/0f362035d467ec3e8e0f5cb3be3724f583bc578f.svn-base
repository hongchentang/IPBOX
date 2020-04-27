package com.hcis.ipanther.common.security.web;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import com.hcis.ipanther.common.security.utils.RoleConstant;
import com.hcis.ipanther.common.user.utils.UserConstants;
import com.hcis.ipanther.common.utils.BaseApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.hcis.ipanther.common.login.vo.LoginUser;
import com.hcis.ipanther.common.security.entity.Role;
import com.hcis.ipanther.common.security.service.IModuleService;
import com.hcis.ipanther.common.security.service.IRoleService;
import com.hcis.ipanther.core.spring.mvc.bind.annotation.FormModel;
import com.hcis.ipanther.core.web.controller.BaseController;
import com.hcis.ipanther.core.web.vo.AjaxReturnObject;
import com.hcis.ipanther.core.web.vo.BaseListForm;
import com.hcis.ipanther.core.web.vo.SearchParam;
import com.hcis.ipr.core.entity.Response;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/common/security/role")
public class RoleController extends BaseController {
	@Resource
	private IRoleService roleService;
	@Autowired
	private IModuleService moduleService;
	//角色列表
	@RequestMapping("/list")
	public ModelAndView list(@ModelAttribute("searchParam")SearchParam searchParam) {		
		ModelAndView mv = this.newModelAndView();
		mv.addObject("list", roleService.list(searchParam));
//		mv.addObject("paramMap", searchParam.getParamMap());
		return mv;
	}

	@RequestMapping("/jsonList")
	@ResponseBody
	public List<Role> jsonList(@ModelAttribute("searchParam")SearchParam searchParam){
		List<Role> reList = new ArrayList<>();
		List<Role> list = roleService.list(searchParam);
		for(Role role : list){
			if(!RoleConstant.SUPER_ADMIN.equals(role.getRoleCode())){
				reList.add(role);
			}
		}

		return reList;
	}

	//查看
	@RequestMapping("/view")
	public ModelAndView view(@FormModel("role")Role role) {
		ModelAndView mv = this.newModelAndView();
		mv.addObject("role", roleService.view(role));
		return mv;
	}
	//新增页面跳转
	@RequestMapping("/add")
	public ModelAndView add() {
		ModelAndView mv = this.newModelAndView();
		//mv.addObject("moduleCheckBox", moduleService.getModuleCheck("role.module", null));
		return mv;
	}
	//编辑页面跳转
	@RequestMapping("/edit")
	public ModelAndView edit(@FormModel("role")Role role) {
		ModelAndView mv = this.newModelAndView();
		mv.setViewName("common/security/role/edit");
		mv.addObject("role", roleService.read(role.getId()));
		//mv.addObject("moduleCheckBox", moduleService.getModuleCheck("role.module", role.getId()));
		return mv;
	}
	//保存
	@RequestMapping("/save")
	public @ResponseBody Response save(@FormModel("role")Role role,@FormModel("oldRoleName")String oldRoleName,HttpServletResponse response) {
		if(null==role.getModule()){
			role.setModule("");
		}
		boolean check= roleService.checkRoleName(role!=null?role.getName():null, oldRoleName);
		if(!check){
			return new Response("300", "角色名已存在");
		}
		int count= roleService.save(role,request);
		return Response.newDefaultResponse(count);
	}
	//删除
	@RequestMapping("/delete")
	public @ResponseBody AjaxReturnObject delete(@FormModel("role")Role role) {
		int count=roleService.delete(role,LoginUser.loginUser(request).getId());
		return AjaxReturnObject.newDefaultAjaxReturnObject(count);
		//return AjaxReturnObject.newDefaultAjaxReturnObject(roleService.delete(role)).forwardUrl("/security/role/list.do");
	}

	@RequestMapping("/batchDelete")
	public @ResponseBody AjaxReturnObject batchDelete(BaseListForm<Role> baseListForm) {
		return AjaxReturnObject.newDefaultAjaxReturnObject(roleService.batchDelete(baseListForm.getBaseForms(),LoginUser.loginUser(request).getId())).forwardUrl("/security/role/list.do");
	}
	
	//检测名字是否重复
	@RequestMapping("/checkRoleName")
	public @ResponseBody boolean checkRoleName(String roleName,String oldRoleName) {
		return roleService.checkRoleName(roleName, oldRoleName);
	}

}
