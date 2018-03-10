package tree;

import java.util.Iterator;
import java.util.Stack;

public class GetIterator {

	class Node {
		int data;
		Node left;
		Node right;
	}
	
	/**
	 * An iterator that gives in-order traversal 
	 * over a tree
	 */
	static class TreeIterator implements Iterator<Node> {
		Stack <Node> stack;
		
		TreeIterator() {
			stack = new Stack<>();
		}
		
		@Override
		public boolean hasNext() {
			return !stack.isEmpty();
		}

		@Override
		public Node next() {
			Node top = stack.pop();
			Node curr = top.right;
			while (curr != null) { 
				stack.push(curr);
				curr = curr.left;
			}
			return top;
		}
		
	}
	
	static Iterator<Node> createIterator(Node root) {
		TreeIterator iterator = new TreeIterator();
		Node curr = root;
		while (curr != null) {
			iterator.stack.push(curr);
			curr = curr.left;
		}
		return iterator;
	}
}
