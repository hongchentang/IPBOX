package com.hcis.ipanther.common.security.web;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.hcis.ipanther.common.login.vo.LoginUser;
import com.hcis.ipanther.common.security.entity.Module;
import com.hcis.ipanther.common.security.service.IModuleService;
import com.hcis.ipr.core.entity.Response;
import com.hcis.ipanther.core.spring.mvc.bind.annotation.FormModel;
import com.hcis.ipanther.core.web.controller.BaseController;
import com.hcis.ipanther.core.web.vo.AjaxReturnObject;

/**
 * 模块
 * @author 邓学辉
 * @time 2014年9月19日18:22:32
 */

@Controller
@RequestMapping("/common/security/module")
public class ModuleController extends BaseController{
	
	@Autowired
	private IModuleService moduleService;
	
	//跳转新增页面
	@RequestMapping("add")
	public ModelAndView add(){
		ModelAndView mv=this.newModelAndView();
		return mv;
	}

	//保存新增
	@RequestMapping("save")
	@ResponseBody
	public Response save(@FormModel("module")Module module){
		int count=0;
		try{
		     count=moduleService.create(module,LoginUser.loginUser(request).getId());
		}catch (Exception e) {
			e.printStackTrace();
			return	new Response("300","模块标识(标识ID)已存在");
		//return AjaxReturnObject.newDefaultAjaxReturnObject(count, 300, "标识ID已存在");
		}
		return count>0?Response.successInstance():Response.failInstance();
		
	}
	//跳转编辑页面
	@RequestMapping("goEdit")
	public ModelAndView goEdit(@FormModel("module")Module module){
		ModelAndView mv=new ModelAndView("/common/security/module/edit");
		module=moduleService.read(module!=null?module.getId():null);
		mv.addObject("module", module);
		return mv;
	}
	
	//保存新增
	@RequestMapping("edit")
	@ResponseBody
	public AjaxReturnObject edit(@FormModel("module")Module module){
		int count=moduleService.update(module,LoginUser.loginUser(request).getId());
		return AjaxReturnObject.newDefaultAjaxReturnObject(count);
	}
	
	//删除
	@RequestMapping("delete")
	@ResponseBody 
	public AjaxReturnObject delete(@FormModel("module")Module module){
		int count=moduleService.delete(module, LoginUser.loginUser(request).getId());
		return AjaxReturnObject.newDefaultAjaxReturnObject(count);
	}
	
	//检测模块ID是否重复
	@RequestMapping("checkModuleCode")
	@ResponseBody
	public String checkModuleCode(@FormModel("module")Module module){
		String flg="true";
		module=moduleService.read(module!=null?module.getId():null);
		if(null!=module){
			flg="编码已存在！";
		}
		return flg;
	}
	
}
