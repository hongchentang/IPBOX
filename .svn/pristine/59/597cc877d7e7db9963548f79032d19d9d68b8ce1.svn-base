package com.haoyu.ipanther.common.login.service;

import com.haoyu.ipanther.common.login.entity.UserLoginAttempt;

public abstract interface IUserLoginAttemptService
{
  public abstract boolean excessiveAttempts(UserLoginAttempt paramUserLoginAttempt);

  public abstract int getMaxAttempts();

  public abstract int incAttempts(String paramString);

  public abstract int clearAttempts(String paramString);
}