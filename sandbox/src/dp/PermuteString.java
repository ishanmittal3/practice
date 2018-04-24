package dp;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class PermuteString {

    int countValid(String str, Set<String> dict) {
        Set<String> perms = getPerms(str, new HashMap<>());
        int valid = 0;
        for (String perm : perms) {
            if (dict.contains(perm)) {
                valid++;
            }
        }
        return valid;
    }

    private Set<String> getPerms(String str, Map<Map<Character, Integer>, Set<String>> seen) {
        if (seen.containsKey(getChars(str))) {
            return seen.get(getChars(str));
        }
        Set<String> perms = new HashSet<>();
        if (str.isEmpty()) {
            return perms;
        }
        if (str.length() == 1) {
            perms.add(str);
            return perms;
        }
        for (int pos = 0; pos < str.length(); pos++) {
            char c = str.charAt(pos);
            String remaining = getRemaining(str, pos);
            Set<String> remPerms = getPerms(remaining, seen);
            for (String remPerm : remPerms) {
                perms.add(c + remPerm);
            }
        }
        seen.put(getChars(str), perms);
        return perms;
    }

    private Map<Character, Integer> getChars(String str) {
        Map<Character, Integer> charsMap = new HashMap<>();
        for (char c : str.toCharArray()) {
            int count = charsMap.getOrDefault(c, 0);
            charsMap.put(c, 1 + count);
        }
        return charsMap;
    }

    private String getRemaining(String str, int exclude) {
        StringBuilder builder = new StringBuilder();
        for (int pos = 0; pos < str.length(); pos++) {
            if (pos != exclude) {
                builder.append(str.charAt(pos));
            }
        }
        return builder.toString();
    }


}
