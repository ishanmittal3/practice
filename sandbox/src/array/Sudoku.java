package array;

public class Sudoku {

    /**
     * A square matrix is given (NxN).
     * It is valid if each row covers all numbers from 1 to N
     * and each column covers all numbers from 1 to N.
     */
    public static boolean isValid(int[][] grid) {
        int N = grid.length;
        for (int i = 0; i < N; i++) {
            boolean[] seen = new boolean[N];
            for (int j = 0; j < N; j++) {
                int num = grid[i][j];
                if (num <= 0 || num > N) {
                    return false;
                }
                seen[num-1] = true;
            }
            for (int pos = 0; pos < N; pos++) {
                if (!seen[pos]) {
                    return false;
                }
            }
        }

        for (int j = 0; j < N; j++) {
            boolean[] seen = new boolean[N];
            for (int i = 0; i < N; i++) {
                int num = grid[i][j];
                if (num <= 0 || num > N) {
                    return false;
                }
                seen[num-1] = true;
            }
            for (int pos = 0; pos < N; pos++) {
                if (!seen[pos]) {
                    return false;
                }
            }
        }

        return true;
    }

}
