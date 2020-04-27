package com.hcis.ipr.train.course.web;

import java.util.List;
import java.util.Map;

import org.activiti.engine.ActivitiObjectNotFoundException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.support.DefaultMultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.hcis.ipanther.common.login.vo.LoginUser;
import com.hcis.ipanther.common.security.utils.RoleConstant;
import com.hcis.ipanther.common.security.utils.SecurityRoleUtils;
import com.hcis.ipanther.core.utils.JsonUtil;
import com.hcis.ipanther.core.web.controller.BaseController;
import com.hcis.ipanther.core.web.vo.AjaxReturnObject;
import com.hcis.ipanther.core.web.vo.SearchParam;
import com.hcis.ipr.train.course.entity.Course;
import com.hcis.ipr.train.course.service.ICourseService;
import com.hcis.ipr.train.train.service.ITrainCourseService;

@RequestMapping("/train/course")
@Controller
public class CourseController extends BaseController{

	@Autowired
	private ICourseService courseService;
	@Autowired
	private ITrainCourseService trainCourseService;
	
	//课程列表
	@RequestMapping("tabList")
	public ModelAndView tabList(SearchParam searchParam){
		ModelAndView modelAndView=new ModelAndView();
		courseService.listTodo(searchParam,request);
		return modelAndView;
	}
	
	//所有课程
	@RequestMapping("listAll")
	public ModelAndView listAll(SearchParam searchParam){
		ModelAndView modelAndView=new ModelAndView();		
		modelAndView.addObject("listCourse", courseService.listAll(searchParam,request));
		return modelAndView;
	}
	
	//编辑中的课程
	@RequestMapping("listEdit")
	public ModelAndView listEdit(SearchParam searchParam){
		ModelAndView modelAndView=new ModelAndView();		
		searchParam.getParamMap().put("creator", LoginUser.loginUser(request).getId());
		modelAndView.addObject("listCourse", courseService.list(searchParam));
		return modelAndView;
	}
	
	//待办任务
	@RequestMapping("listTodo")
	public ModelAndView listTodo(SearchParam searchParam){
		ModelAndView modelAndView=new ModelAndView();
		modelAndView.addObject("listCourse", courseService.listTodo(searchParam,request));
		return modelAndView;
	}
	
	//已办任务
	@RequestMapping("listDone")
	public ModelAndView listDone(SearchParam searchParam){
		ModelAndView modelAndView=new ModelAndView();
		modelAndView.addObject("listCourse", courseService.listDone(searchParam, request));
		return modelAndView;
	}
	
	//已通过
	@RequestMapping("listPass")
	public ModelAndView listPass(SearchParam searchParam){
		ModelAndView modelAndView=new ModelAndView();
		searchParam.getParamMap().put("status", "02");
		modelAndView.addObject("listCourse", courseService.listDone(searchParam, request));
		return modelAndView;
	}
	
	//不通过
	@RequestMapping("listUnPass")
	public ModelAndView listUnPass(SearchParam searchParam){
		ModelAndView modelAndView=new ModelAndView();
		searchParam.getParamMap().put("status", "04");
		modelAndView.addObject("listCourse", courseService.listDone(searchParam, request));
		return modelAndView;
	}
	
	//可以选分配教师的课程列表
	@RequestMapping("listCourse")
	public ModelAndView listCourse(SearchParam searchParam){
		ModelAndView modelAndView=new ModelAndView();
		modelAndView.setViewName("/train/course/listTrainCoure");
		//若为知识产权局则显示所有可以分配教师的课程
		if(!SecurityRoleUtils.hasRole(RoleConstant.GD_IPR_LEADER, LoginUser.loginUser(request).getId())){
			searchParam.getParamMap().put("declareDept", LoginUser.loginUser(request).getFirstDeptId());
		}
		modelAndView.addObject("listCourse", courseService.listTrainCoure(searchParam));
		return modelAndView;
	}
	
	
	//编辑课程页面
	@RequestMapping("goAddCourse")
	public ModelAndView goAddCourse(Course course,SearchParam searchParam){
		ModelAndView modelAndView=new ModelAndView();
		modelAndView.setViewName("/train/course/addCourse");
		if(course!=null&&StringUtils.isNotBlank(course.getId())){
			modelAndView.addObject("course", courseService.read(course.getId()));
		}
		return modelAndView;
	}
	
	//保存课程
	@RequestMapping("saveCourse")
	@ResponseBody
	public AjaxReturnObject saveCourse(@ModelAttribute("course")Course course,DefaultMultipartHttpServletRequest request){
		int statusCode=200;
		String msg="操作成功！";
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;  
		MultipartFile img = multipartRequest.getFile("uploadImg");
		MultipartFile file = multipartRequest.getFile("uploadFile");
		MultipartFile avatar = multipartRequest.getFile("uploadAvatar");
		//课程图片
		if(img!=null&&!img.isEmpty()){
			String imgInfo=courseService.uploadImg(course, img);
			if(StringUtils.isNotBlank(imgInfo)){
				return new AjaxReturnObject(300, imgInfo);
			}
		}
		//主讲教师头像
		if(avatar!=null&&!avatar.isEmpty()){
			String avatarInfo=courseService.uploadAvatar(course, avatar);
			if(StringUtils.isNotBlank(avatarInfo)){
				return new AjaxReturnObject(300, avatarInfo);
			}
		}
		//课程附件
		if(file!=null&&!file.isEmpty()){
			String fileInfo=courseService.uploadFile(course, file);
			if(StringUtils.isNotBlank(fileInfo)){
				return new AjaxReturnObject(300, fileInfo);
			}
		}
		//获取人才和行业
		String personType=course.getPersonType();
		String industryType=course.getIndustryType();
		if(StringUtils.isNotEmpty(personType)){
			try{
				course.setPersonType(JsonUtil.toJson(personType.split(",")));
				course.setIndustryType(JsonUtil.toJson(industryType.split(",")));
			}
			catch(Exception e){
				e.printStackTrace();
			}
		}
		if(StringUtils.isNotBlank( course.getId())){
			courseService.update(course, LoginUser.loginUser(request).getId());
		}else{
//			course.setStatus("00");
			course.setStatus("02");
			int createCount=courseService.create(course, LoginUser.loginUser(request).getId());
		}
		return new AjaxReturnObject(statusCode, msg);
	}
	
	//删除课程
	@RequestMapping("deleteCourse")
	@ResponseBody
	public AjaxReturnObject deleteCourse(@ModelAttribute("course")Course course){
		SearchParam se=new SearchParam();
		se.getParamMap().put("courseId",course.getId());
		List<Map<String,Object>>list=trainCourseService.selectCourseByTrain(se);
		int statusCode=200;
		String msg="操作成功！";		
		//删除课程
		if(list.size()!=0){
		    statusCode=300;
			msg="该课程已添加到培训班，不能删除!";
			return new AjaxReturnObject(statusCode, msg);
		}else{
			//删除流程
			if(null!=course.getProcInstId()&&StringUtils.isNotBlank(course.getProcInstId())){
				//抓获结束流程
				try{
					courseService.deleteWorkFlow(course.getProcInstId());
				}catch(ActivitiObjectNotFoundException e){}
			}
			courseService.delete(course, LoginUser.loginUser(request).getId());
			return new AjaxReturnObject(statusCode, msg);
		}
		
	}
	
	//申报课程
	@RequestMapping("declareCourse")
	public @ResponseBody AjaxReturnObject declareCourse(Course course){
		int statusCode=200;
		String msg="操作成功！";
		course=courseService.read(course.getId());
		courseService.declareCourse(course, request);
		return new AjaxReturnObject(statusCode, msg);
	}
	
	//审核课程页面
	@RequestMapping("goAudit")
	public ModelAndView goAudit(Course course){
		ModelAndView modelAndView=new ModelAndView();
		modelAndView.setViewName("/train/course/audit");
		if(course!=null&&StringUtils.isNotBlank(course.getId())){
			modelAndView.addObject("course", courseService.read(course.getId()));
		}
		return modelAndView;
	}
	
	//查看页面
	@RequestMapping("view")
	public ModelAndView view(SearchParam searchParam){
		Map<String,Object> paramMap=searchParam.getParamMap();
		ModelAndView modelAndView=new ModelAndView();
		modelAndView.setViewName("/train/course/audit");
		if(paramMap.get("courseId")!=null&&StringUtils.isNotBlank(paramMap.get("courseId").toString())){
			modelAndView.addObject("course", courseService.read(paramMap.get("courseId").toString()));
		}
		modelAndView.addObject("viewValue", "view");
		return modelAndView;
	}
	
	
	//领导审核
	@RequestMapping("audit")
	public @ResponseBody AjaxReturnObject audit(Course course,SearchParam searchParam){
		int statusCode=200;
		String msg="操作成功！";
		courseService.audit(course, request, searchParam);
		return new AjaxReturnObject(statusCode, msg);
	}
	
	//查看详细
	@RequestMapping("showDetail")
	public ModelAndView showDetail(Course course){
		ModelAndView modelAndView=new ModelAndView();
		modelAndView.addObject("course", courseService.read(course.getId()));
		return modelAndView;
	}

	//跳往专题专家上传附件页面
	@RequestMapping("goUploadFile")
	public ModelAndView goUploadFile(SearchParam searchParam){
		ModelAndView modelAndView=new ModelAndView();
		modelAndView.setViewName("/space/archive/upload");
		return modelAndView;
	}
	
	//保存专题专家上传的附件
	@RequestMapping("saveExpertFile")
	public @ResponseBody AjaxReturnObject saveExpertFile(SearchParam searchParam,@RequestParam(value = "upload", required = false)MultipartFile[] upload){
		int statusCode=200;
		String msg="操作成功！";
		String info=courseService.uploadExpertFile(searchParam, upload,request);
		if(StringUtils.isNotBlank(info)){
			statusCode=300;
			msg=info;
		}
		return new AjaxReturnObject(statusCode, msg);
	}
	
	//删除专题专家上传附件
	@RequestMapping("deleteExpertFile")
	@ResponseBody
	public AjaxReturnObject deleteExpertFile(SearchParam searchParam){
		int statusCode=200;
		String msg="操作成功！";
		courseService.deleteExpertFile(searchParam, request);
		return new AjaxReturnObject(statusCode, msg);
	}
}
