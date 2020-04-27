package com.hcis.ipanther.common.privilege.shiro.concurrent;

import com.hcis.ipanther.core.security.shiro.ShiroUser;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.shiro.session.SessionException;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.LogoutFilter;

public class ConcurrentAccessLogoutFilter extends LogoutFilter
{
  private ConcurrentAccessService concurrentAccessService;

  public ConcurrentAccessService getConcurrentAccessService()
  {
    return this.concurrentAccessService;
  }

  public void setConcurrentAccessService(ConcurrentAccessService concurrentAccessService) {
    this.concurrentAccessService = concurrentAccessService;
  }

  protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception
  {
    Subject subject = getSubject(request, response);
    String redirectUrl = getRedirectUrl(request, response, subject);
    try
    {
      ShiroUser shiroUser = (ShiroUser)subject.getPrincipal();
      if ((this.concurrentAccessService != null) && 
        (shiroUser != null)) {
        this.concurrentAccessService.onLogout(shiroUser.getId(), ((HttpServletRequest)request).getSession().getId());
      }

      subject.logout();
    } catch (SessionException ise) {
      ise.printStackTrace();
    }
    issueRedirect(request, response, redirectUrl);
    return false;
  }
}