package com.hcis.ipanther.common.security.dao;


import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.hcis.ipanther.common.security.entity.UserRole;
import com.hcis.ipanther.core.persistence.dao.MyBatisDao;
import com.hcis.ipanther.core.web.vo.SearchParam;

@Repository
public class UserRoleDao extends MyBatisDao {
	/**
	 * 根据用户ID删除用户与角色的对应关系
	 * @param userId
	 * @return
	 */
	public int deleteByUserId(String userId){
		return this.getSqlSession().delete(namespace+".deleteByUserId", userId);
	}
	/**
	 * 根据角色ID删除用户与角色的对应关系
	 * @param userId
	 * @return
	 */
	public int deleteByRoleId(String roleId){
		return this.getSqlSession().delete(namespace+".deleteByRoleId", roleId);
	}
	
	public int selectRoleExit(UserRole userRole) {
		return this.getSqlSession().selectOne(namespace+".selectRoleExit", userRole);
	}
	public List<Map> selectByRoleAndUser(Map map){
		return this.getSqlSession().selectList(namespace+".selectByRoleAndUser", map);
	}
	
	//根据版块查询对应关系
	public List<UserRole> selectByModule(SearchParam searchParam){
		return this.getSqlSession().selectList(namespace+".selectByModule", searchParam);
	}
}
