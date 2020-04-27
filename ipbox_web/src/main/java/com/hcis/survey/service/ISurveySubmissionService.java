package com.hcis.survey.service;

import java.util.List;

import com.hcis.ipr.core.entity.Response;
import com.hcis.ipanther.core.service.IBaseService;
import com.hcis.ipanther.core.web.vo.SearchParam;
import com.hcis.survey.entity.SurveySubmission;

public interface ISurveySubmissionService extends IBaseService<SurveySubmission>{

	Response create(SurveySubmission surveySubmission);

	Response deleteByPhysics(SurveySubmission surveySubmission);

	List<SurveySubmission> listTextEntry(SearchParam searchParam);

}
