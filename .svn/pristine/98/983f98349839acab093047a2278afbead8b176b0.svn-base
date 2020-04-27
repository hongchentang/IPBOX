package com.hcis.ipanther.common.privilege.shiro.concurrent;

import com.hcis.ipanther.common.privilege.config.PrivilegeConfig;
import java.io.IOException;
import javax.annotation.Resource;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.shiro.web.filter.authz.AuthorizationFilter;
import org.apache.shiro.web.util.WebUtils;

public class ConcurrentAccessFilter extends AuthorizationFilter
{

  @Resource
  private ConcurrentAccessService concurrentAccessService;
  private String redirectUrl;

  public String getRedirectUrl()
  {
    return this.redirectUrl;
  }

  public void setRedirectUrl(String redirectUrl) {
    this.redirectUrl = redirectUrl;
  }

  public ConcurrentAccessService getConcurrentAccessService() {
    return this.concurrentAccessService;
  }

  public void setConcurrentAccessService(ConcurrentAccessService concurrentAccessService)
  {
    this.concurrentAccessService = concurrentAccessService;
  }

  protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object obj)
    throws Exception
  {
    boolean isAllowed = this.concurrentAccessService.isConcurrentAccessAllowed((HttpServletRequest)request);
    return isAllowed;
  }

  protected boolean onAccessDenied(ServletRequest request, ServletResponse response)
    throws IOException
  {
    ConcurrentAccessConfig cac = this.concurrentAccessService.getConcurrentAccessConfig();
    ((HttpServletRequest)request).getSession().setAttribute("errorMsg", PrivilegeConfig.getFormatProperty("ConcurrentAccess", new String[] { String.valueOf(cac.getMaxConcurrentAccess()) }));
    saveRequest(request);
    WebUtils.issueRedirect(request, response, this.redirectUrl);
    return false;
  }
}