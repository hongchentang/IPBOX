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
import com.hcis.ipanther.common.user.dao.UserResearchDao;
import com.hcis.ipanther.common.user.entity.UserResearch;
import com.hcis.ipanther.common.user.service.IUserResearchService;
import com.hcis.ipanther.core.persistence.dao.MyBatisDao;
import com.hcis.ipanther.core.service.impl.mybatis.BaseServiceImpl;
import com.hcis.ipanther.core.utils.Identities;
import com.hcis.ipanther.core.web.vo.SearchParam;

@Service
public class UserResearchServiceImpl extends BaseServiceImpl<UserResearch> implements IUserResearchService {
	
	private final static Log log=LogFactory.getLog(UserResearchServiceImpl.class);
	
	@Autowired
	private UserResearchDao baseDao;
	
	@Override
	public MyBatisDao getBaseDao() {
		return baseDao;
	}

	@Override
	public List<UserResearch> list(SearchParam searchParam) {
		return baseDao.selectBySearchParam(searchParam);
	}

	@Override
	public int save(UserResearch userResearch) {
		LoginUser loginUser=(LoginUser)SecurityUtils.getSubject().getPrincipal();
		String LoginUserId = loginUser.getId();
		String id = userResearch.getId();
		int count = 0;
		if(StringUtils.isEmpty(id)) {//新增
			userResearch.setId(Identities.uuid2());
			userResearch.setDefaultValue();
			userResearch.setCreator(LoginUserId);
			count = baseDao.insertSelective(userResearch);
		} else {//更新
			userResearch.setUpdatedby(LoginUserId);
			userResearch.setUpdateTime(new Date());
			count = baseDao.updateByPrimaryKeySelective(userResearch);
		}
		return count;
	}
	
	
}
