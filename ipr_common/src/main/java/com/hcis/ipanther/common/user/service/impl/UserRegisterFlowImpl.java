package com.hcis.ipanther.common.user.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcis.ipanther.common.security.service.IUserRoleService;
import com.hcis.ipanther.common.user.dao.UserRegisterFlowDao;
import com.hcis.ipanther.common.user.entity.UserRegisterFlow;
import com.hcis.ipanther.common.user.service.IUserRegisterFlowService;
import com.hcis.ipanther.core.persistence.dao.MyBatisDao;
import com.hcis.ipanther.core.service.impl.mybatis.BaseServiceImpl;
import com.hcis.ipanther.core.web.vo.SearchParam;
/**
 * 用户注册审核流程相关
 * @author wuwentao
 * @date 2015年3月25日
 */
@Service
public class UserRegisterFlowImpl extends BaseServiceImpl<UserRegisterFlow> implements IUserRegisterFlowService {
	
	@Autowired
	private UserRegisterFlowDao baseDao;
	
	@Autowired
	private IUserRoleService userRoleService;

	@Override
	public MyBatisDao getBaseDao() {
		return baseDao;
	}
	
	@Override
	public UserRegisterFlow getLatestByUserId(String userId) {
		return baseDao.getLatestByUserId(userId);
	}

	@Override
	public List<UserRegisterFlow> getFlowsByUserId(SearchParam searchParam) {
		List<UserRegisterFlow> flows = baseDao.getFlowsByUserId(searchParam);
		return flows;
	}
	
}
