package sandbox;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;

public class Anagram {

	public static void main(String[] args) {
		String[] words = {"cat", "dog", "act", "god", "cta"};
		Collection<ArrayList<String>> groups = groupAnagrams(words);
		for (ArrayList<String> group : groups) {
			System.out.println('[');
			for (String word : group) {
				System.out.println(word);
			}
			System.out.println(']');
		}
	}
	
	public static Collection<ArrayList<String>> groupAnagrams(String[] words) {
		HashMap<String, ArrayList<String>> map = new HashMap<String, ArrayList<String>>();
		
		for (String word : words) {
			String sortedWord = mergeSort(word);
		
			if (map.containsKey(sortedWord)) {
				map.get(sortedWord).add(word);
			} else {
				ArrayList<String> group = new ArrayList<String>();
				group.add(word);
				map.put(sortedWord, group);
			}
		}
		return map.values();
	}
	
	public static String sort(String str) {
		ArrayList<Character> chars = new ArrayList<Character>();
		for (int pos = 0; pos < str.length(); pos++) {
			chars.add(str.charAt(pos));
		}
		Collections.sort(chars);
		StringBuilder stringBuilder = new StringBuilder();
		for (char c : chars) {
			stringBuilder.append(c);
		}
		return stringBuilder.toString();
	}
	
	public static String mergeSort(String str) {
		char[] sortedChars = mergeSort(str.toCharArray());
		StringBuilder stringBuilder = new StringBuilder();
		for (char c : sortedChars) {
			stringBuilder.append(c);
		}
		return stringBuilder.toString();
	}

	private static char[] mergeSort(char[] chars) {
		return mergeSort(chars, 0, chars.length - 1);
	}

	private static char[] mergeSort(char[] chars, int start, int end) {
		if (start > end) {
			return new char[]{};
		}
		if (start == end) {
			return new char[]{chars[start]};
		}
		
		int mid = (start + end)/2;
		return merge(mergeSort(chars, start, mid), mergeSort(chars, mid + 1, end));
	}

	private static char[] merge(char[] chars1, char[] chars2) {
		char[] result = new char[chars1.length + chars2.length];
		int pos1 = 0;
		int pos2 = 0;
		int posResult = 0;
		
		while (pos1 < chars1.length && pos2 < chars2.length) {
			if (chars1[pos1] < chars2[pos2]) {
				result[posResult] = chars1[pos1++];
			} else {
				result[posResult] = chars2[pos2++];
			}
			posResult++;
		}
		
		while (pos1 < chars1.length) {
			result[posResult++] = chars1[pos1++];
		}
		while (pos2 < chars2.length) {
			result[posResult++] = chars2[pos2++];
		}
		return result;
	}
}
