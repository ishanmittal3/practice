package sandbox;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Surpasser {

	/**
	 * For each number in an array, find the first number
	 * to its right that is greater than this number
	 * If no such number exists, assign -1 
	 */
	List<Pair> getSurpassers(int[] nums) {
		Stack<Integer> stack = new Stack<Integer>();
		List<Pair> result = new ArrayList<Pair>();
		for(int num : nums) {
			while(!stack.isEmpty() && num > stack.peek()) {
				result.add(new Pair(stack.pop(), num));
			}
			stack.push(num);
		}
		while(!stack.isEmpty()) {
			result.add(new Pair(stack.pop(), -1));
		}
		return result;
	}
	
	class Pair {
		int a;
		int b;
		
		Pair(int a, int b) {
			this.a = a;
			this.b = b;
		}
	}
}
