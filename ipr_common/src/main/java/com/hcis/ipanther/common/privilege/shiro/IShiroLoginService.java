package com.hcis.ipanther.common.privilege.shiro;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;

public abstract interface IShiroLoginService
{
  public abstract boolean onLoginSuccess(UsernamePasswordToken paramUsernamePasswordToken, ServletRequest paramServletRequest, ServletResponse paramServletResponse);

  public abstract boolean onLoginFailure(UsernamePasswordToken paramUsernamePasswordToken, AuthenticationException paramAuthenticationException, ServletRequest paramServletRequest, ServletResponse paramServletResponse);
}