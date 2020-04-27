package com.hcis.ipanther.common.dept.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.hcis.ipanther.common.dept.entity.UnitQualification;
import com.hcis.ipanther.core.persistence.dao.MyBatisDao;

@Repository("unitQualificationDao")
public class UnitQualificationDao extends MyBatisDao {
	
	/**
	 * 根据区域代码返回区域下的所有机构
	 * @param dept
	 * @return
	 */
	public List<UnitQualification> getDeptByRegionsCode(UnitQualification unitQualification) {
		return this.selectForList(namespace+".getDeptByRegionsCode", unitQualification);
	}
	
	/**
	 * 得到区域下的个人机构
	 * @param regionsCode
	 * @return
	 */
	public UnitQualification getVirtualDeptByRegionsCode(String regionsCode) {
		return (UnitQualification) this.selectForOneVO(namespace+".getVirtualDeptByRegionsCode", regionsCode);
	}
}
