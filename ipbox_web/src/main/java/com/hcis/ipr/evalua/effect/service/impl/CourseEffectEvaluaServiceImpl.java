package com.hcis.ipr.evalua.effect.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcis.ipanther.core.service.impl.mybatis.BaseServiceImpl;
import com.hcis.ipr.evalua.effect.dao.CourseEffectEvaluaDao;
import com.hcis.ipr.evalua.effect.entity.CourseEffectEvalua;
import com.hcis.ipr.evalua.effect.service.ICourseEffectEvaluaService;

@Service
public class CourseEffectEvaluaServiceImpl extends BaseServiceImpl<CourseEffectEvalua> implements ICourseEffectEvaluaService{

	@Autowired
	private CourseEffectEvaluaDao baseDao;
	

	@Override
	public CourseEffectEvaluaDao getBaseDao() {
		return baseDao;
	}

 

}
