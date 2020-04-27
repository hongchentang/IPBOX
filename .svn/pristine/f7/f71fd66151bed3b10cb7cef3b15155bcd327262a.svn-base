package com.hcis.survey.web;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.hcis.ipanther.common.login.vo.LoginUser;
import com.hcis.ipr.core.entity.Response;
import com.hcis.ipanther.core.web.vo.SearchParam;
import com.hcis.ipr.core.web.controller.BaseController;
import com.hcis.survey.entity.Survey;
import com.hcis.survey.entity.SurveyParam;
import com.hcis.survey.entity.SurveyQuestion;
import com.hcis.survey.entity.SurveyResult;
import com.hcis.survey.entity.SurveyUser;
import com.hcis.survey.service.ISurveyBizService;
import com.hcis.survey.service.ISurveyQuestionService;
import com.hcis.survey.service.ISurveyService;
import com.hcis.survey.service.ISurveySubmissionService;
import com.hcis.survey.service.ISurveyUserBizService;
import com.hcis.survey.service.ISurveyUserService;
/**
 * 调查问卷管理及执行相关的控制
 * /survey为管理端的请求
 * /space/survey为个人端的请求
 * @date 2015年4月3日
 */
@Controller
@RequestMapping({"/survey","/space/survey"})
public class SurveyController extends BaseController{
	
	@Resource
	private ISurveyBizService surveyBizService;
	@Autowired
	private ISurveyService surveyService;
	@Autowired
	private ISurveyUserBizService surveyUserBizService;
	@Autowired
	private ISurveyUserService surveyUserService;
	@Autowired
	private ISurveySubmissionService surveySubmissionService;
	@Autowired
	private ISurveyQuestionService surveyQuestionService;
	
	/**
	 * 问卷列表
	 * 只差自己机构的
	 * @param searchParam
	 * @return
	 */
	@RequestMapping("/list")
	public ModelAndView list(SearchParam searchParam) {
		ModelAndView mv = new ModelAndView();
		searchParam.getParamMap().put("deptId", LoginUser.loginUser(request).getFirstDeptId());
		List<Survey> surveies = surveyService.list(searchParam);
		mv.addObject("surveies", surveies);
		return mv;
	}
	
	/**
	 * 跳转到新增或者编辑页面
	 * 已经发布的问卷不允许修改问题的信息，但是可以修改问卷的信息，编辑问题的排序
	 * @param survey
	 * @return
	 */
	@RequestMapping({"view","/edit"})
	public ModelAndView edit(Survey survey) {
		ModelAndView mv = new ModelAndView();
		String id = survey.getId();
		if(StringUtils.isNotEmpty(id)) {
			survey = surveyBizService.listSurveyQuestions(id);
			mv.addObject("survey", survey);
		}
		return mv;
	}
	
	/**
	 * 保存问卷
	 * @param survey
	 * @return
	 */
	@RequestMapping("/save")
	@ResponseBody
	public Response save(Survey survey) {
		String id = surveyService.save(survey);
		return new Response(200, id);
	}
	
	/**
	 * 逻辑删除问卷
	 * @param survey
	 * @return
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public Response delete(Survey survey) {
		int count = surveyService.delete(survey);
		return count>0?Response.successInstance():Response.failInstance();
	}
	
	/**
	 * 发布
	 * @param survey
	 * @return
	 */
	@RequestMapping("/publish")
	@ResponseBody
	public Response publish(Survey survey) {
		survey.setState(Survey.SurveyState.PUBLISHED.toString());
		survey.setPublishTime(new Date());
		int count = surveyService.update(survey,LoginUser.loginUser(request).getId());
		return count>0?Response.successInstance():Response.failInstance();
	}
	
	/**
	 * 预览问卷、查看/执行用户的调查问卷
	 * @param survey
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping({"/preview","/userSurvey"})
	public ModelAndView survey(Survey survey,String userId) throws IOException{
		ModelAndView mv = new ModelAndView("/survey/survey");
		//个人端的样式与服务端要区分开
		if(super.isSpace()) {
			mv.setViewName("/space/survey/survey");
		}
		
		if("preview".equals(this.getRequestAction())) {//预览不用加载用户信息
			userId = "";
		} else if(StringUtils.isEmpty(userId)) {//默认加载当前用户的数据
			userId = LoginUser.loginUser(request).getId();
		}
		
		SurveyResult surveyResult = surveyUserBizService.viewSurveyUser(survey.getId(),userId);
		
		mv.addObject("survey",surveyResult.getSurvey());
		mv.addObject("surveyUser",surveyResult.getSurveyUser());
		mv.addObject("choiceInteractionMap",surveyResult.getChoiceInteractionMap());
		mv.addObject("textEntryInteractionMap",surveyResult.getTextEntryInteractionMap());
		return mv;
	}
	
	/**
	 * 调查问卷统计
	 * @param survey
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping("/surveyStat")
	public ModelAndView surveyStat(Survey survey,String userId) throws IOException{
		ModelAndView mv = new ModelAndView("/survey/surveyStat");
		//个人端的样式与服务端要区分开
		if(super.isSpace()) {
			mv.setViewName("/space/survey/surveyStat");
		}
		String surveyId = survey.getId();
		if(StringUtils.isEmpty(userId)) {
			userId = LoginUser.loginUser(request).getId();
		}
		/*
		 * 得到问卷的基本信息
		 */
		SurveyResult surveyResult = surveyUserBizService.viewSurveyUser(surveyId,userId);
		mv.addObject("survey",surveyResult.getSurvey());
		mv.addObject("surveyUser",surveyResult.getSurveyUser());
		mv.addObject("choiceInteractionMap",surveyResult.getChoiceInteractionMap());
		mv.addObject("textEntryInteractionMap",surveyResult.getTextEntryInteractionMap());
		
		/*
		 * 得到问卷的统计信息
		 */
		mv.addObject("surveyStat", surveyUserService.surveyStat(surveyId));
		return mv;
	}
	
	
	@RequestMapping("listTextEntry")
	public ModelAndView listTextEntry(SearchParam searchParam){
		ModelAndView mv = new ModelAndView();
		mv.addObject("textEntryList",surveySubmissionService.listTextEntry(searchParam));
		return mv;
	}
	
	/**
	 * 保存用户的问卷信息
	 * @param surveyUser
	 * @return
	 */
	@ResponseBody
	@RequestMapping("saveUserSurvey")
	public Response saveUserSurvey(SurveyUser surveyUser){
		return surveyUserBizService.saveSurveyUser(surveyUser,LoginUser.loginUser(request).getId());
	}

	/**
	 * 编辑问卷的问题
	 * @param surveyParam
	 * @return
	 */
	@RequestMapping("editSurveyQuestion")
	public ModelAndView editSurveyQuestion(SurveyParam surveyParam){
		ModelAndView mv = new ModelAndView();
		String questionId = surveyParam.getSurveyQuestion().getId();
		if(StringUtils.isNotEmpty(questionId)) {//编辑
			SurveyResult surveyResult = surveyBizService.getSurveyQuestion(surveyParam.getSurveyQuestion());
			mv.addObject("surveyQuestion",surveyResult.getSurveyQuestion());
			mv.addObject("choiceInteraction", surveyResult.getChoiceInteraction());
			mv.addObject("textEntryInteraction", surveyResult.getTextEntryInteraction());
		} else {//新增
			mv.addObject("surveyQuestion",surveyParam.getSurveyQuestion());
		}
		mv.setViewName("/survey/question/edit");
		return mv;
	}
	
	/**
	 * 保存问卷的问题
	 * @param surveyParam
	 * @return
	 */
	@ResponseBody
	@RequestMapping("saveSurveyQuestion")
	public Response saveSurveyQuestion(SurveyParam surveyParam){
		return surveyBizService.saveSurveyQuestion(surveyParam, LoginUser.loginUser(request).getId());
	}
	
	/**
	 * 删除问卷的问题
	 * @param surveyQuestion
	 * @return
	 */
	@RequestMapping("removeSurveyQuestion")
	@ResponseBody
	public Response removeSurveyQuestion(SurveyQuestion surveyQuestion){
		return surveyBizService.removeSurveyQuestion(surveyQuestion, LoginUser.loginUser(request).getId());
	}
	
	/**
	 * 提交问卷问题的顺序
	 * @param surveyParam
	 * @return
	 */
	@RequestMapping("moveSurveyQuestion")
	@ResponseBody
	public Response moveSurveyQuestion(SurveyParam surveyParam){
		return surveyBizService.moveSurveyQuestion(surveyParam.getQuestions(), LoginUser.loginUser(request).getId());
	}
	
}
