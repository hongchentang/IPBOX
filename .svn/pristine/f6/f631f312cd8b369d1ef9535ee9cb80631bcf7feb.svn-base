package com.hcis.ipanther.common.privilege.dao.impl.mybatis;

import com.hcis.ipanther.common.privilege.dao.IPermissionDao;
import com.hcis.ipanther.common.privilege.entity.Permission;
import com.hcis.ipanther.core.page.Pagination;
import com.hcis.ipanther.core.persistence.dao.MyBatisDao;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.session.SqlSession;

public class PermissionDao extends MyBatisDao
  implements IPermissionDao
{
  public int insertPermission(Permission permission)
  {
    return super.insert(permission);
  }

  public int updatePermission(Permission permission)
  {
    return super.updateByPrimaryKeySelective(permission);
  }

  public Permission selectById(String id)
  {
    return (Permission)super.selectByPrimaryKey(id);
  }

  public Permission selectByCode(String code)
  {
    return (Permission)getSqlSession().selectOne(this.namespace + ".selectByCode", code);
  }

  public List<Permission> selectPermission(Permission permission)
  {
    return super.selectForList(this.namespace + ".selectPermission", permission);
  }

  public List<Permission> selectPermission(Permission permission, Pagination pagination)
  {
    return super.selectForList(this.namespace + ".selectPermission", permission, "permission", pagination);
  }

  public int deletePermissionByLogic(Permission permission)
  {
    return super.deleteByLogic(permission);
  }

  public int deletePermissionByPhysics(String id)
  {
    return super.deleteByPhysics(id);
  }

  public int retrievePermission(Permission permission)
  {
    return super.retrieve(permission);
  }

  public Map<String, Permission> selectPermissionForMap(Permission permission)
  {
    return selectForMap(this.namespace + ".selectPermission", permission, "id");
  }
}