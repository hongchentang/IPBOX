/*************************************************
*QuestionServiceImpl.java
*
*2013-12-9
*
*Copyright 2012-2013 HaoYi Co.Ltd. All Rights Reserved
*************************************************/
package com.hcis.survey.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.hcis.ipanther.core.entity.PageAndList;
import com.hcis.ipr.core.entity.Response;
import com.hcis.ipanther.core.page.Pagination;
import com.hcis.ipanther.core.persistence.dao.MyBatisDao;
import com.hcis.ipanther.core.service.impl.mybatis.BaseServiceImpl;
import com.hcis.ipanther.core.utils.Identities;
import com.hcis.survey.dao.SurveyQuestionDao;
import com.hcis.survey.entity.SurveyQuestion;
import com.hcis.survey.service.ISurveyQuestionService;

/**
 *
 *
 *@author 梁华璜
 */
@Service("surveyQuestionService")
public class SurveyQuestionServiceImpl extends BaseServiceImpl<SurveyQuestion> implements ISurveyQuestionService {
	@Resource
	private SurveyQuestionDao surveyQuestionDao;
	
	/* (non-Javadoc)
	 * @see com.hcis.survey.service.IQuestionService#queryQuestionById(java.lang.String)
	 */
	@Override
	public SurveyQuestion queryQuestionById(String id) {
		return surveyQuestionDao.selectById(id);
	}

	/* (non-Javadoc)
	 * @see com.hcis.survey.service.IQuestionService#queryQuestion(com.hcis.survey.entity.Question)
	 */
	@Override
	public List<SurveyQuestion> queryQuestion(SurveyQuestion question) {
		return surveyQuestionDao.select(question);
	}

	/* (non-Javadoc)
	 * @see com.hcis.survey.service.IQuestionService#queryQuestion(com.hcis.survey.entity.Question, com.hcis.ipanther.core.page.Pagination)
	 */
	@Override
	public PageAndList<SurveyQuestion> queryQuestion(SurveyQuestion question,
			Pagination pagination) {
		List<SurveyQuestion> list = surveyQuestionDao.select(question, pagination);
		return new PageAndList<SurveyQuestion>(list,pagination);
	}

	/* (non-Javadoc)
	 * @see com.hcis.survey.service.IQuestionService#addQuestion(com.hcis.survey.entity.Question)
	 */
	@Override
	public SurveyQuestion addQuestion(SurveyQuestion question) {
		if (StringUtils.isEmpty(question.getId())) {
			question.setId(Identities.uuid2());
		}
		question.setDefaultValue();
		surveyQuestionDao.insert(question);
		return question;
	}

	/* (non-Javadoc)
	 * @see com.hcis.survey.service.IQuestionService#removeQuestionLogic(com.hcis.survey.entity.Question)
	 */
	@Override
	public Response removeQuestionLogic(SurveyQuestion question) {
		question.setUpdateTime(new Date());
		int count = surveyQuestionDao.deleteByLogic(question);
		if(count>0){
			return Response.successInstance();
		}
		return Response.failInstance();
	}

	@Override
	public Response updateQuestion(SurveyQuestion question) {
		question.setUpdateTime(new Date());
		int count = surveyQuestionDao.update(question);
		if(count>0){
			return Response.successInstance();
		}
		return Response.failInstance();
	}

	@Override
	public List<String> saveQuestions(String relationId, List<SurveyQuestion> questions) {
		if (questions != null && !questions.isEmpty()) {
			List<String> ids = Lists.newArrayList();
			for (SurveyQuestion surveyQuestion : questions) {
				surveyQuestion.setRelationId(relationId);
				this.addQuestion(surveyQuestion);
				ids.add(surveyQuestion.getId());
			}
			return ids;
		}
		return Lists.newArrayList();
	}

	@Override
	public Response deleteQuestions(String relationId, List<String> ids) {
		int count = surveyQuestionDao.deleteQuestions(relationId, ids);
		if (count>0) {
			return Response.successInstance();
		}
		return Response.failInstance();
	}

	@Override
	public MyBatisDao getBaseDao() {
		return surveyQuestionDao;
	}

}
