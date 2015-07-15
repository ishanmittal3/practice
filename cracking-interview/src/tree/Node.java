package tree;

public class Node {
	int data;
	Node left;
	Node right;
	
	public Node (int data) {
		this.data = data;
	}
	
	public boolean isLeaf() {
	    return this.left  == null &&
	           this.right == null;
	}
}
