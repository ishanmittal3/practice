package graph;

import java.util.HashMap;
import java.util.Map;

public class SnakesAndLadders {

    /**
     * Find the minimum number of throws required
     * to win given Snakes and Ladders game
     */
    int getMinThrows(Map<Integer, Integer> ladders,
                     Map<Integer, Integer> snakes,
                     int start, int end) {
        return getMinThrows(ladders, snakes, new HashMap<>(), start, end);
    }

    int getMinThrows(Map<Integer, Integer> ladders,
                     Map<Integer, Integer> snakes,
                     Map<Integer, Integer> seen,
                     int start, int end) {
        if (ladders.containsKey(start)) {
            start = ladders.get(start);
        }
        if (snakes.containsKey(start)) {
            start = snakes.get(start);
        }
        if (start == end) {
            return 0;
        }
        if (seen.containsKey(start)) {
            return seen.get(start);
        }
        int min = Integer.MAX_VALUE;
        if (start > end) {
            return min;
        }
        for (int num = 1; num <= 6; num++) {
            min = Math.min(min, getMinThrows(ladders, snakes, (num + start), end));
        }
        seen.put(start, 1 + min);
        return 1 + min;
    }
}
