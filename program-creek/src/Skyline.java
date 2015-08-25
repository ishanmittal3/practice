import java.util.List;

public class Skyline {

	static class Edge {
		int x;
		int h;
		boolean isStart;
		
		Edge(int x, int h, boolean isStart) {
			this.x = x;
			this.h = h;
			this.isStart = isStart;
		}
	}
	
	/**
	 * 
	 * @param buildings each building is in the format:
	 * {start, end, height}
	 * @return
	 */
	List<int[]> getSkyline(int[][] buildings) {
		
	}
}
