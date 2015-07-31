package dp;

import java.util.HashMap;
import java.util.Map;

public class LongestSubstring {

	/**
	 * Given a string, get the longest substring 
	 * which does not have duplicated characters
	 */
	String longestSubstring(String str) {
		/* If the character set is bounded,
		 * size of this map will also be bounded - 
		 * independent of n (str.length()) 
		 */
		Map<Character, Integer> positions = new HashMap<Character, Integer>();
		String prev = "";
		String longest = prev;
		
		for(int pos = 0; pos < str.length(); pos++) {
			char c = str.charAt(pos);
			if(!positions.containsKey(c) || 
					positions.get(c) < pos - prev.length()) {
				prev += c;
			} else {
				int prevPos = positions.get(c);
				prev = prev.substring(prevPos + 1) + c;
			}
			positions.put(c, pos);
			if(prev.length() > longest.length()) {
				longest = prev;
			}
		}
		return longest;
	}
}
