package com.hcis.survey.service.impl;

import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.hcis.ipr.core.entity.Response;
import com.hcis.ipanther.core.utils.Identities;
import com.hcis.ipanther.core.utils.JsonUtil;
import com.hcis.survey.entity.Choice;
import com.hcis.survey.entity.ChoiceInteraction;
import com.hcis.survey.entity.Survey;
import com.hcis.survey.entity.SurveyParam;
import com.hcis.survey.entity.SurveyQuestion;
import com.hcis.survey.entity.SurveyResult;
import com.hcis.survey.entity.TextEntryInteraction;
import com.hcis.survey.service.ISurveyBizService;
import com.hcis.survey.service.ISurveyQuestionService;
import com.hcis.survey.service.ISurveyService;

@Service("surveyBizService")
public class SurveyBizServiceImpl implements ISurveyBizService{
	
	@Resource
	private ISurveyService surveyService;
	@Resource
	private ISurveyQuestionService surveyQuestionService;

	@Override
	public Response saveSurveyQuestion(SurveyParam surveyParam, String userId) {
		SurveyQuestion surveyQuestion = surveyParam.getSurveyQuestion();
		if (surveyParam.getChoiceInteraction() != null) {//选择题
			for (Choice choice : surveyParam.getChoiceInteraction().getChoices()) {
				if (StringUtils.isEmpty(choice.getId())) {
					choice.setId(Identities.uuid2());
				}
			}
			surveyQuestion.setInteraction(surveyParam.getChoiceInteraction().toString());
		}else if(surveyParam.getTextEntryInteraction() != null){//简答题
			surveyQuestion.setInteraction(surveyParam.getTextEntryInteraction().toString());
		}
		if (StringUtils.isEmpty(surveyQuestion.getId())) {//新增
			surveyQuestion.setId(Identities.uuid2());
			surveyQuestion = surveyQuestionService.addQuestion(surveyQuestion);
			if (surveyQuestion != null) {
				Survey survey = surveyService.addSurveyQuestion(surveyQuestion.getRelationId(), userId, surveyQuestion);
				if (survey != null) {
					return Response.successInstance();
				}
				return Response.failInstance();
			}
		}else {//修改
			return surveyQuestionService.updateQuestion(surveyQuestion);
		}
		return Response.failInstance();
	}

	@Override
	public SurveyResult getSurveyQuestion(SurveyQuestion surveyQuestion) {
		SurveyResult surveyResult = new SurveyResult();
		surveyQuestion = surveyQuestionService.queryQuestionById(surveyQuestion.getId());
		try {
			if (SurveyQuestion.QuesType.TEXTENTRY.toString().equals(surveyQuestion.getQuesType())) {
				TextEntryInteraction textEntryInteraction = JsonUtil.fromJson(surveyQuestion.getInteraction(), TextEntryInteraction.class);
				surveyResult.setTextEntryInteraction(textEntryInteraction);
			}else {
				ChoiceInteraction choiceInteraction = JsonUtil.fromJson(surveyQuestion.getInteraction(),ChoiceInteraction.class);
				surveyResult.setChoiceInteraction(choiceInteraction);
			} 
		}catch (IOException e) {
			e.printStackTrace();
		}
		surveyResult.setSurveyQuestion(surveyQuestion);
		return surveyResult;
	}

	@Override
	public Response removeSurveyQuestion(SurveyQuestion surveyQuestion, String userId) {
		Response response = surveyQuestionService.removeQuestionLogic(surveyQuestion);
		if (response.isSuccess()) {
			Survey survey = surveyService.removeSurveyQuestion(surveyQuestion.getRelationId(), userId, surveyQuestion);
			if (survey != null) {
				return Response.successInstance();
			}
			return Response.failInstance();
		}
		return Response.failInstance();
	}

	@Override
	public Response moveSurveyQuestion(List<SurveyQuestion> questions, String userId) {
		for (SurveyQuestion surveyQuestion : questions) {
			surveyQuestion.setUpdatedby(userId);
			Response response = surveyQuestionService.updateQuestion(surveyQuestion);
			if (response.isSuccess()) {
				continue;
			}else {
				return Response.failInstance();
			}
		}
		return Response.successInstance();
	}

	@Override
	public Survey listSurveyQuestions(String surveyId) {
		Survey survey = surveyService.read(surveyId);
		return survey;
	}

}
