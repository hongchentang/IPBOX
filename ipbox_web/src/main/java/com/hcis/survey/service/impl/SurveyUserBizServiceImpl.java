package com.hcis.survey.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcis.ipr.core.entity.Response;
import com.hcis.ipanther.core.persistence.dao.MyBatisDao;
import com.hcis.ipanther.core.service.impl.mybatis.BaseServiceImpl;
import com.hcis.ipanther.core.utils.JsonUtil;
import com.hcis.ipanther.core.web.vo.SearchParam;
import com.hcis.survey.dao.SurveySubmissionDao;
import com.hcis.survey.dao.SurveyUserDao;
import com.hcis.survey.entity.ChoiceInteraction;
import com.hcis.survey.entity.Survey;
import com.hcis.survey.entity.SurveyQuestion;
import com.hcis.survey.entity.SurveyResult;
import com.hcis.survey.entity.SurveySubmission;
import com.hcis.survey.entity.SurveyUser;
import com.hcis.survey.entity.SurveyQuestion.QuesType;
import com.hcis.survey.entity.SurveyUser.CompletionStatus;
import com.hcis.survey.entity.TextEntryInteraction;
import com.hcis.survey.service.ISurveyBizService;
import com.hcis.survey.service.ISurveySubmissionService;
import com.hcis.survey.service.ISurveyUserBizService;
import com.hcis.survey.service.ISurveyUserService;

@Service("surveyUserBizService")
public class SurveyUserBizServiceImpl extends BaseServiceImpl<SurveyUser> implements ISurveyUserBizService {

	@Resource
	private ISurveyBizService surveyBizService;
	@Resource
	private ISurveyUserService surveyUserService;
	@Autowired
	private SurveyUserDao surveyUserDao;
	@Autowired
	private ISurveySubmissionService surveySubmissionService;
	
	@Override
	public MyBatisDao getBaseDao() {
		return surveyUserDao;
	}

	@Override
	public SurveyResult viewSurveyUser(String surveyId, String userId) throws IOException {
		SurveyResult surveyResult = new SurveyResult();
		/*
		 * 加载问卷信息
		 */
		Survey survey = surveyBizService.listSurveyQuestions(surveyId);
		if (survey.getQuestions() != null && !survey.getQuestions().isEmpty()) {
			for (SurveyQuestion surveyQuestion : survey.getQuestions()) {
				if (SurveyQuestion.QuesType.TEXTENTRY.toString().equals(surveyQuestion.getQuesType())) {
					TextEntryInteraction textEntryInteraction = JsonUtil.fromJson(surveyQuestion.getInteraction(), TextEntryInteraction.class);
					surveyResult.getTextEntryInteractionMap().put(surveyQuestion.getId(), textEntryInteraction);
				} else {
					ChoiceInteraction choiceInteraction = JsonUtil.fromJson(surveyQuestion.getInteraction(), ChoiceInteraction.class);
					surveyResult.getChoiceInteractionMap().put(surveyQuestion.getId(), choiceInteraction);
				}
			}
		}
		
		/*
		 * 如果用户不为空，则是预览，不用加载用户的问卷信息
		 */
		if(StringUtils.isNotEmpty(userId)) {
			SurveyUser surveyUser = surveyUserService.getSurveyUser(surveyId, userId);
			surveyResult.setSurveyUser(surveyUser);
		}
		surveyResult.setSurvey(survey);
		return surveyResult;
	}

	@Override
	public Response saveSurveyUser(SurveyUser surveyUser,String userId) {
		surveyUser.setCompletionStatus(CompletionStatus.COMPLETE.toString());
		Response response = surveyUserService.saveSurveyUser(surveyUser);
		if (response.isSuccess()) {
			return Response.successInstance();
		}
		return Response.failInstance();
	}

	@Override
	public SurveyResult viewSurveyResult(boolean hasStudentRole,String surveyId, String relationId, String userId) {
		SurveyResult surveyResult = new SurveyResult();
		Survey survey = surveyBizService.listSurveyQuestions(surveyId);
		try {
			if (survey.getQuestions() != null && !survey.getQuestions().isEmpty()) {
				for (SurveyQuestion surveyQuestion : survey.getQuestions()) {
					if (!SurveyQuestion.QuesType.TEXTENTRY.toString().equals(surveyQuestion.getQuesType())) {
						ChoiceInteraction choiceInteraction = JsonUtil.fromJson(surveyQuestion.getInteraction(), ChoiceInteraction.class);
						surveyResult.getChoiceInteractionMap().put(surveyQuestion.getId(), choiceInteraction);
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (hasStudentRole) {
			SurveyUser surveyUser = surveyUserService.getSurveyUser(surveyId, userId);
			surveyResult.setSurveyUser(surveyUser);
		}
		surveyResult.setSurvey(survey);
		return surveyResult;
	}
}
