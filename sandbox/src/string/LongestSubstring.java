package string;

import java.util.HashSet;
import java.util.Set;

public class LongestSubstring {

	public static void main(String[] args) {
	    String[] input= new String[]{"abc" ,"abcda",
	"king of chinaloyd","aabbc","abaaabc","abcdae"};
	    for(String s : input){
	      String output = longestSubstring(s);
	      System.out.println(String.format("%s %s",s,output));
	    }
	  }

	  private static String longestSubstring(String input){
	    // code here
	    int start = 0;
	    int end = 0;
	    int longestStart = 0;
	    int longestEnd = 0;
	    Set<Character> chars = new HashSet<Character>();
	    while(end < input.length()) {
	      char endChar = input.charAt(end);
	      if(!chars.contains(endChar)) {
	        chars.add(endChar);
	      } else {
	        while(input.charAt(start) != endChar) {
	          chars.remove(input.charAt(start));
	          start++;
	        }
	        if(start < end) {
	          start++;
	        }
	      }
	      if(end - start > longestEnd - longestStart) {
	        longestEnd = end;
	        longestStart = start;
	      }
	      end++;
	    }
	    return input.substring(longestStart, longestEnd + 1);
	  }
}
