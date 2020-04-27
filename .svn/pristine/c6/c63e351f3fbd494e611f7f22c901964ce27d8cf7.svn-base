package com.hcis.ipanther.common.privilege.entity;

import java.io.Serializable;

public class PermissionNode
  implements Serializable
{
  private static final long serialVersionUID = -5480105658644205496L;
  public Permission permission;
  public static final String ROOT_NODE_ID = "rootNodeId";
  private Children children = new Children();

  public String toString()
  {
    String result = "{id : '" + (this.permission != null ? this.permission.getId() : "") + "'" + ", name : '" + (this.permission != null ? this.permission.getName() : "") + "'";

    if ((this.children != null) && (this.children.getSize() != 0))
      result = result + ", children : " + this.children.toString();
    else {
      result = result + ", leaf : true";
    }

    return result + "}";
  }

  public void sortChildren()
  {
    if ((this.children != null) && (this.children.getSize() != 0))
      this.children.sortChildren();
  }

  public void addChild(PermissionNode node)
  {
    this.children.addChild(node);
  }

  public Children getChildren() {
    return this.children;
  }

  public boolean hasChild() {
    if ((this.children != null) && (this.children.getSize() != 0)) {
      return true;
    }
    return false;
  }

  public Permission getPermission() {
    return this.permission;
  }
}