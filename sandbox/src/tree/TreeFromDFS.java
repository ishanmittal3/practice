package tree;

import java.util.Stack;

public class TreeFromDFS {

	/**
	 * Given the DFS traversal of a tree and its depth,
	 * construct the tree
	 */
	Node getTree(int[] nums, int depth) {
		Node root = new Node(nums[0]);
		Stack<Node> stack = new Stack<>();
		stack.push(root);
		int currDepth = 0;
		for (int pos = 1; pos < nums.length; pos++) { 
			int num = nums[pos];
			if (currDepth == depth) {
				stack.pop();
				currDepth--;
			}
			Node top = stack.peek();
			while ((top.left != null) && (top.right != null)) {
				stack.pop();
				currDepth--;
				top = stack.peek();
			}
			Node newNode = new Node(num);
			if (top.left == null) {
				top.left = newNode;
			} else {
				top.right = newNode;
			}
			currDepth++;
		}
		
		return root;
	}
}
