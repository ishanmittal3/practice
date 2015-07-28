package sandbox;

public class BstNode<T extends Comparable<T>> {

	T value;
	BstNode<T> left;
	BstNode<T> right;
	
	public BstNode(T value) {
		this.value = value;
		this.left = null;
		this.right = null;
	}
	
	public boolean isLeaf() {
		return left == null && right == null;
	}
	
	public BstNode<T> findNext() {
		BstNode<T> nextNode = this.right;
		if (nextNode == null) {
			return null;
		}
		while(nextNode.hasLeft()) {
			nextNode = nextNode.left;
		}
		return nextNode;
	}

	public boolean hasLeft() {
		return left != null;
	}
}
