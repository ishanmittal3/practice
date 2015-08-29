package arrays;

import java.util.HashSet;
import java.util.Set;

public class LongestConsecutiveSequence {

	/**
	 * Given an unsorted array of integers, 
	 * find the length of the longest consecutive elements sequence.
	 * For example, given [100, 4, 200, 1, 3, 2], 
	 * the longest consecutive elements sequence should be [1, 2, 3, 4]. 
	 * Its length is 4.
	 * 
	 * Your algorithm should run in O(n) complexity.
	 */
	int longestConsecutiveSequence(int[] nums) {
		Set<Integer> set = new HashSet<Integer>();
		for(int num : nums) {
			set.add(num);
		}
		int count = 1;
		while(!set.isEmpty()) {
			int curr = set.iterator().next();
			set.remove(curr);
			int currCount = 1;
			
			int prev = curr - 1;
			while(set.contains(prev)) {
				currCount++;
				set.remove(prev--);
			}
			
			int next = curr + 1;
			while(set.contains(next)) {
				currCount++;
				set.remove(next);
			}
			
			count = Math.max(count, currCount);
		}
		return count;
	}
}
