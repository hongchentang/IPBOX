package com.hcis.ipr.train.requirement.service;

import com.hcis.ipanther.core.service.IBaseService;
import com.hcis.ipr.train.requirement.entity.RequirementSurvey;
import com.hcis.survey.entity.Survey;

public interface IRequirementSurveyService extends IBaseService<RequirementSurvey> {
	
	/**
	 * 新增/更新问卷
	 * @param requirementSurvey
	 * @param survey
	 * @throws Exception
	 */
	public String saveSurvey(RequirementSurvey requirementSurvey,Survey survey) throws Exception;
	
}
