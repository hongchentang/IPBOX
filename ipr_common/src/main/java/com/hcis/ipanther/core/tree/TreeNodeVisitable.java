package com.hcis.ipanther.core.tree;

public abstract interface TreeNodeVisitable
{
  public abstract void accept(TreeVisitor paramTreeVisitor);

  public abstract TreeNodeVisitable getRightSibling();
}