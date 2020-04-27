package com.hcis.ipanther.common.privilege.service;

import com.hcis.ipanther.common.privilege.entity.Permission;
import com.hcis.ipanther.common.privilege.entity.Role;
import com.hcis.ipanther.common.privilege.entity.RolePermission;
import com.hcis.ipanther.core.entity.Response;
import java.util.List;

public abstract interface IRolePermissionService
{
  public abstract List<Role> queryRoleByPermissionId(String paramString);

  public abstract List<Permission> queryPermissionByRoleId(String paramString);

  public abstract List<RolePermission> queryRolePermission(RolePermission paramRolePermission);

  public abstract RolePermission addRolePermission(RolePermission paramRolePermission);

  public abstract List<RolePermission> addRolePermissions(String paramString, String[] paramArrayOfString);

  public abstract Response saveRolePermissions(String paramString, String[] paramArrayOfString);

  public abstract List<RolePermission> addRolesPermission(String[] paramArrayOfString, String paramString);

  public abstract Response saveRolesPermission(String[] paramArrayOfString, String paramString);

  public abstract Response removeRolePermission(RolePermission paramRolePermission);

  public abstract Response removeRolePermissions(String paramString, String[] paramArrayOfString);

  public abstract Response removeRolesPermission(String[] paramArrayOfString, String paramString);
}