package com.hcis.ipr.train.course.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcis.ipanther.core.persistence.dao.MyBatisDao;
import com.hcis.ipanther.core.service.impl.mybatis.BaseServiceImpl;
import com.hcis.ipr.train.course.dao.CourseFlowDao;
import com.hcis.ipr.train.course.entity.CourseFlow;
import com.hcis.ipr.train.course.service.ICourseFlowService;

@Service("courseFlowService")
public class CourseFlowServiceImpl extends BaseServiceImpl<CourseFlow> implements ICourseFlowService{

	@Autowired
	private CourseFlowDao courseFlowDao;
	
	@Override
	public MyBatisDao getBaseDao() {
		return courseFlowDao;
	}

}
