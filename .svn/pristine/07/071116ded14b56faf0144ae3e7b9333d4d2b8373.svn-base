/*************************************************
*ISurveyUserService.java
*
*2013-12-11
*
*Copyright 2012-2013 HaoYi Co.Ltd. All Rights Reserved
*************************************************/
package com.hcis.survey.service;

import java.util.List;

import com.hcis.ipanther.core.entity.PageAndList;
import com.hcis.ipr.core.entity.Response;
import com.hcis.ipanther.core.page.Pagination;
import com.hcis.ipanther.core.web.vo.SearchParam;
import com.hcis.survey.entity.Survey;
import com.hcis.survey.entity.SurveyStat;
import com.hcis.survey.entity.SurveyUser;

/**
 *
 *
 *@author 梁华璜
 */
public interface ISurveyUserService{
	/**
	 * 返回用户参与的调查问卷结果,
	 * 如果用户尚未参与则按照该问卷初始化一条用户参与记录
	 * @param surveyId 调查问卷id
	 * @param userId 用户id
	 * @return
	 */
	public SurveyUser getSurveyUser(String surveyId,String userId);
	/**
	 * 按照id查询用户调查问卷结果
	 * @param id
	 * @return
	 */
	public SurveyUser getSurveyUser(String id);
	/**
	 * 查询用户参与调查问卷
	 * @param surveyUser
	 * 接收参数属性：
	 * surveyId:调查问卷id
	 * relationId：关联id
	 * userId:用户id
	 * completionStatus:完成状态
	 * @return
	 */
	public List<SurveyUser> querySurveyUser(SurveyUser surveyUser);
	/**
	 * 查询用户参与调查问卷
	 * @see #querySurveyUser(SurveyUser)
	 * @param surveyUser
	 * @param users 用户id集合 主要针对客户端调用时需要根据姓名、单位等模糊查询
	 * @return
	 */
	public List<SurveyUser> querySurveyUser(SurveyUser surveyUser,List<String> users);
	
	/**
	 * 带分页查询用户参与调查问卷
	 * @see #querySurveyUser(SurveyUser)
	 * @param surveyUser
	 * @param pagination
	 * @return
	 */
	public PageAndList<SurveyUser> querySurveyUser(SurveyUser surveyUser,Pagination pagination);
	
	/**
	 * 带分页查询用户参与的调查问卷
	 * @see #querySurveyUser(SurveyUser, List)
	 * @param surveyUser
	 * @param users
	 * @param pagination
	 * @return
	 */
	public PageAndList<SurveyUser> querySurveyUser(SurveyUser surveyUser,List<String> users,Pagination pagination);
	
	/**
	 * 保存用户调查问卷结果
	 * @param surveyUser
	 * @return
	 */
	public Response saveSurveyUser(SurveyUser surveyUser);
	
	/**
	 * 得到问卷的统计信息
	 * @param surveyId
	 * @return
	 */
	public SurveyStat surveyStat(String surveyId);
	
}
