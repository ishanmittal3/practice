package linkedList;

public class ReverseInRange {

	Node reverse(Node head, int start, int end) {
		Node curr = head;
		Node prev = start > 0 ? head : null;
		int pos = 0;
		while(pos < start - 1) {
			prev = prev.next;
			pos++;
		}
		if(prev != null) {
			curr = prev.next;			
		}
		Node prevStartNode = prev;
		Node startNode = curr;
		
		prev = curr;
		curr = curr.next;
		for(pos = start; pos < end; pos++) {
			Node next = curr.next;
			curr.next = prev;
			prev = curr;
			curr = next;
		}
		startNode.next = curr;
		if(prevStartNode != null) {
			prevStartNode.next = prev;
			return head;
		} else {
			return prev;
		}
	}
}
