package com.hcis.ipanther.core.tree.composite;

import com.hcis.ipanther.core.tree.TreeNodeVisitable;
import com.hcis.ipanther.core.tree.TreeVisitor;


public class Branch implements TreeNodeVisitable {
	private String key;
	private TreeNodeVisitable left;
	private TreeNodeVisitable right;

	public Branch(String key, TreeNodeVisitable left, TreeNodeVisitable right) {
		this.key = key;
		this.left = left;
		this.right = right;
	}

	public void accept(TreeVisitor visitor) {
		visitor.visitforBranch(this);
	}

	public TreeNodeVisitable getRightSibling() {
		return this.right;
	}

	public String getKey() {
		return this.key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public TreeNodeVisitable getLeft() {
		return this.left;
	}

	public void setLeft(TreeNodeVisitable left) {
		this.left = left;
	}

	public TreeNodeVisitable getRight() {
		return this.right;
	}

	public void setRight(TreeNodeVisitable right) {
		this.right = right;
	}
}