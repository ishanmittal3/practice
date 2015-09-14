package dp;

import java.util.Set;

public class WordBreak {

	/**
	 * Given a string s and a dictionary of words dict, 
	 * determine if s can be segmented into a sequence of 
	 * one or more dictionary words.
	 * 
	 * O(mn); m = str.length(), n = dict.size()
	 */
	boolean isBreakable(String str, Set<String> dict) {
		// result[pos] == true implies that str from 0 to pos
		// can be broken into words from dict
		boolean[] result = new boolean[str.length()];
		for(int pos = 0; pos < str.length(); pos++) {
			if(pos == 0 || result[pos-1]) {
				for(String word : dict) {
					int end = pos + word.length() - 1;
					if(end < str.length() && 
							word.equals(str.substring(pos, end + 1))) {
						result[end] = true;
					}
				}
			}
		}
		return result[str.length() - 1];
	}
}
