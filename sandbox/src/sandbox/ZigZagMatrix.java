package sandbox;

public class ZigZagMatrix {

	/**
	 * Populate a matrix in a zig-zag way
	 *  1 2 4 ..  x ->
	 *  3 5 ..    y
	 *  6 ..      ⬇️
	 */
	public void populate(int[][] matrix) {
		int currNum = 1;
		int X = matrix[0].length;
		int Y = matrix.length;
		int currX = 0;
		int currY = 0;
		while (currNum <= X*Y) {
			while ((currX >= 0) && (currY < Y)) {
				matrix[currX--][currY++] = currNum++;
			}
			currX++;
			while ((currX < X) && (currY >= 0)) {
				currX++;
				currY--;
			}
			currX--;
			currY++;
		} 
	}
}
