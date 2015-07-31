package dp;

public class MaxValueMatrix {
	/**
	 * A board has n*m cells. 
	 * There is a gift with some value (> 0) in every cell. 
	 * You can get gifts starting from the top-left cell, 
	 * move right or down in each step, 
	 * and finally reach the cell at the bottom-right. 
	 * What’s the maximum value of gifts you can get from the board?
	 */
	int maxValue(int[][] values) {
		int numRows = values.length;
		int numCols = values[0].length;
		int[] currRow = new int[values[0].length];
		int[] prevRow = null;

		for(int row = 0; row < numRows; row++) {
			for(int col = 0; col < numCols; col++) {
				int up = Integer.MIN_VALUE;
				int left = Integer.MIN_VALUE;
				if(col > 0) {
					left = currRow[col-1];
				}
				if(row > 0) {
					up = prevRow[col];
				}
				currRow[col] = values[row][col] + Math.max(up, left);
			}
			prevRow = currRow;
		}
		return currRow[numCols-1];
	}
}
