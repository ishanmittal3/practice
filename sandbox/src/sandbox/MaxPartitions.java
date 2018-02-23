package sandbox;

public class MaxPartitions {

	/**
	 * Given an integer array, find the maximum number of partitions
	 * it can be divided into, such that after sorting each partition,
	 * the whole array becomes sorted
	 */
	static int getMaxPartitions(int[] nums) {
		if (nums.length <= 1) {
			return 1;
		}
		int[] maxLeft = new int[nums.length - 1];
		int[] minRight = new int[nums.length - 1];
		
		maxLeft[0] = nums[0];
		int currLeft = 1;
		for (int pos = 1; pos < (nums.length - 1); pos++) {
			if (nums[pos] > maxLeft[currLeft - 1]) {
				maxLeft[currLeft] = nums[pos];
			} else {
				maxLeft[currLeft] = maxLeft[currLeft - 1];
			}
			currLeft++;
		}
		
		minRight[minRight.length - 1] = nums[nums.length - 1];
		int currRight = minRight.length - 2;
		for (int pos = (nums.length - 2); pos > 0; pos--) {
			if (nums[pos] < minRight[currRight + 1]) {
				minRight[currRight] = nums[pos];
			} else {
				minRight[currRight] = minRight[currRight + 1];
			}
			currRight--;
		}
		
		int numPartitions = 1;
		for (int pos = 0; pos < minRight.length; pos++) {
			if (maxLeft[pos] <= minRight[pos]) {
				numPartitions++;
			}
		}
		
		return numPartitions;
	}
	
	public static void main(String[] args) {
		int[] nums = new int[] {3, 2, 1, 5, 4};
		System.out.println(getMaxPartitions(nums));
		nums = new int[] {3, 2, 5, 4, 1};
		System.out.println(getMaxPartitions(nums));
	}
}
