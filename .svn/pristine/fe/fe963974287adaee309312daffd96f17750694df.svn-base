package com.hcis.ipanther.common.customers.service;

import java.util.List;

import com.hcis.ipanther.common.customers.entity.CustomersEmail;
import com.hcis.ipanther.common.user.dto.RegisterDto;
import com.hcis.ipanther.core.service.IBaseService;
import com.hcis.ipanther.core.web.vo.SearchParam;

public interface CustomersService extends IBaseService<CustomersEmail> {

	void addCustomers(RegisterDto register, CustomersEmail Customers);

	public List<CustomersEmail> list(SearchParam searchParam);

	public List<CustomersEmail> selectById(String ID);

	int updateById(String ID);

	int updateByAll(CustomersEmail Customers);

	public List<CustomersEmail> selectcontacts(SearchParam searchParam);

}
