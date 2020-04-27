/*************************************************
*SurveyUserServiceImpl.java
*
*2013-12-11
*
*Copyright 2012-2013 HaoYi Co.Ltd. All Rights Reserved
*************************************************/
package com.hcis.survey.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.hcis.ipanther.core.entity.PageAndList;
import com.hcis.ipr.core.entity.Response;
import com.hcis.ipanther.core.page.Pagination;
import com.hcis.ipanther.core.utils.Identities;
import com.hcis.ipanther.core.web.vo.SearchParam;
import com.hcis.survey.dao.SurveyDao;
import com.hcis.survey.dao.SurveyUserDao;
import com.hcis.survey.entity.Survey;
import com.hcis.survey.entity.SurveyStat;
import com.hcis.survey.entity.SurveySubmission;
import com.hcis.survey.entity.SurveyUser;
import com.hcis.survey.entity.SurveyUser.CompletionStatus;
import com.hcis.survey.service.ISurveySubmissionService;
import com.hcis.survey.service.ISurveyUserService;

/**
 *
 *
 *@author 梁华璜
 */
@Service("surveyUserService")
public class SurveyUserServiceImpl implements ISurveyUserService {
	@Resource
	private SurveyUserDao surveyUserDao;
	@Resource
	private SurveyDao surveyDao;
	@Resource
	private ISurveySubmissionService surveySubmissionService;
	
	@Override
	public SurveyUser getSurveyUser(String surveyId,String userId) {
		SurveyUser surveyUser = new SurveyUser();
		Survey survey = new Survey();
		survey.setId(surveyId);
		surveyUser.setSurvey(survey);
		surveyUser.setUserId(userId);
		List<SurveyUser> surveyUserList = surveyUserDao.select(surveyUser);
		if(surveyUserList!=null&&!surveyUserList.isEmpty()){
			return surveyUserList.get(0);
		}
		//先插入问卷调查用户信息
		survey = surveyDao.selectById(surveyId);
		if(survey!=null){
			surveyUser.setSurveyId(surveyId);
			surveyUser.setId(Identities.uuid2());
			surveyUser.setCompletionStatus(CompletionStatus.NOTATTEMPED.toString());
			surveyUser.setDefaultValue();
			surveyUserDao.insert(surveyUser);
			return surveyUser;
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see com.hcis.survey.service.ISurveyUserService#getSurveyUser(java.lang.String)
	 */
	@Override
	public SurveyUser getSurveyUser(String id) {
		return surveyUserDao.selectById(id);
	}

	/* (non-Javadoc)
	 * @see com.hcis.survey.service.ISurveyUserService#querySurveyUser(com.hcis.survey.entity.SurveyUser)
	 */
	@Override
	public List<SurveyUser> querySurveyUser(SurveyUser surveyUser) {
		return surveyUserDao.select(surveyUser);
	}

	/* (non-Javadoc)
	 * @see com.hcis.survey.service.ISurveyUserService#querySurveyUser(com.hcis.survey.entity.SurveyUser, java.util.List)
	 */
	@Override
	public List<SurveyUser> querySurveyUser(SurveyUser surveyUser,
			List<String> users) {
		return surveyUserDao.select(surveyUser, users);
	}

	/* (non-Javadoc)
	 * @see com.hcis.survey.service.ISurveyUserService#querySurveyUser(com.hcis.survey.entity.SurveyUser, com.hcis.ipanther.core.page.Pagination)
	 */
	@Override
	public PageAndList<SurveyUser> querySurveyUser(SurveyUser surveyUser,
			Pagination pagination) {
		List<SurveyUser> surveyUserList = surveyUserDao.select(surveyUser, pagination);
		return new PageAndList<SurveyUser>(surveyUserList,pagination);
	}

	/* (non-Javadoc)
	 * @see com.hcis.survey.service.ISurveyUserService#querySurveyUser(com.hcis.survey.entity.SurveyUser, java.util.List, com.hcis.ipanther.core.page.Pagination)
	 */
	@Override
	public PageAndList<SurveyUser> querySurveyUser(SurveyUser surveyUser,
			List<String> users, Pagination pagination) {
		List<SurveyUser> surveyUserList = surveyUserDao.select(surveyUser,users, pagination);
		return new PageAndList<SurveyUser>(surveyUserList,pagination);
	}

	/* (non-Javadoc)
	 * @see com.hcis.survey.service.ISurveyUserService#saveSurveyUser(com.hcis.survey.entity.SurveyUser)
	 */
	@Override
	public Response saveSurveyUser(SurveyUser surveyUser) {
		surveyUser.setUpdateTime(new Date());
		int count = surveyUserDao.update(surveyUser);
		if(count>0){
			if (surveyUser.getSurveySubmissions() != null && !surveyUser.getSurveySubmissions().isEmpty()) {
				SurveySubmission surveySubmission = new SurveySubmission();
				surveySubmission.setSurveyUserId(surveyUser.getId());
				surveySubmissionService.deleteByPhysics(surveySubmission);
				for (SurveySubmission ss : surveyUser.getSurveySubmissions()) {
					ss.setSurveyUserId(surveyUser.getId());
					surveySubmissionService.create(ss);
				}
			}
			return Response.successInstance();
		}
		return Response.failInstance();
	}

	@Override
	public SurveyStat surveyStat(String surveyId) {
		SurveyStat surveyStat = surveyUserDao.surveyStat(surveyId);
		/*
		 * 处理问卷的数据
		 */
		String choiceIds = surveyStat.getChoiceIds();
		Map<String,Integer> stat = new HashMap<String,Integer>();
		if(StringUtils.isNotEmpty(choiceIds)) {
			for (String choiceId : choiceIds.split(",")) {
				if(StringUtils.isNotEmpty(choiceId)) {
					Integer count = stat.get(choiceId);
					if(null==count) {
						count = 0;
					}
					stat.put(choiceId, ++count);
				}
			}
		}
		surveyStat.setStat(stat);
		return surveyStat;
	}
}
