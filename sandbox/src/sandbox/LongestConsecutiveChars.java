package sandbox;

import java.util.ArrayList;
import java.util.List;

public class LongestConsecutiveChars {
	public static void main(String[] args) {
//		List<Character> chars = getLongestConsecutiveChars("this is a test sentence");
//		List<Character> chars = getLongestConsecutiveChars("thiis iss a teest seentennce");
//		List<Character> chars = getLongestConsecutiveChars("thiiis iss aa teeest seentennnce");
		List<Character> chars = getLongestConsecutiveChars("thiiiis iss a teeest seeentennncccce");
	}
	
	/* 'this is a test sentence' => [t, h, i, s, i, s, a, t, e, s, t, s, e, n, t, e, n, c, e]
	'thiis iss a teest seentennce' => [i, s, e, e, n]
	'thiiis iss aa teeest seentennnce' => [i, e, n]
	'thiiiis iss a teeest seeentennncccce' => [i, c]
	*/
	static List<Character> getLongestConsecutiveChars(String sentence) {
	    int maxCount = 0;
	    int pos = 0;
	    while (pos < sentence.length()) {
	        char curr = sentence.charAt(pos);
	        int nextPos = pos + 1;
	        if (curr != ' ') {
	        	while (nextPos < sentence.length() && sentence.charAt(nextPos) == curr) {
	                nextPos++;
	            }
	            maxCount = Math.max(maxCount, nextPos - pos);
	        }
	        pos = nextPos;
	    }
	    
	    List<Character> chars = new ArrayList<Character>();
	    pos = 0;
	    while (pos < sentence.length()) {
	        char curr = sentence.charAt(pos);
	        int nextPos = pos + 1;
	        if (curr != ' ') {
	            while (nextPos < sentence.length() && sentence.charAt(nextPos) == curr) {
	                nextPos++;
	            }
	            if (nextPos - pos == maxCount) {
	                chars.add(curr);
	            }
	        }
	        pos = nextPos;
	    }
	    
	    return chars;
	}
}
