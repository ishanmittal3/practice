package dp;

public class LongestCommonSubstring {

	/**
	 * @param str1
	 * @param str2
	 * @return length of the longest common substring
	 * O(mn) time
	 * O(m) space
	 */
	int longestCommonSubstring(String str1, String str2) {
		if(str1.length() > str2.length()) {
			String temp = str1;
			str1 = str2;
			str2 = temp;
		}
		int max = 0;
		/*int[][] lengths = new int[str1.length()][str2.length()];
		*//**
		 * lengths[i][j] stores the length of the 
		 * longest substring ending at position i in str1
		 * and j in str2
		 *//*
		
		for(int pos1 = 0; pos1 < str1.length(); pos1++) {
			for(int pos2 = 0; pos2 < str2.length(); pos2++) {
				if(str1.charAt(pos1) != str2.charAt(pos2)) {
					lengths[pos1][pos2] = 0;
				} else {
					lengths[pos1][pos2] = (pos1 == 0 || pos2 == 0) ?
							1 : 1 + lengths[pos1 - 1][pos2 - 1];
					max = Math.max(max, lengths[pos1][pos2]);
				}
			}
		}*/		
		int[] curr = new int[str1.length()];
		int[] prev = new int[str1.length()];
		/**
		 * curr[pos1] stores the length of the longest substring
		 * ending in position pos1 in str1 and pos2 in str2
		 */
		for(int pos2 = 0; pos2 < str2.length(); pos2++) {
			for(int pos1 = 0; pos1 < str1.length(); pos1++) {
				if(str1.charAt(pos1) != str2.charAt(pos2)) {
					curr[pos1] = 0;
				} else {
					curr[pos1] = (pos1 == 0 || pos2 == 0) ?
							1 : 1 + prev[pos1 - 1];
					max = Math.max(max, curr[pos1]);
				}
			}
			prev = curr;
		}
		return max;
	}
}
