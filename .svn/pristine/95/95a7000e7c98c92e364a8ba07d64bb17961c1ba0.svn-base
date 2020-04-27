package com.hcis.ipanther.common.privilege.shiro.captcha;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.shiro.authc.UsernamePasswordToken;

public class CaptchaUsernamePasswordToken extends UsernamePasswordToken
{
  private String captcha;

  public CaptchaUsernamePasswordToken(String username, String password, String captcha)
  {
    super(username, DigestUtils.md5Hex(password));
    this.captcha = captcha;
  }

  public CaptchaUsernamePasswordToken(String username, String password, boolean rememberMe, String host, String captcha)
  {
    super(username, DigestUtils.md5Hex(password), rememberMe, host);
    this.captcha = captcha;
  }

  public String getCaptcha() {
    return this.captcha;
  }

  public void setCaptcha(String captcha) {
    this.captcha = captcha;
  }
}