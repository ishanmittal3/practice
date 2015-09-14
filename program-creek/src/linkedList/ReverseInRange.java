package linkedList;

public class ReverseInRange {

	void reverse(Node head, int start, int end) {
		Node curr = head;
		int pos = 0;
		while(pos < start) {
			curr = curr.next;
		}
	}
}
