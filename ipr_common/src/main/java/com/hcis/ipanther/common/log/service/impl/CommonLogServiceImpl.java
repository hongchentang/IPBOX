package com.hcis.ipanther.common.log.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcis.ipanther.core.service.impl.mybatis.BaseServiceImpl;
import com.hcis.ipanther.common.log.dao.CommonLogDao;
import com.hcis.ipanther.common.log.entity.CommonLog;
import com.hcis.ipanther.common.log.service.ICommonLogService;



@Service("commonLogService")
public class CommonLogServiceImpl extends BaseServiceImpl<CommonLog> implements ICommonLogService {

	@Autowired 
	private CommonLogDao baseDao;
	
	
	
	
	
	
	
	@Override
	public CommonLogDao getBaseDao() {
		return baseDao;
	}
}
