package com.haoyu.ipanther.common.login.dao.impl.mybatis;

import com.hcis.ipanther.core.persistence.dao.MyBatisDao;
import com.haoyu.ipanther.common.login.dao.IUserLoginAttemptDao;
import com.haoyu.ipanther.common.login.entity.UserLoginAttempt;

public class UserLoginAttemptDao extends MyBatisDao
  implements IUserLoginAttemptDao
{
  public UserLoginAttempt selectByPrimaryKey(String accountId)
  {
    return (UserLoginAttempt)super.selectByPrimaryKey(accountId);
  }

  public int insert(UserLoginAttempt userLoginAttempt)
  {
    return super.insert(userLoginAttempt);
  }

  public int updateByPrimaryKey(UserLoginAttempt userLoginAttempt)
  {
    return super.updateByPrimaryKey(userLoginAttempt);
  }

  public int deleteByPrimaryKey(String accountId)
  {
    return super.delete(this.namespace + ".deleteByPrimaryKey", accountId);
  }
}