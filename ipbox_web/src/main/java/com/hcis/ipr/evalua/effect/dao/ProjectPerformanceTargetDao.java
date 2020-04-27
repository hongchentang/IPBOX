package com.hcis.ipr.evalua.effect.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.hcis.ipanther.core.persistence.dao.MyBatisDao;

@Repository
public class ProjectPerformanceTargetDao extends MyBatisDao{

	public Map<String, Object> selectPerformanceTargetById(String projectId) {
		return (Map<String, Object>) this.selectForVO(namespace+".selectPerformanceTargetById",projectId);
	}
	
	
	public List<Map<String, Object>> selectRegionsCodeList(String projectId) {
		return this.selectForList(namespace+".selectRegionsCodeList",projectId);
	}


	public int deleteFileByPid(String projectId) {
		return this.delete(namespace+".deleteFileByPid",projectId);
	}
	
 
}