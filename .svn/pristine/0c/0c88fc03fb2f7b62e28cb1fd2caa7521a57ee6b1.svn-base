package com.hcis.ipanther.core.tree.model;

import java.util.HashMap;
import java.util.Map;

public class TreeModel
{
  private String rootKey;
  private NodeStringKeys keys;
  private BinaryTreeAlgorithm bta;
  private Map nodesCache;

  public TreeModel(String rootKey, int size)
  {
    this.rootKey = rootKey;
    this.keys = new NodeStringKeys(rootKey, size);
    this.bta = new BinaryTreeAlgorithm(size);
    this.nodesCache = new HashMap(size);
  }

  public void addChild(String parentKey, String newKey)
  {
    char parentIndex = findKey(parentKey, '\1');
    if (parentIndex == 0) {
      throw new IllegalArgumentException("Parent key " + parentKey + 
        " not found when adding child " + newKey + ".");
    }

    char nextIndex = this.bta.getNextIndex();
    this.keys.setKey(nextIndex, newKey);
    this.bta.setNull(nextIndex);

    if (this.bta.getLeftChild(parentIndex) == 0)
    {
      this.bta.setLeftChild(parentIndex, nextIndex);
    }
    else
    {
      long siblingIndex = this.bta.getLeftChild(parentIndex);
      while (this.bta.getRightSibling(new Long(siblingIndex).intValue()) != 0) {
        siblingIndex = this.bta.getRightSibling(new Long(siblingIndex).intValue());
      }

      this.bta.setRightSibling(new Long(siblingIndex).intValue(), nextIndex);
    }

    nextIndex = (char)(nextIndex + '\1');
    this.bta.setNextIndex(nextIndex);
  }

  public char findKey(String value, char startIndex)
  {
    if (startIndex == 0) {
      return '\0';
    }

    if (this.keys.getKey(startIndex).equals(value)) {
      return startIndex;
    }

    char siblingIndex = this.bta.getLeftChild(startIndex);
    while (siblingIndex != 0) {
      char recursiveIndex = findKey(value, siblingIndex);
      if (recursiveIndex != 0) {
        return recursiveIndex;
      }

      siblingIndex = this.bta.getRightSibling(siblingIndex);
    }

    return '\0';
  }

  public BinaryTreeAlgorithm getBinaryTreeAlgorithm()
  {
    return this.bta;
  }

  public NodeStringKeys getNodeLongKeys()
  {
    return this.keys;
  }

  public String getRootKey()
  {
    return this.rootKey;
  }

  public void setRootKey(String rootKey)
  {
    this.rootKey = rootKey;
  }

  public Map getNodesCache()
  {
    return this.nodesCache;
  }
}