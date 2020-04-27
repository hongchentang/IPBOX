package com.hcis.ipanther.common.privilege.service.impl;

import com.hcis.ipanther.common.privilege.dao.IUserRoleDao;
import com.hcis.ipanther.common.privilege.entity.Role;
import com.hcis.ipanther.common.privilege.entity.UserRole;
import com.hcis.ipanther.common.privilege.service.IUserRoleService;
import com.hcis.ipanther.core.entity.Response;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service("userRoleService")
public class UserRoleServiceImpl
  implements IUserRoleService
{

  @Resource
  private IUserRoleDao userRoleDao;

  public List<Role> queryRoleByUserId(String userId)
  {
    return this.userRoleDao.selectRoleByUserId(userId);
  }

  public Response addUserRole(UserRole userRole)
  {
    if ((StringUtils.isNotEmpty(userRole.getRoleId())) && (StringUtils.isNotEmpty(userRole.getUserId()))) {
      int count = this.userRoleDao.insertUserRole(userRole);
      if (count > 0) {
        return Response.successInstance();
      }
      return Response.failInstance();
    }
    return Response.failInstance().responseMsg("roleId is null or roleId is null");
  }

  public Response addUserRoles(String userId, String[] roles)
  {
    if (StringUtils.isNotEmpty(userId)) {
      Map param = new HashMap();
      param.put("userId", userId);
      param.put("roles", roles);
      param.put("type", "exclusion");
      this.userRoleDao.deleteUserRoles(param);
      if (roles != null) {
        this.userRoleDao.insertUserRoles(param);
      }
      return Response.successInstance();
    }
    return Response.failInstance().responseMsg("userId is null");
  }

  public Response addUsersRole(String[] users, String roleId)
  {
    if ((StringUtils.isNotEmpty(roleId)) && (users != null) && (users.length > 0)) {
      Map param = new HashMap();
      param.put("users", users);
      param.put("roleId", roleId);
      int count = this.userRoleDao.insertUsersRole(param);
      if (count > 0) {
        return Response.successInstance();
      }
      return Response.failInstance();
    }
    return Response.failInstance().responseMsg("users is null or roleId is null");
  }

  public Response removeUserRole(UserRole userRole)
  {
    if ((StringUtils.isNotEmpty(userRole.getRoleId())) && (StringUtils.isNotEmpty(userRole.getUserId()))) {
      int count = this.userRoleDao.deleteUserRole(userRole);
      if (count > 0) {
        return Response.successInstance();
      }
      return Response.failInstance();
    }
    return Response.failInstance().responseMsg("roleId is null or userId is null");
  }

  public Response removeUserRoles(String userId, String[] roles)
  {
    if ((StringUtils.isNotEmpty(userId)) && (roles != null) && (roles.length > 0)) {
      Map param = new HashMap();
      param.put("userId", userId);
      param.put("roles", roles);
      int count = this.userRoleDao.deleteUserRoles(param);
      if (count > 0) {
        return Response.successInstance();
      }
      return Response.failInstance();
    }
    return Response.failInstance().responseMsg("userId is null or roles is null");
  }

  public Response removeUsersRole(String[] users, String roleId)
  {
    if ((StringUtils.isNotEmpty(roleId)) && (users != null) && (users.length > 0)) {
      Map param = new HashMap();
      param.put("users", users);
      param.put("roleId", roleId);
      int count = this.userRoleDao.deleteUsersRole(param);
      if (count > 0) {
        return Response.successInstance();
      }
      return Response.failInstance();
    }
    return Response.failInstance().responseMsg("roleId is null or users is null");
  }

  public List<UserRole> queryUserRole(UserRole userRole) {
    return this.userRoleDao.selectUserRole(userRole);
  }
}