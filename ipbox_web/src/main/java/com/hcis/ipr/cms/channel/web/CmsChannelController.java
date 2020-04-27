package com.hcis.ipr.cms.channel.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.hcis.ipanther.common.login.vo.LoginUser;
import com.hcis.ipanther.core.spring.mvc.bind.annotation.FormModel;
import com.hcis.ipanther.core.utils.JsonUtil;
import com.hcis.ipanther.core.web.controller.BaseController;
import com.hcis.ipanther.core.web.vo.SearchParam;
import com.hcis.ipr.cms.channel.entity.CmsChannel;
import com.hcis.ipr.cms.channel.service.ICmsChannelService;
import com.hcis.ipr.cms.site.entity.CmsSite;
import com.hcis.ipr.cms.site.service.ICmsSiteService;
import com.hcis.ipr.core.entity.Response;

import freemarker.template.TemplateException;

@Controller
@RequestMapping("/cms/channel")
public class CmsChannelController extends BaseController{

	@Autowired
	private ICmsChannelService cmsChannelService;
	@Autowired
	private ICmsSiteService cmsSiteService;
	
	
	@RequestMapping("channel")
	public ModelAndView channel(SearchParam searchParam){
		ModelAndView modelAndView=new ModelAndView();
		CmsSite cmsSite=null;
//		List<CmsChannel> list=cmsChannelService.list(searchParam);
//		modelAndView.addObject("resultList", list);
		if(searchParam.getParamMap().containsKey("siteId")){
			String siteId=ObjectUtils.toString(searchParam.getParamMap().get("siteId"));
			cmsSite=cmsSiteService.read(siteId);
		}else{
			cmsSite=(CmsSite) getSession().getAttribute("manageSite");
		}
		
		if(cmsSite!=null){
			searchParam.getParamMap().put("siteId", cmsSite.getId());
			modelAndView.addObject("cmsSite", cmsSite);
		}
		modelAndView.addObject("paramMap", searchParam.getParamMap());
		return modelAndView;
	}
	
	
	@RequestMapping("getChannelTree")
	@ResponseBody
	public String getChannelTree(SearchParam searchParam, HttpServletResponse response){
		searchParam.setPageAvailable(false);//不分页
		List<CmsChannel> list=cmsChannelService.list(searchParam);
		String json="{}";
		try {
			json=JsonUtil.toJson(setTreeMap(list));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return this.renderHtmlUTF8(json, response);
	}
	
	
	@RequestMapping("addChannel")
	public ModelAndView addChannel(@FormModel("cmsChannel")CmsChannel cmsChannel){
		ModelAndView modelAndView=new ModelAndView();
		if(cmsChannel!=null){
			if(StringUtils.isNotEmpty(cmsChannel.getId())){
				cmsChannel=cmsChannelService.read(cmsChannel.getId());
			}
			if(StringUtils.isNotEmpty(cmsChannel.getSiteName())){
				CmsSite cmsSite= cmsSiteService.read(cmsChannel.getSiteId());
				cmsChannel.setSiteName(cmsSite!=null?cmsSite.getName():null);
			}
		}
		modelAndView.addObject("cmsChannel", cmsChannel);
		return modelAndView;
	}
	
	@RequestMapping("saveChannel")
	@ResponseBody
	public String saveChannel(@ModelAttribute("cmsChannel")CmsChannel cmsChannel,@RequestParam(value = "imgUpload", required = false) MultipartFile[] imgUpload, HttpServletResponse response){
//		//判断页面标识是否存在
//		if(cmsChannel!=null&&StringUtils.isNotEmpty(cmsChannel.getPageMark())&&cmsChannelService.hasPagemark(cmsChannel.getSiteId(), cmsChannel.getPageMark())){
//			return this.renderHtmlUTF8(Response.newDefaultResponse(0,"当前页面标识已存在").toString(), response);
//		}
//		//处理文件
//		String meagsse=cmsChannelService.uploadFile(cmsChannel,imgUpload);
//		if(StringUtils.isNotEmpty(meagsse)){
//			this.renderHtmlUTF8(Response.newDefaultResponse(0,meagsse).toString(), response);
//		}
//		int updateRecCount=cmsChannelService.create(cmsChannel, LoginUser.loginUser(request)!=null? LoginUser.loginUser(request).getId():null);
//		if(updateRecCount>0){
//			
//		}
		//设置默认站点
		CmsSite cmsSite=null;
		if(cmsChannel!=null&&StringUtils.isEmpty(cmsChannel.getSiteId())){
			cmsSite=(CmsSite) getSession().getAttribute("manageSite");
			if(cmsSite!=null){
				cmsChannel.setSiteId(cmsSite.getId());
			}
		}
		Response returnResponse=cmsChannelService.create(cmsChannel,imgUpload,LoginUser.loginUser(request)!=null? LoginUser.loginUser(request).getId():null,getSession().getServletContext().getRealPath("/"));
		return this.renderHtmlUTF8(returnResponse.toString() , response);
	}
	
	
	@RequestMapping("editChannel")
	@ResponseBody
	public String editChannel(@ModelAttribute("cmsChannel")CmsChannel cmsChannel,@RequestParam(value = "imgUpload", required = false) MultipartFile[] imgUpload, HttpServletResponse response){
//		//处理文件
//		String meagsse=cmsChannelService.uploadFile(cmsChannel,imgUpload);
//		if(StringUtils.isNotEmpty(meagsse)){
//			this.renderHtmlUTF8(Response.newDefaultResponse(0,meagsse).toString(), response);
//		}
//		int updateRecCount=cmsChannelService.update(cmsChannel, LoginUser.loginUser(request)!=null? LoginUser.loginUser(request).getId():null);
//		if(updateRecCount>0){
//			
//		}
		CmsSite cmsSite=null;
		if(cmsChannel!=null&&StringUtils.isNotEmpty(cmsChannel.getSiteId())){
			cmsSite=cmsSiteService.read(cmsChannel.getSiteId());
		}else{
			cmsSite=(CmsSite) getSession().getAttribute("manageSite");
			if(cmsSite!=null){
				//设置默认站点
				cmsChannel.setSiteId(cmsSite.getId());
			}
		}
		Response returnResponse=cmsChannelService.update(cmsChannel,imgUpload,LoginUser.loginUser(request)!=null? LoginUser.loginUser(request).getId():null,getSession().getServletContext().getRealPath("/"),cmsSite,getSession().getServletContext().getRealPath("/"));
		return this.renderHtmlUTF8(returnResponse.toString(), response);
	}
	
	
	@RequestMapping("deleteChannel")
	@ResponseBody
	public String deleteChannel(@FormModel("cmsChannel")CmsChannel cmsChannel, HttpServletResponse response){
		int updateRecCount=cmsChannelService.delete(cmsChannel, LoginUser.loginUser(request)!=null? LoginUser.loginUser(request).getId():null);
		return this.renderHtmlUTF8(Response.newDefaultResponse(updateRecCount).toString(), response);
	}
	
	@RequestMapping("changeChannel")
	public ModelAndView changeChannel(SearchParam searchParam){
		ModelAndView modelAndView=new ModelAndView();
		modelAndView.addObject("paramMap", searchParam.getParamMap());
		return modelAndView;
	}
	
	/**
	 * Udapte parent id. 更新 父栏目
	 *
	 * @param cmsChannel the cms channel
	 * @param response the response
	 * @return the string
	 */
	@RequestMapping("udapteParentId")
	@ResponseBody
	public String udapteParentId(@FormModel("cmsChannel")CmsChannel cmsChannel, HttpServletResponse response){
		int updateRecCount=cmsChannelService.update(cmsChannel, LoginUser.loginUser(request)!=null? LoginUser.loginUser(request).getId():null);
		return this.renderHtmlUTF8(Response.newDefaultResponse(updateRecCount).toString(), response);
	}
	
	
	@RequestMapping("getChannel")
	public ModelAndView getChannel(SearchParam searchParam){
		ModelAndView modelAndView=new ModelAndView();
		modelAndView.addObject("paramMap", searchParam.getParamMap());
		return modelAndView;
	}
	
	
	/**
	 * Sets the tree map.为防止 undefined 重新组装  return list
	 *
	 * @param list the list
	 * @return the list
	 */
	public static List<Map<String,Object>> setTreeMap(List<CmsChannel> list){
		List<Map<String,Object>> maps=new ArrayList<Map<String,Object>>();
		if(list!=null&&(!list.isEmpty())){
			for (CmsChannel cmsChannel : list) {
				Map<String,Object> map=new HashMap<String, Object>();
				map.put("id", cmsChannel.getId());
				map.put("text", cmsChannel.getName());
				map.put("parentField", cmsChannel.getParentId());
				map.put("name", cmsChannel.getName());
				map.put("parentId", cmsChannel.getParentId());
				maps.add(map);
			}
		}
		return maps;
	}
	
	@RequestMapping("makehtml")
	@ResponseBody
	public String makehtml(@FormModel("cmsChannel")CmsChannel cmsChannel, HttpServletResponse response){
		//int updateRecCount=cmsChannelService.update(cmsChannel, LoginUser.loginUser(request)!=null? LoginUser.loginUser(request).getId():null);
		Response returnResponse=Response.failInstance();
		try {
			returnResponse = cmsChannelService.html( cmsChannel.getSiteId(),cmsChannel.getId(), getSession().getServletContext(),request.getContextPath()+"/",LoginUser.loginUser(request)!=null? LoginUser.loginUser(request).getId():null,request.getRemoteAddr());
		} catch (IOException | TemplateException e) {
			e.printStackTrace();
		}
		return this.renderHtmlUTF8(returnResponse.toString(), response);
	}
	
	
}
