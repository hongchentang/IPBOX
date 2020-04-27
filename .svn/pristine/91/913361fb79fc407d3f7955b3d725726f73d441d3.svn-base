package com.hcis.ipanther.common.privilege.service.impl;

import com.hcis.ipanther.common.privilege.dao.IRoleDao;
import com.hcis.ipanther.common.privilege.entity.Role;
import com.hcis.ipanther.common.privilege.service.IRoleService;
import com.hcis.ipanther.core.entity.PageAndList;
import com.hcis.ipanther.core.entity.Response;
import com.hcis.ipanther.core.page.Pagination;
import com.hcis.ipanther.core.utils.Identities;
import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service("roleService")
public class RoleServiceImpl
  implements IRoleService
{

  @Resource
  private IRoleDao roleDao;

  public Role queryRoleById(String id)
  {
    return this.roleDao.selectById(id);
  }

  public List<Role> queryRole(Role role)
  {
    return this.roleDao.selectRole(role);
  }

  public PageAndList<Role> queryRole(Role role, Pagination pagination)
  {
    List list = this.roleDao.selectRole(role, pagination);
    return new PageAndList(list, pagination);
  }

  public Response addRole(Role role)
  {
    if (StringUtils.isEmpty(role.getId())) {
      role.setId(Identities.uuid2());
    }
    role.setDefaultValue();
    return this.roleDao.insertRole(role) > 0 ? Response.successInstance() : Response.failInstance();
  }

  public Response editRole(Role role)
  {
    if (StringUtils.isNotEmpty(role.getId())) {
      role.setUpdateTime(new Date());
      return this.roleDao.updateRole(role) > 0 ? Response.successInstance() : Response.failInstance();
    }
    return Response.failInstance();
  }

  public Response saveRole(Role role)
  {
    if (StringUtils.isNotEmpty(role.getId())) {
      return editRole(role);
    }
    return addRole(role);
  }

  public Response removeRoleByLogic(Role role)
  {
    if (StringUtils.isNotEmpty(role.getId())) {
      role.setUpdateTime(new Date());
      int count = this.roleDao.deleteRoleByLogic(role);

      if (count > 0) {
        return Response.successInstance();
      }
    }
    return Response.failInstance();
  }

  public Response removeRoleByPhysics(Role role)
  {
    if (StringUtils.isNotEmpty(role.getId())) {
      return this.roleDao.deleteRoleByPhysics(role.getId()) > 0 ? Response.successInstance() : Response.failInstance();
    }
    return Response.failInstance();
  }

  public Response retrieveRole(Role role)
  {
    if (StringUtils.isNotEmpty(role.getId())) {
      role.setUpdateTime(new Date());
      return this.roleDao.retrieveRole(role) > 0 ? Response.successInstance() : Response.failInstance();
    }
    return Response.failInstance();
  }
}