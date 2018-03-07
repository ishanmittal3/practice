package stack;

import java.util.Stack;

public class LongestValidParentheses {

	public static int getLongestValidParens(String parens) {
	    Stack<Character> stack = new Stack<>();
	    int max = 0;
	    int length = 0;
	    for (int pos = 0; pos < parens.length(); pos++) {
	        char c = parens.charAt(pos);
	        if (c == '(') {
	            stack.push(c);
	            continue;
	        }
	        if (stack.isEmpty()) {
	            max = Math.max(max, length);
	            length = 0;
	        } else {
	            char top = stack.pop();
	            length += 2;
	        }
	    }
	    max = Math.max(max, length);
	    return max;
	}
	
	public static void main(String[] args) {
		System.out.println(getLongestValidParens("((()))()")); // 8
		System.out.println(getLongestValidParens("(())))))))(()()()())")); // 10
		System.out.println(getLongestValidParens("))))((((")); // 0
		System.out.println(getLongestValidParens("((((())))))")); // 10
		System.out.println(getLongestValidParens("()()()))))))()()()")); // 6
		System.out.println(getLongestValidParens("))))((()")); // 2
	}

}
