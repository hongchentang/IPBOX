package com.hcis.ipr.train.project.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.hcis.ipanther.core.web.controller.BaseController;
import com.hcis.ipanther.core.web.vo.SearchParam;
import com.hcis.ipr.train.project.service.IProjectFlowService;

@RequestMapping("/train/projectFlow")
@Controller
public class ProjectFlowController extends BaseController{
	
	@Autowired
	private IProjectFlowService projectFlowService;
	
	//日记列表
	@RequestMapping("listProjectFlow")
	public ModelAndView listProFlow(SearchParam searchParam){
		ModelAndView modelAndView=new ModelAndView();
		modelAndView.setViewName("/train/project/listProjectFlow");
		modelAndView.addObject("listProFlow", projectFlowService.list(searchParam));
		return modelAndView;
	}

}
