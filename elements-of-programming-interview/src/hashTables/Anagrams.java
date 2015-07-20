package hashTables;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Anagrams {

	Set<Set<String>> partitionIntoAnagrams(Set<String> words) {
		Map<Map<Character, Integer>, Set<String>> map = 
				new HashMap<Map<Character, Integer>, Set<String>>();
		for(String word : words) {
			Map<Character, Integer> chars = getChars(word);
			if(map.containsKey(chars)) {
				map.get(chars).add(word);
			} else {
				HashSet<String> newSet = new HashSet<String>();
				newSet.add(word);
				map.put(chars, newSet);
			}
		}
		Set<Set<String>> partition = new HashSet<Set<String>>();
		partition.addAll(map.values());
		return partition;
	}

	private Map<Character, Integer> getChars(String str) {
		Map<Character, Integer> chars = new HashMap<Character, Integer>();
		for(int pos = 0; pos < str.length(); pos++) {
			char c = str.charAt(pos);
			if(chars.containsKey(c)) {
				chars.put(c, 1 + chars.get(c));
			} else {
				chars.put(c, 1);
			}
		}
		return chars;
	}
}
