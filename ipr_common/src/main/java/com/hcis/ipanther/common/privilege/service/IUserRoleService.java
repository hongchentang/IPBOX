package com.hcis.ipanther.common.privilege.service;

import com.hcis.ipanther.common.privilege.entity.Role;
import com.hcis.ipanther.common.privilege.entity.UserRole;
import com.hcis.ipanther.core.entity.Response;
import java.util.List;

public abstract interface IUserRoleService
{
  public abstract List<Role> queryRoleByUserId(String paramString);

  public abstract List<UserRole> queryUserRole(UserRole paramUserRole);

  public abstract Response addUserRole(UserRole paramUserRole);

  public abstract Response addUserRoles(String paramString, String[] paramArrayOfString);

  public abstract Response addUsersRole(String[] paramArrayOfString, String paramString);

  public abstract Response removeUserRole(UserRole paramUserRole);

  public abstract Response removeUserRoles(String paramString, String[] paramArrayOfString);

  public abstract Response removeUsersRole(String[] paramArrayOfString, String paramString);
}