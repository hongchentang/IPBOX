package com.hcis.survey.service;

import java.io.IOException;
import java.util.List;

import com.hcis.ipr.core.entity.Response;
import com.hcis.ipanther.core.web.vo.SearchParam;
import com.hcis.survey.entity.SurveyResult;
import com.hcis.survey.entity.SurveySubmission;
import com.hcis.survey.entity.SurveyUser;

public interface ISurveyUserBizService {

	SurveyResult viewSurveyUser(String surveyId, String userId) throws IOException;

	Response saveSurveyUser(SurveyUser surveyUser,String userId);

	SurveyResult viewSurveyResult(boolean hasStudentRole, String surveyId, String relationId, String userId);

}
