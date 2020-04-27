package com.hcis.survey.service;

import java.util.List;

import com.hcis.ipr.core.entity.Response;
import com.hcis.survey.entity.Survey;
import com.hcis.survey.entity.SurveyParam;
import com.hcis.survey.entity.SurveyQuestion;
import com.hcis.survey.entity.SurveyResult;

public interface ISurveyBizService {

	Response saveSurveyQuestion(SurveyParam surveyParam, String userId);

	SurveyResult getSurveyQuestion(SurveyQuestion surveyQuestion);

	Response removeSurveyQuestion(SurveyQuestion surveyQuestion, String userId);

	Response moveSurveyQuestion(List<SurveyQuestion> questions, String userId);

	Survey listSurveyQuestions(String surveyId);

}
