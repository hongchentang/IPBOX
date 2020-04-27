package com.hcis.ipr.evalua.effect.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.hcis.ipanther.core.persistence.dao.MyBatisDao;
import com.hcis.ipanther.core.web.vo.SearchParam;
import com.hcis.ipr.evalua.effect.entity.TeachingEvalua;

@Repository
public class TeachingEvaluaDao extends MyBatisDao{

	public List<TeachingEvalua> selectList(SearchParam searchParam) {
		return this.selectForList(namespace+".selectList", searchParam);
	}
 
}