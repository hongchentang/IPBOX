/**
 * 
 */
package com.hcis.ipanther.common.user.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.hcis.ipanther.common.user.entity.UserDept;
import com.hcis.ipanther.core.persistence.dao.MyBatisDao;

/**
 * @author Chaos
 * @date 2013-3-12
 * @time 下午6:02:55
 *
 */
@Repository
public class UserDeptDao extends MyBatisDao {
	
	/**
	 * 按用户ID读取关联（非标记删除的数据）
	 * @param userId
	 * @return
	 */
	public List<UserDept> selectByUserId(String userId){
		return this.getSqlSession().selectList(namespace+".selectByUserId", userId);
	}
	
	/**
	 * 按用户ID读取关联（全部数据）
	 * @param userId
	 * @return
	 */
	public List<UserDept> selectAllByUserId(String userId){
		return this.getSqlSession().selectList(namespace+".selectAllByUserId", userId);
	}
	
	/**
	 * 按机构ID读取关联（非标记删除的数据）
	 * @param deptId
	 * @return
	 */
	public List<UserDept> selectByDeptId(String deptId){
		return this.getSqlSession().selectList(namespace+".selectByDeptId", deptId);
	}
	
	/**
	 * 按机构ID读取关联（全部数据）
	 * @param deptId
	 * @return
	 */
	public List<UserDept> selectAllByDeptId(String deptId){
		return this.getSqlSession().selectList(namespace+".selectAllByDeptId", deptId);
	}
	
	/**
	 * 按用户ID标记删除关联
	 * @param userDept
	 * @return
	 */
	public int deleteByUserIdLogic(UserDept userDept){
		return this.getSqlSession().update(namespace+".deleteByUserIdLogic", userDept);
	}
	
	/**
	 * 按机构ID标记删除关联
	 * @param userDept
	 * @return
	 */
	public int deleteByDeptIdLogic(UserDept userDept){
		return this.getSqlSession().update(namespace+".deleteByDeptIdLogic", userDept);
	}

	public int deleteByUserIdPhysic(UserDept userDept){
		return this.getSqlSession().update(namespace+".deleteByUserIdPhysic", userDept);
	}
	public int deleteByDeptIdPhysic(UserDept userDept){
		return this.getSqlSession().update(namespace+".deleteByDeptIdPhysic", userDept);
	}
	
	public int updateByUserDeptId(UserDept userDept) {
		return this.update(namespace+".updateByUserDeptId", userDept);
	}

    public List<String> getUserDeptIds(String id) {
    	return this.getSqlSession().selectList(namespace + ".getUserDeptIds", id);
	}

	public void deleteByUserId(String userId) {
		this.getSqlSession().delete(namespace + ".deleteByUserId", userId);
	}

	public List<String> getDeptIdsByUserId(String userId) {
		return this.getSqlSession().selectList(namespace + ".getDeptIdsByUserId", userId);
	}

    public List<UserDept> getUsersDept(List<String> ids) {
    	return this.getSqlSession().selectList(this.namespace + ".getUsersDept", ids);
	}
}
