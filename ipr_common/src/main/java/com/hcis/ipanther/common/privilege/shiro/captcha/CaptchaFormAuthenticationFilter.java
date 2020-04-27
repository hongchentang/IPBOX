package com.hcis.ipanther.common.privilege.shiro.captcha;

import com.alibaba.fastjson.JSONObject;
import com.hcis.ipanther.common.privilege.shiro.IShiroLoginService;
import com.hcis.ipanther.common.privilege.shiro.concurrent.ConcurrentAccessService;
import com.hcis.ipanther.core.security.shiro.ShiroUser;
import com.haoyu.ipanther.common.login.service.IUserLoginLogService;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;

import java.io.IOException;

public class CaptchaFormAuthenticationFilter extends FormAuthenticationFilter
{
  public static final String DEFAULT_CAPTCHA_PARAM = "captcha";
  private String captchaParam = "captcha";
  private String universalCaptcha;
  private boolean validateCaptcha = true;
  private IShiroLoginService shiroLoginService;
  private IUserLoginLogService userLoginLogService;
  private ConcurrentAccessService concurrentAccessService;

  public ConcurrentAccessService getConcurrentAccessService()
  {
    return this.concurrentAccessService;
  }

  public void setConcurrentAccessService(ConcurrentAccessService concurrentAccessService)
  {
    this.concurrentAccessService = concurrentAccessService;
  }

  public boolean isValidateCaptcha() {
    return this.validateCaptcha;
  }

  public void setValidateCaptcha(boolean validateCaptcha) {
    this.validateCaptcha = validateCaptcha;
  }

  public IUserLoginLogService getUserLoginLogService() {
    return this.userLoginLogService;
  }

  public void setUserLoginLogService(IUserLoginLogService userLoginLogService) {
    this.userLoginLogService = userLoginLogService;
  }

  public String getUniversalCaptcha() {
    return this.universalCaptcha;
  }

  public void setUniversalCaptcha(String universalCaptcha) {
    this.universalCaptcha = universalCaptcha;
  }

  public IShiroLoginService getShiroLoginService() {
    return this.shiroLoginService;
  }

  public void setShiroLoginService(IShiroLoginService shiroLoginService) {
    this.shiroLoginService = shiroLoginService;
  }

  public String getCaptchaParam() {
    return this.captchaParam;
  }

  public void setCaptchaParam(String captchaParam) {
    this.captchaParam = captchaParam;
  }

  protected String getCaptcha(ServletRequest request) {
    return WebUtils.getCleanParam(request, getCaptchaParam());
  }

  @Override
  protected String getUsername(ServletRequest request) {
    return WebUtils.getCleanParam(request, "userName");
  }

  @Override
  protected CaptchaUsernamePasswordToken createToken(ServletRequest request, ServletResponse response)
  {
    String username = getUsername(request);
    String password = getPassword(request);
    String captcha = getCaptcha(request);
    boolean rememberMe = isRememberMe(request);
    String host = getHost(request);

    try {
      JSONObject object = GetRequestJsonUtils.getRequestJsonObject(request);
      username = object.getString("userName");
      password = object.getString("password");
      captcha = object.getString("captcha");
    } catch (Exception e) {
      e.printStackTrace();
    }

    return new CaptchaUsernamePasswordToken(
      username, password, rememberMe, host, captcha);
  }

  protected void doCaptchaValidate(HttpServletRequest request, CaptchaUsernamePasswordToken token)
  {
    String captcha = (String)request.getSession().getAttribute(
      "KAPTCHA_SESSION_KEY");

    if ((this.validateCaptcha) && ((StringUtils.isEmpty(token.getCaptcha())) || ((captcha != null) && 
      (!captcha.equalsIgnoreCase(token.getCaptcha())))) && (
      (this.universalCaptcha == null) || (StringUtils.isEmpty(token.getCaptcha())) || (!token.getCaptcha().equalsIgnoreCase(this.universalCaptcha))))
      throw new IncorrectCaptchaException("验证码错误！");
  }

  @Override
  public boolean executeLogin(ServletRequest request, ServletResponse response)
    throws Exception
  {
    CaptchaUsernamePasswordToken token = createToken(request, response);
    try {
      doCaptchaValidate((HttpServletRequest)request, token);
      Subject subject = getSubject(request, response);
      subject.login(token);
      if (this.shiroLoginService != null) {
        this.shiroLoginService.onLoginSuccess(token, request, response);
      }
      if (this.userLoginLogService != null) {
        ShiroUser shiroUser = (ShiroUser)subject.getPrincipal();
        this.userLoginLogService.logLogin((HttpServletRequest)request, shiroUser);
      }
      if (this.concurrentAccessService != null) {
        ShiroUser shiroUser = (ShiroUser)subject.getPrincipal();
        this.concurrentAccessService.onLoginSuccess(shiroUser.getId(), (HttpServletRequest)request);
      }
      //判断是哪种请求
      Object flag = request.getAttribute("isVueRequest");
      if(flag != null && (Boolean)flag){
        return false;
      }else {
        return onLoginSuccess(token, subject, request, response);
      }
    } catch (AuthenticationException e) {
      if (this.shiroLoginService != null) {
        this.shiroLoginService.onLoginFailure(token, e, request, response);
      }
      return onLoginFailure(token, e, request, response);
    }
  }
}