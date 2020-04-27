package com.hcis.ipanther.core.tree.model;

public class BinaryTreeAlgorithm
{
  private char[] leftChildren;
  private char[] rightSiblings;
  char nextIndex = '\2';

  public BinaryTreeAlgorithm(int size)
  {
    this.leftChildren = new char[size + 1];
    this.rightSiblings = new char[size + 1];

    setNull(1);
  }

  public void setNull(int i) {
    this.leftChildren[i] = '\0';
    this.rightSiblings[i] = '\0';
  }

  public char[] getLeftChildren()
  {
    return this.leftChildren;
  }

  public char getLeftChild(int i) {
    return this.leftChildren[i];
  }

  public void setLeftChild(int i, char value)
  {
    this.leftChildren[i] = value;
  }

  public char getNextIndex()
  {
    return this.nextIndex;
  }

  public char[] getRightSiblings()
  {
    return this.rightSiblings;
  }

  public char getRightSibling(int i)
  {
    return this.rightSiblings[i];
  }

  public void setRightSibling(int i, char value) {
    this.rightSiblings[i] = value;
  }

  public void setNextIndex(char nextIndex)
  {
    this.nextIndex = nextIndex;
  }
}