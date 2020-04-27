package com.hcis.ipanther.common.customers.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.hcis.ipanther.common.customers.entity.CustomersEmail;
import com.hcis.ipanther.core.persistence.dao.MyBatisDao;
import com.hcis.ipanther.core.web.vo.SearchParam;

@Repository("CustomersDao")
public class CustomersDao extends MyBatisDao {
	public List<CustomersEmail> selectList(SearchParam searchParam) {
		return this.getSqlSession().selectList(namespace + ".selectList", searchParam);
	}

	public int updateById(String ID) {

		return this.getSqlSession().update(namespace + ".updateById", ID);
	}

	public List<CustomersEmail> selectById(String ID) {
		return this.getSqlSession().selectList(namespace + ".selectById", ID);
	}

	public int updateByAll(CustomersEmail customersEmail) {

		return this.getSqlSession().update(namespace + ".updateByAll", customersEmail);
	}

	public List<CustomersEmail> selectcontacts(SearchParam searchParam) {
		return this.getSqlSession().selectList(namespace + ".selectcontacts", searchParam);
	}

}
