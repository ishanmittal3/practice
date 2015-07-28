package sandbox;

public class BinarySearchNext {
	
	char findNext(char[] chars, char key) {
		return findNext(chars, 0, chars.length - 1, key);
	}

	char findNext(char[] chars, int start, int end, char key) {
		if (start > end) {
			return chars[start];
		}
		if (start == end) {
			if (key < chars[start]) {
				return chars[start];
			} else {
				return chars[(start+1) % chars.length];
			}
		}
		
		int mid = (start + end)/2;
		if (key == chars[mid]) {
			return chars[(mid+1) % chars.length];
		}
		if (key < chars[mid]) {
			return findNext(chars, start, mid-1, key);
		} else {
			return findNext(chars, mid+1, end, key);
		}
	}
}
