package bst;

/**
 * Write a function that takes a BST T and a key k, and returns the
 * first entry larger than k that would appear in an inorder traversal. 
 * If k is absent or no key larger than k is present, return null.
 */
public class SearchKey {
	int firstKey(Node root, int key) {
		Node node = root;
		Integer result = null;
		while(node != null) {
			if(node.value <= key) {
				node = node.right;
			} else {
				if(result == null) {
					result = node.value;
				} else {
					result = Math.min(result, node.value);
				}
				node = node.left;
			}
		}
		return result;
	}
}
