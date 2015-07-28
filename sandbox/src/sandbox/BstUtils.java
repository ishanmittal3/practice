package sandbox;

public class BstUtils {

	public static <T extends Comparable<T>> BstNode<T> find(BstNode<T> root, T key) {
		if (root == null || key == null) {
			return null;
		}
		
		if (root.value.equals(key)) {
			return root;
		}
		
		if (key.compareTo(root.value) < 0) {
			return find(root.left, key);
		} else {
			return find(root.right, key);
		}
	}
	
	public static <T extends Comparable<T>> void delete(BstNode<T> root, T key) {
		delete(root, find(root, key));
	}
	
	public static <T extends Comparable<T>> void delete(BstNode<T> root, BstNode<T> key) {
		
		if (root == null || key == null) {
			return;
		}
		
		if (key.isLeaf()) {
			deleteLeafNode(root, key);
			return;
		}
		
		BstNode<T> parent = findParent(root, key);
		
		if (key.left == null) {
			if (parent.left == key) {
				parent.left = key.right;
			} else {
				parent.right = key.right;
			}
			return;
		}
		
		if (key.right == null) {
			if (parent.left == key) {
				parent.left = key.left;
			} else {
				parent.right = key.left;
			}
		}
		
		BstNode<T> nextNode = key.findNext();
		key.value = nextNode.value;
		delete(root, nextNode);
	}
	
	private static <T extends Comparable<T>> void deleteLeafNode(BstNode<T> root, BstNode<T> key) {
		
		BstNode<T> parent = findParent(root, key);
		
		if (parent.left == key) {
			parent.left = null;
		} else {
			parent.right = null;
		}
	}
	
	public static <T extends Comparable<T>> BstNode<T> findParent(BstNode<T> root, BstNode<T> key) {
		
		if (root == null || key == null || root == key) {
			return null;
		}
		
		if (root.left == key || root.right == key) {
			return root;
		} else {
			if (key.value.compareTo(root.value) < 0) {
				return findParent(root.left, key);
			} else {
				return findParent(root.right, key);
			}
		}
	}
}
