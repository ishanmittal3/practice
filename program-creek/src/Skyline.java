import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class Skyline {

	static class Edge {
		int x;
		int height;
		boolean isStart;
		
		Edge(int x, int height, boolean isStart) {
			this.x = x;
			this.height = height;
			this.isStart = isStart;
		}
	}
	
	/**
	 * 
	 * @param buildings Each building is in the format:
	 * {start, end, height}
	 * @return List of points that make up the skyline
	 */
	List<int[]> getSkyline(int[][] buildings) {
		List<Edge> edges = new ArrayList<Edge>();
		for(int[] building : buildings) {
			edges.add(new Edge(building[0], building[2], true));
			edges.add(new Edge(building[1], building[2], false));
		}
		Collections.sort(edges, new Comparator<Edge>() {
			public int compare(Edge a, Edge b) {
				if(a.x != b.x) {
					return Integer.compare(a.x, b.x);
				}
				if(a.isStart && b.isStart) {
					return Integer.compare(b.height, a.height);
				}
				if(!a.isStart && !b.isStart) {
					return Integer.compare(a.height, b.height);
				}
				return a.isStart ? -1 : 1;
			}
		});
		List<int[]> result = new ArrayList<int[]>();
		PriorityQueue<Integer> heights = new PriorityQueue<Integer>(10, Collections.reverseOrder());
		for(Edge edge : edges) {
			if (edge.isStart) {
				if (heights.isEmpty() || edge.height > heights.peek()) {
					result.add(new int[] { edge.x, edge.height });
				}
				heights.add(edge.height);
			} else {
				heights.remove(edge.height);
				if(heights.isEmpty()) {
					result.add(new int[] {edge.x, 0});
				} else {
					if(edge.height > heights.peek()) {
						result.add(new int[] {edge.x, heights.peek()});
					}
				}
			}
		}
		return result;
	}
}
