package com.hcis.ipanther.common.privilege.shiro.captcha;

import org.apache.shiro.authc.AuthenticationException;

public class IncorrectCaptchaException extends AuthenticationException
{
  public IncorrectCaptchaException()
  {
  }

  public IncorrectCaptchaException(String message, Throwable cause)
  {
    super(message, cause);
  }

  public IncorrectCaptchaException(String message) {
    super(message);
  }

  public IncorrectCaptchaException(Throwable cause) {
    super(cause);
  }
}