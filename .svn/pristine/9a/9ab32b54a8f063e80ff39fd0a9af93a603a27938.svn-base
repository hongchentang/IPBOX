package com.hcis.ipr.train.project.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcis.ipanther.core.persistence.dao.MyBatisDao;
import com.hcis.ipanther.core.service.impl.mybatis.BaseServiceImpl;
import com.hcis.ipr.train.project.dao.ProjectFlowDao;
import com.hcis.ipr.train.project.entity.ProjectFlow;
import com.hcis.ipr.train.project.service.IProjectFlowService;

@Service("projectFlowService")
public class ProjectFlowServiceImpl extends BaseServiceImpl<ProjectFlow> implements IProjectFlowService{

	@Autowired
	private ProjectFlowDao projectFlowDao;
	
	@Override
	public MyBatisDao getBaseDao() {
		return projectFlowDao;
	}

}
