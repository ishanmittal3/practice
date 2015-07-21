package graph;

import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;

public class BooleanMatrix {
	
	static class Point {
		int x;
		int y;
		
		Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	void paintMatrix(boolean[][] matrix, Point point) {
		Queue<Point> queue = new ArrayDeque<Point>();
		Set<Point> visited = new HashSet<Point>();
		queue.add(point);
		visited.add(point);
		flip(matrix, point);
		
		while(!queue.isEmpty()) {
			point = queue.poll();
			for(Point neighbor : getNeighbors(matrix, point)) {
				if(!visited.contains(neighbor) && 
						matrix[neighbor.x][neighbor.y] != matrix[point.x][point.y]) {
					queue.add(neighbor);
					visited.add(point);
					flip(matrix, point);
				}
			}
		}
		
		
	}
	
	Set<Point> getNeighbors(boolean[][] matrix, Point point) {
		int x = point.x;
		int y = point.y;
		
		Set<Point> neighbors = new HashSet<Point>();
		if(x > 0) {
			neighbors.add(new Point(x-1, y));
		}
		if(x < matrix.length-1) {
			neighbors.add(new Point(x+1, y));
		}
		if(y > 0) {
			neighbors.add(new Point(x, y-1));
		}
		if(y < matrix[0].length - 1) {
			neighbors.add(new Point(x, y+1));
		}
		return neighbors;
	}
	
	void flip(boolean[][] matrix, Point point) {
		int x = point.x;
		int y = point.y;
		matrix[x][y] ^= true;  
	}
}
