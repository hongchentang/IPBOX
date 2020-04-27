package com.hcis.ipanther.common.dept.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.hcis.ipanther.common.dept.entity.UnitLegal;
import com.hcis.ipanther.core.persistence.dao.MyBatisDao;

@Repository("unitLegalDao")
public class UnitLegalDao extends MyBatisDao {
	
	/**
	 * 根据区域代码返回区域下的所有机构
	 * @param dept
	 * @return
	 */
	public List<UnitLegal> getDeptByRegionsCode(UnitLegal unitLegal) {
		return this.selectForList(namespace+".getDeptByRegionsCode", unitLegal);
	}
	
	/**
	 * 得到区域下的个人机构
	 * @param regionsCode
	 * @return
	 */
	public UnitLegal getVirtualDeptByRegionsCode(String regionsCode) {
		return (UnitLegal) this.selectForOneVO(namespace+".getVirtualDeptByRegionsCode", regionsCode);
	}
}
