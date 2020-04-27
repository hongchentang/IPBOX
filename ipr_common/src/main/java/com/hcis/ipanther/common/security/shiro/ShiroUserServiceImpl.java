/**
 * 
 */
package com.hcis.ipanther.common.security.shiro;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hcis.ipanther.common.login.service.ILoginService;
import com.hcis.ipanther.common.privilege.shiro.IShiroUserService;
import com.hcis.ipanther.core.security.shiro.ShiroUser;

/**
 * @author Administrator
 *
 */
@Service("shiroUserService")
public class ShiroUserServiceImpl implements IShiroUserService {
	@Resource
	private ILoginService loginService;
	/* (non-Javadoc)
	 * @see com.hcis.ipanther.common.privilege.shiro.IShiroUserService#queryShiroUserByUserName(java.lang.String)
	 */
	@Override
	public ShiroUser queryShiroUserByUserName(String userName) {
		return loginService.getLoginUserByAccount(userName);
	}

	/* (non-Javadoc)
	 * @see com.hcis.ipanther.common.privilege.shiro.IShiroUserService#lockShiroUser(java.lang.String, java.lang.String)
	 */
	@Override
	public void lockShiroUser(String accountId, String updatedby) {
		

	}

	/* (non-Javadoc)
	 * @see com.hcis.ipanther.common.privilege.shiro.IShiroUserService#isLock(java.lang.String)
	 */
	@Override
	public boolean isLock(String state) {
		return state!=null&&state.equals("-1");
	}

}
