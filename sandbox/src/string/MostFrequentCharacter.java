package string;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

// Docusign interview
public class MostFrequentCharacter {

    public static char maximumOccurringCharacter(String text) {
        // Write your code here
        Map<Character, Integer> counts = new HashMap<>();
        for (int pos = 0; pos < text.length(); pos++) {
            char c = text.charAt(pos);
            if (counts.containsKey(c)) {
                counts.put(c, 1 + counts.get(c));
            } else {
                counts.put(c, 1);
            }
        }
        int maxCount = 0;
        for (Map.Entry<Character, Integer> entry : counts.entrySet()) {
            if (entry.getValue() > maxCount) {
                maxCount = entry.getValue();
            }
        }
        Set<Character> max = new HashSet<>();
        for (Map.Entry<Character, Integer> entry : counts.entrySet()) {
            if (entry.getValue() == maxCount) {
                max.add(entry.getKey());
            }
        }
        for (int pos = 0; pos < text.length(); pos++) {
            char c = text.charAt(pos);
            if (max.contains(c)) {
                return c;
            }
        }
        return ' ';
    }
}
