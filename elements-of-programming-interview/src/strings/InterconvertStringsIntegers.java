package strings;

public class InterconvertStringsIntegers {

	public static int stringToInt(String str) {
		int mul = 1;
		int result = 0;
		check(str);
		for(int pos = str.length()-1; pos >= 0; pos--) {
			char c = str.charAt(pos);
			if(c == '-') {
				result = -result;
			} else {
				result += mul * (c - '0');
			}
			mul *= 10;
		}
		return result;
	}

	private static void check(String str) {
		for(int pos = 0; pos < str.length(); pos++) {
			char c = str.charAt(pos);
			if(c == '-' && pos == 0 && !"-".equals(str)) {
				continue;
			}
			if(c < '0' || c > '9') {
				throw new IllegalArgumentException(str + " is not a valid encoding");
			}
		}
	}
	
	public static String intToString(int num) {
		StringBuilder sb = new StringBuilder();
		int val = Math.abs(num);
		while(val > 0) {
			sb.append(val % 10);
			val /= 10;
		}
		String result = sb.reverse().toString();
		if(result.isEmpty()) {
			return "0";
		}
		return num >= 0 ? result : 
						  '-' + result; 
	}

	public static void main(String[] args) {
		int i = stringToInt("012");
		i = stringToInt("-23");
		i = stringToInt("0");
//		i = stringToInt("123abc");
		
		String str = intToString(-123);
		str = intToString(23);
		str = intToString(0);
	}
}
