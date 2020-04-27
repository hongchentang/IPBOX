package com.hcis.survey.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.hcis.ipr.core.entity.Response;
import com.hcis.ipanther.core.persistence.dao.MyBatisDao;
import com.hcis.ipanther.core.service.impl.mybatis.BaseServiceImpl;
import com.hcis.ipanther.core.utils.Identities;
import com.hcis.ipanther.core.web.vo.SearchParam;
import com.hcis.survey.dao.SurveySubmissionDao;
import com.hcis.survey.entity.SurveySubmission;
import com.hcis.survey.entity.SurveyQuestion.QuesType;
import com.hcis.survey.entity.SurveyUser.CompletionStatus;
import com.hcis.survey.service.ISurveySubmissionService;

@Service
public class SurveySubmissionServiceImpl extends BaseServiceImpl<SurveySubmission> implements ISurveySubmissionService{

	@Resource
	private SurveySubmissionDao surveySubmissionDao;
	
	@Override
	public Response create(SurveySubmission surveySubmission) {
		if (StringUtils.isEmpty(surveySubmission.getId())) {
			surveySubmission.setId(Identities.uuid2());
		}
		int count = surveySubmissionDao.insert(surveySubmission);
		return count>0?Response.successInstance():Response.failInstance();
	}

	@Override
	public Response deleteByPhysics(SurveySubmission surveySubmission) {
		int count = surveySubmissionDao.deleteByPhysics(surveySubmission);
		return count>0?Response.successInstance():Response.failInstance();
	}

	@Override
	public MyBatisDao getBaseDao() {
		// TODO Auto-generated method stub
		return surveySubmissionDao;
	}
	
	@Override
	public List<SurveySubmission> listTextEntry(SearchParam searchParam) {
		Map<String,Object> paramMap = searchParam.getParamMap();
		paramMap.put("quesType", QuesType.TEXTENTRY.toString());
		paramMap.put("status" , CompletionStatus.COMPLETE.toString());
		List<SurveySubmission> textEntryList = this.list(searchParam);
		return textEntryList;
	}

}
