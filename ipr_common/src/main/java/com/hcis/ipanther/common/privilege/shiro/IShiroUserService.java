package com.hcis.ipanther.common.privilege.shiro;

import com.hcis.ipanther.core.security.shiro.ShiroUser;

public abstract interface IShiroUserService
{
  public abstract ShiroUser queryShiroUserByUserName(String paramString);

  public abstract void lockShiroUser(String paramString1, String paramString2);

  public abstract boolean isLock(String paramString);
}