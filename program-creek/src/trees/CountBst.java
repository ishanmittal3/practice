package trees;

import java.util.HashMap;
import java.util.Map;

public class CountBst {

	/**
	 * Given n, how many structurally unique BST's 
	 * (binary search trees) that store values 1...n?
	 */
	int numTrees(int n) {
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		map.put(0, 1);
		return numTrees(n, map);
	}
	
	int numTrees(int n, Map<Integer, Integer> map) {
		if(map.containsKey(n)) {
			return map.get(n);
		}
		int count = 0;
		for(int root = 1; root <= n; root++) {
			count += numTrees(root - 1) * numTrees(n - root);
		}
		map.put(n, count);
		return count;
	}
}
