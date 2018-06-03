package graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class MorseCode {

    public static String[] doIt(String[] input) {
        Map<String, Character> codesToChars = new HashMap<>();
        Map<Character, String> charsToCodes = new HashMap<>();
        int index = 0;
        while (index < input.length) {
            String curr = input[index++].trim();
            if ("*".equals(curr)) {
                break;
            }
            String[] split = curr.split(" ");
            char c = split[0].charAt(0);
            String code = split[split.length-1];
            codesToChars.put(code, c);
            charsToCodes.put(c, code);
        }

        Set<String> dict = new HashSet<>();
        while (index < input.length) {
            String curr = input[index++].trim();
            if ("*".equals(curr)) {
                break;
            }
            dict.add(curr);
        }

        List<String> codes = new ArrayList<>();
        while (index < input.length) {
            String curr = input[index++].trim();
            if ("*".equals(curr)) {
                break;
            }
            String[] split = curr.split(" ");
            for (String str : split) {
                if (str.length() > 0) {
                    codes.add(str);
                }
            }
        }

        Map<String, Set<String>> seen = new HashMap<>();
        String[] result = new String[codes.size()];
        for (int pos = 0; pos < result.length; pos++) {
            result[pos] = decode(codes.get(pos), dict, seen, codesToChars, charsToCodes);
            System.out.println(result[pos]);
        }
        return result;
    }

    private static String decode(String str, Set<String> dict,
            Map<String, Set<String>> seen, Map<String, Character> codesToChars,
            Map<Character, String> charsToCodes) {

        Set<String> words = getWords(str, seen, codesToChars);
        List<String> matching = words.stream()
            .filter(dict::contains).collect(Collectors.toList());
        if (matching.size() > 1) {
            return getSmallest(matching) + '!';
        }
        if (matching.size() == 1) {
            return matching.get(0);
        }
        int minDistance = Integer.MAX_VALUE;
        String closest = null;
        for (String word : words) {
            for (String dictWord : dict) {
                int currDistance = getDistance(word, dictWord, charsToCodes);
                if (currDistance < minDistance) {
                    closest = dictWord;
                    minDistance = currDistance;
                }
            }
        }
        return (closest == null)
            ? "No matching word found"
            : closest + '?';
    }

    private static String getSmallest(List<String> strings) {
        String smallest = strings.get(0);
        for (String string : strings) {
            if (string.length() < smallest.length()) {
                smallest = string;
            }
        }
        return smallest;
    }

    private static int getDistance(String word, String dictWord,
            Map<Character, String> charsToCodes) {

        String wordMorse = encode(word, charsToCodes);
        String dictWordMorse = encode(dictWord, charsToCodes);
        if (wordMorse.startsWith(dictWordMorse) || dictWordMorse.startsWith(wordMorse)) {
            return Math.abs(wordMorse.length() - dictWordMorse.length());
        } else {
            return Integer.MAX_VALUE;
        }
    }

    private static String encode(String word, Map<Character, String> charsToCodes) {
        StringBuilder sb = new StringBuilder();
        for (char c : word.toCharArray()) {
            sb.append(charsToCodes.get(c));
        }
        return sb.toString();
    }

    // To make it faster, we can store the words in a Trie
    private static Set<String> getWords(String str, Map<String, Set<String>> seen,
            Map<String, Character> codesToChars) {

        if (seen.containsKey(str)) {
            return seen.get(str);
        }
        Set<String> words = new HashSet<>();
        if (str.length() == 0) {
            words.add("");
            return words;
        }
        for (int end = 1; end <= 6; end++) {
            if (end > str.length()) {
                break;
            }
            String substring = str.substring(0, end);
            if (codesToChars.containsKey(substring)) {
                char c = codesToChars.get(substring);
                Set<String> remaining = getWords(str.substring(end), seen, codesToChars);
                for (String rem : remaining) {
                    words.add(c + rem);
                }
            }
        }
        seen.put(str, words);
        return words;
    }

    public static void main(String[] args) {
        String[] testInput = {
            "A .-",
            "B -...",
            "C -.-.",
            "D -..",
            "E .",
            "F ..-.",
            "G --.",
            "H ....",
            "I ..",
            "J .---",
            "K -.-",
            "L .-..",
            "M --",
            "N -.",
            "O ---",
            "P .--.",
            "Q --.-",
            "R .-.",
            "S ...",
            "T -",
            "U ..-",
            "V ...-",
            "W .--",
            "X -..-",
            "Y -.--",
            "Z --..",
            "0 ------",
            "1 .-----",
            "2 ..---",
            "3 ...--",
            "4 ....-",
            "5 .....",
            "6 -....",
            "7 --...",
            "8 ---..",
            "9 ----.",
            "*",
            "AN",
            "EARTHQUAKE",
            "EAT",
            "GOD",
            "HATH",
            "IM",
            "READY",
            "TO",
            "WHAT",
            "WROTH",
            "*",
            ".--.....-- .....--....",
            "--.----.. .--.-.----..",
            ".--.....-- .--.",
            "..-.-.-....--.-..-.--.-.",
            "..-- .-...--..-.--",
            ".-..-.--- ..--",
            "*"
        };

        doIt(testInput);
    }
}
