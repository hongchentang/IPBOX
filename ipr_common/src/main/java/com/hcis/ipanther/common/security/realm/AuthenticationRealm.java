/*************************************************
Copyright (C), 2012
Author:梁华璜 
Version: 
Date: 2013-8-21
Description: // 用于详细说明此程序文件完成的主要功能，与其他模块
// 或函数的接口，输出值、取值范围、含义及参数间的控
// 制、顺序、独立或依赖等关系
Function List: // 主要函数列表，每条记录应包括函数名及功能简要说明
1. ....
History: // 修改历史记录列表，每条修改记录应包括修改日期、修改
// 者及修改内容简述
1. Date:
Author:
Modification:
2. ...
*************************************************/
package com.hcis.ipanther.common.security.realm;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.AccountException;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.hcis.ipanther.common.login.vo.LoginUser;
import com.hcis.ipanther.common.privilege.config.PrivilegeConfig;
import com.hcis.ipanther.common.privilege.shiro.IShiroUserService;
import com.hcis.ipanther.common.security.entity.Role;
import com.hcis.ipanther.common.security.service.IUserRoleService;
import com.hcis.ipanther.common.user.entity.User;
import com.hcis.ipanther.common.user.utils.UserConstants;
import com.hcis.ipanther.core.security.shiro.ShiroUser;
import com.haoyu.ipanther.common.login.entity.UserLoginAttempt;
import com.haoyu.ipanther.common.login.service.IUserLoginAttemptService;

public class AuthenticationRealm extends AuthorizingRealm {
	
	private static final Logger logger=LoggerFactory.getLogger(AuthenticationRealm.class);
/*	
	@Autowired
	private ILoginService loginService;*/
	@Autowired
	private IUserRoleService userRoleService;
	
	
	@Resource
	protected IShiroUserService shiroUserService;
	@Resource
	protected IUserLoginAttemptService userLoginAttemptService;
	
/*	@Override
	public String getName(){
		return "appRealm";
	}*/

	/**
	 * 登陆验证
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken authenticationToken) throws AuthenticationException {
		UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
		if (StringUtils.isEmpty(token.getUsername())) {
			throw new AccountException(PrivilegeConfig.getProperty("IncorrectCredentials"));
		}
		ShiroUser user = shiroUserService.queryShiroUserByUserName(token.getUsername());
		if (user == null) {
			throw new UnknownAccountException(PrivilegeConfig.getProperty("IncorrectCredentials"));
		}else if(shiroUserService.isLock(user.getState())){
			throw new LockedAccountException(PrivilegeConfig.getProperty("LockedAccount"));
		}else if(null!=user.getState()&&UserConstants.USER_STATUS_NO_CONFIRM.equals(user.getState())) {
			throw new AccountException("用户未验证！");
		}else{
			UserLoginAttempt ula = null;
			if(userLoginAttemptService!=null){
				ula = new UserLoginAttempt(user.getId());
				if(userLoginAttemptService.excessiveAttempts(ula)){
					throw new ExcessiveAttemptsException(PrivilegeConfig.getProperty("ExcessiveAttempts"));
				}
			}
			if (user.getPassword().equals(new String(token.getPassword()))) {
				if(ula!=null&&ula.getLoginPeriodStartTime()!=null){
					userLoginAttemptService.clearAttempts(user.getId());
				}
				return new SimpleAuthenticationInfo(user,user.getPassword(), this.getName());
			} else {
				if(userLoginAttemptService!=null){
					userLoginAttemptService.incAttempts(user.getId());
					int maxAttempts =  userLoginAttemptService.getMaxAttempts();
					if(maxAttempts>0&&(ula.getLoginAttemptCount()+1)>=maxAttempts){
						shiroUserService.lockShiroUser(user.getId(), null);
						throw new ExcessiveAttemptsException(PrivilegeConfig.getProperty("ExcessiveAttempts"));
					}
					throw new IncorrectCredentialsException(PrivilegeConfig.getFormatProperty("IncorrectCredentialsAttempts",new String[]{String.valueOf(maxAttempts-(ula.getLoginAttemptCount()+1))}));
				}
				throw new IncorrectCredentialsException(PrivilegeConfig.getProperty("IncorrectCredentials"));
			}
		}
		
	}

	/**
	 * 授权验证
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
		LoginUser loginUser = (LoginUser) principalCollection.getPrimaryPrincipal();
/*		String userId = (String) principalCollection.fromRealm(this.getName())
				.iterator().next();*/
		User user=new User();
		user.setId(loginUser.getId());
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		List<Role> roleList= userRoleService.selectRoleByUser(user);
		for (Role role : roleList) {
			// 基于Role的权限信息
			info.addRole(role.getRoleCode());
			// 基于Permission的权限信息
//			info.addStringPermissions(role.getResourceIdList());
		}
		return info;
	}
	
/*	*//**
	 * 设定Password校验的Hash算法与迭代次数.
	 *//*
	@PostConstruct
	public void initCredentialsMatcher() {
		HashedCredentialsMatcher matcher = new HashedCredentialsMatcher(Md5Hash.ALGORITHM_NAME);
		// matcher.setHashIterations(AccountService.HASH_INTERATIONS);
		setCredentialsMatcher(matcher);
	}*/

/*	public void setLoginService(ILoginService loginService) {
		this.loginService = loginService;
	}*/

	public void setUserRoleService(IUserRoleService userRoleService) {
		this.userRoleService = userRoleService;
	}
}
