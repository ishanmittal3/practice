package arrays;

public class QuickSelect {

	// Average case time is O(n), worst case time is O(n^2)
	Integer getKth(int k, int[] nums) {
		if(k < 1 || nums == null) {
			return null;
		}
		return getKth(k, nums, 0, nums.length - 1);
	}
	
	Integer getKth(int k, int[] nums, int start, int end) {
		if(start > end) {
			return null;
		}
		int pivot = nums[start];
		int swapPos = end;
		int curr = start + 1;
		
		while(curr < swapPos) {
			if(nums[curr] <= pivot) {
				curr++;
			} else {
				swap(nums, curr, swapPos--);
			}
		}
		if(nums[curr] > pivot) {
			curr--;
		}
		swap(nums, start, curr);
		
		if(k == curr) {
			return nums[curr];
		}
		if(k > curr) {
			return getKth(k, nums, curr + 1, end);
		} else {
			return getKth(k, nums, start, curr - 1);
		}
	}
	
	void swap(int[] nums, int pos1, int pos2) {
		int temp = nums[pos1];
		nums[pos1] = nums[pos2];
		nums[pos2] = temp;
	}
}
