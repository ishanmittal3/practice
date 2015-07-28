package sandbox;

import java.util.ArrayList;
import java.util.HashSet;

public class TopologicalSort {

	public ArrayList<String> topSort(HashSet<Edge> edgeSet) {
		ArrayList<String> sorted = new ArrayList<String>();
		HashSet<Edge> edges = (HashSet<Edge>) edgeSet.clone();
		HashSet<String> startNodes = getStartNodes(edges);
		
		while (!startNodes.isEmpty()) { // O(V)
			String startNode = startNodes.iterator().next();
			startNodes.remove(startNode);
			sorted.add(startNode);
			removeEdgesWithFromNode(edges, startNode);
			startNodes.addAll(getStartNodes(edges));
		}
		
		return sorted;
	}
	
	// O(E)
	private void removeEdgesWithFromNode(HashSet<Edge> edges, String startNode) {
		HashSet<Edge> edgeSet = (HashSet<Edge>) edges.clone();
		for (Edge edge : edgeSet) {
			if (edge.from.equals(startNode)) {
				edges.remove(edge);
			}
		}
	}

	// O(E)
	private HashSet<String> getStartNodes(HashSet<Edge> edges) {
		HashSet<String> nodes = getAllNodes(edges);
		for (Edge edge : edges) {
			nodes.remove(edge.to);
		}
		return nodes;
	}

	// O(E)
	private HashSet<String> getAllNodes(HashSet<Edge> edges) {
		HashSet<String> nodes = new HashSet<String>();
		for (Edge edge : edges) {
			nodes.add(edge.from);
			nodes.add(edge.to);
		}
		return nodes;
	}

	class Edge {
		String from;
		String to;
	}
}
