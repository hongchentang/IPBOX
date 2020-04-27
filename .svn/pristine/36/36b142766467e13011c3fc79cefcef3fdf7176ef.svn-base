package com.hcis.ipanther.common.dept.dao;

import java.util.List;

import com.hcis.ipanther.common.user.entity.UserDept;
import org.springframework.stereotype.Repository;

import com.hcis.ipanther.common.dept.entity.Department;
import com.hcis.ipanther.core.persistence.dao.MyBatisDao;

@Repository("departmentDao")
public class DepartmentDao extends MyBatisDao {
	
	/**
	 * 根据区域代码返回区域下的所有机构
	 * @param dept
	 * @return
	 */
	public List<Department> getDeptByRegionsCode(Department dept) {
		return this.selectForList(namespace+".getDeptByRegionsCode", dept);
	}
	
	/**
	 * 得到区域下的个人机构
	 * @param regionsCode
	 * @return
	 */
	public Department getVirtualDeptByRegionsCode(String regionsCode) {
		return (Department) this.selectForOneVO(namespace+".getVirtualDeptByRegionsCode", regionsCode);
	}

	/**
	 * 查询组织信息
	 * @param id
	 * @return
	 */
    public Department getPatentById(String id) {
    	return this.getSqlSession().selectOne( namespace + ".getPatentById", id);
    }

    public List<Department> getAllDeptByUnitId(String companyId) {
    	return this.getSqlSession().selectList(namespace + ".getAllDeptByUnitId", companyId);
    }

    public List<UserDept> getUserDeptMap(List<String> userIds) {
    	return this.getSqlSession().selectList(namespace + ".getUserDeptMap", userIds);
    }
}
