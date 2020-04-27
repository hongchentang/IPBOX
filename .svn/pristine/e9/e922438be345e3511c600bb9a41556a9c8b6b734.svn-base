package com.hcis.ipanther.common.privilege.dao;

import com.hcis.ipanther.common.privilege.entity.Permission;
import com.hcis.ipanther.common.privilege.entity.Role;
import com.hcis.ipanther.common.privilege.entity.RolePermission;
import java.util.List;
import java.util.Map;

public abstract interface IRolePermissionDao
{
  public abstract List<Role> selectRoleByPermissionId(String paramString);

  public abstract List<Permission> selectPermissionByRoleId(String paramString);

  public abstract List<RolePermission> selectRolePermission(RolePermission paramRolePermission);

  public abstract int insertRolePermission(RolePermission paramRolePermission);

  public abstract int insertRolePermissions(Map<String, Object> paramMap);

  public abstract int insertRolesPermission(Map<String, Object> paramMap);

  public abstract int insertRolesPermissions(Map<String, Object> paramMap);

  public abstract int deleteRolePermission(RolePermission paramRolePermission);

  public abstract int deleteRolePermissions(Map<String, Object> paramMap);

  public abstract int deleteRolesPermission(Map<String, Object> paramMap);

  public abstract int deleteRolesPermissions(Map<String, Object> paramMap);
}