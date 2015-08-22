package sandbox;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MakeTree {

	static class ListNode {
		int value;
		Integer parent;
		boolean isLeft;
	}
	
	static class Node {
		int value;
		Node left;
		Node right;
		
		Node(int value) {
			this.value = value;
		}
	}
	
	/**
	 * @param list - does not have duplicates
	 * @return root of the newly constructed tree
	 */
	Node makeTree(List<ListNode> list) {
		// mapping from a node to its children 
		Map<Integer, List<ListNode>> map = new HashMap<Integer, List<ListNode>>();
		for(ListNode node : list) {
			Integer parent = node.parent;
			if(parent == null) { // root node
				continue;
			}
			if(map.containsKey(parent)) {
				map.get(parent).add(node);
			} else {
				List<ListNode> children = new ArrayList<ListNode>();
				children.add(node);
				map.put(parent, children);
			}
		}
		int root = getRoot(list);
		return makeTree(root, map);
	}
	
	private Integer getRoot(List<ListNode> list) {
		for(ListNode node : list) {
			if(node.parent == null) {
				return node.value;
			}
		}
		return null;
	}
	
	Node makeTree(int value, Map<Integer, List<ListNode>> map) {
		Node node = new Node(value);
		if(!map.containsKey(node)) {
			return node;
		}
		for(ListNode child : map.get(node.value)) {
			if(child.isLeft) {
				node.left = makeTree(child.value, map);
			} else {
				node.right = makeTree(child.value, map);
			}
		}
		return node;
	}
}
