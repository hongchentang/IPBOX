package com.hcis.survey.dao;

import org.springframework.stereotype.Repository;

import com.hcis.ipanther.core.persistence.dao.MyBatisDao;
import com.hcis.survey.entity.SurveySubmission;
@Repository("surveySubmissionDao")
public class SurveySubmissionDao extends MyBatisDao{

	public int insert(SurveySubmission surveySubmission) {
		return super.insertSelective(surveySubmission);
	}

	public int deleteByPhysics(SurveySubmission surveySubmission) {
		return super.deleteByPhysics(surveySubmission);
	}

}
