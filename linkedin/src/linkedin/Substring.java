package linkedin;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Substring {

	/**
	 * Find the shortest substring of a given string
	 * that contains all characters from a target string
	 */
	String substring(String str, String target) {
		String result = str;
		Map<Character, Integer> countsTarget = charCounts(target);
		Map<Character, Integer> counts = new HashMap<Character, Integer>();
		int start = 0, end = 0;
		while(start < str.length()) {
			Set<Character> missingChars = new HashSet<Character>();
			for(Map.Entry<Character, Integer> entry : countsTarget.entrySet()) {
				char c = entry.getKey();
				if(!counts.containsKey(c) || counts.get(c) < countsTarget.get(c)) {
					missingChars.add(c);
				}
			}
			while(end < str.length() && !missingChars.isEmpty()) {
				char endChar = str.charAt(end);
				counts.put(endChar, 1 + (counts.containsKey(endChar) ? counts.get(endChar) : 0));
				if(counts.get(endChar) >= countsTarget.get(endChar)) {
					missingChars.remove(endChar);
				}
				end++;
			}
			if(!missingChars.isEmpty()) {
				break;
			}
			while(start < end) {
				char startChar = str.charAt(start);
				if(counts.get(startChar) > countsTarget.get(startChar)) {
					decrement(counts, startChar);
					start++;
				} else {
					break;
				}
			}
			if(end - start < result.length()) {
				result = str.substring(start, end);
			}
			start++;
		}
		return result;
	}

	<Key> void decrement(Map<Key, Integer> counts, Key key) {
		if(!counts.containsKey(key)){
			return;
		}
		if(counts.get(key) > 1) {
			counts.put(key, counts.get(key) - 1);
		} else {
			counts.remove(key);
		}
	}

	Map<Character, Integer> charCounts(String str) {
		Map<Character, Integer> counts = new HashMap<Character, Integer>();
		for(int pos = 0; pos < str.length(); pos++) {
			char c = str.charAt(pos);
			counts.put(c, 1 + (counts.containsKey(c) ? counts.get(c) : 0));
		}
		return counts;
	}
}
