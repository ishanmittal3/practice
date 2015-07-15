package recursion;

import java.util.HashSet;
import java.util.Set;

public class SubSets {
	public static <T> Set<Set<T>> getSubSets(Set<T> set) {
		Set<Set<T>> finalSubsets = new HashSet<Set<T>>();
		
		if (set.isEmpty()) {
			return finalSubsets;
		}
		
		if (set.size() == 1) {
			finalSubsets.add(new HashSet<T> ());
			finalSubsets.add(set);
            return finalSubsets;
		}

        for (T element : set) {
            Set<T> reducedSet = new HashSet<T>(set);
            reducedSet.remove(element);
            Set<Set<T>> subSets = getSubSets(reducedSet);
            
            for (Set<T> subset : subSets) {
            	finalSubsets.add(subset);
            	Set<T> subsetCopy = new HashSet<T>(subset);
            	subsetCopy.add(element);
            	finalSubsets.add(subsetCopy);
            }
        }

        return finalSubsets;
	}
	
	public static HashSet<HashSet<Integer>> getSubSets2(HashSet<Integer> set) {
		HashSet<HashSet<Integer>> setOfSets = new HashSet<HashSet<Integer>>();
		int combination = (1 << set.size()) - 1;
		Integer[] elements = (Integer[]) set.toArray();
		
		while (combination >= 0) {
			int curr = combination;
			HashSet<Integer> currSubset = new HashSet<Integer> ();
			
			for (int elementIndex = 0; elementIndex < set.size(); elementIndex++) {
				if ((curr & 1) > 0) {
					currSubset.add(elements[elementIndex]);
				}
				curr = curr >> 1;
			}
			setOfSets.add(currSubset);
			combination--;
		}
		return setOfSets;
	}
}
