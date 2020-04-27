package com.hcis.ipanther.common.privilege.service.impl;

import com.hcis.ipanther.common.privilege.dao.IPermissionDao;
import com.hcis.ipanther.common.privilege.entity.Permission;
import com.hcis.ipanther.common.privilege.service.IPermissionService;
import com.hcis.ipanther.core.entity.PageAndList;
import com.hcis.ipanther.core.entity.Response;
import com.hcis.ipanther.core.page.Pagination;
import com.hcis.ipanther.core.utils.Identities;
import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import org.apache.commons.lang3.StringUtils;
import org.springframework.aop.framework.AopContext;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

@Service("permissionService")
public class PermissionServiceImpl
  implements IPermissionService
{

  @Resource
  private IPermissionDao permissionDao;

  @Cacheable(value={"privilegeCache"}, key="#id")
  public Permission queryPermissionById(String id)
  {
    return this.permissionDao.selectById(id);
  }

  public Permission queryPermissionByCode(String code)
  {
    return this.permissionDao.selectByCode(code);
  }

  public List<Permission> queryPermission(Permission permission)
  {
    return this.permissionDao.selectPermission(permission);
  }

  public PageAndList<Permission> queryPermission(Permission permission, Pagination pagination)
  {
    List list = this.permissionDao.selectPermission(permission, pagination);
    return new PageAndList(list, pagination);
  }

  @CacheEvict(value={"privilegeCache"}, key="'permissionNodeMap'")
  public Response addPermission(Permission permission)
  {
    if (StringUtils.isEmpty(permission.getId())) {
      permission.setId(Identities.uuid2());
    }
    if (StringUtils.isNotEmpty(permission.getCode())) {
      if (this.permissionDao.selectByCode(permission.getCode()) != null) {
        return Response.failInstance().responseMsg("权限编码已存在，请重新编码后提交!");
      }
      permission.setDefaultValue();
      int count = this.permissionDao.insertPermission(permission);
      if (count > 0) {
        return Response.successInstance();
      }
      return Response.failInstance().responseMsg("权限插入操作失败!请刷新后重试，或尝试联系管理员!");
    }
    return Response.failInstance().responseMsg("权限编码为空,请编码后提交!");
  }

  @Caching(evict={@CacheEvict(value={"privilegeCache"}, key="#permission.id"), @CacheEvict(value={"privilegeCache"}, key="'permissionNodeMap'")})
  public Response editPermission(Permission permission)
  {
    if (StringUtils.isNotEmpty(permission.getId())) {
      permission.setUpdateTime(new Date());
      int count = this.permissionDao.updatePermission(permission);
      if (count > 0) {
        return Response.successInstance();
      }
      return Response.failInstance().responseMsg("权限更新操作失败!请刷新后重试，或尝试联系管理员!");
    }
    return Response.failInstance().responseMsg("权限id为空!操作非法!");
  }

  public Response savePermission(Permission permission)
  {
    if (StringUtils.isEmpty(permission.getId())) {
      return ((IPermissionService)AopContext.currentProxy()).addPermission(permission);
    }
    return ((IPermissionService)AopContext.currentProxy()).editPermission(permission);
  }

  @Caching(evict={@CacheEvict(value={"privilegeCache"}, key="#permission.id"), @CacheEvict(value={"privilegeCache"}, key="'permissionNodeMap'")})
  public Response removePermissionByLogic(Permission permission)
  {
    if (StringUtils.isNotEmpty(permission.getId())) {
      permission.setUpdateTime(new Date());
      int count = this.permissionDao.deletePermissionByLogic(permission);
      if (count > 0) {
        return Response.successInstance();
      }
      return Response.failInstance().responseMsg("权限删除操作失败!请刷新后重试!");
    }
    return Response.failInstance().responseMsg("权限id为空!操作非法!");
  }

  @Caching(evict={@CacheEvict(value={"privilegeCache"}, key="#permission.id"), @CacheEvict(value={"privilegeCache"}, key="'permissionNodeMap'")})
  public Response removePermissionByPhysics(Permission permission)
  {
    if (StringUtils.isNotEmpty(permission.getId())) {
      int count = this.permissionDao.deletePermissionByPhysics(permission.getId());
      if (count > 0) {
        return Response.successInstance();
      }
      return Response.failInstance();
    }
    return Response.failInstance();
  }

  @CacheEvict(value={"privilegeCache"}, key="'permissionNodeMap'")
  public Response retrievePermission(Permission permission)
  {
    if (StringUtils.isNotEmpty(permission.getId())) {
      permission.setUpdateTime(new Date());
      int count = this.permissionDao.retrievePermission(permission);
      if (count > 0) {
        return Response.successInstance();
      }
    }
    return Response.failInstance();
  }
}