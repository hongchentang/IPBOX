/*************************************************
*SurveyDao.java
*
*2013-12-9
*
*Copyright 2012-2013 HaoYi Co.Ltd. All Rights Reserved
*************************************************/
package com.hcis.survey.dao;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.hcis.ipanther.core.persistence.dao.MyBatisDao;
import com.hcis.ipanther.core.web.vo.SearchParam;
import com.hcis.survey.entity.Survey;
import com.hcis.survey.entity.SurveyStat;

/**
 *
 *
 *@author 梁华璜
 */
@Repository("surveyDao")
public class SurveyDao extends MyBatisDao{

	public String insert(Survey survey) {
		int count = super.insertSelective(survey);
		return count>0?survey.getId():null;
	}

	public int update(Survey survey) {
		survey.setUpdateTime(new Date());
		return super.updateByPrimaryKeySelective(survey);
	}

	public int deleteByLogic(Survey survey) {
		survey.setUpdateTime(new Date());
		return super.deleteByLogic(survey);
	}

	public Survey selectById(String id) {
		return super.selectByPrimaryKey(id);
	}
	
	/**
	 * 找到个人端用户可参加的问卷
	 * @param searchParam
	 * @return
	 */
	public List<Survey> listSurveyForSpace(SearchParam searchParam) {
		return this.selectForList(namespace+".listSurveyForSpace",searchParam);
	}
}
