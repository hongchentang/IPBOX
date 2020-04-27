package com.hcis.ipanther.common.privilege.dao;

import com.hcis.ipanther.common.privilege.entity.Role;
import com.hcis.ipanther.core.page.Pagination;
import java.util.List;

public abstract interface IRoleDao
{
  public abstract int insertRole(Role paramRole);

  public abstract int updateRole(Role paramRole);

  public abstract Role selectById(String paramString);

  public abstract List<Role> selectRole(Role paramRole);

  public abstract List<Role> selectRole(Role paramRole, Pagination paramPagination);

  public abstract int deleteRoleByLogic(Role paramRole);

  public abstract int deleteRoleByPhysics(String paramString);

  public abstract int retrieveRole(Role paramRole);
}