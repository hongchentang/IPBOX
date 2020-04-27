package com.hcis.ipanther.common.dept.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.hcis.ipanther.common.dept.entity.UnitBrand;
import com.hcis.ipanther.core.persistence.dao.MyBatisDao;

@Repository("unitBrandDao")
public class UnitBrandDao extends MyBatisDao {
	
	/**
	 * 根据区域代码返回区域下的所有机构
	 * @param dept
	 * @return
	 */
	public List<UnitBrand> getDeptByRegionsCode(UnitBrand unitBrand) {
		return this.selectForList(namespace+".getDeptByRegionsCode", unitBrand);
	}
	
	/**
	 * 得到区域下的个人机构
	 * @param regionsCode
	 * @return
	 */
	public UnitBrand getVirtualDeptByRegionsCode(String regionsCode) {
		return (UnitBrand) this.selectForOneVO(namespace+".getVirtualDeptByRegionsCode", regionsCode);
	}
}
