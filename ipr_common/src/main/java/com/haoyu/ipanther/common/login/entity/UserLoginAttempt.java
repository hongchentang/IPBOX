package com.haoyu.ipanther.common.login.entity;

import java.util.Date;

public class UserLoginAttempt
{
  private String accountId;
  private Date loginPeriodStartTime;
  private Date lastLoginAttemptTime;
  private int loginAttemptCount;

  public UserLoginAttempt()
  {
  }

  public UserLoginAttempt(String accountId)
  {
    this.accountId = accountId;
  }

  public String getAccountId() {
    return this.accountId;
  }
  public void setAccountId(String accountId) {
    this.accountId = accountId;
  }
  public Date getLoginPeriodStartTime() {
    return this.loginPeriodStartTime;
  }
  public void setLoginPeriodStartTime(Date loginPeriodStartTime) {
    this.loginPeriodStartTime = loginPeriodStartTime;
  }
  public Date getLastLoginAttemptTime() {
    return this.lastLoginAttemptTime;
  }
  public void setLastLoginAttemptTime(Date lastLoginAttemptTime) {
    this.lastLoginAttemptTime = lastLoginAttemptTime;
  }
  public int getLoginAttemptCount() {
    return this.loginAttemptCount;
  }
  public void setLoginAttemptCount(int loginAttemptCount) {
    this.loginAttemptCount = loginAttemptCount;
  }
}