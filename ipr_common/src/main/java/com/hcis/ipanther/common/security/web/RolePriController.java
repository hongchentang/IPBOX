package com.hcis.ipanther.common.security.web;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.hcis.ipanther.common.security.entity.Module;
import com.hcis.ipanther.common.security.entity.Role;
import com.hcis.ipanther.common.security.service.IModuleService;
import com.hcis.ipanther.common.security.service.IRolePriService;
import com.hcis.ipanther.common.security.service.IRoleService;
import com.hcis.ipanther.core.spring.mvc.bind.annotation.FormModel;
import com.hcis.ipanther.core.utils.JsonUtil;
import com.hcis.ipanther.core.web.controller.BaseController;
import com.hcis.ipanther.core.web.vo.AjaxReturnObject;
import com.hcis.ipanther.core.web.vo.SearchParam;
@Controller
@RequestMapping("/common/security/rolePri")
public class RolePriController extends BaseController{
	
	@Resource
	private IRolePriService rolePriService;
	@Autowired
	private IModuleService moduleService;
	@Autowired
	private IRoleService roleService;

	//跳转角色与功能对应关系配置页面
	@RequestMapping("/rolePriEdit")
	public ModelAndView rolePriEdit(@FormModel("role")Role role) {
		ModelAndView mv = this.newModelAndView();
		mv.setViewName("common/security/role/rolePriEdit");
/*		//获取配置的模块id判断是否是子系统
		String moduleId=CommonConfig.getProperty("app.module.id");
		String[] moduleIds=moduleId.split(";");
		if(null!=moduleIds){
			Module module=moduleService.read(moduleIds[moduleIds.length-1]);
			if(null!=module){
				if(StringUtils.equals(module.getType(), SecurityConstants.MODULE_TYPE_MASTER)){
					SearchParam searchParam=new SearchParam();
					mv.addObject("moduleList", moduleService.list(searchParam));
				}else{
					mv.addObject("module", module);
				}
			}
		}*/
		mv.addObject("role", roleService.read(role.getId()));
		return mv;
	}
	//根据模板ID列出功能列表
	@RequestMapping("/priByModule")
	public @ResponseBody String priByModule(@FormModel("role")Role role,HttpServletResponse response){
	//	ModelAndView mv = this.newModelAndView();
		Module module=moduleService.read(role.getModule());
		List<Map<String,Object>> list=rolePriService.rolePriTree(role,module);
		String json=null;
		try {
			json=JsonUtil.toJson(list);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return this.renderText(json,response);
		//return mv;
		//return treeView;
	}
	
	//更新角色与功能对应关系 参数roleId,priIds,moduleId
	@RequestMapping("/update")
	public @ResponseBody AjaxReturnObject update(SearchParam searchParam) {
		int count=rolePriService.updateRolePri(searchParam);
		return AjaxReturnObject.newDefaultAjaxReturnObject(count);
	}
	
}
