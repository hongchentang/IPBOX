package com.haoyu.ipanther.common.login.util;

import com.hcis.ipanther.core.security.shiro.ShiroUser;
import com.hcis.ipanther.core.utils.Identities;
import com.haoyu.ipanther.common.login.entity.UserLoginLog;
import eu.bitwalker.useragentutils.UserAgent;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class UserLoginLogUtil
{
  public static String getIpAddr(HttpServletRequest request)
  {
    String ip = request.getHeader("X-Real-IP");
    if ((ip == null) || (ip.length() == 0) || (" unknown ".equalsIgnoreCase(ip))) {
      ip = request.getHeader(" x-forwarded-for ");
    }
    if ((ip == null) || (ip.length() == 0) || (" unknown ".equalsIgnoreCase(ip))) {
      ip = request.getHeader(" Proxy-Client-IP ");
    }
    if ((ip == null) || (ip.length() == 0) || (" unknown ".equalsIgnoreCase(ip))) {
      ip = request.getHeader(" WL-Proxy-Client-IP ");
    }
    if ((ip == null) || (ip.length() == 0) || (" unknown ".equalsIgnoreCase(ip))) {
      ip = request.getRemoteAddr();
    }
    return ip;
  }

  public static UserLoginLog getUserLoginLog(HttpServletRequest request, ShiroUser shiroUser) {
    UserLoginLog ull = new UserLoginLog();
    ull.setId(Identities.uuid2());
    ull.setAccountId(shiroUser.getId());
    ull.setIpAddr(getIpAddr(request));
    ull.setLoginTime(new Date());
    ull.setSessionId(request.getSession().getId());
    UserAgent useragent = getUserAgent(request);
    ull.setUserAgent(useragent.toString() + "-" + useragent.getBrowserVersion());
    return ull;
  }

  /**
   * 获取客户端浏览器信息
   * @param request
   * @return
   */
  public static UserAgent getUserAgent(HttpServletRequest request) {
    UserAgent useragent = new UserAgent(request.getHeader("User-Agent"));    
    return useragent;
  }
  
  
  /**
   * 当前链接使用的协议 + "://" + 服务器地址 + ":" +端口号 + 应用名称，如果应用名称
   * @param request
   * @return
   */
  public static String getDomain(HttpServletRequest request) {
//      String domain = request.getScheme()
//              + "://" + request.getServerName()
//              + ":" + request.getServerPort()
//              + request.getContextPath()
//              + "/";
      String domain = request.getRequestURL().toString();
      return domain;
  }

}