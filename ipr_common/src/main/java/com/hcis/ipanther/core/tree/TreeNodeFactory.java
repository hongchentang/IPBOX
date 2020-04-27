package com.hcis.ipanther.core.tree;

import java.util.Map;

import com.hcis.ipanther.core.tree.composite.Branch;
import com.hcis.ipanther.core.tree.composite.Leaf;
import com.hcis.ipanther.core.tree.model.TreeModel;



public class TreeNodeFactory
{
  private TreeModel treeModel;

  public TreeNodeFactory(TreeModel treeModel)
  {
    this.treeModel = treeModel;
  }

  public TreeNodeVisitable createNode(String key) {
    TreeNodeVisitable treeNode = null;
    if (key == null)
      return treeNode;

    treeNode = (TreeNodeVisitable)(TreeNodeVisitable)this.treeModel.getNodesCache().get(key);
    if (treeNode != null) {
      return treeNode;
    }

    char index = this.treeModel.findKey(key, '\1');
    if (index == 0) {
      return treeNode;
    }

    if (this.treeModel.getBinaryTreeAlgorithm().getLeftChild(index) == 0) {
      treeNode = createLeaf(key, index);
      this.treeModel.getNodesCache().put(key, treeNode);
      return treeNode;
    }

    treeNode = createBranch(key, index);
    this.treeModel.getNodesCache().put(key, treeNode);
    return treeNode;
  }

  private TreeNodeVisitable createLeaf(String key, char index)
  {
    char rightIndex = this.treeModel.getBinaryTreeAlgorithm().getRightSibling(index);
    String rightChildKey = null;
    if (rightIndex != 0)
      rightChildKey = this.treeModel.getNodeLongKeys().getKey(rightIndex);

    return new Leaf(key, createNode(rightChildKey));
  }

  private TreeNodeVisitable createBranch(String key, char index) {
    char leftChildIndex = this.treeModel.getBinaryTreeAlgorithm().getLeftChild(index);
    String leftChildKey = new String(this.treeModel.getNodeLongKeys().getKey(leftChildIndex));

    String rightChildKey = null;
    char rightIndex = this.treeModel.getBinaryTreeAlgorithm().getRightSibling(index);
    if (rightIndex != 0)
      rightChildKey = new String(this.treeModel.getNodeLongKeys().getKey(rightIndex));

    return new Branch(key, createNode(leftChildKey), createNode(rightChildKey));
  }
}