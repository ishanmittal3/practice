package array;

import java.util.ArrayList;
import java.util.List;

public class ReadMatrix {

    /**
     * We're given a char matrix consisting of 'R' and 'B'.
     * For each row, going from left to right, we have to count groups of 'B's.
     * e.g. ['B','R','B','B'] -> [1,2]
     * Similarly we can count for columns.
     * Given the char matrix and a solution for rows and columns,
     * find if the solution is valid
     */
    public static boolean isValid(char[][] matrix, int[][] rows, int[][] columns) {
        int N = matrix.length;
        int M = matrix[0].length;

        for (int i = 0; i < N; i++) {
            List<Integer> counts = new ArrayList<>();
            int currCount = 0;
            for (int j = 0; j < M; j++) {
                char c = matrix[i][j];
                if (c == 'B') {
                    currCount++;
                } else {
                    if (currCount > 0) {
                        counts.add(currCount);
                        currCount = 0;
                    }
                }
            }
            if (currCount > 0) {
                counts.add(currCount);
            }
            if (counts.size() != rows[i].length) {
                return false;
            }
            for (int pos = 0; pos < counts.size(); pos++) {
                if (counts.get(pos) != rows[i][pos]) {
                    return false;
                }
            }
        }

        for (int j = 0; j < M; j++) {
            List<Integer> counts = new ArrayList<>();
            int currCount = 0;
            for (int i = 0; i < N; i++) {
                char c = matrix[i][j];
                if (c == 'B') {
                    currCount++;
                } else {
                    if (currCount > 0) {
                        counts.add(currCount);
                        currCount = 0;
                    }
                }
            }
            if (currCount > 0) {
                counts.add(currCount);
            }
            if (counts.size() != columns[j].length) {
                return false;
            }
            for (int pos = 0; pos < counts.size(); pos++) {
                if (counts.get(pos) != columns[j][pos]) {
                    return false;
                }
            }
        }

        return true;
    }
}
