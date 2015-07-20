package searching;

public class LocalMin {
	
	/**
	 * Let A be an unsorted array of n integers, with A[0] ≥ A[1] and
	   A[n − 2] ≤ A[n − 1]. Call an index i a local minimum if A[i] 
	   is less than or equal to its neighbors. 
	   How would you efficiently find a local minimum, if one exists?
	 * @param nums
	 * @return index of a local minimum (or -1 if none exists)
	 */
	int localMin(int[] nums) {
		return localMin(nums, 0, nums.length-1);
	}
	
	int localMin(int[] nums, int start, int end) {
		// A[0] ≥ A[1] and A[n − 2] ≤ A[n − 1] imply that
		// there are at least 3 elements in the array
		if(end - start < 2) {
			if(start > 0 && isLocalMin(nums, start)) {
				return start;
			}
			if(end < nums.length-1 && isLocalMin(nums, end)) {
				return end;
			}
			return -1;
		}
		int mid = (start + end)/2;
		if(isLocalMin(nums, mid)) {
			return mid;
		}
		if(nums[mid-1] < nums[mid] && mid-1 > start) {
			return localMin(nums, start, mid);
		}
		if(nums[mid+1] < nums[mid] && mid+1 < end) {
			return localMin(nums, mid, end);
		}
		return -1;
	}

	private boolean isLocalMin(int[] nums, int pos) {
		if(nums.length < 3 || pos == 0 || pos == nums.length-1) {
			return false;
		}
		return nums[pos-1] > nums[pos] &&
			   nums[pos+1] > nums[pos];
	}
}
