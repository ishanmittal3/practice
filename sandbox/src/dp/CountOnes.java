package dp;

public class CountOnes {
	/**
	Given a large rectangular 2D grid of arbitrarily-placed 1's and 0's, 
	write a service that answers the query "how many 1's in a given subgrid?". 
	You should assume that the grid is large, doesn't change, and is given to you ahead of time. 
	The query will be called many times, so it needs to be fast.
	 
	examples:

	0 0 0 1
	1 0 1 0
	1 1 1 0


	0 0 0 1
	* * 1 0 -- return --> 3
	* * 1 0



	0 0 0 1
	1 * * 0 -- return --> 1
	1 1 1 0

	 
	0 0 0 1
	1 1 2 3
	2 3 5 6
	 
	def getValue(x, y):
	  if x < 0 or y < 0:
	    return 0
	  return self.pgrid[x][y]
	 
	 **/
	
	/*
	int countOnes(int x1, int x2, int y1, int y2) {
	      int count = 0;
	      for (int x = x1; x <= x2; x++) {
	        for (int y = y1; y <= y2; y++) {
	          count += getValue(x, y);
	        }
	      }
	      return count;
	}
	*/

	// (0. 0, big number, big number)
	// (0, 0, big number -1, big number -1)


	int[][] getCounts(int[][] matrix) {
	  int X = matrix[0].length;
	  int Y = matrix.length;
	  int[][] counts = new int[X][Y];
	  for (int x = 0; x < X; x++) {
	    for (int y = 0; y < Y; y++) {
	      int count = matrix[x][y];
	      if (x > 0) {
	        count += counts[x-1][y];
	      }
	      if (y > 0) {
	        count += counts[x][y-1];
	      }
	      if ((x > 0) && (y > 0)) {
	        count -= counts[x-1][y-1];
	      }
	      counts[x][y] = count;
	    }
	  }
	  return counts;
	}

	int countOnes(int x1, int y1, int x2, int y2, int[][] matrix) {
	  int[][] counts = getCounts(matrix);
	  int count = counts[x2][y2];
	  if (x1 > 0) {
	    count -= counts[x1-1][y2];
	  }
	  if (y1 > 0) {
	    count -= counts[x2][y1-1];
	  }
	  if ((x1 > 0) && (y1 > 0)) {
	    count += counts[x1-1][y1-1];
	  }
	  return count;
	}

	// f(x, y) = f(x-1,y) + f(x,y-1) - f(x-1,y-1) + getValue(x,y)
	 
	// grid2(x2,y2) - grid2(x1,y2) - grid2(x2,y1) + grid2(x1,y1)
}
