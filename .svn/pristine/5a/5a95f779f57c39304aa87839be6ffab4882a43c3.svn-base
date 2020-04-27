package com.hcis.ipr.evalua.effect.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcis.ipanther.core.service.impl.mybatis.BaseServiceImpl;
import com.hcis.ipanther.core.web.vo.SearchParam;
import com.hcis.ipr.evalua.effect.dao.TeachingEvaluaDao;
import com.hcis.ipr.evalua.effect.entity.TeachingEvalua;
import com.hcis.ipr.evalua.effect.service.ITeachingEvaluaService;

@Service
public class TeachingEvaluaServiceImpl extends BaseServiceImpl<TeachingEvalua> implements ITeachingEvaluaService{

	@Autowired
	private TeachingEvaluaDao baseDao;
	
	@Override
	public TeachingEvaluaDao getBaseDao() {
		return baseDao;
	}


	@Override
	public int create(List<TeachingEvalua> teachingEvaluas, String id) {
		int count=0;
		if(CollectionUtils.isNotEmpty(teachingEvaluas)){
			for (TeachingEvalua teachingEvalua : teachingEvaluas) {
				count+=create(teachingEvalua, id);
			}
		}
		return count;
	}

	@Override
	public Map<String, TeachingEvalua> selectList(SearchParam searchParam) {
		Map<String, TeachingEvalua> map=new HashMap<String, TeachingEvalua>();
		List<TeachingEvalua> list= baseDao.selectList(searchParam);
		if(CollectionUtils.isNotEmpty(list)){
			for (TeachingEvalua teachingEvalua : list) {
				map.put(teachingEvalua.getTeacherId(), teachingEvalua);
			}
		}
		return map;
	}

}
