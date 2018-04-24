package sandbox;

import java.util.HashSet;
import java.util.Set;

/**
 * Given a string, find the longest substring with no repeated characters.
 *  try these
 * Input kingofjapan Output kingofjap
 * Input aabbc Output ab bc
 * Input abaaabc Output abc
 */
public class LongestSubstring {

    public static void main(String[] args) {
        LongestSubstring solution = new LongestSubstring();
        String[] input = new String[]{"kingofjapan","aabbc","abaaabc"};
        for(String s : input){
            String output = solution.longestSubstring(s);
            System.out.println(String.format("%s %s",s,output));
        }
    }

    private String longestSubstring(String input){
        Set<Character> chars = new HashSet<>();
        int maxStart = 0;
        int maxEnd = 0;
        int pos = 0;
        int start = 0;
        int end = 0;
        while (pos < input.length()) {
            if (start > pos) {
                start = pos;
            }
            char c = input.charAt(pos);
            if (chars.contains(c)) {
                chars.remove(input.charAt(start++));
            } else {
                chars.add(c);
                if (end - start > maxEnd - maxStart) {
                    maxEnd = end;
                    maxStart = start;
                }
                end++;
                pos++;
            }
        }
        return input.substring(maxStart, 1 + maxEnd);
    }
}
