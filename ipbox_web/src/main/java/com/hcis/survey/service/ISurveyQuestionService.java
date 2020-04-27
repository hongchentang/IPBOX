/*************************************************
*IQuestionService.java
*
*2013-12-9
*
*Copyright 2012-2013 HaoYi Co.Ltd. All Rights Reserved
*************************************************/
package com.hcis.survey.service;

import java.util.List;

import com.hcis.ipanther.core.entity.PageAndList;
import com.hcis.ipr.core.entity.Response;
import com.hcis.ipanther.core.page.Pagination;
import com.hcis.ipanther.core.service.IBaseService;
import com.hcis.survey.entity.SurveyQuestion;


/**
 *
 *
 *@author 梁华璜
 */
public interface ISurveyQuestionService extends IBaseService<SurveyQuestion>{
	/**
	 * 
	 * @param id
	 * @return
	 */
	public SurveyQuestion  queryQuestionById(final String id);

	/**
	 * 
	 * @param question
	 * @return
	 */
	public List<SurveyQuestion>  queryQuestion(final SurveyQuestion question);
	
	/**
	 * 
	 * @param question
	 * @param pagination
	 * @return
	 */
	public PageAndList<SurveyQuestion>  queryQuestion(final SurveyQuestion question,Pagination pagination);
	
	/**
	 * add Question 
	 * @param question
	 * @return
	 */
	public SurveyQuestion addQuestion(SurveyQuestion question);
	
	/**
	 * 逻辑删除题目，可撤销删除
	 * @param question
	 * @return
	 */
	public Response removeQuestionLogic(SurveyQuestion question);
	
	public Response updateQuestion(SurveyQuestion question);

	public List<String> saveQuestions(String relationId, List<SurveyQuestion> questions);
	
	/**
	 * 批量删除题目(反向删除, 即删除relationId下所有ID不在ids集合中的数据)
	 * @param relationId
	 * @param ids 
	 * @return
	 */
	public Response deleteQuestions(String relationId, List<String> ids);

}
