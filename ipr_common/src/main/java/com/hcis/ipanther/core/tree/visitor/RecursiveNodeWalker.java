package com.hcis.ipanther.core.tree.visitor;

import java.util.Collection;
import java.util.Iterator;

import com.hcis.ipanther.core.tree.TreeNodeVisitable;
import com.hcis.ipanther.core.tree.TreeVisitor;
import com.hcis.ipanther.core.tree.composite.Branch;
import com.hcis.ipanther.core.tree.composite.Leaf;



public class RecursiveNodeWalker implements TreeVisitor {
	private static final String module = RecursiveNodeWalker.class.getName();

	public void visitCollection(Collection list) {
		Iterator iter = list.iterator();
		while (iter.hasNext()) {
			TreeNodeVisitable node = (TreeNodeVisitable) (TreeNodeVisitable) iter
					.next();
			node.accept(this);
		}
	}

	public void visitforBranch(Branch branch) {
		visitforOneBranch(branch);
		recursiveDeep(branch);
	}

	private void recursiveDeep(Branch branch) {
		TreeNodeVisitable child = branch.getLeft();

		child.accept(this);

		while ((child = child.getRightSibling()) != null)
			child.accept(this);
	}

	public void visitforLeaf(Leaf leaf) {
		//Debug.logVerbose(" leaf=" + leaf.getKey());
	}

	public void visitforOneBranch(Branch branch) {
		//Debug.logVerbose("  Branch=" + branch.getKey());
	}
}