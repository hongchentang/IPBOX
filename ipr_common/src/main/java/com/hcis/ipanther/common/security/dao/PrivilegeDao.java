package com.hcis.ipanther.common.security.dao;


import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.hcis.ipanther.common.security.entity.Privilege;
import com.hcis.ipanther.core.persistence.dao.MyBatisDao;
import com.hcis.ipanther.core.web.vo.SearchParam;

@Repository
public class PrivilegeDao extends MyBatisDao {
	
	//根据用户拥有角色查询相应的功能菜单
	public List<Privilege> selectByUserId(SearchParam searchParam){
		return this.getSqlSession().selectList(namespace+".selectByUserId", searchParam);
	}
	
	//检测名字是否重复
	public String selectCountByName(SearchParam searchParam){
		return (String) this.selectOne(namespace+".selectCountByName", searchParam);
	}
	
	public List<Privilege> selectAllPrivilegeRole(){
		return this.getSqlSession().selectList(namespace+".selectAllPrivilegeRole");
	}

	public List<Map<String, Object>> selectListPrivilege(Map<String, Object> map) {
		return this.getSqlSession().selectList(namespace+".selectListPrivilege",map);
	}
	
	public List<Map<String, Object>> selectListRolePri(Map<String, Object> map) {
		return this.getSqlSession().selectList(this.namespace+".selectListRolePri", map);
	}

    public List<String> getChild(String privilegeId) {
    	return this.getSqlSession().selectList(namespace + ".getChild", privilegeId);
	}

	public int deleteBatch(List<String> list) {
		return this.getSqlSession().delete(namespace + ".deleteBatch", list);
	}
}
