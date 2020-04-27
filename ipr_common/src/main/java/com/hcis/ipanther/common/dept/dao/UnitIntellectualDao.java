package com.hcis.ipanther.common.dept.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.hcis.ipanther.common.dept.entity.UnitIntellectual;
import com.hcis.ipanther.core.persistence.dao.MyBatisDao;

@Repository("unitIntellectualDao")
public class UnitIntellectualDao extends MyBatisDao {
	
	/**
	 * 根据区域代码返回区域下的所有机构
	 * @param dept
	 * @return
	 */
	public List<UnitIntellectual> getDeptByRegionsCode(UnitIntellectual unitIntellectual) {
		return this.selectForList(namespace+".getDeptByRegionsCode", unitIntellectual);
	}
	
	/**
	 * 得到区域下的个人机构
	 * @param regionsCode
	 * @return
	 */
	public UnitIntellectual getVirtualDeptByRegionsCode(String regionsCode) {
		return (UnitIntellectual) this.selectForOneVO(namespace+".getVirtualDeptByRegionsCode", regionsCode);
	}
}
