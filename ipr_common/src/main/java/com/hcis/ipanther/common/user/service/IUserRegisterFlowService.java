package com.hcis.ipanther.common.user.service;

import java.util.List;

import com.hcis.ipanther.common.user.entity.UserRegisterFlow;
import com.hcis.ipanther.core.service.IBaseService;
import com.hcis.ipanther.core.web.vo.SearchParam;

public interface IUserRegisterFlowService extends IBaseService<UserRegisterFlow> {

	/**
	 * 根据用户ID取最新一条记录
	 * @param userId
	 * @return
	 */
	UserRegisterFlow getLatestByUserId(String userId);
	
	/**
	 * 根据用户的ID取出用户的所有流程流转信息,包括教师和学生
	 * @param userId
	 * @return
	 */
	List<UserRegisterFlow> getFlowsByUserId(SearchParam searchParam);
}
