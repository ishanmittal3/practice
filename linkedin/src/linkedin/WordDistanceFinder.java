package linkedin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* This class will be given a list of words (such as might be tokenized
 * from a paragraph of text), and will provide a method that takes two
 * words and returns the shortest distance (in words) between those two
 * words in the provided text.
 * Example:
 *   WordDistanceFinder finder = new WordDistanceFinder(Arrays.asList("the", "quick", "brown", "fox", "quick"));
 *   assert(finder.distance("fox", "the") == 3);
 *   assert(finder.distance("quick", "fox") == 1);
 *
 * "quick" appears twice in the input. There are two possible distance values for "quick" and "fox":
 *     (3 - 1) = 2 and (4 - 3) = 1.
 * Since we have to return the shortest distance between the two words we return 1.
 */
public class WordDistanceFinder {
    Map<String, List<Integer>> positions;

    public WordDistanceFinder (List<String> words) {
        // implementation here
        positions = new HashMap<>();
        for (int pos = 0; pos < words.size(); pos++) {
            String word = words.get(pos);
            if (positions.containsKey(word)) {
                List<Integer> list = positions.get(word);
                list.add(pos);
            } else {
                List<Integer> list = new ArrayList<>();
                list.add(pos);
                positions.put(word, list);
            }
        }
    }
    public int distance (String wordOne, String wordTwo) {
        // implementation here
        if (!positions.containsKey(wordOne) || !positions.containsKey(wordTwo)) {
            return Integer.MAX_VALUE;
        }
        return distance(positions.get(wordOne), positions.get(wordTwo));
    }

    private int distance(List<Integer> list1, List<Integer> list2) {
        int pos1 = 0;
        int pos2 = 0;
        int min = Integer.MAX_VALUE;
        while (pos1 < list1.size() && pos2 < list2.size()) {
            min = Math.min(min, Math.abs(list1.get(pos1) - list2.get(pos2)));
            if (list1.get(pos1) < list2.get(pos2)) {
                pos1++;
            } else {
                pos2++;
            }
        }
        return min;
    }
}


