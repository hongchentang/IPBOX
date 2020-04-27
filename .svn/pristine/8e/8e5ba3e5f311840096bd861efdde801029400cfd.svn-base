package com.hcis.ipanther.common.privilege.dao;

import com.hcis.ipanther.common.privilege.entity.Role;
import com.hcis.ipanther.common.privilege.entity.UserRole;
import java.util.List;
import java.util.Map;

public abstract interface IUserRoleDao
{
  public abstract List<Role> selectRoleByUserId(String paramString);

  public abstract List<UserRole> selectUserRole(UserRole paramUserRole);

  public abstract int insertUserRole(UserRole paramUserRole);

  public abstract int insertUserRoles(Map<String, Object> paramMap);

  public abstract int insertUsersRole(Map<String, Object> paramMap);

  public abstract int deleteUserRole(UserRole paramUserRole);

  public abstract int deleteUserRoles(Map<String, Object> paramMap);

  public abstract int deleteUsersRole(Map<String, Object> paramMap);
}