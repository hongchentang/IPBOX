package com.hcis.ipr.cms.info.web;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.hcis.ipanther.common.dict.utils.DictUtils;
import com.hcis.ipanther.common.login.vo.LoginUser;
import com.hcis.ipr.core.entity.Response;
import com.hcis.ipr.train.course.entity.Course;
import com.hcis.ipr.train.course.service.ICourseService;
import com.hcis.ipr.train.course.utils.CourseConstants;
import com.hcis.ipanther.core.spring.mvc.bind.annotation.FormModel;
import com.hcis.ipanther.core.utils.DateEditor;
import com.hcis.ipanther.core.web.controller.BaseController;
import com.hcis.ipanther.core.web.vo.AjaxReturnObject;
import com.hcis.ipanther.core.web.vo.SearchParam;
import com.hcis.ipr.cms.info.entity.CmsInfo;
import com.hcis.ipr.cms.info.service.ICmsInfoService;
import com.hcis.ipr.cms.info.utils.InfoContants;
import com.hcis.ipr.cms.site.entity.CmsSite;
import com.hcis.ipr.cms.site.service.ICmsSiteService;

@Controller
@RequestMapping("/cms/info")
public class CmsInfoController extends BaseController {

	@Autowired
	private ICmsInfoService cmsInfoService;
	@Autowired
	private ICmsSiteService cmsSiteService;
	@Autowired
	private ICourseService courseService;
	
	@InitBinder
	protected void initBinder(HttpServletRequest request,
	                              ServletRequestDataBinder binder) throws Exception {
	    //对于需要转换为Date类型的属性，使用DateEditor进行处理
		//SimpleDateFormat datetimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		 //binder.registerCustomEditor(Date.class, new DateEditor(datetimeFormat,false));
	     binder.registerCustomEditor(Date.class, new DateEditor());
	}
	
	
	@RequestMapping("fastAddInfo")
	public ModelAndView fastAddInfo(@FormModel("cmsInfo")CmsInfo cmsInfo){
		ModelAndView modelAndView=new ModelAndView();
		if(StringUtils.isEmpty(cmsInfo.getSiteId())){
			CmsSite cmsSite=(CmsSite) getSession().getAttribute("manageSite");
			if(cmsSite!=null){
				cmsInfo.setSiteId(cmsSite.getId());
			}
		}
		modelAndView.addObject("cmsInfo", cmsInfo);
		return modelAndView;
	}
	
	
	@RequestMapping("info")
	public ModelAndView info(SearchParam searchParam){
		ModelAndView modelAndView=new ModelAndView();
		CmsSite cmsSite=null;
		if(searchParam.getParamMap().containsKey("siteId")){
			String siteId=ObjectUtils.toString(searchParam.getParamMap().get("siteId"));
			cmsSite= cmsSiteService.read(siteId);
		}else{
			cmsSite=(CmsSite) getSession().getAttribute("manageSite");
		}
		
		modelAndView.addObject("cmsSite", cmsSite);
		modelAndView.addObject("paramMap", searchParam.getParamMap());
		return modelAndView;
	}
	
	

	@RequestMapping("listInfo")
	public ModelAndView listInfo(SearchParam searchParam){
		ModelAndView modelAndView=new ModelAndView();
		List<CmsInfo> list=cmsInfoService.list(searchParam);
		modelAndView.addObject("resultList", list);
		modelAndView.addObject("paramMap", searchParam.getParamMap());
		return modelAndView;
	}
	
	
	@RequestMapping("addInfo")
	public ModelAndView addInfo(SearchParam searchParam,@FormModel("cmsInfo")CmsInfo cmsInfo){
		ModelAndView modelAndView=new ModelAndView("/cms/info/fastAddInfo");
		modelAndView.addObject("paramMap", searchParam.getParamMap());
		if(cmsInfo!=null&&StringUtils.isNotEmpty(cmsInfo.getId())){
			cmsInfo=cmsInfoService.read(cmsInfo.getId());
			if(StringUtils.isNotEmpty(cmsInfo.getCourseId())){
				Course course=courseService.read(cmsInfo.getCourseId());
				modelAndView.addObject("course", course);
			}
		}
		
		/*if(StringUtils.isEmpty(cmsInfo.getSiteId())){
			CmsSite cmsSite=(CmsSite) getSession().getAttribute("manageSite");
			if(cmsSite!=null){
				cmsInfo.setSiteId(cmsSite.getId());
			}
		}*/
		modelAndView.addObject("cmsInfo", cmsInfo);
		return modelAndView;
	}
	
	//保存信息内容
	@RequestMapping("saveInfo")
	@ResponseBody
	public AjaxReturnObject saveInfo(@ModelAttribute("cmsInfo")CmsInfo cmsInfo ,@RequestParam(value = "upload", required = false) MultipartFile[] upload,HttpServletResponse response){
		int statusCode=200;
		String msg="操作成功！";
		String basePath=getSession().getServletContext().getRealPath("/");
		LoginUser loginUser=LoginUser.loginUser(request);
		String regionsName=DictUtils.getEntryListName(InfoContants.SUB_SITE, loginUser.getRegionsCode());
		if(StringUtils.isNotBlank(regionsName)){
			cmsInfoService.uploadFile(cmsInfo,upload,basePath);
			cmsInfo.setRegionsCode(loginUser.getRegionsCode());
			cmsInfo.setState("00");//未发布
			cmsInfo.setIsTop("00");//未置顶
			cmsInfo.setAddTime(new Date());
			cmsInfoService.create(cmsInfo,loginUser.getId());
		}else{
			statusCode=300;
			msg="你还没有新建消息的权限！";
		}
		return new AjaxReturnObject(statusCode, msg);
		//Response returnResponse=Response.newDefaultResponse(0,"静态化失败");
		//处理文件
		//String meagsse=cmsInfoService.uploadFile(cmsInfo,upload);
		//if(StringUtils.isNotEmpty(meagsse)){
		//	this.renderHtmlUTF8(Response.newDefaultResponse(0,meagsse).toString(), response);
		//}
		//int count=cmsInfoService.create(cmsInfo,LoginUser.loginUser(request)!=null?LoginUser.loginUser(request).getId():null);
		/*returnResponse=cmsInfoService.create(cmsInfo, upload,getSession().getServletContext(),
				request.getContextPath()+"/",request.getRemoteAddr(),
				LoginUser.loginUser(request)!=null?LoginUser.loginUser(request).getId():null);
		return this.renderHtml(returnResponse.toString(), response); */
		
	}
	
	//修改信息内容
	@RequestMapping("editInfo")
	@ResponseBody
	public AjaxReturnObject editInfo(@ModelAttribute("cmsInfo")CmsInfo cmsInfo ,@RequestParam(value = "upload", required = false) MultipartFile[] upload, HttpServletResponse response){
		int statusCode=200;
		String msg="操作成功！";
		Response returnResponse=Response.newDefaultResponse(0,"静态化失败");
		//处理文件
		//String meagsse=cmsInfoService.uploadFile(cmsInfo,upload);
		//if(StringUtils.isNotEmpty(meagsse)){
		//	this.renderHtmlUTF8(Response.newDefaultResponse(0,meagsse).toString(), response);
		//}
		//int count=cmsInfoService.update(cmsInfo,LoginUser.loginUser(request)!=null?LoginUser.loginUser(request).getId():null);
		//return this.renderHtml(Response.newDefaultResponse(count).toString(), response); 
		returnResponse=cmsInfoService.update(cmsInfo, upload,getSession().getServletContext(),
				request.getContextPath()+"/",request.getRemoteAddr(),
				LoginUser.loginUser(request)!=null?LoginUser.loginUser(request).getId():null);
		return new AjaxReturnObject(statusCode, msg);
	}
	
	@RequestMapping("deleteInfo")
	@ResponseBody
	public String deleteInfo(@FormModel("cmsInfo")CmsInfo cmsInfo ,HttpServletResponse response){
		int count=cmsInfoService.delete(cmsInfo,LoginUser.loginUser(request)!=null?LoginUser.loginUser(request).getId():null);
		return this.renderHtml(Response.newDefaultResponse(count).toString(), response); 
	}
	
	//发布
	@RequestMapping("setInfo")
	@ResponseBody
	public AjaxReturnObject setInfo(@FormModel("cmsInfo")CmsInfo cmsInfo ,HttpServletResponse response){
		LoginUser loginUser=LoginUser.loginUser(request);
		int statusCode=200;
		String msg="操作成功！";
		//发操作则需设置发布时间
		if(StringUtils.isNotBlank(cmsInfo.getState())&&cmsInfo.getState().equals("01")){
			cmsInfo.setAddTime(new Date());
		}
		cmsInfoService.update(cmsInfo, loginUser.getId());
		return new AjaxReturnObject(statusCode, msg);
	}
	
	@RequestMapping("changeChannel")
	public ModelAndView changeChannel(@FormModel("cmsInfo")CmsInfo cmsInfo,SearchParam searchParam){
		ModelAndView modelAndView=new ModelAndView();
		modelAndView.addObject("paramMap", searchParam.getParamMap());
		if(cmsInfo!=null&&StringUtils.isNotEmpty(cmsInfo.getId())){
			cmsInfo=cmsInfoService.read(cmsInfo.getId());
		}
		modelAndView.addObject("cmsInfo", cmsInfo);
		return modelAndView;
	}
	
	/**
	 * Update channel id. 更新所属 栏目
	 *
	 * @param cmsInfo the cms info
	 * @param response the response
	 * @return the string
	 */
	@RequestMapping("updateChannelId")
	@ResponseBody
	public String updateChannelId(@FormModel("cmsInfo")CmsInfo cmsInfo ,HttpServletResponse response){
		int count=cmsInfoService.update(cmsInfo,LoginUser.loginUser(request)!=null?LoginUser.loginUser(request).getId():null);
		return this.renderHtml(Response.newDefaultResponse(count).toString(), response); 
	}
	
	
	
	@RequestMapping("makeHtml")
	@ResponseBody
	public String makeHtml(@FormModel("cmsInfo")CmsInfo cmsInfo ,HttpServletResponse response){
		Response returnResponse=cmsInfoService.html(cmsInfo,getSession().getServletContext(),getSession().getServletContext().getRealPath("/"),request.getRemoteAddr(),LoginUser.loginUser(request)!=null?LoginUser.loginUser(request).getId():null);
		return this.renderHtml(returnResponse.toString(), response);
	}
	
	@RequestMapping("infoAjaxClick")
	@ResponseBody
	public String infoAjaxClick(@FormModel("cmsInfo")CmsInfo cmsInfo ,HttpServletResponse response){
		String count=cmsInfoService.updateClick(cmsInfo,LoginUser.loginUser(request)!=null?LoginUser.loginUser(request).getId():null);
		return this.renderHtml(count, response); 
	}
	
	@RequestMapping("recycleInfo")
	public ModelAndView recycleInfo(SearchParam searchParam){
		ModelAndView modelAndView=new ModelAndView();
		CmsSite cmsSite=null;
		if(searchParam.getParamMap().containsKey("siteId")){
			String siteId=ObjectUtils.toString(searchParam.getParamMap().get("siteId"));
			cmsSite= cmsSiteService.read(siteId);
		}else{
			cmsSite=(CmsSite) getSession().getAttribute("manageSite");
		}
		
		modelAndView.addObject("cmsSite", cmsSite);
		modelAndView.addObject("paramMap", searchParam.getParamMap());
		return modelAndView;
	}
	
	@RequestMapping("listRecycleBin")
	public ModelAndView listRecycleBin(SearchParam searchParam){
		ModelAndView modelAndView=new ModelAndView();
		searchParam.getParamMap().put("isRecycle", "01");
		List<CmsInfo> list=cmsInfoService.list(searchParam);
		modelAndView.addObject("resultList", list);
		modelAndView.addObject("paramMap", searchParam.getParamMap());
		return modelAndView;
	}
	
	@RequestMapping("recycleBinInfo")
	@ResponseBody
	public String recycleBinInfo(@FormModel("cmsInfo")CmsInfo cmsInfo ,HttpServletResponse response){
		int count=cmsInfoService.update(cmsInfo,LoginUser.loginUser(request)!=null?LoginUser.loginUser(request).getId():null);
		return this.renderHtml(Response.newDefaultResponse(count).toString(), response); 
	}
	
	@RequestMapping("restoreInfo")
	@ResponseBody
	public String restoreInfo(@FormModel("cmsInfo")CmsInfo cmsInfo ,HttpServletResponse response){
		int count=cmsInfoService.update(cmsInfo,LoginUser.loginUser(request)!=null?LoginUser.loginUser(request).getId():null);
		return this.renderHtml(Response.newDefaultResponse(count).toString(), response); 
	}
	
	
	@RequestMapping("viweInfo")
	public void viweInfo(@FormModel("cmsInfo")CmsInfo cmsInfo ,HttpServletResponse response){
		cmsInfo=cmsInfoService.read(cmsInfo.getId());
		try{
			CmsSite cmsSite =cmsSiteService.read(cmsInfo.getSiteId());
			SimpleDateFormat yearFormat=new SimpleDateFormat("yyyy");
			SimpleDateFormat mmFormat=new SimpleDateFormat("MM");
			
			cmsInfoService.html(cmsInfo, request.getSession().getServletContext(), request.getContextPath()+"/", request.getRemoteAddr(), LoginUser.loginUser(request)!=null?LoginUser.loginUser(request).getId():null);
			response.sendRedirect(request.getSession().getServletContext().getContextPath()+"/site/"+cmsSite.getSourcePath()+"/"+cmsInfo.getChannelFolder()+"/info/"+ yearFormat.format(cmsInfo.getAddTime())+"/"+mmFormat.format(cmsInfo.getAddTime())+"/"+cmsInfo.getHtmlFileName()+".html");
		}catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
	}
	/**
	 * 后台门户管理里新增培训里的课程选择
	 * @param searchParam
	 * @return
	 */
	@RequestMapping("/chooseCourse")
	public ModelAndView chooseCourse(SearchParam searchParam){
		ModelAndView modelAndView=new ModelAndView();
		//已审核通过的
		searchParam.getParamMap().put("status", CourseConstants.COURSE_STATUS_02);
		List<Course>listCourses=courseService.list(searchParam);
		//modelAndView.setViewName("");
		modelAndView.addObject("listCourses", listCourses);
		return modelAndView;
		
	}
	
}
