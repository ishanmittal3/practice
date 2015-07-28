package sandbox;

public class SubtractString {

	public static void main(String[] args) {
		System.out.println(subtract("nagger", "gg"));
		System.out.println(subtract("baaab", "aa"));
	}
	
	public static String subtract(String str, String key) {
		StringBuilder result = new StringBuilder();
		int len = key.length();
		int pos = 0;
		
		for (pos = 0; pos < str.length() - len; pos++) {
			if (str.substring(pos, pos + len).equals(key)) {
				pos += len - 1;
			} else {
				result.append(str.charAt(pos));
			}
		}
		
		while (pos < str.length()) {
			result.append(str.charAt(pos)); 
			pos++;
		}
		
		return result.toString();
	}
}
