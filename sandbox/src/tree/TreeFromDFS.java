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
			if (currDepth == depth-1) {
				if (stack.peek().left == null) {
					stack.peek().left = new Node(num);
				} else if (stack.peek().right == null) {
					stack.peek().right = new Node(num);
				} else {
					while (!stack.isEmpty() && (stack.peek().left != null) && (stack.peek().right != null)) {
						Node top = stack.pop();
						currDepth--;
						if (stack.isEmpty()) {
							break;
						}
						if (stack.peek().left != null) {
							stack.peek().left = top;
						} else if (stack.peek().right != null) {
							stack.peek().right = top;
						}
					}
				}
			} else {
				stack.push(new Node(num));
				currDepth++;
			}
		}

		while (!stack.isEmpty()) {
			Node top = stack.pop();
			if (stack.isEmpty()) {
				break;
			}
			if (stack.peek().left != null) {
				stack.peek().left = top;
			} else if (stack.peek().right != null) {
				stack.peek().right = top;
			}
		}
		
		return root;
	}
}
