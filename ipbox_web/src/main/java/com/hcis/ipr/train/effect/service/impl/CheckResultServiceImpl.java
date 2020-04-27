package com.hcis.ipr.train.effect.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcis.ipanther.core.persistence.dao.MyBatisDao;
import com.hcis.ipanther.core.service.impl.mybatis.BaseServiceImpl;
import com.hcis.ipr.train.effect.dao.CheckResultDao;
import com.hcis.ipr.train.effect.entity.CheckResult;
import com.hcis.ipr.train.effect.service.ICheckResultService;

@Service
public class CheckResultServiceImpl extends BaseServiceImpl<CheckResult> implements ICheckResultService {
	
	@Autowired
	private CheckResultDao baseDao;
	
	@Override
	public MyBatisDao getBaseDao() {
		return baseDao;
	}
}
