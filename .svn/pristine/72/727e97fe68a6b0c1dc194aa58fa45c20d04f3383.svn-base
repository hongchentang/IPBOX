package com.hcis.ipanther.common.security.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.hcis.ipanther.common.security.entity.Role;
import com.hcis.ipanther.common.user.entity.User;
import com.hcis.ipanther.core.persistence.dao.MyBatisDao;
import com.hcis.ipanther.core.web.vo.SearchParam;

@Repository
public class RoleDao extends MyBatisDao {
	
	/**
	 * 根据用户ID查询拥有的角色
	 * @param user
	 * @return
	 */
	public List<Role> selectByUser(User user){
		return this.getSqlSession().selectList(namespace+".selectByUser", user);
	}
	
	/**
	 * 根据名字查询总数
	 */
	public String selectCountByName(SearchParam searchParam){
		return (String) this.selectOne(namespace+".selectCountByName", searchParam);
	}
	
	/**
	 * Select by map.
	 *
	 * @param map the map
	 * @return the list
	 */
	public List<Role> selectByMap(Map<String,Object> map){
		return this.getSqlSession().selectList(namespace+".selectByMap", map);
	}
	
	//根据角色查询所有的功能权限
	public List<Role> selectAllRolePrivilege(){
		return this.getSqlSession().selectList(namespace+".selectAllRolePrivilege");
	}

	public List<Map<String, Object>> selectRolesByUser(User user) {
		return this.getSqlSession().selectList(namespace+".selectRolesByUser",user);
	}
	
	/**
	 * 根据用户ID和角色编码查询是否存在记录
	 * @param args key包括：roleCode和userId
	 * @return
	 */
	public int countByUserId(Map<String,Object> args) {
		return this.selectForInt(namespace+".countByUserId", args);
	}
}
