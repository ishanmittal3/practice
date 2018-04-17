package tree;

public class TreeToList {
	
	// Convert a tree to a doubly linked list
	Node getList(Node node) {
	    if (node.left != null) {
	        Node leftNode = getList(node.left);
	        while (leftNode.right != null) {
	            leftNode = leftNode.right;
	        }
	        leftNode.right = node;
	        node.left = leftNode;
	    }
	    if (node.right != null) {
	        Node rightNode = getList(node.right);
	        node.right = rightNode;
	        rightNode.left = node;
	    }
	    while (node.left != null) {
	        node = node.left;
	    }
	    return node;
	}
}
