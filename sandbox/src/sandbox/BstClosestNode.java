package sandbox;

public class BstClosestNode {

	Node closestNode(Node node, int key) {
		return closestNode(node, key, null);
	}
	
	Node closestNode(Node node, int key, Node closest) {
		if(node == null) {
			return closest;
		}
		if(node.value == key) {
			return node;
		}
		if(closest == null || 
				Math.abs(node.value - key) < Math.abs(closest.value - key)) {
			closest = node;
		}
		Node next = node.value < key ? 
				node.right : node.left;
		return closestNode(next, key, closest);
	}
	
	static class Node {
		int value;
		Node left;
		Node right;
	}
}
