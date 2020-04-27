package com.hcis.ipr.index;


import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.hcis.ipanther.core.web.controller.BaseController;
import com.hcis.ipr.cms.site.entity.CmsSite;
import com.hcis.ipr.cms.site.service.ICmsSiteService;

@Controller("cmsIndexController")
@RequestMapping("/cms/index")
public class CmsIndexController extends BaseController{

	@Autowired
	private ICmsSiteService cmsSiteService;
	
	@RequestMapping("index")
	public void index(@ModelAttribute("siteId")String siteId,HttpServletRequest servletRequest,HttpServletResponse response) throws IOException, ServletException{
		CmsSite manageSite=null;
		if (StringUtils.isNotEmpty(siteId)) {
			//指定管理站点
			manageSite=cmsSiteService.read(siteId);
		}else {
			if (getSession().getAttribute("manageSite")!=null) {
				manageSite=(CmsSite)getSession().getAttribute("manageSite");
			}else {
				//提取主站点
				manageSite=cmsSiteService.selectMainSite();
				//未指定管理站点  
				if(manageSite==null){
					//提取一级站点
					manageSite=cmsSiteService.selectFirstSite();
				}
			}
		}
		if(manageSite!=null){
			response.sendRedirect(request.getContextPath()+"/site/"+manageSite.getSourcePath()+"/index.html");
			//response.sendRedirect("/site/"+manageSite.getSourcePath()+"/index.html");
			
			//javax.servlet.http.HttpServletResponse servletResponse=response;
			//return new ModelAndView("/site/"+manageSite.getSourcePath()+"/index.html");
		}else{
			response.sendRedirect(request.getContextPath()+"/index.do");
			//return new ModelAndView("/index");
		}
	}
	
	@RequestMapping("top")
	public ModelAndView top(){
		ModelAndView modelAndView=new ModelAndView("/cms/top");
		return modelAndView;
	}
	
	
}
