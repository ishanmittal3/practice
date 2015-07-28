package sandbox;

import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;

public class Countries {
	
	public static void main(String[] args) {
		System.out.println(getNumCountries(new int[][] {{1, 1, 1, 0},
													    {1, 1, 0, 0},
													    {0, 0, 1, 1},
													    {0, 1, 0, 0}}));
	}
	
	public static int getNumCountries(int[][] matrix) {
		Set<Point> unvisited = getOnes(matrix);
		int numCountries = 0;
		while (!unvisited.isEmpty()) {
			Queue<Point> q = new ArrayDeque<Point>();
			q.add(unvisited.iterator().next());
			while (!q.isEmpty()) {
				Point curr = q.poll();
				unvisited.remove(curr);
				q.addAll(getUnvisitedNeighbors(curr, matrix, unvisited));
			}
			numCountries++;
		}
		return numCountries;
	}

	private static Set<Point> getUnvisitedNeighbors(Point curr, 
			int[][] matrix, Set<Point> unvisited) {
		Set<Point> neighbors = new HashSet<Point>();
		neighbors.add(new Point(curr.col-1, curr.row));
		neighbors.add(new Point(curr.col+1, curr.row));
		neighbors.add(new Point(curr.col  , curr.row+1));
		neighbors.add(new Point(curr.col  , curr.row-1));

		Set<Point> unvisitedNeighbors = new HashSet<Point>();
		for (Point neighbor : neighbors) {
			if (unvisited.contains(neighbor)) {
				unvisitedNeighbors.add(neighbor); 
			}
		}
		return unvisitedNeighbors;
	}

	private static Set<Point> getOnes(int[][] matrix) {
		Set<Point> ones = new HashSet<Point>();
		for (int col = 0; col < matrix.length; col++) {
			for (int row = 0; row < matrix[0].length; row++) {
				if (matrix[col][row] == 1) {
					ones.add(new Point(col, row));
				}
			}
		}
		return ones;
	}
	
	static class Point {
		
		int col;
		int row;
		
		public Point(int col, int row) {
			this.col = col;
			this.row = row;
		}
		
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + col;
			result = prime * result + row;
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Point other = (Point) obj;
			if (col != other.col)
				return false;
			if (row != other.row)
				return false;
			return true;
		}
	}
}
