package com.hcis.ipanther.common.security.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.hcis.ipanther.core.persistence.dao.MyBatisDao;
import com.hcis.ipanther.core.web.vo.SearchParam;

@Repository
public class RolePriDao extends MyBatisDao {

	/**
	 * 根据角色ID删除对应关系
	 * @param roleId
	 * @return
	 */
	public int deleteByRoleId(String roleId){
		return this.getSqlSession().delete(this.namespace+".deleteByRoleId", roleId);
	}
	/**
	 * 根据角色，版块ID删除对应关系
	 */
	public int deleteByModelIdAndRoleId(SearchParam searchParam){
		return this.getSqlSession().delete(this.namespace+".deleteByModelIdAndRoleId", searchParam);
	}
	/**
	 * 根据功能ID删除对应关系
	 * @param priId
	 * @return
	 */
	public int deleteByPriId(String priId){
		return this.getSqlSession().delete(this.namespace+".deleteByPriId", priId);
	}
	//递归
	public Object selectByPriId(Map map){
		return this.selectOne(namespace+".selectByPriId", map);
	}

	public void deleteBatch(List<String> privilegeIds) {
		this.getSqlSession().delete(namespace + ".deleteBatch", privilegeIds);
	}
}
