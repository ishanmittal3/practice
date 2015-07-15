package tree;

public class BinarySearchTree extends BinaryTree {

	public BinarySearchTree(int data) {
		super(data);
	}
	
	public void insert(int newData) {
		insert(root, newData);
	}

	private void insert(Node node, int newData) {
		if (newData < node.data) {
			if (node.left == null) {
				node.left = new Node(newData);
			}
			else {
				insert(node.left, newData);
			}
		}
		else {
			if (node.right == null) {
				node.right = new Node(newData);
			}
			else {
				insert(node.right, newData);
			}
		}
	}

	public int minValue() {
	    return minValue(root);
	}

	public int minValue(Node node) {
	    if (node.left != null) {
	        return minValue(node.left);
	    }
	    else {
	        return node.data;
	    }
	}
	
	public static BinarySearchTree createMinimalHeightBst(int[] array) {
		if (array.length == 0) {
			return null;
		}
		BinarySearchTree bst = new BinarySearchTree(0);
		bst.root = createBstNode(array, 0, array.length -1);
		return bst;
	}

	private static Node createBstNode(int[] array, int start, int end) {
		if (start > end) {
			return null;
		}
		int middleIndex = (start + end)/2;
		Node node = new Node(array[middleIndex]);
		node.left = createBstNode(array, start, middleIndex -1);
		node.right = createBstNode(array, middleIndex +1, end);
		return node;
	}
	
	public static int countTrees(int numKeys) {
	    if (numKeys < 1) {
	        return 0;
	    }
	    if (numKeys == 1) {
	        return 1;
	    }
	    
	    int numTrees = 0;
	    for (int key = 1; key <= numKeys; key++) {
	        int numLeftTrees  = countTrees(key-1);
	        int numRightTrees = countTrees(numKeys - key);
	        if (numLeftTrees == 0 || numRightTrees == 0) {
	        	numTrees += numLeftTrees + numRightTrees;
	        }
	        else {
	        	numTrees += numLeftTrees * numRightTrees;
	        }
	    }
	    return numTrees;
	}

}
