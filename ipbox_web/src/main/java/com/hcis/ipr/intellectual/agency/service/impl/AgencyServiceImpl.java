package com.hcis.ipr.intellectual.agency.service.impl;

import com.hcis.ipanther.core.persistence.dao.MyBatisDao;
import com.hcis.ipanther.core.service.impl.mybatis.BaseServiceImpl;
import com.hcis.ipr.intellectual.agency.dao.AgencyDao;
import com.hcis.ipr.intellectual.agency.entity.Agency;
import com.hcis.ipr.intellectual.agency.service.AgencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author zhw
 */
@Service("AgencyService")
public class AgencyServiceImpl extends BaseServiceImpl<Agency> implements AgencyService {

	@Autowired
	private AgencyDao agencyDao;

	@Override
	public MyBatisDao getBaseDao() {
		return agencyDao;
	}

	@Override
	public void insert(Agency agency) {
		agency.setDefaultValue();
		agencyDao.insert(agency);
	}

	@Override
	public void deleteById(String id, String userId) {
		Agency agency = new Agency();
		agency.setId(id);
		agency.setUpdatedby(userId);
		agencyDao.deleteFaker(agency);
	}

    @Override
    public void updateAgency(Agency agency, String userId) {
        agency.setUpdatedby(userId);
        agency.setUpdateTime(new Date());

        agencyDao.updateByPrimaryKey(agency);
	}
}
