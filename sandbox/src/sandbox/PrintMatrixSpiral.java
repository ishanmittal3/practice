package sandbox;

public class PrintMatrixSpiral {

	void printSpiral(int[][] matrix) {
		printSpiral(matrix, 0, matrix.length-1, 0, matrix[0].length-1);
	}

	void printSpiral(int[][] matrix, int startRow, int endRow,
			int startCol, int endCol) {
		if(startRow > endRow || startCol > endCol) {
			return;
		}
		if(startRow == endRow && startCol == endCol) {
			print(matrix[startRow][startCol]);
			return;
		}
		if(startRow == endRow) {
			for(int col = startCol; col <= endCol; col++) {
				print(matrix[startRow][col]);
			}
			return;
		}
		if(startCol == endCol) {
			for(int row = startRow; row <= endRow; row++) {
				print(matrix[row][startCol]);
			}             
			return; 
		}

		for(int col = startCol; col < endCol; col++) {
			print(matrix[startRow][col]);
		}
		for(int row = startRow; row < endRow; row++) {
			print(matrix[row][endCol]);                       
		}
		for(int col = endCol; col > startCol; col--) {
			print(matrix[endRow][col]);
		}
		for(int row = endRow; row > startRow; row--) {
			print(matrix[row][startCol]);
		}

		printSpiral(matrix, startRow+1, endRow-1, startCol+1, endCol-1);
	}
	
	void print(int i) {
		System.out.println(i);
	}
}
