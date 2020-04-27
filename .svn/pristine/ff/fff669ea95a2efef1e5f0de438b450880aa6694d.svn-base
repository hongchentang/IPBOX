package com.hcis.ipr.intellectual.call.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hcis.ipanther.core.persistence.dao.MyBatisDao;
import com.hcis.ipanther.core.service.impl.mybatis.BaseServiceImpl;
import com.hcis.ipr.intellectual.call.dao.ProcedureDao;
import com.hcis.ipr.intellectual.call.entity.PatentApplyer;
import com.hcis.ipr.intellectual.call.entity.PatentCost;
import com.hcis.ipr.intellectual.call.entity.PatentInvent;
import com.hcis.ipr.intellectual.call.entity.PatentIpic;
import com.hcis.ipr.intellectual.call.entity.PatentType;
import com.hcis.ipr.intellectual.call.service.ProcedureService;

@Service("procedureService")
public class ProcedureServiceImpl extends BaseServiceImpl<PatentType> implements ProcedureService {
	@Autowired
	private ProcedureDao procedureDao;

	@Override
	public MyBatisDao getBaseDao() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional(readOnly = false)
	public List<PatentType> getPatentType(String ID, String deptIds, String searchUserId) {
		return procedureDao.getPatentType(ID, deptIds, searchUserId);

	}

	@Override
	@Transactional(readOnly = false)
	public List<PatentCost> getPatentCost(String companyId) {

		return procedureDao.getPatentCost(companyId);

	}

	@Override
	public List<PatentInvent> getPatentInvent(String ID) {
		Map<String, String> map = new HashMap<String, String>();
		List<PatentInvent> listMap = procedureDao.getPatentInvent(ID);

		return listMap;
	}

	@Override
	public List<PatentApplyer> getPatentApplyer(String ID) {
		// TODO Auto-generated method stub
		Map<String, String> map = new HashMap<String, String>();
		List<PatentApplyer> listMap = procedureDao.getPatentApplyer(ID);

		return listMap;
	}

	@Override
	public List<PatentIpic> getPatentIpic(String companId) {
		// TODO Auto-generated method stub
		return procedureDao.getPatentIpic(companId);
	}

	@Override
	public List<PatentApplyer> getPatentDept(String ID) {
		// TODO Auto-generated method stub
		return procedureDao.getPatentDept(ID);
	}

	@Override
	public List<String> getindexCost(String ID, String deptIds, String searchUserId) {
		// TODO Auto-generated method stub
		return procedureDao.getindexCost(ID, deptIds, searchUserId);
	}

}
