package com.hcis.ipanther.common.privilege.service;

import com.hcis.ipanther.common.privilege.entity.Role;
import com.hcis.ipanther.core.entity.PageAndList;
import com.hcis.ipanther.core.entity.Response;
import com.hcis.ipanther.core.page.Pagination;
import java.util.List;

public abstract interface IRoleService
{
  public abstract Role queryRoleById(String paramString);

  public abstract List<Role> queryRole(Role paramRole);

  public abstract PageAndList<Role> queryRole(Role paramRole, Pagination paramPagination);

  public abstract Response addRole(Role paramRole);

  public abstract Response editRole(Role paramRole);

  public abstract Response saveRole(Role paramRole);

  public abstract Response removeRoleByLogic(Role paramRole);

  public abstract Response removeRoleByPhysics(Role paramRole);

  public abstract Response retrieveRole(Role paramRole);
}