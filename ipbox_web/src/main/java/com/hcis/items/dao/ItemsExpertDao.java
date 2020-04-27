package com.hcis.items.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.hcis.ipanther.core.persistence.dao.MyBatisDao;
import com.hcis.ipanther.core.web.vo.SearchParam;
@Repository("itemsExpertDao")
public class ItemsExpertDao extends MyBatisDao{
	//申请人显目全部列表
	public List<Map<String,Object>> listExpert(SearchParam searchParam){
		return this.selectForList(namespace+".queryeItems", searchParam);
	}
	
	public int deleteExperts(SearchParam searchParam) {
		return this.deleteByLogic(searchParam);
	}
	public int updateByParam(SearchParam searchParam) {
		return updateByPrimaryKeySelective(searchParam);
	}
	
}