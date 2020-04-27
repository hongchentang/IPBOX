package com.hcis.ipanther.common.privilege.service;

import com.hcis.ipanther.common.privilege.entity.Permission;
import com.hcis.ipanther.core.entity.PageAndList;
import com.hcis.ipanther.core.entity.Response;
import com.hcis.ipanther.core.page.Pagination;
import java.util.List;

public abstract interface IPermissionService
{
  public abstract Permission queryPermissionById(String paramString);

  public abstract Permission queryPermissionByCode(String paramString);

  public abstract List<Permission> queryPermission(Permission paramPermission);

  public abstract PageAndList<Permission> queryPermission(Permission paramPermission, Pagination paramPagination);

  public abstract Response addPermission(Permission paramPermission);

  public abstract Response editPermission(Permission paramPermission);

  public abstract Response savePermission(Permission paramPermission);

  public abstract Response removePermissionByLogic(Permission paramPermission);

  public abstract Response removePermissionByPhysics(Permission paramPermission);

  public abstract Response retrievePermission(Permission paramPermission);
}