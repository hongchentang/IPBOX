package com.hcis.items.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.hcis.ipanther.core.persistence.dao.MyBatisDao;
import com.hcis.ipanther.core.web.vo.SearchParam;
@Repository("itemsLibraryDao")
public class ItemsLibraryDao extends MyBatisDao{
	//申请人显目全部列表
	public List<Map<String,Object>> listLibrary(SearchParam searchParam){
		return this.selectForList(namespace+".listLibrary", searchParam);
	}
	
	
}