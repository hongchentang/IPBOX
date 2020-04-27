package com.hcis.ipanther.common.privilege.dao.impl.mybatis;

import com.hcis.ipanther.common.privilege.dao.IPrivilegeDao;
import com.hcis.ipanther.common.privilege.entity.Permission;
import com.hcis.ipanther.core.persistence.dao.MyBatisDao;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.session.SqlSession;

public class PrivilegeDao extends MyBatisDao
  implements IPrivilegeDao
{
  public List<Permission> selectPermissionByUserId(String userId)
  {
    return getSqlSession().selectList(this.namespace + ".selectPermissionByUserId", userId);
  }

  public Map<String, Permission> selectPermissionByRole(String roleId)
  {
    return getSqlSession().selectMap(this.namespace + ".selectPermissionByRole", roleId, "id");
  }
}