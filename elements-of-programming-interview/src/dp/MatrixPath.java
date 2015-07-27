package dp;

public class MatrixPath {

	/**
	 * Find the number of ways to go from top-left
	 * to bottom-right in a matrix
	 * @param obstacles 
	 */
	int numPaths(int rows, int cols, boolean[][] obstacles) {
		int[][] counts = new int[rows][cols];
		counts[0][0] = 1;
		for(int row = 0; row < rows; row++) {
			for(int col = 0; col < cols; col++) {
				if(!obstacles[row][col]) {
					if(col > 0) {
						counts[row][col] += counts[row][col-1];
					}
					if(row > 0) {
						counts[row][col] = counts[row-1][col];
					}
				}
			}
		}
		return counts[rows-1][cols-1];
	}
}
