package sandbox;

/**
 * Given an integer and the rules:
 * 1 is translated to 'a', 2 to 'b', …, 12 to 'l', …, 26 to 'z'
 * How many ways can a given integer
 * be translated into a string?
 */
public class EncodeInt {

	int encode(int num) {
		String str = String.valueOf(num);
		return encode(str, 0);
	}
	
	private int encode(String str, int start) {
		if(start >= str.length()) {
			return 1;
		}
		char c = str.charAt(start);
		if(c == '0') {
			return 0;
		}
		if(start == str.length() - 1) {
			return 1;
		}
		switch(c) {
		case '1':
			return encode(str, start + 1) + 
				   encode(str, start + 2);
		case '2':
			return encode(str,start + 1) + 
				str.charAt(start+1) > '6' ? 0 :
					encode(str, start + 2);
		default:
			return encode(str,start + 1);
		}
	}
}
