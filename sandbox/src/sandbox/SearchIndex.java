package sandbox;

/**
 * Given an array of sorted integers (strictly increasing),
 * find an index, the value at which equals the index itself
 */
public class SearchIndex {

	int find(int[] nums) {
		return find(nums, 0, nums.length - 1);
	}
	
	int find(int[] nums, int start, int end) {
		if(start > end) {
			return -1;
		}
		if(start == end) {
			if(nums[start] == start) {
				return start;
			} else {
				return -1;
			}
		}
		int mid = (start + end)/2;
		if(nums[mid] == mid) {
			return mid;
		}
		if(nums[mid] < mid) {
			return find(nums, mid+1, end);
		} else {
			return find(nums, start, mid-1);
		}
	}
}
