package strings;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Mnemonic {

	public static List<String> phoneMnemonicRecursive(String num, 
			Map<Character, Set<Character>> mapping) {
		List<String> result = new ArrayList<String>();
		
		if(num.length() == 0) {
			result.add("");
			return result;
		}
		Set<Character> chars = mapping.get(num.charAt(0));
		for(String str : phoneMnemonicRecursive(num.substring(1, num.length()), mapping)) {
			for(char c : chars) {
				result.add(c + str);
			}
		}
		return result;
	}
	
	public static List<String> phoneMnemonicIterative(String num,
			Map<Character, Set<Character>> mapping) {
		List<String> result = new ArrayList<String>();
		result.add("");
		for (int pos = 0; pos < num.length(); pos++) {
			List<String> strings = new ArrayList<String>();
			Set<Character> chars = mapping.get(num.charAt(pos));
			for (String str : result) {
				for(char c : chars) {
					strings.add(str + c);
				}
			}
			result.addAll(strings);
		}
		return result;
	}
}
