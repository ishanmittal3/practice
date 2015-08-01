package sandbox;

public class PostOrderTraversal {

	/**
	 * Determine whether an input array is a post-order traversal sequence 
	 * of a binary tree or not. Assume all numbers in an input array are unique.
	 * For example, 
	 * {5, 7, 6, 9, 11, 10, 8} - true 
	 * {7, 4, 6, 5} - false 
	 * @param nums - Post Order traversal
	 */
	boolean isPostOrderTraversal(int[] nums) {
		return isPostOrderTraversal(nums, 0, nums.length - 1);
	}
	
	boolean isPostOrderTraversal(int[] nums, int start, int end) {
		if(start >= end) {
			return true;
		}
		int root = nums[end];
		int pos = start;
		while(pos < end && nums[pos] <= root) {
			pos++;
		}
		int rightStart = pos;
		for(; pos < end; pos++) {
			if(nums[pos] <= root) {
				return false;
			}
		}
		return isPostOrderTraversal(nums, start, rightStart - 1) &&
			   isPostOrderTraversal(nums, rightStart, end - 1);
	}
}
