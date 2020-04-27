package com.hcis.ipanther.common.privilege.service;

import com.hcis.ipanther.common.privilege.entity.Permission;
import com.hcis.ipanther.common.privilege.entity.PermissionNode;
import java.util.List;
import java.util.Map;

public abstract interface IPrivilegeService
{
  public abstract List<Permission> queryAllPermissionByUserId(String paramString);

  public abstract PermissionNode getPermissionNode(String paramString);

  public abstract Map<String, PermissionNode> getPermissionNodeMap();

  public abstract PermissionNode getUserPermissionNode(String paramString);
}