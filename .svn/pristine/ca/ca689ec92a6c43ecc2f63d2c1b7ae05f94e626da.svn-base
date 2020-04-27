package com.hcis.ipr.intellectual.trademark.dao;

import com.hcis.ipanther.core.persistence.dao.MyBatisDao;
import com.hcis.ipanther.core.web.vo.SearchParam;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository("trademarkDao")
public class TrademarkDao extends MyBatisDao {

    public void deleteByPrimaryKeys(List<String> patentIds) {
		this.selectForList(namespace+".deleteByPrimaryKeys", patentIds);
    }
}