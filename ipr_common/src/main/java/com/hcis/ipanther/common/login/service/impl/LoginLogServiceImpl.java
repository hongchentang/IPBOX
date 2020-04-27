package com.hcis.ipanther.common.login.service.impl;

import java.util.Calendar;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcis.ipanther.common.login.dao.LoginLogDao;
import com.hcis.ipanther.common.login.service.ILoginLogService;
import com.hcis.ipanther.common.login.vo.LoginLog;
import com.hcis.ipanther.core.utils.Identities;
import com.hcis.ipanther.core.web.vo.SearchParam;

/**
 * @author Chaos
 * @date 2013-3-21
 *
 */
@Service("loginLogService")
public class LoginLogServiceImpl implements ILoginLogService {

	@Autowired
	private LoginLogDao loginLogDao;
	
	@Override
	public LoginLog getLoginLog(String id){
		return (LoginLog) loginLogDao.selectByPrimaryKey(id);
	}

	@Override
	public int addLoginLog(LoginLog loginLog){
		if(StringUtils.isEmpty(loginLog.getId())){
			loginLog.setId(Identities.uuid2());
		}
		loginLog.setDefaultValue();
		loginLog.setUpdateTime(null);
		return this.loginLogDao.insertSelective(loginLog);
	}
	
	@Override
	public int updateLoginLog(LoginLog loginLog){
		loginLog.setUpdateTime(Calendar.getInstance().getTime());
		return this.loginLogDao.updateByPrimaryKeySelective(loginLog);
	}
	
	@Override
	public int deleteByLogout(LoginLog loginLog){
		loginLog.setUpdateTime(Calendar.getInstance().getTime());
		return this.loginLogDao.deleteByLogout(loginLog);
	}
	
	@Override
	public int deleteLoginLog(LoginLog loginLog){
		loginLog.setUpdateTime(Calendar.getInstance().getTime());
		return this.loginLogDao.deleteByLogic(loginLog);
	}
	
	@Override
	public int deleteLoginLogBySearchParam(SearchParam searchParam){
		return this.loginLogDao.deleteBySearchParam(searchParam);
	}
	
	@Override
	public List<LoginLog> listLoginLog(SearchParam searchParam){
		return this.loginLogDao.selectBySearchParam(searchParam);
	}

	@Override
	public List<LoginLog> checkLoginLog(String paperworkNo,String sessionId){
		SearchParam searchParam=new SearchParam();
		searchParam.getParamMap().put("paperworkNo",new String[]{paperworkNo});
		searchParam.getParamMap().put("sessionId",new String[]{sessionId});
		searchParam.getParamMap().put("isDeleted",new String[]{"N"});
		return this.loginLogDao.selectBySearchParam(searchParam);
	}

	public LoginLogDao getLoginLogDao() {
		return loginLogDao;
	}

	public void setLoginLogDao(LoginLogDao loginLogDao) {
		this.loginLogDao = loginLogDao;
	}

}
