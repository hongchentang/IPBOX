package com.haoyu.ipanther.common.login.service.impl;

import com.hcis.ipanther.core.security.shiro.ShiroUser;
import com.hcis.ipanther.core.web.vo.SearchParam;
import com.haoyu.ipanther.common.login.dao.IUserLoginLogDao;
import com.haoyu.ipanther.common.login.entity.UserLoginLog;
import com.haoyu.ipanther.common.login.service.IUserLoginLogService;
import com.haoyu.ipanther.common.login.util.UserLoginLogUtil;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Service;

@Service("userLoginLogService")
public class UserLoginLogServiceImpl
  implements IUserLoginLogService
{

  @Resource
  private IUserLoginLogDao userLoginLogDao;
  public static String USER_LOGIN_LOG_KEY = "userLoginLog";

  public int addUserLoginLog(UserLoginLog ull)
  {
    return this.userLoginLogDao.insert(ull);
  }

  public int updateUserLogoutTime(UserLoginLog ull)
  {
    ull.setLogoutTime(new Date());
    return this.userLoginLogDao.updateLogoutTime(ull);
  }

  public void logLogin(HttpServletRequest request, ShiroUser shiroUser)
  {
    UserLoginLog ull = UserLoginLogUtil.getUserLoginLog(request, shiroUser);
    request.getSession().setAttribute(USER_LOGIN_LOG_KEY, ull);
    //addUserLoginLog(ull);
  }

  public void logLogout(UserLoginLog ull)
  {
    ull.setLogoutTime(new Date());
    //updateUserLogoutTime(ull);
    this.userLoginLogDao.updateLogoutTime(ull);
  }
  
  public List<UserLoginLog> list(SearchParam searchParam)
  {
    return this.userLoginLogDao.list(searchParam);
  }
}