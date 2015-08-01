package sandbox;

public class Maxima {

	/**
	 * Find the index of the maximum element
	 * in an array that first increases then decreases
	 */
	int maxima(int[] nums) {
		return maxima(nums, 0, nums.length - 1);
	}
	
	int maxima(int[] nums, int start, int end) {
		if(end - start < 2) {
			return -1;
		}
		int mid = (start + end)/2;
		if(nums[mid] > nums[mid-1] && 
		   nums[mid] > nums[mid+1]) {
			return mid;
		}
		if(nums[mid] > nums[mid-1] &&
		   nums[mid] < nums[mid+1]) {
			return maxima(nums, mid, end);
		}
		if(nums[mid] < nums[mid-1] &&
		   nums[mid] > nums[mid+1]) {
			return maxima(nums, start, mid);
		}
		return -1;
	}
}
