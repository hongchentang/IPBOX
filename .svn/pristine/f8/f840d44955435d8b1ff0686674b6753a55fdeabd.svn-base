package com.hcis.ipr.cms.site.web;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.hcis.ipanther.common.login.vo.LoginUser;
import com.hcis.ipanther.common.user.utils.UserConstants;
import com.hcis.ipr.core.entity.Response;
import com.hcis.ipanther.core.spring.mvc.bind.annotation.FormModel;
import com.hcis.ipanther.core.utils.JsonUtil;
import com.hcis.ipanther.core.web.controller.BaseController;
import com.hcis.ipanther.core.web.vo.SearchParam;
import com.hcis.ipr.cms.common.utils.CmsCommonFileUtil;
import com.hcis.ipr.cms.site.entity.CmsSite;
import com.hcis.ipr.cms.site.service.ICmsSiteService;


@RequestMapping("/cms/site")
@Controller
public class CmsSiteController extends BaseController{

	@Autowired
	private ICmsSiteService cmsSiteService;
	
	
	@RequestMapping("siteConfig")
	public ModelAndView siteConfig(){
		ModelAndView modelAndView=new ModelAndView();
		//cmsSiteService.get
		return modelAndView;
	}
	
	
	@RequestMapping("saveSite")
	@ResponseBody
	public String siteSave(@FormModel("cmsSite")CmsSite cmsSite, HttpServletResponse response){
		 if(cmsSite!=null&&StringUtils.isNotEmpty(cmsSite.getSourcePath())){
			 if (cmsSiteService.haveSourcePath( cmsSite.getSourcePath())) {
				 return this.renderText(Response.newDefaultResponse(0,"站点源文件目录已存在").toString() ,response);
			 }
			 cmsSite.setIndexTemplet(cmsSite.getSourcePath());
		 }
		//创建源文件目录
		 CmsCommonFileUtil.mkdir(request.getRealPath("/")+"site/"+cmsSite.getSourcePath());
		 int count=cmsSiteService.create(cmsSite, LoginUser.loginUser(request).getId());
		 if(count>0){
			 //静态化当前站点首页
			// cmsSiteService.html(cmsSite, getSession().getServletContext(), request.getContextPath(), request, LoginUser.loginUser(request).getId());
		 }
	     return this.renderText(Response.newDefaultResponse(count).toString() ,response);
	}
	
	@RequestMapping("editSite")
	@ResponseBody
	public String editSite(@FormModel("cmsSite")CmsSite cmsSite, HttpServletResponse response){
		 if(cmsSite!=null&&StringUtils.isNotEmpty(cmsSite.getSourcePath())){
			 cmsSite.setIndexTemplet(cmsSite.getSourcePath());
		 }
		 int count=cmsSiteService.update(cmsSite, LoginUser.loginUser(request).getId());
		 if(count>0){
			 //静态化当前站点首页
			// cmsSiteService.html(cmsSite, getSession().getServletContext(), request.getContextPath(), request, LoginUser.loginUser(request).getId());
		 }
	     return this.renderText(Response.newDefaultResponse(count).toString() ,response);
	}
	
	@RequestMapping("addSite")
	public ModelAndView addSite(@FormModel("cmsSite")CmsSite cmsSite){
		ModelAndView modelAndView=new ModelAndView();
		if(cmsSite!=null&&StringUtils.isNotEmpty(cmsSite.getId())){
			cmsSite=cmsSiteService.read(cmsSite.getId());
			modelAndView.addObject("cmsSite", cmsSite);
		}
		return modelAndView;
	}
	
	
	@RequestMapping("deleteSite")
	@ResponseBody
	public String deleteSite(@FormModel("cmsSite")CmsSite cmsSite, HttpServletResponse response){
		 int count=cmsSiteService.delete(cmsSite, LoginUser.loginUser(request).getId());
	     return this.renderText(Response.newDefaultResponse(count).toString() ,response);
	}
	
	@RequestMapping("listSite")
	public ModelAndView listSite(SearchParam searchParam){
		ModelAndView modelAndView=new ModelAndView();
		List<CmsSite> list= cmsSiteService.list(searchParam);
		modelAndView.addObject("resultList", list);
		modelAndView.addObject("paramMap", searchParam.getParamMap());
		return modelAndView;
	}
	
	
	@RequestMapping("selectSite")
	public ModelAndView selectSite(SearchParam searchParam){
		ModelAndView modelAndView=new ModelAndView();
		List<CmsSite> list= cmsSiteService.list(searchParam);
		modelAndView.addObject("resultList", list);
		modelAndView.addObject("paramMap", searchParam.getParamMap());
		return modelAndView;
	}
	
//	@RequestMapping("setMainSite")
//	@ResponseBody
//	public String  setMainSite(@FormModel("cmsSite")CmsSite cmsSite,HttpServletResponse response){
//		cmsSite=cmsSiteService.read(cmsSite.getId());
//		getSession().setAttribute("manageSite", cmsSite);
//		return this.renderHtmlUTF8(Response.successInstance().toString(), response);
//	}
	
	
	@RequestMapping("site")
	public ModelAndView site(SearchParam searchParam){
		ModelAndView modelAndView=new ModelAndView();
		modelAndView.addObject("paramMap", searchParam.getParamMap());
		return modelAndView;
	}
	
	@RequestMapping("getSiteTree")
	@ResponseBody
	public String getSiteTree(SearchParam searchParam, HttpServletResponse response){
		searchParam.setPageAvailable(false);
		LoginUser loginUser = LoginUser.loginUser(request);
		if(loginUser!=null&&!ObjectUtils.equals(UserConstants.USER_ROLECODE_SUPER_ADMIN,loginUser.getRoleCode())){
			if(loginUser.getDeptLevel().intValue()==2){
				searchParam.getParamMap().put("city", loginUser.getRegionsCode());
			}else if(loginUser.getDeptLevel().intValue()==3){
				searchParam.getParamMap().put("counties", loginUser.getRegionsCode());
			}
		}
		List<CmsSite> list=cmsSiteService.list(searchParam);
		String json="{}";
		try {
			json=JsonUtil.toJson(setTreeMap(list));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return this.renderHtmlUTF8(json, response);
	}
	
	@RequestMapping("siteAdmin")
	public ModelAndView siteAdmin(SearchParam searchParam,@FormModel("cmsSite")CmsSite cmsSite){
		ModelAndView modelAndView=new ModelAndView();
		if(cmsSite!=null&&StringUtils.isNotEmpty(cmsSite.getId())){
			cmsSite=cmsSiteService.read(cmsSite.getId());
			modelAndView.addObject("cmsSite", cmsSite);
		}
		modelAndView.addObject("paramMap", searchParam.getParamMap());
		return modelAndView;
	}
	
	@RequestMapping("changeSite")
	public ModelAndView changeSite(SearchParam searchParam){
		ModelAndView modelAndView=new ModelAndView();
		modelAndView.addObject("paramMap", searchParam.getParamMap());
		return modelAndView;
	}
	
	
	@RequestMapping("updateParentId")
	@ResponseBody
	public String updateParentId(@FormModel("cmsSite")CmsSite cmsSite, HttpServletResponse response){
		 int count=cmsSiteService.update(cmsSite, LoginUser.loginUser(request)!=null?LoginUser.loginUser(request).getId():null);
	     return this.renderText(Response.newDefaultResponse(count).toString() ,response);
	}


	private List<Map<String,Object>> setTreeMap(List<CmsSite> list) {
		List<Map<String,Object>> maps=new ArrayList<Map<String,Object>>();
		if(list!=null&&(!list.isEmpty())){
			for (CmsSite cmsSite : list) {
				Map<String,Object> map=new HashMap<String, Object>();
				map.put("id", cmsSite.getId());
				map.put("text", cmsSite.getName());
				map.put("parentField", cmsSite.getParentId());
				map.put("name", cmsSite.getName());
				map.put("parentId", cmsSite.getParentId());
				maps.add(map);
			}
		}
		return maps;
	}
	
	@RequestMapping("setMainSite")
	@ResponseBody
	public String setMainSite(@FormModel("cmsSite")CmsSite cmsSite, HttpServletResponse response){
		//删除 旧数据
		int delCount= cmsSiteService.deleteMainSite(cmsSite, LoginUser.loginUser(request)!=null?LoginUser.loginUser(request).getId():null);
		logger.info("deleteMainSite count is :"+ delCount);
		int count=cmsSiteService.update(cmsSite, LoginUser.loginUser(request)!=null?LoginUser.loginUser(request).getId():null);
	     if(count>0){
	    	 cmsSite=cmsSiteService.read(cmsSite.getId());
	 		 getSession().setAttribute("manageSite", cmsSite);
	     }
		 return this.renderText(Response.newDefaultResponse(count).toString() ,response);
	}
	
	
	@RequestMapping("getSiteJson")
	@ResponseBody
	public String setMainSite(SearchParam searchParam, HttpServletResponse response){
		searchParam.setPageAvailable(false);
		List<CmsSite> list=cmsSiteService.list(searchParam);
		String json="{}";
		try {
			json=JsonUtil.toJson(setMapJson(list));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return this.renderHtmlUTF8(json, response);
	}
	
	
	private List<Map<String,Object>> setMapJson(List<CmsSite> list) {
		List<Map<String,Object>> maps=new ArrayList<Map<String,Object>>();
		if(list!=null&&(!list.isEmpty())){
			for (CmsSite cmsSite : list) {
				Map<String,Object> map=new HashMap<String, Object>();
				map.put("id", cmsSite.getId());
			    Map<String, Object> temp=new HashMap<String, Object>();
			    temp.put("sourcePath", cmsSite.getSourcePath());
				map.put("attributes",temp);
				map.put("text", cmsSite.getName());
				map.put("parentField", cmsSite.getParentId());
				//map.put("name", cmsSite.getName());
				//map.put("parentId", cmsSite.getParentId());
				maps.add(map);
			}
		}
		return maps;
	}
	
	@RequestMapping("viweSite")
	public void viweSite(@FormModel("cmsSite")CmsSite cmsSite,HttpServletResponse response){
		CmsSite site =cmsSiteService.read(cmsSite.getId());
		try{
			cmsSiteService.html(site, request.getSession().getServletContext(), request.getContextPath()+"/", request.getRemoteAddr(), LoginUser.loginUser(request)!=null?LoginUser.loginUser(request).getId():null);
			response.sendRedirect(request.getContextPath()+"/site/"+site.getSourcePath()+"/index.html");
		}catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		
	}
	
}
