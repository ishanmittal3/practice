package binaryTrees;

public class Successor {
	
	Node getNextInOrder(Node node) {
		if(node == null) {
			return null;
		}
		Node parent = node.parent;
		if(parent == null || node.right != null) {
			return getLeftMostChild(node.right);
		}
		while(parent != null && parent.right == node) {
			node = parent;
			parent = node.parent;
		}
		return parent;
	}

	private Node getLeftMostChild(Node node) {
		if(node == null) {
			return null;
		}
		while(node.left != null) {
			node = node.left;
		}
		return node;
	}
	
	Node preOrderSuccessor(Node node) {
		if(node == null) {
			return null;
		}
		if(node.left != null) {
			return node.left;
		}
		if(node.right != null) {
			return node.right;
		}
		Node parent = node.parent;
		Node prev = node;
		while(parent != null && (parent.right == node || parent.right == null)) {
			prev = node;
			node = parent;
			parent = node.parent;
		}
		if(parent == null) {
			if(prev == node.right) {
				return null;
			} else {
				return node.right;
			}
		}
		return parent.right;
	}
	
	/*Node postOrderSuccessor(Node node) {
		if(node == null) {
			return null;
		}
		if(node.left != null) {
			return getLeftMostChild(node);
		}
		Node parent = node.parent;
		if(parent == null) {
			return getLeftMostChild(node.right);
		}
		while(parent != null && parent.right == null) {
			node = parent;
			parent = node.parent;
		}
		
	}*/
}
