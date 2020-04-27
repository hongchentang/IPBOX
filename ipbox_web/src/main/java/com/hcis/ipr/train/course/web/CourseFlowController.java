package com.hcis.ipr.train.course.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.hcis.ipanther.core.web.controller.BaseController;
import com.hcis.ipanther.core.web.vo.SearchParam;
import com.hcis.ipr.train.course.service.ICourseFlowService;

@RequestMapping("/train/courseFlow")
@Controller
public class CourseFlowController extends BaseController{

	@Autowired
	public ICourseFlowService courseFlowService;
	
	//列表
	@RequestMapping("listCourseFlow")
	public ModelAndView listCourseFlow(SearchParam searchParam){
		ModelAndView modelAndView=new ModelAndView();
		modelAndView.setViewName("/train/course/listCourseFlow");
		modelAndView.addObject("listCourseFlow", courseFlowService.list(searchParam));
		return modelAndView;
	}
	
	
	
}
