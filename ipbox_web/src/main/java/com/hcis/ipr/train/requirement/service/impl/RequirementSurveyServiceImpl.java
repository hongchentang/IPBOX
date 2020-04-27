package com.hcis.ipr.train.requirement.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcis.ipanther.common.login.vo.LoginUser;
import com.hcis.ipanther.core.persistence.dao.MyBatisDao;
import com.hcis.ipanther.core.service.impl.mybatis.BaseServiceImpl;
import com.hcis.ipanther.core.utils.Identities;
import com.hcis.ipanther.core.utils.JsonUtil;
import com.hcis.ipr.train.requirement.dao.RequirementSurveyDao;
import com.hcis.ipr.train.requirement.entity.RequirementSurvey;
import com.hcis.ipr.train.requirement.service.IRequirementSurveyService;
import com.hcis.survey.entity.Survey;
import com.hcis.survey.service.ISurveyService;

@Service
public class RequirementSurveyServiceImpl extends BaseServiceImpl<RequirementSurvey> implements IRequirementSurveyService {
	
	@Autowired
	private RequirementSurveyDao baseDao;
	@Autowired
	private ISurveyService surveyService;
	
	@Override
	public MyBatisDao getBaseDao() {
		return baseDao;
	}

	@Override
	public String saveSurvey(RequirementSurvey requirementSurvey,Survey survey) throws Exception {
		LoginUser loginUser=(LoginUser)SecurityUtils.getSubject().getPrincipal();
		String userId = loginUser.getId();
		String id = requirementSurvey.getId();
		/*
		 * 保存基础问卷信息
		 */
		String surveyId = surveyService.save(survey);
		
		/*
		 * 处理数据-转换为json集合存储
		 */
		String regionsCodes = requirementSurvey.getRegionsCodes();
		if(StringUtils.isNotEmpty(regionsCodes)) {
			requirementSurvey.setRegionsCodes(JsonUtil.toJson(regionsCodes.split(",")));			
		}
		String projectIds = requirementSurvey.getProjectIds();
		if(StringUtils.isNotEmpty(projectIds)) {
			requirementSurvey.setProjectIds(JsonUtil.toJson(projectIds.split(",")));
		}
		
		if(StringUtils.isEmpty(id)) {//新增
			id = Identities.uuid2();
			requirementSurvey.setId(id);
			requirementSurvey.setSurveyId(surveyId);
			this.create(requirementSurvey, userId);
		} else {//更新
			this.update(requirementSurvey, userId);
		}
		return id;
	}
	
}
