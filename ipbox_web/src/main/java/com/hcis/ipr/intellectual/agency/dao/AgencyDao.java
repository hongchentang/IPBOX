package com.hcis.ipr.intellectual.agency.dao;

import com.hcis.ipanther.core.persistence.dao.MyBatisDao;
import com.hcis.ipr.intellectual.agency.entity.Agency;
import org.springframework.stereotype.Repository;

@Repository("AgencyDao")
public class AgencyDao extends MyBatisDao {

    public void deleteFaker(Agency agency) {
        this.getSqlSession().update(namespace + ".deleteFaker", agency);
    }
}
