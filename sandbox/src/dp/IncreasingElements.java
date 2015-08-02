package dp;

import java.util.ArrayList;
import java.util.List;

public class IncreasingElements {

	/**
	 * Given an array of integers A, 
	 * find three indices i, j, k, 
	 * such that i<j<k and A[i]<A[j]<A[k].
	 */
	List<Integer> findIndices(int[] nums) {
		int[] lesser = new int[nums.length];
		int[] greater = new int[nums.length];
		
		lesser[0] = 0;
		for(int pos = 1; pos < nums.length; pos++) {
			lesser[pos] = nums[pos] <= nums[lesser[pos-1]] ?
					pos : lesser[pos-1];
		}
		greater[nums.length-1] = nums.length-1;
		for(int pos = nums.length-2; pos >= 0; pos--) {
			greater[pos] = nums[pos] >= nums[greater[pos+1]] ?
					pos : greater[pos+1];
		}
		List<Integer> result = null;
		for(int pos = 1; pos < nums.length-2; pos++) {
			if(lesser[pos] < pos && greater[pos] > pos) {
				result = new ArrayList<Integer>();
				result.add(lesser[pos]);
				result.add(pos);
				result.add(greater[pos]);
			}
		}
		return result;
	}
}
