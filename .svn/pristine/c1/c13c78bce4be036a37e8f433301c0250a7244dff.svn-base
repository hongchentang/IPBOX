package com.hcis.ipanther.common.privilege.entity;

import java.util.Comparator;

public class NodeSequenceComparator
  implements Comparator
{
  public int compare(Object o1, Object o2)
  {
    if ((o1 != null) && (o2 != null)) {
      int j1 = ((PermissionNode)o1).permission.getSequence();
      int j2 = ((PermissionNode)o2).permission.getSequence();
      return j1 == j2 ? 0 : j1 < j2 ? -1 : 1;
    }
    return 0;
  }
}