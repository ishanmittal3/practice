package stringSets;

import java.util.ArrayList;
import java.util.HashSet;

public class StringSets {
	
	public static boolean allStringSetsIdentical(String[ ][ ] sets) {
		
		int numSets = sets.length;
		ArrayList<HashSet<String>> setList = new ArrayList<HashSet<String>>();
		
		for (String[] strings : sets) {
			setList.add(createSet(strings));
		}
		
		for (int curr = 0; curr < numSets-1; curr++) {
			if (!setList.get(curr).equals(setList.get(curr+1))) {
				return false;
			}
		}
		return true;
	}

	private static HashSet<String> createSet(String[] strings) {
		
		HashSet<String> result = new HashSet<String>();
		for (String str : strings) {
			result.add(str);
		}
		return result;
	}
	
	public static void main(String[] args) {
		System.out.println(allStringSetsIdentical(new String[][] 
				{{"a","b"},{"b","b","a"},{"b","a"}}));
		System.out.println(allStringSetsIdentical(new String[][] 
				{{"a","b"},{"a"},{"b"}}));
	}
}
