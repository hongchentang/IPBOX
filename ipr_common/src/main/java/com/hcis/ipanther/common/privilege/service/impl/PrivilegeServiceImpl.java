package com.hcis.ipanther.common.privilege.service.impl;

import com.hcis.ipanther.common.privilege.dao.IPrivilegeDao;
import com.hcis.ipanther.common.privilege.entity.Permission;
import com.hcis.ipanther.common.privilege.entity.PermissionNode;
import com.hcis.ipanther.common.privilege.service.IPermissionService;
import com.hcis.ipanther.common.privilege.service.IPrivilegeService;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import javax.annotation.Resource;
import org.apache.commons.lang3.StringUtils;
import org.springframework.aop.framework.AopContext;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service("privilegeService")
public class PrivilegeServiceImpl
  implements IPrivilegeService
{

  @Resource
  private IPermissionService permissionService;

  @Resource
  private IPrivilegeDao privilegeDao;

  public List<Permission> queryAllPermissionByUserId(String userId)
  {
    return this.privilegeDao.selectPermissionByUserId(userId);
  }

  public PermissionNode getPermissionNode(String nodeId)
  {
    Map permissionNodeMap = ((IPrivilegeService)AopContext.currentProxy()).getPermissionNodeMap();
    if (permissionNodeMap != null) {
      if (StringUtils.isEmpty(nodeId)) {
        return (PermissionNode)permissionNodeMap.get("rootNodeId");
      }
      return (PermissionNode)permissionNodeMap.get(nodeId);
    }

    return null;
  }

  @Cacheable(value={"privilegeCache"}, key="'permissionNodeMap'")
  public Map<String, PermissionNode> getPermissionNodeMap() {
    Permission permission = new Permission();
    permission.setIsDeleted("N");
    List<Permission> permissions = this.permissionService.queryPermission(permission);
    Map permissionNodeMap = new HashMap();
    if ((permissions != null) && (!permissions.isEmpty())) {
      PermissionNode root = new PermissionNode();
      Permission pss = new Permission();
      pss.setId("rootNodeId");
      root.permission = pss;

      for (Permission ps : permissions) {
        PermissionNode pn = new PermissionNode();
        pn.permission = ps;
        permissionNodeMap.put(ps.getId(), pn);
      }
      Set entrySet = permissionNodeMap.entrySet();
      for (Iterator it = entrySet.iterator(); it.hasNext(); ) {
        PermissionNode pn = (PermissionNode)((Map.Entry)it.next()).getValue();
        if (StringUtils.isEmpty(pn.permission.getParentId())) {
          root.addChild(pn);
        }
        else if (permissionNodeMap.containsKey(pn.permission.getParentId())) {
          ((PermissionNode)permissionNodeMap.get(pn.permission.getParentId())).addChild(pn);
        }
      }

      permissionNodeMap.put("rootNodeId", root);
      return permissionNodeMap;
    }

    return permissionNodeMap;
  }

  public PermissionNode getUserPermissionNode(String userId)
  {
    List<Permission> permissions = ((IPrivilegeService)AopContext.currentProxy()).queryAllPermissionByUserId(userId);
    Map permissionNodeMap = new HashMap();
    if ((permissions != null) && (!permissions.isEmpty())) {
      PermissionNode root = new PermissionNode();
      Permission pss = new Permission();
      pss.setId("rootNodeId");
      root.permission = pss;

      for (Permission ps : permissions) {
        if (ps.getDisplay().equals("Y")) {
          PermissionNode pn = new PermissionNode();
          pn.permission = ps;
          permissionNodeMap.put(ps.getId(), pn);
        }
      }
      Set entrySet = permissionNodeMap.entrySet();
      for (Iterator it = entrySet.iterator(); it.hasNext(); ) {
        PermissionNode pn = (PermissionNode)((Map.Entry)it.next()).getValue();
        if (StringUtils.isEmpty(pn.permission.getParentId())) {
          root.addChild(pn);
        }
        else if (permissionNodeMap.containsKey(pn.permission.getParentId())) {
          ((PermissionNode)permissionNodeMap.get(pn.permission.getParentId())).addChild(pn);
        }
      }

      return root;
    }

    return null;
  }
}