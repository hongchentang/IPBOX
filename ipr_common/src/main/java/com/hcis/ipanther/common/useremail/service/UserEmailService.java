package com.hcis.ipanther.common.useremail.service;

import java.util.List;

import com.hcis.ipanther.common.useremail.entity.UserEmail;
import com.hcis.ipanther.core.service.IBaseService;
import com.hcis.ipanther.core.web.vo.SearchParam;

public interface UserEmailService extends IBaseService<UserEmail> {

	/*
	 * void addCustomers(RegisterDto register, CustomersEmail Customers);
	 * 
	 * public List<CustomersEmail> list(SearchParam searchParam);
	 * 
	 * public List<CustomersEmail> selectById(String ID);
	 * 
	 * int updateById(String ID);
	 * 
	 * int updateByAll(CustomersEmail Customers);
	 */
	public List<UserEmail> list(SearchParam searchParam);

	void addUserEmail(UserEmail userEmail);

	int updateById(String ID);

	int updateByAll(UserEmail userEmail);

	public List<UserEmail> selectById(String ID);

}
