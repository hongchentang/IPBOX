package com.hcis.ipr.train.register.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.hcis.ipanther.core.persistence.dao.MyBatisDao;
import com.hcis.ipanther.core.web.vo.SearchParam;

@Repository("trainRegisterDao")
public class TrainRegisterDao extends MyBatisDao {
    
	public int batchUpdate(SearchParam searchParam){
		return this.update(namespace+".batchDelete", searchParam);
	}
	
	public int updateByIds(SearchParam searchParam){
		return this.update(namespace+".updateByIds", searchParam);
	}
	
	public List<Map<String,Object>> selectCountRegister(Map<String, Object> map) {
		return this.selectForList(namespace + ".selectCountRegister", map);
	}
}