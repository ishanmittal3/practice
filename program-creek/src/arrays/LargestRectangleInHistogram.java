package arrays;

import java.util.Stack;

public class LargestRectangleInHistogram {

	/**
	 * Given n non-negative integers representing 
	 * the histogram's bar height where the width of each bar is 1, 
	 * find the area of largest rectangle in the histogram.
	 */
	int largestRectangleArea(int[] heights) {
		Stack<Integer> stack = new Stack<Integer>();
		int maxArea = 0;
		int pos = 0;
		while(pos < heights.length) {
			if(stack.isEmpty() || heights[pos] >= heights[stack.peek()]) {
				stack.push(pos++);
			} else {
				int currPos = stack.pop();
				int height = heights[currPos];
				int width = pos - currPos;
				maxArea = Math.max(maxArea, width * height);
			}
		}
		while(!stack.isEmpty()) {
			int currPos = stack.pop();
			int height = heights[currPos];
			int width = pos - currPos;
			maxArea = Math.max(maxArea, width * height);
		}
		return maxArea;
	}
}
