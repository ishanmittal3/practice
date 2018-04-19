package sandbox;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Fibonacci {
	
	/**
	 * Given a fibonacci list, find a set of distinct numbers from this list that sum up to a given number
	(It's proven that there will always be such a combination for any number)
	 **/
	
	private static final int LIMIT = 100;
	private static int[] fib = getFib(LIMIT);

	static int[] getFib(int limit) {
	    int[] fib = new int[limit];
	    fib[0] = 1;
	    fib[1] = 1;
	    for (int pos = 2; pos < fib.length; pos++) {
	        fib[pos] = fib[pos-1] + fib[pos-2];
	    }
	    return fib;
	}

	public static List<Integer> getComb(int key) {
	    int end = search(fib, key);
	    return getComb(key, end);
	}

	private static List<Integer> getComb(int key, int end) {
	    if ((end < 1) || (key <= 0)) {
	        return new ArrayList<Integer>();
	    }
	    if (fib[end] == key) {
	        return Arrays.asList(key);
	    }
	    int newKey = key - fib[end];
	    List<Integer> comb = getComb(newKey, search(fib, newKey, end));
	    if (!comb.isEmpty()) {
	        comb.add(fib[end]);
	        return comb;
	    }
	    return getComb(key, end-1);
	}

	private static int search(int[] nums, int key) {
		return search(nums, key, (nums.length - 1));
	}

	private static int search(int[] nums, int key, int end) {
		return search(nums, key, 1, end);
	}

	private static int search(int[] nums, int key, int start, int end) {
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
		int mid = (start + end)/2;
		if (key == nums[mid]) {
			return mid;
		}
		if (key < nums[mid]) {
			return search(nums, key, start, mid-1);
		} else {
			// key >= nums[mid]
			if (start == mid) {
				if (key == nums[end]) {
					return end;
				} else {
					return start;
				}
			}
			return search(nums, key, mid, end);
		}
	}
}
