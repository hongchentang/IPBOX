package com.haoyu.ipanther.common.login.service.impl;

import com.haoyu.ipanther.common.login.dao.impl.mybatis.UserLoginAttemptDao;
import com.haoyu.ipanther.common.login.entity.UserLoginAttempt;
import com.haoyu.ipanther.common.login.service.IUserLoginAttemptService;
import java.util.Date;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

@Service("userLoginAttemptService")
public class UserLoginAttemptServiceImpl
  implements IUserLoginAttemptService
{

  @Resource
  private UserLoginAttemptDao userLoginAttemptDao;
  private int maxAttempts;
  private int periodMinutes;

  public UserLoginAttemptDao getUserLoginAttemptDao()
  {
    return this.userLoginAttemptDao;
  }

  public void setUserLoginAttemptDao(UserLoginAttemptDao userLoginAttemptDao) {
    this.userLoginAttemptDao = userLoginAttemptDao;
  }

  public int getMaxAttempts()
  {
    return this.maxAttempts;
  }

  public void setMaxAttempts(int maxAttempts) {
    this.maxAttempts = maxAttempts;
  }

  public int getPeriodMinutes() {
    return this.periodMinutes;
  }

  public void setPeriodMinutes(int periodMinutes) {
    this.periodMinutes = periodMinutes;
  }

  public boolean excessiveAttempts(UserLoginAttempt userLoginAttempt)
  {
    UserLoginAttempt ula = this.userLoginAttemptDao.selectByPrimaryKey(userLoginAttempt.getAccountId());
    if (ula != null) {
      userLoginAttempt.setLoginAttemptCount(ula.getLoginAttemptCount());
      userLoginAttempt.setLoginPeriodStartTime(ula.getLoginPeriodStartTime());
      if (this.maxAttempts > 0) {
        if ((this.periodMinutes > 0) && (ula.getLoginAttemptCount() >= this.maxAttempts)) {
          Date nd = new Date();
          Date lastLoginAttemptTime = ula.getLastLoginAttemptTime();
          long diff = lastLoginAttemptTime.getTime() - nd.getTime();
          long period = diff / 60000L;
          if (this.periodMinutes > period) {
            return true;
          }
        }
        else if (ula.getLoginAttemptCount() >= this.maxAttempts) {
          return true;
        }
      }
    }

    return false;
  }

  public int incAttempts(String accountId)
  {
    UserLoginAttempt ula = new UserLoginAttempt();
    ula.setAccountId(accountId);
    ula.setLastLoginAttemptTime(new Date());
    int count = this.userLoginAttemptDao.updateByPrimaryKey(ula);
    if (count == 0) {
      ula.setLoginAttemptCount(1);
      ula.setLoginPeriodStartTime(new Date());
      count = this.userLoginAttemptDao.insert(ula);
    }
    return count;
  }

  public int clearAttempts(String accountId)
  {
    return this.userLoginAttemptDao.deleteByPrimaryKey(accountId);
  }
}