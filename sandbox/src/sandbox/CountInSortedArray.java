package sandbox;

public class CountInSortedArray {
	
	public int count(int nums[], int key) {
		int firstIndex = findFirstInstance(nums, key, 0, nums.length-1);
		int lastIndex = findLastInstance(nums, key, 0, nums.length-1);
		if (firstIndex < 0 || lastIndex < 0) {
			return 0;
		}
		return lastIndex - firstIndex + 1;
	}
	
	private int findFirstInstance(int[] nums, int key, int start, int end) {
		if (start > end) {
			return -1;
		}
		if (start == end) {
			if (nums[start] == key) {
				return start;
			} else {
				return -1;
			}
		}
		int mid = (start + end)/2;
		if (key > nums[mid]) {
			return findFirstInstance(nums, key, mid+1, end);
		} else {
			return findFirstInstance(nums, key, start, mid);
		}
	}
	
	private int findLastInstance(int[] nums, int key, int start, int end) {
		if (start > end) {
			return -1;
		}
		if (start == end) {
			if (nums[start] == key) {
				return start;
			} else {
				return -1;
			}
		}
		int mid = (start + end)/2;
		if (key < nums[mid]) {
			return findLastInstance(nums, key, start, mid-1);
		} else {
			return findLastInstance(nums, key, mid, end);
		}
	}
}
