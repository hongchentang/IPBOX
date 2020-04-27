package com.haoyu.ipanther.common.login.listener;

import com.hcis.ipanther.common.login.vo.LoginUser;
import com.hcis.ipanther.core.utils.BeanLocator;
import com.haoyu.ipanther.common.login.dao.impl.mybatis.UserLoginLogDao;
import com.haoyu.ipanther.common.login.entity.UserLoginLog;
import org.apache.shiro.SecurityUtils;

import java.util.Date;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class UserLoginLogListener
  implements HttpSessionAttributeListener, HttpSessionListener
{
  private UserLoginLogDao userLoginLogDao;

  public void sessionCreated(HttpSessionEvent se)
  {
  }

  public void sessionDestroyed(HttpSessionEvent se)
  {
  }

  public void attributeAdded(HttpSessionBindingEvent event)
  {
    String key = event.getName();
    if (key.equals("userLoginLog")) {
      LoginUser user = (LoginUser) SecurityUtils.getSubject().getPrincipal();
      UserLoginLog ull = (UserLoginLog)event.getValue();
      ull.setDefaultValue();
      ull.setCreator(user.getId());
      this.userLoginLogDao = ((UserLoginLogDao)BeanLocator.getBean("userLoginLogDao"));
      try {

        this.userLoginLogDao.insert(ull);
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }

  public void attributeRemoved(HttpSessionBindingEvent event)
  {
    String key = event.getName();
    if (key.equals("userLoginLog")) {
      UserLoginLog ull = (UserLoginLog)event.getValue();

      ull.setLogoutTime(new Date());
      try {
        this.userLoginLogDao = ((UserLoginLogDao)BeanLocator.getBean("userLoginLogDao"));
        this.userLoginLogDao.updateLogoutTime(ull);
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }

  public void attributeReplaced(HttpSessionBindingEvent event)
  {
  }
}