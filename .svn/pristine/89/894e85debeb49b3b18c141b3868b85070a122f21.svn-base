package com.hcis.ipanther.common.privilege.shiro;

import com.hcis.ipanther.common.privilege.config.PrivilegeConfig;
import com.hcis.ipanther.common.privilege.entity.Permission;
import com.hcis.ipanther.common.privilege.entity.Role;
import com.hcis.ipanther.common.privilege.service.IPrivilegeService;
import com.hcis.ipanther.common.privilege.service.IUserRoleService;
import com.hcis.ipanther.core.security.shiro.ShiroUser;
import com.haoyu.ipanther.common.login.entity.UserLoginAttempt;
import com.haoyu.ipanther.common.login.service.IUserLoginAttemptService;
import java.util.Collection;
import java.util.Iterator;
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
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

public class AuthenticationRealm extends AuthorizingRealm
{
  private IPrivilegeService privilegeService;

  @Resource
  protected IShiroUserService shiroUserService;

  @Resource
  private IUserRoleService userRoleService;

  @Resource
  protected IUserLoginAttemptService userLoginAttemptService;

  protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals)
  {
    if (principals == null) {
      throw new AuthorizationException("principals should not be null");
    }
    SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
    String userId = 
      (String)principals.fromRealm(getName())
      .iterator().next();

    List<Role> roles = this.userRoleService.queryRoleByUserId(userId);
    for (Role role : roles) {
      info.addRole(role.getCode());
    }

    List<Permission> permissions = this.privilegeService.queryAllPermissionByUserId(userId);
    for (Permission p : permissions) {
      info.addStringPermission(p.getCode());
    }
    return info;
  }

  protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken)
    throws AuthenticationException
  {
    UsernamePasswordToken token = (UsernamePasswordToken)authenticationToken;
    if (StringUtils.isEmpty(token.getUsername())) {
      throw new AccountException(PrivilegeConfig.getProperty("IncorrectCredentials"));
    }
    ShiroUser user = this.shiroUserService.queryShiroUserByUserName(token.getUsername());
    if (user == null)
      throw new UnknownAccountException(PrivilegeConfig.getProperty("IncorrectCredentials"));
    if (this.shiroUserService.isLock(user.getState())) {
      throw new LockedAccountException(PrivilegeConfig.getProperty("LockedAccount"));
    }
    UserLoginAttempt ula = null;
    if (this.userLoginAttemptService != null) {
      ula = new UserLoginAttempt(user.getId());
      if (this.userLoginAttemptService.excessiveAttempts(ula)) {
        throw new ExcessiveAttemptsException(PrivilegeConfig.getProperty("ExcessiveAttempts"));
      }
    }
    if (user.getPassword().equals(new String(token.getPassword()))) {
      if ((ula != null) && (ula.getLoginPeriodStartTime() != null)) {
        this.userLoginAttemptService.clearAttempts(user.getId());
      }
      return new SimpleAuthenticationInfo(user.getId(), user.getPassword(), getName());
    }
    if (this.userLoginAttemptService != null) {
      this.userLoginAttemptService.incAttempts(user.getId());
      int maxAttempts = this.userLoginAttemptService.getMaxAttempts();
      if ((maxAttempts > 0) && (ula.getLoginAttemptCount() + 1 >= maxAttempts)) {
        this.shiroUserService.lockShiroUser(user.getId(), null);
        throw new ExcessiveAttemptsException(PrivilegeConfig.getProperty("ExcessiveAttempts"));
      }
      throw new IncorrectCredentialsException(PrivilegeConfig.getFormatProperty("IncorrectCredentialsAttempts", new String[] { ObjectUtils.toString(Integer.valueOf(maxAttempts - (ula.getLoginAttemptCount() + 1))) }));
    }
    throw new IncorrectCredentialsException(PrivilegeConfig.getProperty("IncorrectCredentials"));
  }
}