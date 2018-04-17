package stack;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

public class SanitizeParentheses {
	/**
	Given a string with alpha-numeric characters and parentheses, 
	return a string with balanced parentheses by removing the fewest characters possible. 
	You cannot add anything to the string.

			examples:
			balance("()") -> "()"
			balance("a(b)c)") -> "a(b)c"
			balance(")(") -> ""
			balance("(((((") -> ""
			balance("(()()(") -> "()()"
			balance(")(())(") -> "(())"
	 **/
			 
	String sanitize(String str) {
		char[] chars = str.toCharArray();
		Stack<Node> stack = new Stack<>();
		Set<Node> set = new HashSet<>();

		for (int pos = 0; pos < chars.length; pos++) {
			char c = chars[pos];
			if (c != '(' && c != ')') {
				continue;
			}
			Node newNode = new Node(c, pos);
			if (c == ')') {
				if (!stack.empty()) {
					Node prev = stack.pop();
					if (prev.c == '(') {
						set.add(prev);
						set.add(newNode);
					}
				} else {
					stack.push(newNode);
				}
			} else {
				stack.push(newNode);
			}
		}

		String result = "";
		for (int pos = 0; pos < chars.length; pos++) {
			if (chars[pos] == '(' || chars[pos] == ')') {
				if (set.contains(new Node(chars[pos], pos))) {
					result = result + chars[pos];
				}
			} else {
				result = result + chars[pos];
			}
		}

		return result;
	}

	class Node {
		char c;
		int pos;
		
		Node(char c, int pos) {
			this.c = c;
			this.pos = pos;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + getOuterType().hashCode();
			result = prime * result + c;
			result = prime * result + pos;
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Node other = (Node) obj;
			if (!getOuterType().equals(other.getOuterType()))
				return false;
			if (c != other.c)
				return false;
			if (pos != other.pos)
				return false;
			return true;
		}

		private SanitizeParentheses getOuterType() {
			return SanitizeParentheses.this;
		}
		
	}
}
