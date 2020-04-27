package com.hcis.ipanther.core.tree.composite;

import com.hcis.ipanther.core.tree.TreeNodeVisitable;
import com.hcis.ipanther.core.tree.TreeVisitor;


public class Leaf implements TreeNodeVisitable {
	private String key;
	private TreeNodeVisitable rightSibling;

	public Leaf(String key, TreeNodeVisitable rightSibling) {
		this.key = key;
		this.rightSibling = rightSibling;
	}

	public void accept(TreeVisitor visitor) {
		visitor.visitforLeaf(this);
	}

	public TreeNodeVisitable getRightSibling() {
		return this.rightSibling;
	}

	public String getKey() {
		return this.key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public void setRightSibling(TreeNodeVisitable rightSibling) {
		this.rightSibling = rightSibling;
	}
}