package trees;

import java.util.Stack;

public class KthInBst {

	/**
	 * Given a binary search tree, write a function 
	 * to find the kth smallest element in it. 
	 * (1 ≤ k ≤ BST's total elements)
	 */
	Node kthSmallest(Node root, int k) {
		Stack<Node> stack = new Stack<Node>();
		Node curr = root;
		stack.push(root);
		while(!stack.isEmpty()) {
			while(curr.left != null) {
				stack.push(curr.left);
				curr = curr.left;
			}
			if(k == 1) {
				return curr;
			}
			k--;
			while(curr.right == null && !stack.isEmpty()) {
				curr = stack.pop();
				if(k == 1) {
					return curr;
				}
				k--;
			}
			if(stack.isEmpty()) {
				return null;
			}
			stack.push(curr.right);
		}
		return null;
	}
}
