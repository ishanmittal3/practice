package sandbox;

public class Hexadecimal {

	/**
	 * @param dec >= 0
	 */
	public static String toHex(int dec) {
		if(dec == 0) {
			return "0";
		}
		if(dec < 10) {
			return String.valueOf(dec);
		}
		if(dec < 16) {
			char c = (char) ('A' + (dec % 10));
			return String.valueOf(c);
		}
		String result = "";
		while(dec > 0) {
			result = toHex(dec % 16) + result;
			dec = dec / 16;
		}
		return result;
	}
	
	public static int toDec(String hex) {
		if(hex.isEmpty()) {
			throw new IllegalArgumentException("Empty string");
		}
		int result = 0;
		int mul = 1;
		for(int pos = hex.length() - 1; pos >= 0; pos--) {
			char c = hex.charAt(pos);
			if(c >= '0' && c <= '9') {
				result += mul * (c - '0');
				mul *= 16;
				continue;
			}
			if(c >= 'A' && c <= 'F') {
				result += mul * (10 + c - 'A');
				mul *= 16;
				continue;
			}
			if(c >= 'a' && c <= 'f') {
				result += mul * (10 + c - 'a');
				mul *= 16;
				continue;
			}
			throw new IllegalArgumentException("Invalid char at pos " + pos);
		}
		return result;
	}
	
	public static void main(String[] args) {
		String hex = toHex(31);
		int dec = toDec("2F");
	}
}
