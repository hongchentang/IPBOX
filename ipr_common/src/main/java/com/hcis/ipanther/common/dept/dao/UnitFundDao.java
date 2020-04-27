package com.hcis.ipanther.common.dept.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.hcis.ipanther.common.dept.entity.UnitFund;
import com.hcis.ipanther.core.persistence.dao.MyBatisDao;

@Repository("unitFundDao")
public class UnitFundDao extends MyBatisDao {
	
	/**
	 * 根据区域代码返回区域下的所有机构
	 * @param dept
	 * @return
	 */
	public List<UnitFund> getDeptByRegionsCode(UnitFund unitFund) {
		return this.selectForList(namespace+".getDeptByRegionsCode", unitFund);
	}
	
	/**
	 * 得到区域下的个人机构
	 * @param regionsCode
	 * @return
	 */
	public UnitFund getVirtualDeptByRegionsCode(String regionsCode) {
		return (UnitFund) this.selectForOneVO(namespace+".getVirtualDeptByRegionsCode", regionsCode);
	}
}
