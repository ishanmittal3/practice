package tree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;

public class GetPath {

	/**
	 * Given a binary tree and a node, get the path to reach that node
	 */
	List<Node> getPath(Node root, int key) {
		if (root == null) {
			return null;
		}
		Stack<Node> stack = new Stack<>();
		stack.push(root);
		Set<Node> visited = new HashSet<>();
		while (!stack.isEmpty()) {
			Node curr = stack.peek();
			// This is the difference from the usual traversal - don't pop the first time ,
			// but only when its children are exhausted (and therefore no path exists there)
			if (visited.contains(curr)) {
				stack.pop();
				continue;
			}
			visited.add(curr);
			if (curr.value == key) {
				break;
			}
			if (curr.left != null) {
				stack.push(curr.left);
			}
			if (curr.right != null) {
				stack.push(curr.right);
			}
		}
		
		List<Node> path = new ArrayList<>();
		while (!stack.isEmpty()) {
			Node curr = stack.pop();
			if (visited.contains(curr)) {
				path.add(curr);
			}
		}
		Collections.reverse(path);
		return path;
	}
}
