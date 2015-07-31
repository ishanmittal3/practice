package dp;

import java.util.HashMap;
import java.util.Map;

public class CutRope {

	/**
	 * Given a rope with length n, 
	 * how should the rope be cut into m parts with length 
	 * n[0], n[1], ..., n[m-1], in order to 
	 * get the maximum	 product of n[0]*n[1]* ... *n[m-1]? 
	 * We have to cut at least once. 
	 * Additionally, length of the whole rope, 
	 * as well as length of each part are integers.
	 */
	int maxProduct(int length) {
		Map<Integer, Integer> results = new HashMap<Integer, Integer>();
		results.put(1, 1);
		return maxProduct(length, results);
	}
	
	int maxProduct(int length, Map<Integer, Integer> results) {
		if(results.containsKey(length)) {
			return results.get(length);
		}
		int max = 0;
		for(int size = 1; size <= length/2; size++) {
			max = Math.max(max, maxProduct(size) * maxProduct(length - size));
		}
		results.put(length, max);
		return max;
	}
	
	int maxProductIter(int length) {
		Map<Integer, Integer> results = new HashMap<Integer, Integer>();
		results.put(1, 1);
		for(int currLength = 2; currLength <= length; currLength++) {
			int max = 0;
			for(int size = 1; size <= currLength/2; size++) {
				max = Math.max(max, 
						results.get(size) * results.get(currLength - size));
			}
			results.put(currLength, max);
		}
		return results.get(length);
	}
}
