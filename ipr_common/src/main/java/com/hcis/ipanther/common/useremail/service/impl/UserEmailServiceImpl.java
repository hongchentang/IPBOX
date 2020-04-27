package com.hcis.ipanther.common.useremail.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcis.ipanther.common.login.vo.LoginUser;
import com.hcis.ipanther.common.useremail.dao.UserEmailDao;
import com.hcis.ipanther.common.useremail.entity.UserEmail;
import com.hcis.ipanther.common.useremail.service.UserEmailService;
import com.hcis.ipanther.core.persistence.dao.MyBatisDao;
import com.hcis.ipanther.core.service.impl.mybatis.BaseServiceImpl;
import com.hcis.ipanther.core.utils.Identities;
import com.hcis.ipanther.core.web.vo.SearchParam;

@Service("useremailService")
public class UserEmailServiceImpl extends BaseServiceImpl<UserEmail> implements UserEmailService {
	@Autowired
	private UserEmailDao userEmailDao;

	@Override
	public MyBatisDao getBaseDao() {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * @Autowired private CustomersDao customersDao;
	 * 
	 * @Override public void addCustomers(RegisterDto register, CustomersEmail
	 * Customers) {
	 * 
	 * Customers.setId(Identities.uuid2());
	 * 
	 * 模块上增加
	 * 
	 * 
	 * Customers.setUserName(register.getUserName());
	 * 
	 * Customers.setUserEmail(register.getUnitEmail());
	 * Customers.setCompanyName(register.getUnitName());
	 * Customers.setMobilePhoe(register.getUnitPhone());
	 * Customers.setDefaultValue();
	 * 
	 * customersDao.insert(Customers);
	 * 
	 * }
	 * 
	 * @Override public List<CustomersEmail> list(SearchParam searchParam) {
	 * Map<String, Object> paramMap = searchParam.getParamMap(); LoginUser
	 * loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
	 * 
	 * 其它只能管理自己单位的 系统管理员、超级管理员可以管理所有
	 * 
	 * 
	 * if (!SecurityRoleUtils.isSystemAdmin(loginUser.getId())) {
	 * paramMap.put("deptId", loginUser.getDeptId());
	 * paramMap.put("isSystemAdmin", false);// 标识不是管理，页面上判断用 } else {
	 * paramMap.put("isSystemAdmin", true);// 标识不是管理，页面上判断用 } if
	 * (!UserConstants.USER_ROLECODE_SUPER_ADMIN.equals(loginUser.
	 * getRoleCode())) {// 非超级管理 searchParam.getParamMap().put("notRoleCode",
	 * "0");// 列出不包括rolecode=0的超级管理员 }
	 * 
	 * 
	 * return customersDao.selectList(searchParam); }
	 * 
	 * @Override public MyBatisDao getBaseDao() { // TODO Auto-generated method
	 * stub return null; }
	 * 
	 * @Override public int updateById(String ID) {
	 * 
	 * return customersDao.updateById(ID); }
	 * 
	 * @Override public List<CustomersEmail> selectById(String ID) { return
	 * customersDao.selectById(ID);
	 * 
	 * }
	 * 
	 * @Override public int updateByAll(CustomersEmail Customers) { SearchParam
	 * searchParam = new SearchParam(); Map<String, Object> paramMap =
	 * searchParam.getParamMap(); paramMap.put("userName",
	 * Customers.getUserName()); paramMap.put("userEmail",
	 * Customers.getUserEmail()); paramMap.put("companyName",
	 * Customers.getCompanyName()); paramMap.put("mobilePhoe",
	 * Customers.getMobilePhoe()); paramMap.put("ID", Customers.getId()); return
	 * customersDao.updateByAll(Customers);
	 * 
	 * }
	 */
	@Override
	public List<UserEmail> list(SearchParam searchParam) {
		Map<String, Object> paramMap = searchParam.getParamMap();
		LoginUser loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();

		return userEmailDao.selectList(searchParam);
	}

	@Override
	public void addUserEmail(UserEmail userEmail) {
		// TODO Auto-generated method stub

		userEmail.setId(Identities.uuid2());

		userEmailDao.insert(userEmail);

	}

	@Override
	public int updateById(String ID) {
		return userEmailDao.updateById(ID);

	}

	@Override
	public int updateByAll(UserEmail userEmail) {
		return userEmailDao.updateByAll(userEmail);
	}

	@Override
	public List<UserEmail> selectById(String ID) {
		return userEmailDao.selectById(ID);
	}
}
