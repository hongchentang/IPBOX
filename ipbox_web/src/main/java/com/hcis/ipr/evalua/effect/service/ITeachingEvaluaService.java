package com.hcis.ipr.evalua.effect.service;

import java.util.List;
import java.util.Map;

import com.hcis.ipanther.core.service.IBaseService;
import com.hcis.ipanther.core.web.vo.SearchParam;
import com.hcis.ipr.evalua.effect.entity.TeachingEvalua;

public interface ITeachingEvaluaService extends IBaseService<TeachingEvalua>{

	int create(List<TeachingEvalua> teachingEvaluas, String id);

	Map<String, TeachingEvalua> selectList(SearchParam searchParam);

}
