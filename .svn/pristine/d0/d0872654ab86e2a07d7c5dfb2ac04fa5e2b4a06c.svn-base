package com.hcis.ipanther.common.privilege.dao;

import com.hcis.ipanther.common.privilege.entity.Permission;
import com.hcis.ipanther.core.page.Pagination;
import java.util.List;
import java.util.Map;

public abstract interface IPermissionDao
{
  public abstract int insertPermission(Permission paramPermission);

  public abstract int updatePermission(Permission paramPermission);

  public abstract Permission selectById(String paramString);

  public abstract Permission selectByCode(String paramString);

  public abstract List<Permission> selectPermission(Permission paramPermission);

  public abstract Map<String, Permission> selectPermissionForMap(Permission paramPermission);

  public abstract List<Permission> selectPermission(Permission paramPermission, Pagination paramPagination);

  public abstract int deletePermissionByLogic(Permission paramPermission);

  public abstract int deletePermissionByPhysics(String paramString);

  public abstract int retrievePermission(Permission paramPermission);
}