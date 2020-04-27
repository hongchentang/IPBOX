/*************************************************
*SurveyUserDao.java
*
*2013-12-11
*
*Copyright 2012-2013 HaoYi Co.Ltd. All Rights Reserved
*************************************************/
package com.hcis.survey.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.hcis.ipanther.core.page.Pagination;
import com.hcis.ipanther.core.persistence.dao.MyBatisDao;
import com.hcis.ipanther.core.web.vo.SearchParam;
import com.hcis.survey.entity.SurveyStat;
import com.hcis.survey.entity.SurveyUser;
@Repository("surveyUserDao")
public class SurveyUserDao extends MyBatisDao {

	public SurveyUser selectById(String id) {
		return super.selectByPrimaryKey(id);
	}

	public List<SurveyUser> select(SurveyUser surveyUser) {
		return this.select(surveyUser, null, null);
	}
	
	public List<SurveyUser> select(SurveyUser surveyUser, List<String> users) {
		return this.select(surveyUser, users, null);
	}

	public List<SurveyUser> select(SurveyUser surveyUser, Pagination pagination) {
		return this.select(surveyUser, null, pagination);
	}

	public List<SurveyUser> select(SurveyUser surveyUser, List<String> users, Pagination pagination) {
		SearchParam searchParam = new SearchParam();
		if (pagination == null) {
			searchParam.setPageAvailable(false);
		}else {
			searchParam.setPagination(pagination);
		}
		if (users!=null && !users.isEmpty()) {
			searchParam.getParamMap().put("uids", users);
		}
		searchParam.getParamMap().put("surveyUser", surveyUser);
		return super.selectBySearchParam(searchParam);
	}

	public int insert(SurveyUser surveyUser) {
		return super.insertSelective(surveyUser);
	}

	public int update(SurveyUser surveyUser) {
		return super.updateByPrimaryKeySelective(surveyUser);
	}
	
	public SurveyStat surveyStat(String surveyId) {
		return (SurveyStat) super.selectForOneVO(namespace+".surveyStat", surveyId);
	}

}
