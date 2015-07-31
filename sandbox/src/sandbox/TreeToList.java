package sandbox;

/**
 * Convert a binary search tree to a sorted double-linked list. 
 * We can only change the target of pointers, but cannot create any new nodes
 */
public class TreeToList {

	Node makeList(Node node) {
		if(node.left != null) {
			Node left = makeList(node.left);
			Node last = getLast(left);
			last.right = node;
			node.left = last;
		}
		if(node.right != null) {
			Node right = makeList(node.right);
			node.right = right;
			right.left = node;
		}
		return getFirst(node);
	}
	
	private Node getFirst(Node node) {
		while(node.left != null) {
			node = node.left;
		}
		return node;
	}

	private Node getLast(Node node) {
		while(node.right != null) {
			node = node.right;
		}
		return node;
	}

	static class Node {
		int value;
		Node left;
		Node right;
	}
}
