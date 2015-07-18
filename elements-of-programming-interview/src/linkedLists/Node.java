package linkedLists;

public class Node {
	int data;
	Node next;
	
	public Node(int data) {
		this.data = data;
	}
	
	public void print() {
		Node curr = this;
		while(curr != null) {
			System.out.print(curr.data + " ");
			curr = curr.next;
		}
	}
}
