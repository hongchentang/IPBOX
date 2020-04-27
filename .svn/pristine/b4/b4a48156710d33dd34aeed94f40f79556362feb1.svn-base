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
import com.hcis.ipanther.common.user.dao.UserParttimeDao;
import com.hcis.ipanther.common.user.entity.UserParttime;
import com.hcis.ipanther.common.user.service.IUserParttimeService;
import com.hcis.ipanther.core.persistence.dao.MyBatisDao;
import com.hcis.ipanther.core.service.impl.mybatis.BaseServiceImpl;
import com.hcis.ipanther.core.utils.Identities;
import com.hcis.ipanther.core.web.vo.SearchParam;

@Service
public class UserParttimeServiceImpl extends BaseServiceImpl<UserParttime> implements IUserParttimeService {
	
	private final static Log log=LogFactory.getLog(UserParttimeServiceImpl.class);
	
	@Autowired
	private UserParttimeDao baseDao;
	
	@Override
	public MyBatisDao getBaseDao() {
		return baseDao;
	}

	@Override
	public List<UserParttime> list(SearchParam searchParam) {
		return baseDao.selectBySearchParam(searchParam);
	}

	@Override
	public int save(UserParttime userParttime) {
		LoginUser loginUser=(LoginUser)SecurityUtils.getSubject().getPrincipal();
		String LoginUserId = loginUser.getId();
		String id = userParttime.getId();
		int count = 0;
		if(StringUtils.isEmpty(id)) {//新增
			userParttime.setId(Identities.uuid2());
			userParttime.setDefaultValue();
			userParttime.setCreator(LoginUserId);
			count = baseDao.insertSelective(userParttime);
		} else {//更新
			userParttime.setUpdatedby(LoginUserId);
			userParttime.setUpdateTime(new Date());
			count = baseDao.updateByPrimaryKeySelective(userParttime);
		}
		return count;
	}
	
	
}
