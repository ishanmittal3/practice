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
	
	public static Set<String> permute2(String string) {
		if (string.length() == 0) {
			return new HashSet<>();
		}
		if (string.length() == 1) {
			Set<String> set = new HashSet<>();
			set.add(string);
			return set;
		}
		return permute(string.charAt(0), permute(string.substring(1)));
	}
	
	public static Set<String> permute(char c, Set<String> strings) {
		Set<String> set = new HashSet<>();
		for (String string : strings) {
			set.add(c + string);
			for (int pos = 1; pos < string.length(); pos++) {
				set.add(string.substring(0, pos) + c + string.substring(pos));
			}
		}
		return set;
	}
	public static void main(String[] args) {
		Set<String> perms = permute("abca", 3);
//		Set<String> perms = permute("abca");
		Set<String> perms2 = permute2("test");
		int size = perms.size();
	}
}
