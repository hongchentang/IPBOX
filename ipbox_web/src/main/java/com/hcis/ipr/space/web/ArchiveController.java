package com.hcis.ipr.space.web;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.hcis.ipanther.common.login.vo.LoginUser;
import com.hcis.ipanther.core.web.vo.AjaxReturnObject;
import com.hcis.ipanther.core.web.vo.SearchParam;
import com.hcis.ipr.core.web.controller.BaseController;
import com.hcis.ipr.space.service.IArchiveService;
import com.hcis.ipr.train.course.service.ICourseService;
import com.hcis.ipr.train.register.service.ICourseRegisterService;
import com.hcis.ipr.train.register.service.ITrainRegisterService;
import com.hcis.ipr.train.train.service.ITrainCourseService;

@RequestMapping("/space/archive")
@Controller
public class ArchiveController extends BaseController{

	@Autowired
	private ITrainRegisterService trainRegisterService;
	@Autowired
	private ICourseRegisterService courseRegisterService;
	@Autowired
	private ICourseService courseService;
	@Autowired
	private IArchiveService archiveService;
	@Autowired
	private ITrainCourseService trainCourseService;
	
	//学生档案tab
	@RequestMapping({"/studentArchive","/noskin/studentArchive"})
	public ModelAndView studentArchive(SearchParam searchParam){
		ModelAndView mv=new ModelAndView();
		mv.setViewName("/space/archive/studentArchive");
		return mv;
	}
	
	//学生培训班报名档案列表
	@RequestMapping("/noskin/listTrainRegister")
	public ModelAndView listTrainRegister(SearchParam searchParam){
		ModelAndView mv=new ModelAndView();
		mv.setViewName("/space/archive/listTrainRegister");
		searchParam.getParamMap().put("studentId", LoginUser.loginUser(request).getId());
		mv.addObject("listTrainRegister", trainRegisterService.list(searchParam));
		return mv;
	}
	
	//学生档案分数查询
	@RequestMapping("/noskin/listScoreRegister")
	public ModelAndView listScoreRegister(SearchParam searchParam){
		ModelAndView mv=new ModelAndView();
		mv.setViewName("/space/archive/listScoreRegister");
		searchParam.getParamMap().put("studentId", LoginUser.loginUser(request).getId());
		mv.addObject("listScoreInfo", courseRegisterService.listScoreInfo(searchParam));
		return mv;
	}
	
	//教师记录tab
	@RequestMapping({"/teacherArchive","/noskin/teacherArchive"})
	public ModelAndView teacherArchive(SearchParam searchParam){
		ModelAndView mv=new ModelAndView();
		mv.setViewName("/space/archive/teacherArchive");
		return mv;
	}
	
	//专题课程
	@RequestMapping("/noskin/listExpertCourse")
	public ModelAndView listExpertCourse(SearchParam searchParam){
		ModelAndView mv=new ModelAndView();
		mv.setViewName("/space/archive/listExpertCourse");
		searchParam.getParamMap().put("expertId", LoginUser.loginUser(request).getId());
		mv.addObject("listCourse", courseService.listCourse(searchParam));
		return mv;
	}
	
	//专题课程附件列表
	@RequestMapping("/noskin/listExpertAttachment")
	public ModelAndView listExpertAttachment(SearchParam searchParam){
		ModelAndView mv=new ModelAndView();
		mv.setViewName("/space/archive/listExpertAttachment");
		mv.addObject("listAttachment", archiveService.listExpertAttachment(searchParam));
		return mv;
	}
	
	//课程
	@RequestMapping("/noskin/listTeacherCourse")
	public ModelAndView listTeacherCourse(SearchParam searchParam){
		ModelAndView mv=new ModelAndView();
		mv.setViewName("/space/archive/listTeacherCourse");
		searchParam.getParamMap().put("teacherId", LoginUser.loginUser(request).getId());
		mv.addObject("listCourse", courseService.listTrainCoure(searchParam));
		return mv;
	}
	
	//课程附件列表
	@RequestMapping("/noskin/listCourseAttachment")
	public ModelAndView listCourseAttachment(SearchParam searchParam){
		ModelAndView mv=new ModelAndView();
		mv.setViewName("/space/archive/listCourseAttachment");
		mv.addObject("listAttachment", archiveService.listExpertAttachment(searchParam));
		return mv;
	}
	
	//跳往课程上传附件页面
	@RequestMapping("/noskin/goUploadFile")
	public ModelAndView goUploadFile(SearchParam searchParam){
		ModelAndView modelAndView=new ModelAndView();
		modelAndView.setViewName("/space/archive/uploadCourseFile");
		return modelAndView;
	}
		
	//课程附件上传
	@RequestMapping("/noskin/saveCourseFile")
	public @ResponseBody AjaxReturnObject saveCourseFile(SearchParam searchParam,@RequestParam(value = "upload", required = false)MultipartFile[] upload){
		int statusCode=200;
		String msg="操作成功！";
		String info=trainCourseService.uploadCourseFile(searchParam, upload, request);
		if(StringUtils.isNotBlank(info)){
			statusCode=300;
			msg=info;
		}
		return new AjaxReturnObject(statusCode, msg);
	}
	
	//删除课程附件
	@RequestMapping("/noskin/deleteCourseFile")
	@ResponseBody
	public AjaxReturnObject deleteCourseFile(SearchParam searchParam){
		int statusCode=200;
		String msg="操作成功！";
		trainCourseService.deleteCourseFile(searchParam, request);
		return new AjaxReturnObject(statusCode, msg);
	}
	
	//课程详细tab
	@RequestMapping("/noskin/courseTab")
	public ModelAndView courseTab(SearchParam searchParam){
		ModelAndView mv=new ModelAndView();
		mv.setViewName("/space/archive/courseTab");
		return mv;
	}
	
}
