package com.hcis.ipanther.common.customers.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcis.ipanther.common.customers.dao.CustomersDao;
import com.hcis.ipanther.common.customers.entity.CustomersEmail;
import com.hcis.ipanther.common.customers.service.CustomersService;
import com.hcis.ipanther.common.login.vo.LoginUser;
import com.hcis.ipanther.common.user.dto.RegisterDto;
import com.hcis.ipanther.core.persistence.dao.MyBatisDao;
import com.hcis.ipanther.core.service.impl.mybatis.BaseServiceImpl;
import com.hcis.ipanther.core.utils.Identities;
import com.hcis.ipanther.core.web.vo.SearchParam;

@Service("customersService")

public class CustomersServiceImpl extends BaseServiceImpl<CustomersEmail> implements CustomersService {
	@Autowired
	private CustomersDao customersDao;

	@Override
	public void addCustomers(RegisterDto register, CustomersEmail Customers) {

		Customers.setId(Identities.uuid2());
		/*
		 * 模块上增加
		 */

		Customers.setUserName(register.getUserName());

		Customers.setUserEmail(register.getUnitEmail());
		Customers.setCompanyName(register.getUnitName());
		Customers.setMobilePhoe(register.getUnitPhone());
		Customers.setDefaultValue();

		customersDao.insert(Customers);

	}

	@Override
	public List<CustomersEmail> list(SearchParam searchParam) {
		Map<String, Object> paramMap = searchParam.getParamMap();
		LoginUser loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
		paramMap.put("userId", loginUser.getId());
		return customersDao.selectList(searchParam);
	}

	@Override
	public MyBatisDao getBaseDao() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateById(String ID) {

		return customersDao.updateById(ID);
	}

	@Override
	public List<CustomersEmail> selectById(String ID) {
		return customersDao.selectById(ID);

	}

	@Override
	public int updateByAll(CustomersEmail Customers) {
		SearchParam searchParam = new SearchParam();
		Map<String, Object> paramMap = searchParam.getParamMap();
		paramMap.put("userName", Customers.getUserName());
		paramMap.put("userEmail", Customers.getUserEmail());
		paramMap.put("companyName", Customers.getCompanyName());
		paramMap.put("mobilePhoe", Customers.getMobilePhoe());
		paramMap.put("ID", Customers.getId());
		return customersDao.updateByAll(Customers);

	}

	@Override
	public List<CustomersEmail> selectcontacts(SearchParam searchParam) {
		Map<String, Object> paramMap = searchParam.getParamMap();
		LoginUser loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
		paramMap.put("userId", loginUser.getId());
		return customersDao.selectcontacts(searchParam);
	}

}
