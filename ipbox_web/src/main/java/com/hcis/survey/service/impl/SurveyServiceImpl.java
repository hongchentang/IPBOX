/*************************************************
*SurveyServiceImpl.java
*
*2013-12-9
*
*Copyright 2012-2013 HaoYi Co.Ltd. All Rights Reserved
*************************************************/
package com.hcis.survey.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.hcis.ipanther.common.login.vo.LoginUser;
import com.hcis.ipr.core.entity.Response;
import com.hcis.ipanther.core.persistence.dao.MyBatisDao;
import com.hcis.ipanther.core.service.impl.mybatis.BaseServiceImpl;
import com.hcis.ipanther.core.utils.Identities;
import com.hcis.ipanther.core.web.vo.SearchParam;
import com.hcis.survey.dao.SurveyDao;
import com.hcis.survey.entity.Survey;
import com.hcis.survey.entity.SurveyQuestion;
import com.hcis.survey.service.ISurveyQuestionService;
import com.hcis.survey.service.ISurveyService;

/**
 *
 *
 *@author 梁华璜
 */
@Service("surveyService")
public class SurveyServiceImpl extends BaseServiceImpl<Survey> implements ISurveyService {
	@Resource
	private SurveyDao surveyDao;
	@Resource
	private ISurveyQuestionService surveyQuestionService;
	
	@Override
	public String save(Survey survey) {
		LoginUser loginUser=(LoginUser)SecurityUtils.getSubject().getPrincipal();
		String id = survey.getId();
		if(StringUtils.isNotEmpty(id)) {
			this.update(survey, loginUser.getId());
		} else {
			id = Identities.uuid2();
			survey.setState(Survey.SurveyState.UNPUBLISHED.toString());//未发布
			survey.setId(id);
			this.create(survey, loginUser.getId());
		}
		return id;
	}
	
	/* (non-Javadoc)
	 * @see com.hcis.survey.service.ISurveyService#addSurveyQuestion(java.lang.String, java.lang.String, com.hcis.survey.entity.Question)
	 */
	@Override
	public Survey addSurveyQuestion(String id, String userId, SurveyQuestion question) {
		if(!StringUtils.isEmpty(id)&&question!=null){
			Survey survey = new Survey();
			survey.setId(id);
			survey.setUpdatedby(userId);
			survey.setUpdateTime(new Date());
			List<SurveyQuestion> questions = new ArrayList<SurveyQuestion>();
			questions.add(question);
			survey.setQuestions(questions);
			question.setRelationId(id);
			return survey;
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see com.hcis.survey.service.ISurveyService#addSurveyQuestions(java.lang.String, java.lang.String, java.util.List)
	 */
	@Override
	public Survey addSurveyQuestions(String id, String userId,
			List<SurveyQuestion> questions) {
		if(!StringUtils.isEmpty(id)&&questions!=null&&!questions.isEmpty()){
			Survey survey = new Survey();
			survey.setId(id);
			survey.setUpdatedby(userId);
			survey.setUpdateTime(new Date());
			survey.setQuestions(questions);
			
			surveyQuestionService.saveQuestions(id, questions);
			return survey;
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see com.hcis.survey.service.ISurveyService#removeSurveyQuestion(java.lang.String, java.lang.String, com.hcis.survey.entity.Question)
	 */
	@Override
	public Survey removeSurveyQuestion(String id, String userId,
			SurveyQuestion question) {
		if(!StringUtils.isEmpty(id)&&question!=null){
			Survey survey = new Survey();
			survey.setId(id);
			survey.setUpdatedby(userId);
			survey.setUpdateTime(new Date());
			List<SurveyQuestion> questions = new ArrayList<SurveyQuestion>();
			questions.add(question);
			survey.setQuestions(questions);
			
			question.setUpdatedby(userId);
			surveyQuestionService.removeQuestionLogic(question);
			return survey;
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see com.hcis.survey.service.ISurveyService#removeSurveyQuestions(java.lang.String, java.lang.String, java.util.List)
	 */
	@Override
	public Survey removeSurveyQuestions(String id, String userId,
			List<SurveyQuestion> questions) {
		if(!StringUtils.isEmpty(id)&&questions!=null&&!questions.isEmpty()){
			Survey survey = new Survey();
			survey.setId(id);
			survey.setUpdatedby(userId);
			survey.setUpdateTime(new Date());
			survey.setQuestions(questions);
			
			List<String> ids = Lists.newArrayList();
			for (SurveyQuestion surveyQuestion : questions) {
				ids.add(surveyQuestion.getId());
			}
			surveyQuestionService.deleteQuestions(id, ids);
			return survey;
		}
		return null;
	}

	public Response removeSurveyLogic(Survey survey) {
		survey.setUpdateTime(new Date());
		int count = surveyDao.deleteByLogic(survey);
		if(count>0){
			return Response.successInstance();
		}
		return Response.failInstance();
	}

	@Override
	public MyBatisDao getBaseDao() {
		// TODO Auto-generated method stub
		return surveyDao;
	}

	@Override
	public int delete(Survey survey) {
		LoginUser loginUser=(LoginUser)SecurityUtils.getSubject().getPrincipal();
		survey.setUpdatedby(loginUser.getId());
		return surveyDao.deleteByLogic(survey);
	}
	
	@Override
	public List<Survey> listSurveyForSpace(SearchParam searchParam) {
		LoginUser loginUser=(LoginUser)SecurityUtils.getSubject().getPrincipal();
		Map<String,Object> paramMap = searchParam.getParamMap();
		paramMap.put("userId", loginUser.getId());//当前用户的
		paramMap.put("regionsCode", "\""+loginUser.getRegionsCode()+"\"");//当前人所在区域
		return surveyDao.listSurveyForSpace(searchParam);
	}

}
