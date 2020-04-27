package com.hcis.ipanther.common.privilege.dao;

import com.hcis.ipanther.common.privilege.entity.Permission;
import java.util.List;
import java.util.Map;

public abstract interface IPrivilegeDao
{
  public abstract List<Permission> selectPermissionByUserId(String paramString);

  public abstract Map<String, Permission> selectPermissionByRole(String paramString);
}