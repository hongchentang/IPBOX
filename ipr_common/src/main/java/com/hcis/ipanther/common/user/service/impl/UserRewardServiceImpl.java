package com.hcis.ipanther.common.user.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcis.ipanther.common.login.vo.LoginUser;
import com.hcis.ipanther.common.user.dao.UserRewardDao;
import com.hcis.ipanther.common.user.entity.UserReward;
import com.hcis.ipanther.common.user.service.IUserRewardService;
import com.hcis.ipanther.core.persistence.dao.MyBatisDao;
import com.hcis.ipanther.core.service.impl.mybatis.BaseServiceImpl;
import com.hcis.ipanther.core.utils.Identities;
import com.hcis.ipanther.core.web.vo.SearchParam;

@Service
public class UserRewardServiceImpl extends BaseServiceImpl<UserReward> implements IUserRewardService {
	
	private final static Log log=LogFactory.getLog(UserRewardServiceImpl.class);
	
	@Autowired
	private UserRewardDao baseDao;
	
	@Override
	public MyBatisDao getBaseDao() {
		return baseDao;
	}

	@Override
	public List<UserReward> list(SearchParam searchParam) {
		return baseDao.selectBySearchParam(searchParam);
	}

	@Override
	public int save(UserReward userReward) {
		LoginUser loginUser=(LoginUser)SecurityUtils.getSubject().getPrincipal();
		String LoginUserId = loginUser.getId();
		String id = userReward.getId();
		int count = 0;
		if(StringUtils.isEmpty(id)) {//新增
			userReward.setId(Identities.uuid2());
			userReward.setDefaultValue();
			userReward.setCreator(LoginUserId);
			count = baseDao.insertSelective(userReward);
		} else {//更新
			userReward.setUpdatedby(LoginUserId);
			userReward.setUpdateTime(new Date());
			count = baseDao.updateByPrimaryKeySelective(userReward);
		}
		return count;
	}
	
	
}
