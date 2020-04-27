/*************************************************
*ISurveyService.java
*
*2013-12-9
*
*Copyright 2012-2013 HaoYi Co.Ltd. All Rights Reserved
*************************************************/
package com.hcis.survey.service;

import java.util.List;

import com.hcis.ipr.core.entity.Response;
import com.hcis.ipanther.core.service.IBaseService;
import com.hcis.ipanther.core.web.vo.SearchParam;
import com.hcis.survey.entity.Survey;
import com.hcis.survey.entity.SurveyQuestion;

/**
 *
 *
 *@author 梁华璜
 */
public interface ISurveyService extends IBaseService<Survey>{
	
	/**
	 * 保存问卷
	 * @param survey
	 */
	public String save(Survey survey);
	
	/**
	 * 删除问卷
	 * @param survey
	 * @return
	 */
	public int delete(Survey survey);
	
	/**
	 * 添加调查问卷题目引用信息
	 * @param id
	 * @param userId
	 * @param question
	 * @return
	 */
	public Survey addSurveyQuestion(String id,String userId,SurveyQuestion question);
	
	/**
	 * 添加调查问卷题目引用信息
	 * @param id
	 * @param userId
	 * @param questions
	 * @return
	 */
	public Survey addSurveyQuestions(String id,String userId,List<SurveyQuestion> questions);
	
	/**
	 * 删除调查问卷题目引用信息
	 * @param id
	 * @param userId
	 * @param question
	 * @return
	 */
	public Survey removeSurveyQuestion(String id,String userId,SurveyQuestion question);
	
	/**
	 * 移除调查问卷题目引用信息
	 * @param id
	 * @param userId
	 * @param questions
	 * @return
	 */
	public Survey removeSurveyQuestions(String id,String userId,List<SurveyQuestion> questions);

	/**
	 * 逻辑删除问卷
	 * @param survey
	 * @return
	 */
	public Response removeSurveyLogic(Survey survey);
	
	/**
	 * 个人端
	 * 找到个人可以参加的调查问卷
	 * @param searchParam
	 * @return
	 */
	public List<Survey> listSurveyForSpace(SearchParam searchParam);
}
