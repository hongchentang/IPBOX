package com.hcis.ipr.intellectual.call.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.hcis.ipanther.core.persistence.dao.MyBatisDao;
import com.hcis.ipr.intellectual.call.entity.PatentApplyer;
import com.hcis.ipr.intellectual.call.entity.PatentCost;
import com.hcis.ipr.intellectual.call.entity.PatentInvent;
import com.hcis.ipr.intellectual.call.entity.PatentIpic;
import com.hcis.ipr.intellectual.call.entity.PatentType;

@Repository("procedurDao")

public class ProcedureDao extends MyBatisDao {
	public List<PatentType> getPatentType(String ID, String deptIds, String searchUserId) {
		List<PatentType> patent = null;

		if (searchUserId == null) {
			patent = this.getSqlSession().selectList(namespace + ".getPatentType", ID);
		} else {
			patent = this.getSqlSession().selectList(namespace + ".getPatentTypekids", searchUserId);

		}

		return patent;

	}

	public List<PatentType> getPatentTypeList(String ID) {

		List<PatentType> patent = this.getSqlSession().selectList(namespace + ".getPatentTypeList", ID);

		return patent;

	}

	public List<PatentCost> getPatentCost(String companyId) {

		List<PatentCost> patent = this.getSqlSession().selectList(namespace + ".getPatentCost", companyId);

		return patent;

	}

	public List<PatentInvent> getPatentInvent(String ID) {
		return this.getSqlSession().selectList(namespace + ".getPatentInvent", ID);

	}

	public List<PatentApplyer> getPatentApplyer(String ID) {
		return this.getSqlSession().selectList(namespace + ".getPatentApplyer", ID);

	}

	public List<PatentIpic> getPatentIpic(String companyId) {
		List<PatentIpic> list = this.getSqlSession().selectList(namespace + ".getPatentIpic", companyId);
		return list;

	}

	public List<PatentApplyer> getPatentDept(String ID) {
		return this.getSqlSession().selectList(namespace + ".getPatentDept", ID);

	}

	public List<String> getindexCost(String ID, String deptIds, String searchUserId) {
		List<String> list = null;
		if (searchUserId == null) {
			list = this.getSqlSession().selectList(namespace + ".getindexCost", ID);
		} else {
			list = this.getSqlSession().selectList(namespace + ".getindexCostkids", searchUserId);

		}

		return list;

	}
}
