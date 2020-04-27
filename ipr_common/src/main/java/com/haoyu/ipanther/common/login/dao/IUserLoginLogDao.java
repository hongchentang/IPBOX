package com.haoyu.ipanther.common.login.dao;

import java.util.List;

import com.haoyu.ipanther.common.login.entity.UserLoginLog;
import com.hcis.ipanther.core.web.vo.SearchParam;

public abstract interface IUserLoginLogDao
{
  public abstract int insert(UserLoginLog paramUserLoginLog);

  public abstract int updateLogoutTime(UserLoginLog paramUserLoginLog);
  
  public abstract List<UserLoginLog> list(SearchParam searchParam);
  
}