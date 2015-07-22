package arrays;

public class MaxDifference {

	// Max diff between any two numbers
	int maxDiff(int[] nums) {
		int min = nums[0];
		int maxDiff = 0;
		for(int pos = 1; pos < nums.length; pos++) {
			maxDiff = Math.max(maxDiff, nums[pos] - min);
			min = Math.min(nums[pos], min);
		}
		return maxDiff;
	}
	
	// Find the length of a longest subarray
	// all of whose entries are equal
	int longestSubArrayEqual(int[] nums) {
		int maxLength = 1;
		int prev = 0;
		for(int pos = 0; pos < nums.length; pos++) {
			if(nums[pos] == nums[pos-1]) {
				maxLength = Math.max(maxLength, pos - prev + 1);
			} else {
				prev = pos;
			}
		}
		return maxLength;
	}
}
