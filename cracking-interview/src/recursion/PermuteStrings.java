package recursion;

import java.util.HashSet;
import java.util.Set;

public class PermuteStrings {
	
	public static Set<String> permute(String str) {
		final Set<String> finalPerms = new HashSet<String>();
		
		if (str.length() <= 1) {
			finalPerms.add(str);
			return finalPerms;
		}
		
		for (int pos = 0; pos < str.length(); pos++) {
			Set<String> perms = permute(str.substring(0, pos) + str.substring(pos+1, str.length()));
			for (String perm : perms) {
				finalPerms.add(str.charAt(pos) + perm);
			}
		}
		return finalPerms;
	}
	
	public static Set<String> permute(String str, int size) {
		final Set<String> finalPerms = new HashSet<String>();
		
		if (size > str.length()) {
			return finalPerms;
		}
		
		if (size == 0) {
			finalPerms.add("");
			return finalPerms;
		}
		
		for (int pos = 0; pos < str.length(); pos++) {
			Set<String> perms = permute(str.substring(0, pos) + str.substring(pos+1, str.length()), size-1);
			for (String perm : perms) {
				finalPerms.add(str.charAt(pos) + perm);
			}
		}
		return finalPerms;
	}
	
	public static void main(String[] args) {
		Set<String> perms = permute("abca", 3);
//		Set<String> perms = permute("abca");
		int size = perms.size();
	}
}
