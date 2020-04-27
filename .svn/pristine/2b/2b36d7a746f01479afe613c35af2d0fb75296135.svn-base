package com.hcis.ipanther.common.user.service;

import com.hcis.ipanther.common.user.entity.UserRegister;
import com.hcis.ipanther.core.service.IBaseService;

public interface IUserRegisterService extends IBaseService<UserRegister> {

	int update(UserRegister userRegister);
	
	/**
	 * 取到最新的一次注册记录
	 * @param userId
	 * @return
	 */
	UserRegister getLatestByUserId(String userId);
	
}
