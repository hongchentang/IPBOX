package com.hcis.ipanther.common.privilege.service.impl;

import com.hcis.ipanther.common.privilege.dao.IRolePermissionDao;
import com.hcis.ipanther.common.privilege.entity.Permission;
import com.hcis.ipanther.common.privilege.entity.Role;
import com.hcis.ipanther.common.privilege.entity.RolePermission;
import com.hcis.ipanther.common.privilege.service.IRolePermissionService;
import com.hcis.ipanther.core.entity.Response;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service("rolePermissionService")
public class RolePermissionServiceImpl
  implements IRolePermissionService
{

  @Resource
  private IRolePermissionDao rolePermissionDao;

  public List<Role> queryRoleByPermissionId(String permissionId)
  {
    return this.rolePermissionDao.selectRoleByPermissionId(permissionId);
  }

  public List<Permission> queryPermissionByRoleId(String roleId)
  {
    return this.rolePermissionDao.selectPermissionByRoleId(roleId);
  }

  public RolePermission addRolePermission(RolePermission rolePermission)
  {
    if ((StringUtils.isNotEmpty(rolePermission.getRoleId())) && (StringUtils.isNotEmpty(rolePermission.getPermissionId()))) {
      return this.rolePermissionDao.insertRolePermission(rolePermission) > 0 ? rolePermission : null;
    }
    return null;
  }

  public List<RolePermission> addRolePermissions(String roleId, String[] permissions)
  {
    if ((StringUtils.isNotEmpty(roleId)) && (permissions != null) && (permissions.length > 0)) {
      Map param = new HashMap();
      param.put("roleId", roleId);
      param.put("permissions", permissions);
      int count = this.rolePermissionDao.insertRolePermissions(param);
      if (count > 0) {
        List list = new ArrayList();
        for (String permissionId : permissions) {
          list.add(new RolePermission(permissionId, roleId));
        }
        return list;
      }
      return null;
    }
    return null;
  }

  public Response saveRolePermissions(String roleId, String[] permissions)
  {
    if ((StringUtils.isNotEmpty(roleId)) && (permissions != null) && (permissions.length > 0)) {
      Map param = new HashMap();
      param.put("roleId", roleId);
      param.put("permissions", permissions);
      param.put("type", "exclusion");
      this.rolePermissionDao.deleteRolePermissions(param);
      this.rolePermissionDao.insertRolePermissions(param);
      return Response.successInstance();
    }
    return Response.failInstance().responseMsg("roleId is null or permissions is null!");
  }

  public List<RolePermission> addRolesPermission(String[] roles, String permissionId)
  {
    if ((StringUtils.isNotEmpty(permissionId)) && (roles != null) && (roles.length > 0)) {
      Map param = new HashMap();
      param.put("roles", roles);
      param.put("permissionId", permissionId);
      int count = this.rolePermissionDao.insertRolesPermission(param);
      if (count > 0) {
        List list = new ArrayList();
        for (String roleId : roles) {
          list.add(new RolePermission(permissionId, roleId));
        }
        return list;
      }
      return null;
    }
    return null;
  }

  public Response saveRolesPermission(String[] roles, String permissionId)
  {
    if ((StringUtils.isNotEmpty(permissionId)) && (roles != null) && (roles.length > 0)) {
      Map param = new HashMap();
      param.put("roles", roles);
      param.put("permissionId", permissionId);
      param.put("type", "exclusion");
      this.rolePermissionDao.deleteRolesPermission(param);
      this.rolePermissionDao.insertRolesPermission(param);
      return Response.successInstance();
    }
    return Response.failInstance().responseMsg("permissionId is null or roles is null");
  }

  public Response removeRolePermission(RolePermission rolePermission)
  {
    if ((StringUtils.isNotBlank(rolePermission.getRoleId())) && (StringUtils.isNotEmpty(rolePermission.getPermissionId()))) {
      int count = this.rolePermissionDao.deleteRolePermission(rolePermission);
      if (count > 0) {
        return Response.successInstance();
      }
      return Response.failInstance();
    }
    return Response.failInstance().responseMsg("roleId is null or permission is null");
  }

  public Response removeRolePermissions(String roleId, String[] permissions)
  {
    if ((StringUtils.isNotEmpty(roleId)) && (permissions != null) && (permissions.length > 0)) {
      Map param = new HashMap();
      param.put("roleId", roleId);
      param.put("permissions", permissions);
      int count = this.rolePermissionDao.deleteRolePermissions(param);
      if (count > 0) {
        return Response.successInstance();
      }
      return Response.failInstance();
    }
    return Response.failInstance().responseData("roleId or permissions is null");
  }

  public Response removeRolesPermission(String[] roles, String permissionId)
  {
    if ((StringUtils.isNotEmpty(permissionId)) && (roles != null) && (roles.length > 0)) {
      Map param = new HashMap();
      param.put("roles", roles);
      param.put("permissionId", permissionId);
      int count = this.rolePermissionDao.deleteRolesPermission(param);
      if (count > 0) {
        return Response.successInstance();
      }
      return Response.failInstance();
    }
    return Response.failInstance().responseData("roleId or permissions is null");
  }

  public List<RolePermission> queryRolePermission(RolePermission rolePermission)
  {
    return this.rolePermissionDao.selectRolePermission(rolePermission);
  }
}