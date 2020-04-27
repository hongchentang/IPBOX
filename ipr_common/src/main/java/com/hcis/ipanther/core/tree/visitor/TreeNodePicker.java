package com.hcis.ipanther.core.tree.visitor;

import java.util.ArrayList;
import java.util.List;

import com.hcis.ipanther.core.tree.composite.Branch;
import com.hcis.ipanther.core.tree.composite.Leaf;

import org.apache.log4j.Logger;

public class TreeNodePicker extends RecursiveNodeWalker
{
  private static final Logger logger = Logger.getLogger(TreeNodePicker.class);
  private List result;

  public TreeNodePicker()
  {
    this.result = new ArrayList();
  }

  public void visitforLeaf(Leaf leaf) {
   // Debug.logVerbose(" leaf=" + leaf.getKey());
    addToList(leaf.getKey());
  }

  public void visitforOneBranch(Branch branch)
  {
   // Debug.logVerbose("  Branch=" + branch.getKey());
    addToList(branch.getKey());
  }

  private void addToList(String messageId) {
    this.result.add(messageId);
  }

  public List getResult()
  {
    return this.result;
  }
}