/*************************************************
*QuestionDao.java
*
*2013-12-9
*
*Copyright 2012-2013 HaoYi Co.Ltd. All Rights Reserved
*************************************************/
package com.hcis.survey.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.google.common.collect.Maps;
import com.hcis.ipanther.core.page.Pagination;
import com.hcis.ipanther.core.persistence.dao.MyBatisDao;
import com.hcis.ipanther.core.web.vo.SearchParam;
import com.hcis.survey.entity.SurveyQuestion;
@Repository("surveyQuestionDao")
public class SurveyQuestionDao extends MyBatisDao{


	public List<SurveyQuestion> select(SurveyQuestion question) {
		return this.select(question, null);
	}

	public List<SurveyQuestion> select(SurveyQuestion question, Pagination pagination) {
		SearchParam searchParam = new SearchParam();
		if (pagination == null) {
			searchParam.setPageAvailable(false);
		}else {
			searchParam.setPagination(pagination);
		}
		searchParam.getParamMap().put("question", question);
		return super.selectBySearchParam(searchParam);
	}
	
	public String insert(SurveyQuestion question) {
		int count = super.insertSelective(question);
		return count>0?question.getId():null;
	}

	/* (non-Javadoc)
	 * @see com.hcis.ipanther.core.persistence.dao.ICommonDao#update(java.lang.Object)
	 */
	public int update(SurveyQuestion question) {
		question.setUpdateTime(new Date());
		return super.updateByPrimaryKeySelective(question);
	}

	/* (non-Javadoc)
	 * @see com.hcis.ipanther.core.persistence.dao.ICommonDao#deleteByLogic(java.lang.Object)
	 */
	public int deleteByLogic(SurveyQuestion question) {
		question.setUpdateTime(new Date());
		return super.deleteByLogic(question);
	}

	public int deleteQuestions(String relationId, List<String> ids){
		Map<String, Object> param = Maps.newHashMap();
		param.put("relationId", relationId);
		param.put("ids", ids);
		param.put("updateTime", new Date());
		return super.update(namespace+".deleteQuestions", param);
	}
	
	public SurveyQuestion selectById(String id) {
		return super.selectByPrimaryKey(id);
	}

}
