package com.hcis.ipr.train.userquestion.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.support.DefaultMultipartHttpServletRequest;

import com.hcis.ipanther.common.login.vo.LoginUser;
import com.hcis.ipanther.common.user.entity.User;

import com.hcis.ipanther.core.service.IBaseService;
import com.hcis.ipanther.core.web.vo.SearchParam;
import com.hcis.ipr.train.userquestion.entity.UserQuestion;

public interface IUserQuestionService extends IBaseService<UserQuestion>{

	/**
	 * 增加
	 * @param userQuestion
	 * @param loginUser
	 * @return
	 */
	public int addUserQuestion(UserQuestion userQuestion,LoginUser loginUser);
	
	/**
	 * 数据列表
	 * @param searchParam
	 * @return
	 */
	//public List<UserQuestion> selectBySearchParam(SearchParam searchParam);
	
	/**
	 * 邮件发送
	 * 
	 * @param userQuestion
	 */
	public void sendMail(UserQuestion userQuestion);
	
	/**
	 * 逻辑删除在线留言记录
	 * @param userQuestion
	 * @return
	 */
	public int deleteByLogic(UserQuestion userQuestion);
	
	/**
	 * 根据某个字段查找出符合的所有在线留言记录
	 * @param map
	 * @return
	 */
	public List<UserQuestion> selectByMap(Map<String,Object> map);
	
	/**
	 * 保存操作或保存并发送邮件操作
	 * @param userQuestion
	 * @param type
	 * @return
	 */
	public int updateAndSend(HttpServletRequest request,UserQuestion userQuestion,String type);
	
	/**
	 * 计算出不同类型的数据数量
	 * @param map
	 * @return
	 */
	public int selectCount(Map<String,Object> map);
}
