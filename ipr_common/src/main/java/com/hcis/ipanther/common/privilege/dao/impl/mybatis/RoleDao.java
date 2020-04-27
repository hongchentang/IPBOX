package com.hcis.ipanther.common.privilege.dao.impl.mybatis;

import com.hcis.ipanther.common.privilege.dao.IRoleDao;
import com.hcis.ipanther.common.privilege.entity.Role;
import com.hcis.ipanther.core.page.Pagination;
import com.hcis.ipanther.core.persistence.dao.MyBatisDao;
import java.util.List;

public class RoleDao extends MyBatisDao
  implements IRoleDao
{
  public int insertRole(Role role)
  {
    return super.insert(role);
  }

  public int updateRole(Role role)
  {
    return super.updateByPrimaryKeySelective(role);
  }

  public Role selectById(String id)
  {
    return (Role)selectByPrimaryKey(id);
  }

  public List<Role> selectRole(Role role)
  {
    return selectForList(this.namespace + ".selectRole", role);
  }

  public List<Role> selectRole(Role role, Pagination pagination)
  {
    return selectForList(this.namespace + ".selectRole", role, "role", pagination);
  }

  public int deleteRoleByLogic(Role role)
  {
    return deleteByLogic(role);
  }

  public int deleteRoleByPhysics(String id)
  {
    return deleteByPhysics(id);
  }

  public int retrieveRole(Role role)
  {
    return retrieve(role);
  }
}