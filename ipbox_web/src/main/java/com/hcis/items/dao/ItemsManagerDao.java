package com.hcis.items.dao;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Repository;
import com.hcis.items.entity.ItemsManager;

import com.hcis.ipanther.core.persistence.dao.MyBatisDao;
import com.hcis.ipanther.core.web.vo.SearchParam;
@Repository("itemsManagerDao")
public class ItemsManagerDao extends MyBatisDao{
	//申请人显目全部列表
	public List<Map<String,Object>> listProjectItem(SearchParam searchParam){
		return this.selectForList(namespace+".listProjectItem", searchParam);
	}
	
	public List<Map<String,Object>> checkProjectItems(SearchParam searchParam){
		return this.selectForList(namespace+".checkProjectItems", searchParam);
	}
	
	public List<Map<String,Object>> listExperts(SearchParam searchParam){
		return this.selectForList(namespace+".listExpert", searchParam);
	}
	
	public List<Map<String,Object>> listExpertView(SearchParam searchParam){
		return this.selectForList(namespace+".listExpertView", searchParam);
	}
	
	public List<Map<String,Object>> listExpertName(SearchParam searchParam){
		return this.selectForList(namespace+".listExpertName", searchParam);
	}
	
	public List<Map<String,Object>> listAgencyName(SearchParam searchParam){
		return this.selectForList(namespace+".listAgencyName", searchParam);
	}
	
	public List<Map<String,Object>> auditAgencyList(SearchParam searchParam){
		return this.selectForList(namespace+".auditAgencyList", searchParam);
	}
	
	
	
//	public ItemsManager readbyProjectSourceCode(SearchParam searchParam) {
		//return this.selectByPrimaryKey(searchParam);
		//return this.selectOne(namespace+".listExpertView", searchParam);
		
		//return this.selectOne(searchParam);
		//return this.getSqlSession().selectOne(namespace+".listExpertView",searchParam);
		//return itemsManager;
//		List<ItemsManager> list=this.getSqlSession().selectList(namespace+".listExpertView",searchParam);
//		return list!=null&&!list.isEmpty()?list.get(0):null;
		
//		return this.selectByDoublePrimaryKey(namespace+".")
//	}
	
	
	
}