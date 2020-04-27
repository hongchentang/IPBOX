package com.hcis.ipr.cms.html.web;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.hcis.ipanther.common.login.vo.LoginUser;
import com.hcis.ipr.core.entity.Response;
import com.hcis.ipanther.core.web.controller.BaseController;
import com.hcis.ipanther.core.web.vo.SearchParam;
import com.hcis.ipr.cms.channel.service.ICmsChannelService;
import com.hcis.ipr.cms.info.service.ICmsInfoService;
import com.hcis.ipr.cms.site.entity.CmsSite;
import com.hcis.ipr.cms.site.service.ICmsSiteService;



@Controller
@RequestMapping("/cms/html")
public class HtmlController extends BaseController{

	@Autowired
	private ICmsSiteService cmsSiteService;
	@Autowired
	private ICmsInfoService cmsInfoService;
	@Autowired
	private ICmsChannelService cmsChannelService;
	

	@RequestMapping("html")
	public ModelAndView html(){
		ModelAndView modelAndView=new ModelAndView();
		return modelAndView;
	}
	
	
	@RequestMapping("staticizeIndex")
	public ModelAndView staticizeIndex(SearchParam searchParam){
		ModelAndView modelAndView=new ModelAndView("/cms/html/index");
		CmsSite cmsSite=(CmsSite) request.getSession().getAttribute("manageSite");
		modelAndView.addObject("cmsSite", cmsSite);
		if(cmsSite!=null){
			searchParam.getParamMap().put("siteId", cmsSite.getId());
		}
		return modelAndView;
	}
	
	@RequestMapping("staticizeChannel")
	public ModelAndView staticizeChannel(SearchParam searchParam){
		ModelAndView modelAndView=new ModelAndView("/cms/html/channel");
		CmsSite cmsSite=(CmsSite) request.getSession().getAttribute("manageSite");
		if(cmsSite!=null){
			searchParam.getParamMap().put("siteId", cmsSite.getId());
		}
		return modelAndView;
	}
	
	
	@RequestMapping("staticizeInfo")
	public ModelAndView staticizeInfo(SearchParam searchParam){
		ModelAndView modelAndView=new ModelAndView("/cms/html/info");
		CmsSite cmsSite=(CmsSite) request.getSession().getAttribute("manageSite");
		if(cmsSite!=null){
			searchParam.getParamMap().put("siteId", cmsSite.getId());
		}
		return modelAndView;
	}
	
	
	
	
	@RequestMapping("htmlIndex")
	public String htmlIndex(SearchParam searchParam,HttpServletResponse response){
		CmsSite cmsSite=null;
		Response defaultResponse=Response.newDefaultResponse(0,"静态化失败");
		if(searchParam.getParamMap().containsKey("siteId")){
			String siteId=ObjectUtils.toString(searchParam.getParamMap().get("siteId"));
			cmsSite=cmsSiteService.read(siteId);
		}else{
			cmsSite=(CmsSite) request.getSession().getAttribute("manageSite");
		}
		
		if(cmsSite!=null){
			//defaultResponse=cmsSiteService.staticizeIndex(cmsSite);
			//生成首页
			defaultResponse=cmsSiteService.html(cmsSite,getSession().getServletContext(), request.getContextPath()+"/", request.getRemoteAddr(), LoginUser.loginUser(request)!=null?LoginUser.loginUser(request).getRealName():null);
		}
		return this.renderHtmlUTF8(defaultResponse.toString(), response);
	}
	
	
	@RequestMapping("htmlChannel")
	public String htmlChannel(SearchParam searchParam,HttpServletResponse response){
		CmsSite cmsSite=null;
		Response defaultResponse=Response.newDefaultResponse(0,"静态化失败");
		if(searchParam.getParamMap().containsKey("siteId")){
			String siteId=ObjectUtils.toString(searchParam.getParamMap().get("siteId"));
			cmsSite=cmsSiteService.read(siteId);
		}else{
			cmsSite=(CmsSite) request.getSession().getAttribute("manageSite");
		}
			
		if(cmsSite!=null){
			defaultResponse=cmsChannelService.htmlChannels(cmsSite,searchParam,getSession().getServletContext(), request.getContextPath()+"/", request.getRemoteAddr(), LoginUser.loginUser(request)!=null?LoginUser.loginUser(request).getRealName():null);
		}
		return this.renderHtmlUTF8(defaultResponse.toString(), response);
	}
	
	@RequestMapping("htmlInfo")
	public String htmlInfo(SearchParam searchParam,HttpServletResponse response){
		CmsSite cmsSite=null;
		Response defaultResponse=Response.newDefaultResponse(0,"静态化失败");
		if(searchParam.getParamMap().containsKey("siteId")){
			String siteId=ObjectUtils.toString(searchParam.getParamMap().get("siteId"));
			cmsSite=cmsSiteService.read(siteId);
		}else{
			cmsSite=(CmsSite) request.getSession().getAttribute("manageSite");
		}
			
		if(cmsSite!=null){
			defaultResponse=cmsInfoService.htmlInfo(cmsSite,searchParam,getSession().getServletContext(), request.getContextPath()+"/", request.getRemoteAddr(), LoginUser.loginUser(request)!=null?LoginUser.loginUser(request).getRealName():null);
		}
		return this.renderHtmlUTF8(defaultResponse.toString(), response);
	}
	
}
