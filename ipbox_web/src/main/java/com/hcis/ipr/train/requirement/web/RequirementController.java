package com.hcis.ipr.train.requirement.web;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.hcis.ipanther.common.login.vo.LoginUser;
import com.hcis.ipanther.core.spring.mvc.bind.annotation.FormModel;
import com.hcis.ipanther.core.web.vo.SearchParam;
import com.hcis.ipr.core.entity.Response;
import com.hcis.ipr.core.web.controller.BaseController;
import com.hcis.ipr.train.project.service.IProjectService;
import com.hcis.ipr.train.requirement.entity.RequirementSurvey;
import com.hcis.ipr.train.requirement.service.IRequirementSurveyService;
import com.hcis.survey.entity.Survey;
import com.hcis.survey.entity.Survey.SurveyState;
import com.hcis.survey.service.ISurveyBizService;
/**
 * 培训需求调研子系统相关控制
 * @author wuwentao
 * @date 2015年4月14日
 */
@Controller
@RequestMapping("/train/requirement")
public class RequirementController  extends BaseController {
	
	@Autowired
	private IRequirementSurveyService requirementSurveyService;
	@Autowired
	private ISurveyBizService surveyBizService;
	@Autowired
	private IProjectService projectService;
	
	/**
	 * 问卷管理、问卷统计分析
	 * 只查本机构的
	 * 问卷统计分析只查已发布的
	 * @param searchParam
	 * @return
	 */
	@RequestMapping({"/listSurvey","/listSurveyStat"})
	public ModelAndView list(SearchParam searchParam) {
		ModelAndView mv = new ModelAndView();
		Map<String,Object> paramMap = searchParam.getParamMap();
		paramMap.put("deptId", LoginUser.loginUser(request).getFirstDeptId());
		if("listSurveyStat".equals(this.getRequestAction())) {//统计分析
			paramMap.put("state", SurveyState.PUBLISHED.toString());//已发布
		}
		List<RequirementSurvey> requirementSurveies = requirementSurveyService.list(searchParam);
		mv.addObject("requirementSurveies", requirementSurveies);
		return mv;
	}
	
	/**
	 * 编辑/查看问卷
	 * @param requirementSurvey
	 * @return
	 */
	@RequestMapping({"viewSurvey","/editSurvey"})
	public ModelAndView editSurvey(RequirementSurvey requirementSurvey) {
		ModelAndView mv = new ModelAndView();
		String id = requirementSurvey.getId();
		if(StringUtils.isNotEmpty(id)) {
			requirementSurvey = requirementSurveyService.read(id);
			Survey survey = surveyBizService.listSurveyQuestions(requirementSurvey.getSurveyId());
			mv.addObject("requirementSurvey", requirementSurvey);
			mv.addObject("survey", survey);
		}
		/*
		 * 加载项目
		 */
		/*SearchParam searchParam = new SearchParam();
		searchParam.getParamMap().put("status", "02");
		mv.addObject("projects", projectService.listDone(searchParam, request));*/
		return mv;
	}
	
	/**
	 * 保存问卷
	 * @param survey
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/saveSurvey")
	@ResponseBody
	public Response save(@FormModel("requirementSurvey") RequirementSurvey requirementSurvey,@FormModel("survey") Survey survey) throws Exception {
		String id = requirementSurveyService.saveSurvey(requirementSurvey,survey);
		return new Response(200, id);
	}
	
	/**
	 * 逻辑删除问卷
	 * @param requirementSurvey
	 * @return
	 */
	@RequestMapping("/deleteSurvey")
	@ResponseBody
	public Response deleteSurvey(RequirementSurvey requirementSurvey) {
		int count = requirementSurveyService.delete(requirementSurvey,LoginUser.loginUser(request).getId());
		return count>0?Response.successInstance():Response.failInstance();
	}
}
