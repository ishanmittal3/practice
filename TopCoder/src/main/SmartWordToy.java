package main;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Queue;

public class SmartWordToy {
	
	public static int minPresses(String start, String finish, String[] forbid) {
		Queue<WordEntry> queue = new ArrayDeque<WordEntry>();
		queue.add(new WordEntry(start, 0));
		
		HashSet<String> visited = new HashSet<String>();
		visited.add(start);
		
		while (!queue.isEmpty()) {
			WordEntry entry = queue.poll();
			for (int pos = 0; pos < entry.word.length(); pos++) {
				List<String> newWords = Arrays.asList(clickForward(entry.word, pos), 
													 clickBackward(entry.word, pos));
				for (String newWord : newWords) {
					if (!visited.contains(newWord) && isAllowed(newWord, forbid)) {
						if (newWord.equals(finish)) {
							return 1 + entry.numClicks;
						} else {
							queue.add(new WordEntry(newWord, 1 + entry.numClicks));
							visited.add(newWord);
						}
					}
				}
			}
		}
		return -1;
	}
	
	private static String clickBackward(String word, int clickPos) {
		StringBuilder builder = new StringBuilder();
		for (int pos = 0; pos < word.length(); pos++) {
			if (pos == clickPos) {
				if (word.charAt(pos) == 'a') {
					builder.append('z');
				} else {
					builder.append((char) (word.charAt(pos) - 1));
				}
			} else {
				builder.append(word.charAt(pos));
			}
		}
		return builder.toString();
	}

	private static String clickForward(String word, int clickPos) {
		StringBuilder builder = new StringBuilder();
		for (int pos = 0; pos < word.length(); pos++) {
			if (pos == clickPos) {
				if (word.charAt(pos) == 'z') {
					builder.append('a');
				} else {
					builder.append((char) (word.charAt(pos) + 1));
				}
			} else {
				builder.append(word.charAt(pos));
			}
		}
		return builder.toString();
	}
	
	private static boolean isAllowed(String newWord, String[] forbid) {
		for (String forbidden : forbid) {
			boolean matches = true;
			String[] splitForbidden = forbidden.split(" ");
			for (int pos = 0; pos < newWord.length(); pos++) {
				if (!contains(splitForbidden[pos], newWord.charAt(pos))) {
					matches = false;
					break;
				}
			}
			if (matches) {
				return false;
			}
		}
		return true;
	}

	private static boolean contains(String str, char key) {
		for (int pos = 0; pos < str.length(); pos++) {
			if (str.charAt(pos) == key) {
				return true;
			}
		}
		return false;
	}

	private static class WordEntry {
		String word;
		int numClicks;
		
		WordEntry(String word, int numClicks) {
			this.word = word;
			this.numClicks = numClicks;
		}
	}
}
