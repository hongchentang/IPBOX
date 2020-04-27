package com.hcis.ipr.cms.directive.web;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.hcis.ipanther.common.login.vo.LoginUser;
import com.hcis.ipanther.core.spring.mvc.bind.annotation.FormModel;
import com.hcis.ipanther.core.web.vo.SearchParam;
import com.hcis.ipr.cms.directive.entity.CmsDirective;
import com.hcis.ipr.cms.directive.service.ICmsDirectiveService;
import com.hcis.ipr.cms.site.service.ICmsSiteService;
import com.hcis.ipr.core.entity.Response;
import com.hcis.ipr.core.web.controller.BaseController;

@RequestMapping("/cms/directive")
@Controller
public class CmsDirectiveController extends BaseController{

	@Autowired
	private ICmsSiteService cmsSiteService;
	@Autowired
	private ICmsDirectiveService cmsDirectiveService;
	
	@RequestMapping("listDirective")
	public ModelAndView listDirective(SearchParam searchParam){
		ModelAndView modelAndView=new ModelAndView();
		List<CmsDirective> list =cmsDirectiveService.list(searchParam);
		modelAndView.addObject("resultList", list);
		modelAndView.addObject("paramMap", searchParam.getParamMap());
		return modelAndView;
	}
	
	@RequestMapping("addDirective")
	public ModelAndView listDirective(@FormModel("cmsDirective")CmsDirective cmsDirective){
		ModelAndView modelAndView=new ModelAndView();
		if(cmsDirective!=null&&StringUtils.isNotEmpty(cmsDirective.getId())){
			cmsDirective=cmsDirectiveService.read(cmsDirective.getId());
		}
		modelAndView.addObject("cmsDirective",cmsDirective);
		return modelAndView;
	}
	
	@RequestMapping("readDirective")
	public ModelAndView readDirective(@FormModel("cmsDirective")CmsDirective cmsDirective){
		ModelAndView modelAndView=new ModelAndView();
		if(cmsDirective!=null&&StringUtils.isNotEmpty(cmsDirective.getId())){
			cmsDirective=cmsDirectiveService.read(cmsDirective.getId());
		}
		modelAndView.addObject("cmsDirective",cmsDirective);
		return modelAndView;
	}
	
	@RequestMapping("editDirective")
	@ResponseBody
	public String listDirective(@FormModel("cmsDirective")CmsDirective cmsDirective,HttpServletResponse response){
		int count=cmsDirectiveService.update(cmsDirective, LoginUser.loginUser(request)!=null?LoginUser.loginUser(request).getId():null);
		return this.renderHtml(Response.newDefaultResponse(count).toString(), response);
	}
	
	@RequestMapping("saveDirective")
	@ResponseBody
	public String saveDirective(@FormModel("cmsDirective")CmsDirective cmsDirective,HttpServletResponse response){
		int count=cmsDirectiveService.create(cmsDirective, LoginUser.loginUser(request)!=null?LoginUser.loginUser(request).getId():null);
		return this.renderHtml(Response.newDefaultResponse(count).toString(), response);
	}
	
	@RequestMapping("deleteDirective")
	@ResponseBody
	public String deleteDirective(@FormModel("cmsDirective")CmsDirective cmsDirective,HttpServletResponse response){
		int count=cmsDirectiveService.delete(cmsDirective, LoginUser.loginUser(request)!=null?LoginUser.loginUser(request).getId():null);
		return this.renderHtml(Response.newDefaultResponse(count).toString(), response);
	}
	
//	@RequestMapping("readDirectiveTxt")
//	@ResponseBody
//	public String readDirectiveTxt(SearchParam searchParam,HttpServletResponse response){
//		String root="directive/directiveTxt.txt";
//		String realPath=null;
//		if(searchParam.getParamMap().containsKey("realPath")){
//			realPath=ObjectUtils.toString(searchParam.getParamMap().get("realPath"));
//		}else{
//			realPath =getSession().getServletContext().getRealPath("/");
//			if(StringUtils.contains(realPath, "\\")){
//				realPath = StringUtils.replace(realPath, "\\", "/");
//			}
//		}
//		if(!realPath.endsWith("/")){
//			realPath=realPath+"/";
//		}
//		if(!(realPath+root).endsWith("/")){
//			realPath=realPath+root;
//		}
//	    String contnet=CmsCommonFileUtil.readFile(realPath);
//		return this.renderHtml(contnet, response);
//	}
}
