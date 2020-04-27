package com.hcis.ipr.train.register.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.hcis.ipanther.core.persistence.dao.MyBatisDao;
import com.hcis.ipanther.core.web.vo.SearchParam;

@Repository("courseRegisterDao")
public class CourseRegisterDao extends MyBatisDao{
	
	public int batchUpdate(SearchParam searchParam){
		return this.update(namespace+".batchDelete", searchParam);
	}
	
	public List<Map<String,Object>> selectScoreInfo(SearchParam searchParam){
		return this.selectForList(namespace+".selectScoreInfo", searchParam);
				
	}
	
	public List<Map<String,Object>> listScoreInfo(SearchParam searchParam){
		return this.selectForList(namespace+".listScoreInfo", searchParam);
				
	}
}