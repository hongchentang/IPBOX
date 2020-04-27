package com.hcis.ipanther.common.privilege.dao.impl.mybatis;

import com.hcis.ipanther.common.privilege.dao.IRolePermissionDao;
import com.hcis.ipanther.common.privilege.entity.Permission;
import com.hcis.ipanther.common.privilege.entity.Role;
import com.hcis.ipanther.common.privilege.entity.RolePermission;
import com.hcis.ipanther.core.persistence.dao.MyBatisDao;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.session.SqlSession;

public class RolePermissionDao extends MyBatisDao
  implements IRolePermissionDao
{
  public int insertRolePermission(RolePermission rolePermission)
  {
    return super.insert(rolePermission);
  }

  public int insertRolePermissions(Map<String, Object> param)
  {
    return getSqlSession().insert(this.namespace + ".insertRolePermissions", param);
  }

  public int insertRolesPermission(Map<String, Object> param)
  {
    return getSqlSession().insert(this.namespace + ".insertRolesPermission", param);
  }

  public int insertRolesPermissions(Map<String, Object> param)
  {
    return getSqlSession().insert(this.namespace + ".insertRolesPermissions", param);
  }

  public int deleteRolePermission(RolePermission rolePermission)
  {
    return super.deleteByPhysics(rolePermission);
  }

  public int deleteRolePermissions(Map<String, Object> param)
  {
    return getSqlSession().insert(this.namespace + ".deleteRolePermissions", param);
  }

  public int deleteRolesPermission(Map<String, Object> param)
  {
    return getSqlSession().insert(this.namespace + ".deleteRolesPermission", param);
  }

  public int deleteRolesPermissions(Map<String, Object> param)
  {
    return getSqlSession().insert(this.namespace + ".deleteRolesPermissions", param);
  }

  public List<Role> selectRoleByPermissionId(String permissionId)
  {
    return getSqlSession().selectList(this.namespace + ".selectRoleByPermissionId", permissionId);
  }

  public List<Permission> selectPermissionByRoleId(String roleId)
  {
    return getSqlSession().selectList(this.namespace + ".selectPermissionByRoleId", roleId);
  }

  public List<RolePermission> selectRolePermission(RolePermission rolePermission)
  {
    return selectForList(this.namespace + ".selectRolePermission", rolePermission);
  }
}