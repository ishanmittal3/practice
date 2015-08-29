package arrays;

public class TrappingRainWater {

	/**
	 * Given n non-negative integers representing an elevation map 
	 * where the width of each bar is 1, compute how much water 
	 * it is able to trap after raining.
	 */
	int collectWater(int[] heights) {
		// left[pos] contains max height to the left of pos
		int[] left = new int[heights.length];
		int max = heights[0];
		for(int pos = 0; pos < heights.length; pos++) {
			left[pos] = Math.max(heights[pos], max);
			max = left[pos];
		}
		
		// right[pos] contains max height to the right of pos
		int[] right = new int[heights.length];
		max = heights[heights.length - 1];
		for(int pos = heights.length - 1; pos >= 0; pos--) {
			right[pos] = Math.max(heights[pos], max);
			max = right[pos];
		}
		
		int water = 0;
		for(int pos = 0; pos < heights.length; pos++) {
			int waterLevel = Math.min(left[pos], right[pos]);
			water += waterLevel - heights[pos];
		}
		return water;
	}
}
