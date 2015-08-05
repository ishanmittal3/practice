package sandbox;

public class Popular {

	int findPopular(int[] nums) {
		int n = nums.length;
		if (n < 8) {
			return nums[0]; // n/4 = 1; All numbers are popular
		}
		for (int pos = (n/4)-1; pos < nums.length; pos += n/4) {
			if (nums[pos] == nums[pos-1] || nums[pos] == nums[pos+1]) {
				int leftIndex = findMinIndex(nums, pos-(n/4)+1, pos, nums[pos]);
				int rightIndex;
				if (pos < n-1) {
					rightIndex = findMaxIndex(nums, pos, pos+(n/4)-2, nums[pos]);
				} else {
					rightIndex = n-1;
				}
				if (rightIndex - leftIndex + 1 >= (n/4)) {
					return nums[pos];
				}
			}
		}
		return DEFAULT_VALUE; // when no number is popular
	}

	int findMinIndex(int[] nums, int start, int end, int key) {
		if (start > end) {
			return -1;
		}
		if (start == end) {
			if (key == nums[start]) {
				return start;
			} else {
				return -1;
			}
		}
		int mid = (start+end)/2;
		if (key <= nums[mid]) {
			return findMinIndex(nums, start, mid, key);
		} else {
			return findMinIndex(nums, mid+1, end, key);
		}
	}

	int findMaxIndex(int[] nums, int start, int end, int key) {
		if (start > end) {
			return -1;
		}
		if (start == end) {
			if (key == nums[start]) {
				return start;
			} else {
				return -1;
			}
		}
		int mid = (start+end)/2;
		if (key < nums[mid]) {
			return findMaxIndex(nums, start, mid-1, key);
		} else {
			return findMaxIndex(nums, mid, end, key);
		}
	}
	
	static int DEFAULT_VALUE = 0; 
}
