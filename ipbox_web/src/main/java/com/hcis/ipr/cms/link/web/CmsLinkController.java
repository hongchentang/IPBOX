package com.hcis.ipr.cms.link.web;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.hcis.ipanther.common.login.vo.LoginUser;
import com.hcis.ipr.core.entity.Response;
import com.hcis.ipanther.core.spring.mvc.bind.annotation.FormModel;
import com.hcis.ipanther.core.web.controller.BaseController;
import com.hcis.ipanther.core.web.vo.SearchParam;
import com.hcis.ipr.cms.link.entity.CmsLink;
import com.hcis.ipr.cms.link.service.ICmsLinkService;
import com.hcis.ipr.cms.site.entity.CmsSite;

@Controller
@RequestMapping("/cms/link")
public class CmsLinkController extends BaseController {

	
	@Autowired
	private ICmsLinkService cmsLinkService;
	
	
	@RequestMapping("listLink")
	public ModelAndView listLink(SearchParam searchParam){
		ModelAndView modelAndView=new ModelAndView();
		if(!searchParam.getParamMap().containsKey("siteId")){
		  CmsSite cmsSite= (CmsSite) getSession().getAttribute("manageSite");
		  searchParam.getParamMap().put("siteId", cmsSite.getId());
		}
		modelAndView.addObject("resultList", cmsLinkService.list(searchParam));
		return modelAndView;
	}
	
	@RequestMapping("addLink")
	public ModelAndView addLink(@FormModel("cmsLink")CmsLink cmsLink){
		ModelAndView modelAndView=new ModelAndView();
		if(StringUtils.isNotEmpty(cmsLink.getId())){
			cmsLink=cmsLinkService.read(cmsLink.getId());
			modelAndView.addObject("cmsLink", cmsLink);
		}
		return modelAndView;
	}
	
	
	@RequestMapping("deleteLink")
	@ResponseBody
	public String deleteLink(@FormModel("cmsLink")CmsLink cmsLink,HttpServletResponse response){
		int count=cmsLinkService.delete(cmsLink, LoginUser.loginUser(request)!=null?LoginUser.loginUser(request).getId(): null);
		 return this.renderHtml(Response.newDefaultResponse(count).toString(), response);
	}
	
	@RequestMapping("saveLink")
	@ResponseBody
	public String saveLink(@FormModel("cmsLink")CmsLink cmsLink,HttpServletResponse response){
		int count=cmsLinkService.create(cmsLink, LoginUser.loginUser(request)!=null?LoginUser.loginUser(request).getId(): null);
		return this.renderHtml(Response.newDefaultResponse(count).toString(), response);
	}
	
	@RequestMapping("editLink")
	@ResponseBody
	public String editLink(@FormModel("cmsLink")CmsLink cmsLink,HttpServletResponse response){
		int count=cmsLinkService.update(cmsLink, LoginUser.loginUser(request)!=null?LoginUser.loginUser(request).getId(): null);
		return this.renderHtml(Response.newDefaultResponse(count).toString(), response);
	}
	
}
