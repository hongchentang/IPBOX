package com.hcis.ipanther.common.privilege.dao.impl.mybatis;

import com.hcis.ipanther.common.privilege.dao.IUserRoleDao;
import com.hcis.ipanther.common.privilege.entity.Role;
import com.hcis.ipanther.common.privilege.entity.UserRole;
import com.hcis.ipanther.core.persistence.dao.MyBatisDao;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.session.SqlSession;

public class UserRoleDao extends MyBatisDao
  implements IUserRoleDao
{
  public int insertUserRole(UserRole userRole)
  {
    return super.insert(userRole);
  }

  public int insertUserRoles(Map<String, Object> param)
  {
    return getSqlSession().insert(this.namespace + ".insertUserRoles", param);
  }

  public int insertUsersRole(Map<String, Object> param)
  {
    return getSqlSession().insert(this.namespace + ".insertUsersRole", param);
  }

  public int deleteUserRole(UserRole userRole)
  {
    return super.deleteByPhysics(userRole);
  }

  public int deleteUserRoles(Map<String, Object> param)
  {
    return getSqlSession().delete(this.namespace + ".deleteUserRoles", param);
  }

  public int deleteUsersRole(Map<String, Object> param)
  {
    return getSqlSession().delete(this.namespace + ".deleteUsersRole", param);
  }

  public List<Role> selectRoleByUserId(String userId)
  {
    return getSqlSession().selectList(this.namespace + ".selectRoleByUserId", userId);
  }

  public List<UserRole> selectUserRole(UserRole userRole)
  {
    return selectForList(this.namespace + ".selectUserRole", userRole);
  }
}