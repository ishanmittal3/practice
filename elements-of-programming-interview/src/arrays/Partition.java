package arrays;

public class Partition {

	/**
	 * Write a function that takes an array A of length n and an index i into A,
	 * and rearranges the elements such that all elements less than A[i] appear first, 
	 * followed by elements equal to A[i], followed by elements greater than A[i]. 
	 * Your algorithm should have O(1) space complexity and O(n) time complexity.
	 * @param nums
	 * @param pivot_index
	 */
	void partition(int[] nums, int pivot_index) {
		int pivot = nums[pivot_index];
		int smaller = 0;
		int equal = 0;
		int larger = nums.length - 1;
		
		/* < : 0 - smaller-1
		 * = : smaller - equal-1
		 * > : larger+1 - end
		 * */
		while(larger >= equal) {
			int curr = nums[equal];
			if(curr == pivot) {
				equal++;
			}
			if(curr < pivot) {
				swap(nums, smaller++, equal++);
			} else {
				swap(nums, equal, larger--);
			}
		}
	}

	/**
	 * Assuming that keys take one of three values, reorder the array so
	 * that all objects with the same key appear together. 
	 * The order of the sub-arrays is not important.
	 * Use O(1) additional space and O(n) time.
	 * @param nums
	 */
	void reorder(int[] nums, int[] values) {
		int[] counts = new int[values.length];
		for(int num : nums) {
			for(int pos = 0; pos < values.length; pos++) {
				if(num == values[pos]) {
					counts[pos]++;
				}
			}
		}
		int currValuePos = 0;
		for(int pos = 0; pos < nums.length; pos++) {
			while(counts[currValuePos] > 0) {
				nums[pos++] = values[currValuePos];
				counts[currValuePos]--;
			}
			currValuePos++;
		}
	}
	
	private void swap(int[] nums, int pos1, int pos2) {
		int temp = nums[pos1];
		nums[pos1] = nums[pos2];
		nums[pos2] = temp;
	}
	
	/**
	 * Given an array A of n objects with Boolean-valued keys, reorder the
	 * array so that objects that have the key false appear first. The relative ordering of
	 * objects with key true should not change. Use O(1) additional space and O(n) time.
	 */
	void rearrange(Boolean[] values) {
		int currPos = values.length - 1;
		int truePos = values.length - 1;
		while(truePos >= 0) {
			if(values[truePos] == true) {
				values[currPos--] = values[truePos];
			}
			truePos--;
		}
		for(int pos = 0; pos <= currPos; pos++) {
			values[pos] = false;
		}
	}
}
