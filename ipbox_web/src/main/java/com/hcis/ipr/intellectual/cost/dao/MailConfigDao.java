package com.hcis.ipr.intellectual.cost.dao;

import com.hcis.ipanther.core.persistence.dao.MyBatisDao;
import com.hcis.ipr.intellectual.cost.entity.MailConfig;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

/**
 * @author zhw
 * @date 2019/9/23
 **/
@Repository("mailConfigDao")
public class MailConfigDao extends MyBatisDao {
    public MailConfig getMail() {
        return this.getSqlSession().selectOne(namespace + ".getOne");
    }

}
