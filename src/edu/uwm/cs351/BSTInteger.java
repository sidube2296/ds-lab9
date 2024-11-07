package edu.uwm.cs351;

public class BSTInteger {
	public BSTNode root;
	public BSTNode current;
	
	public BSTInteger() {
		root = null;
		current = null;
	}
	
	//examples for testing with BSTApplet
	public BSTInteger(int i) {
		if(i == 1) {
			root = new BSTNode(100, null);
			root.left = new BSTNode(50, root);
			root.right = new BSTNode(150, root);
			root.left.left = new BSTNode(10, root.left);
			root.left.left.right = new BSTNode(25, root.left.left);
			root.left.right = new BSTNode(90, root.left);
			root.left.right.left = new BSTNode(75, root.left.right);
			root.left.right.left.left = new BSTNode(60, root.left.right.left);
			root.right.left = new BSTNode(125, root.right);
			root.right.left.right = new BSTNode(130, root.right.left);
			root.right.left.right.right = new BSTNode(135, root.right.left.right);
			root.right.right = new BSTNode(200, root.right);
		}
		else if (i == 2) {
			root = new BSTNode(100, null);
			root.left = new BSTNode(50, root);
			root.left.left = new BSTNode(10, root.left);
			root.left.left.right = new BSTNode(25, root.left.left);
			root.left.right = new BSTNode(90, root.left);
			root.left.right.left = new BSTNode(75, root.left.right);
			root.left.right.left.left = new BSTNode(60, root.left.right.left);
		}
		else if (i == 3) {
			root = new BSTNode(100, null);
		}		
		else if (i == 4) {
			root = null;
		}
		current = null;
	}

	/**
	 * Node Class
	 *
	 */
	public class BSTNode {
		public Integer data;
		public BSTNode left, right, parent;

		public BSTNode(int v, BSTNode p) {
			data = v;
			parent = p;
		}
	}

	public boolean isCurrent() {
		return current!=null;
	}
	
	public void advance() {
        if (!isCurrent()) throw new IllegalStateException("no current");
        // TODO find the next node in the tree
	}
	
	public void start() {
		current = root;
		if(current != null)
			while(current.left != null) current = current.left;
	}
	
	public Integer getCurrent() {
		if (!isCurrent()) throw new IllegalStateException("no current");
		return current.data;
	}
	
	public void reset() {
		current = null;
	}
	
}
