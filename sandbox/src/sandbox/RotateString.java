package sandbox;

public class RotateString {

	String rotateLeft(String str, int rotateBy) {
		rotateBy %= str.length();
		if(rotateBy == 0) {
			return str;
		}
		if(rotateBy < 0) {
			rotateBy += str.length();
		}
		return str.substring(rotateBy) + str.substring(0, rotateBy-1);
	}
	
	String rotateRight(String str, int rotateBy) {
		rotateBy %= str.length();
		if(rotateBy == 0) {
			return str;
		}
		if(rotateBy < 0) {
			rotateBy += str.length();
		}
		return str.substring(str.length() - rotateBy) + 
				str.substring(0, str.length() - rotateBy);
	}
}
