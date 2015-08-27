package dp;

public class LongestPalindromeSubstring {

	String longestPalindrome(String str) {
		int maxLength = 1;
		String longest = str.substring(0, 1);
		for(int pos = 0; pos < str.length() - 1; pos++) {
			String curr = longest(str, pos, pos);
			if(curr.length() > maxLength) {
				maxLength = curr.length();
				longest = curr;
			}
			curr = longest(str, pos, pos+1);
			if(curr.length() > maxLength) {
				maxLength = curr.length();
				longest = curr;
			}
		}
		return longest;
	}
	
	private String longest(String str, int pos1, int pos2) {
		int rad = 0;
		while(pos1 - rad > 0 && pos2 + rad < str.length()) {
			if(str.charAt(pos1 - rad) != str.charAt(pos2 + rad)) {
				break;
			}
			rad++;
		}
		if(rad == 0) {
			return "";
		}
		rad--;
		return str.substring(pos1 - rad, pos2 + rad + 1);
	}
}
