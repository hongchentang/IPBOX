package com.hcis.ipanther.common.user.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcis.ipanther.common.user.dao.UserRegisterDao;
import com.hcis.ipanther.common.user.entity.UserRegister;
import com.hcis.ipanther.common.user.service.IUserRegisterService;
import com.hcis.ipanther.core.persistence.dao.MyBatisDao;
import com.hcis.ipanther.core.service.impl.mybatis.BaseServiceImpl;
/**
 * 
 * @author wuwentao
 * @date 2015年3月30日
 */
@Service
public class UserRegisterImpl extends BaseServiceImpl<UserRegister> implements IUserRegisterService {
	
	@Autowired
	private UserRegisterDao baseDao;
	
	@Override
	public MyBatisDao getBaseDao() {
		return baseDao;
	}
	
	@Override
	public int update(UserRegister userRegister) {
		return baseDao.updateByPrimaryKeySelective(userRegister);
	}

	@Override
	public UserRegister getLatestByUserId(String userId) {
		return baseDao.getLatestByUserId(userId);
	}
	
}
