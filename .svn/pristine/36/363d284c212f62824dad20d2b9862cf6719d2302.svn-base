package com.hcis.ipr.intellectual.call.service;

import java.util.List;

import com.hcis.ipanther.core.service.IBaseService;
import com.hcis.ipr.intellectual.call.entity.PatentApplyer;
import com.hcis.ipr.intellectual.call.entity.PatentCost;
import com.hcis.ipr.intellectual.call.entity.PatentInvent;
import com.hcis.ipr.intellectual.call.entity.PatentIpic;
import com.hcis.ipr.intellectual.call.entity.PatentType;

public interface ProcedureService extends IBaseService<PatentType> {

	List<PatentType> getPatentType(String ID, String deptIds, String searchUserId);

	List<PatentCost> getPatentCost(String companyId);

	List<PatentInvent> getPatentInvent(String ID);

	List<PatentApplyer> getPatentApplyer(String ID);

	List<PatentIpic> getPatentIpic(String companId);

	List<PatentApplyer> getPatentDept(String ID);

	List<String> getindexCost(String ID, String deptIds, String searchUserId);
}
