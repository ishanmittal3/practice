package graph;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SearchMaze {
	// DFS => O(V + E)
	List<Node> findPath(Set<Node> nodes, Node start, Node end) {
		return findPath(nodes, start, end, new HashSet<Node>(), 
				new ArrayList<Node>());
	}
	
	List<Node> findPath(Set<Node> nodes, Node start,
			Node end, Set<Node> visited, List<Node> path) {
		visited.add(start);
		path.add(start);
		if(start == end) {
			return path;
		}
		for(Node neighbor : start.neighbors) {
			if(!visited.contains(neighbor)) {
				List<Node> currPath = findPath(nodes, neighbor, end, 
						new HashSet<Node>(visited), 
						new ArrayList<Node>(path));
				if(currPath != null) {
					return currPath;
				}
			}
		}
		return null;
	}
}
