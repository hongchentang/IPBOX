package com.haoyu.ipanther.common.login.service;

import com.hcis.ipanther.core.security.shiro.ShiroUser;
import com.hcis.ipanther.core.web.vo.SearchParam;
import com.haoyu.ipanther.common.login.entity.UserLoginLog;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

public abstract interface IUserLoginLogService
{
  public abstract int addUserLoginLog(UserLoginLog paramUserLoginLog);

  public abstract int updateUserLogoutTime(UserLoginLog paramUserLoginLog);

  public abstract void logLogin(HttpServletRequest paramHttpServletRequest, ShiroUser paramShiroUser);

  public abstract void logLogout(UserLoginLog paramUserLoginLog);
  
  public abstract List<UserLoginLog> list(SearchParam searchParam);
}