package com.hcis.ipanther.common.privilege.entity;

import com.google.common.collect.Lists;
import java.io.Serializable;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class Children
  implements Serializable
{
  private static final long serialVersionUID = -5779232821321443868L;
  private List<PermissionNode> list = Lists.newArrayList();

  public List<PermissionNode> getList() {
    return this.list;
  }

  public int getSize() {
    return this.list.size();
  }

  public void addChild(PermissionNode node) {
    this.list.add(node);
  }

  public String toString()
  {
    String result = "[";
    for (Iterator it = this.list.iterator(); it.hasNext(); ) {
      result = result + ((PermissionNode)it.next()).toString();
      result = result + ",";
    }
    result = result.substring(0, result.length() - 1);
    result = result + "]";
    return result;
  }

  public void sortChildren()
  {
    Collections.sort(this.list, new NodeSequenceComparator());

    for (Iterator it = this.list.iterator(); it.hasNext(); )
      ((PermissionNode)it.next()).sortChildren();
  }
}