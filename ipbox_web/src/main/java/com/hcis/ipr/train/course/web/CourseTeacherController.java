package com.hcis.ipr.train.course.web;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.hcis.ipanther.common.login.vo.LoginUser;
import com.hcis.ipanther.core.web.controller.BaseController;
import com.hcis.ipanther.core.web.vo.AjaxReturnObject;
import com.hcis.ipanther.core.web.vo.SearchParam;
import com.hcis.ipr.train.course.entity.Course;
import com.hcis.ipr.train.course.service.ICourseService;
import com.hcis.ipr.train.train.service.ITrainCourseService;

@RequestMapping("/train/courseTeacher")
@Controller
public class CourseTeacherController extends BaseController{

	@Autowired
	private ICourseService courseService;
	@Autowired
	private ITrainCourseService trainCourseService;
	
	//跳转分配教师页面
	@RequestMapping("divideView")
	public ModelAndView divideView(SearchParam searchParam){
		ModelAndView modelAndView=new ModelAndView();
		modelAndView.setViewName("train/course/divideView");
		Object trainCourseIdObj=searchParam.getParamMap().get("trainCourseId");
		if(null!=trainCourseIdObj&&StringUtils.isNotBlank(trainCourseIdObj.toString())){
			modelAndView.addObject("listTrainCourse", courseService.listTrainCoure(searchParam));
		}
		return modelAndView;
	}
	//跳转分配专家页面
	@RequestMapping("divideExpert")
	public ModelAndView divideExpert(SearchParam searchParam){
		ModelAndView modelAndView=new ModelAndView();
		modelAndView.setViewName("train/course/divideExpert");
		Object courseIdObj=searchParam.getParamMap().get("courseId");
		if(null!=courseIdObj&&StringUtils.isNotBlank(courseIdObj.toString())){
			modelAndView.addObject("listExpert", courseService.list(searchParam));
		}
		return modelAndView;
	}
	
	//教师列表
	@RequestMapping("divideTeacher")
	public ModelAndView divideTeacher(SearchParam searchParam){
		ModelAndView modelAndView=new ModelAndView();
		modelAndView.setViewName("train/course/divideTeacher");
		Object teacherIdsObj=searchParam.getParamMap().get("teacherIds");
		if(null!=teacherIdsObj&&StringUtils.isNotBlank(teacherIdsObj.toString())){
			//String teacherIds="'"+teacherIdsObj.toString().replaceAll(",", "','")+"'";
			searchParam.getParamMap().put("excludeId", teacherIdsObj.toString());
		}
		//角色为教师类型的人
		searchParam.getParamMap().put("roleCode", "teacher");
		modelAndView.addObject("listTeacher", trainCourseService.listTeacher(searchParam));
		return modelAndView;
	}
	/*//新增修改课程的主讲教师选择
	@RequestMapping("selectTeacher")
	public ModelAndView selectTeacher(SearchParam searchParam){
		ModelAndView modelAndView=new ModelAndView();
		modelAndView.setViewName("train/course/selectTeacher");
		Object teacherIdsObj=searchParam.getParamMap().get("teacherIds");
		if(null!=teacherIdsObj&&StringUtils.isNotBlank(teacherIdsObj.toString())){
			String teacherIds="'"+teacherIdsObj.toString().replaceAll(",", "','")+"'";
			searchParam.getParamMap().put("excludeId", teacherIds);
		}
		//角色为教师类型的人
		searchParam.getParamMap().put("roleCode", "teacher");
		modelAndView.addObject("listTeacher", trainCourseService.listTeacher(searchParam));
		return modelAndView;
	}*/
	
	//保存分配教师
	@RequestMapping("saveDevide")
	@ResponseBody
	public AjaxReturnObject saveDevide(SearchParam searchParam){
		int statusCode=200;
		String msg="操作成功！";
		trainCourseService.saveDivide(searchParam, LoginUser.loginUser(request));
		return new AjaxReturnObject(statusCode, msg);
	}
	
	//保存分配专家
	@RequestMapping("saveDevideExpert")
	@ResponseBody
	public AjaxReturnObject saveDevideExpert(SearchParam searchParam){
		int statusCode=200;
		String msg="操作成功！";
		Course c=new Course();
		String courseId=searchParam.getParamMap().get("courseId").toString();
		Object teacherIdsObj=searchParam.getParamMap().get("teacherIds");
		c.setId(courseId);
		c.setExpertId(teacherIdsObj.toString());
		if(courseService.update(c, LoginUser.loginUser(request).getId())<=0){
			statusCode=300;
			msg="操作失败！";
		};
		return new AjaxReturnObject(statusCode, msg);
	}
}
