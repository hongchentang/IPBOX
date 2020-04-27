package com.haoyu.ipanther.common.login.dao.impl.mybatis;

import com.hcis.ipanther.core.persistence.dao.MyBatisDao;
import com.hcis.ipanther.core.web.vo.SearchParam;

import java.util.List;

import com.haoyu.ipanther.common.login.dao.IUserLoginLogDao;
import com.haoyu.ipanther.common.login.entity.UserLoginLog;

public class UserLoginLogDao extends MyBatisDao
  implements IUserLoginLogDao
{
  public int insert(UserLoginLog userLoginLog)
  {
    return super.insert(userLoginLog);
  }

  public int updateLogoutTime(UserLoginLog userLoginLog)
  {
    return update(this.namespace + ".updateLogoutTime", userLoginLog);
  }
  
  public List<UserLoginLog> list(SearchParam searchParam)
  {
    return selectBySearchParam(searchParam);
//    return this.getSqlSession().selectList(namespace+".selectBySearchParam",searchParam);
  }
}