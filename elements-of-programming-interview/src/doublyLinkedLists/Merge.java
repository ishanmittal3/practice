package doublyLinkedLists;

public class Merge {
	Node merge(Node L1, Node L2) {
		if(L1 == null) {
			return L2;
		}
		if(L2 == null) {
			return L1;
		}
		
		Node result;
		if(L1.data < L2.data) {
			result = L1;
			L1 = L1.next;
		} else {
			result = L2;
			L2 = L2.next;
		}
		
		Node prev = null;
		Node curr = result;
		while(L1 != null && L2 != null) {
			if(L1.data < L2.data) {
				curr.next = L1;
				L1 = L1.next;
			} else {
				curr.next = L2;
				L2 = L2.next;
			}
			prev = curr;
			curr = curr.next;
			curr.prev = prev;
		}
		
		if(L1 == null && L2 == null) {
			return result;
		}
		
		if(L1 == null) {
			curr.next = L2;
			L2.prev = curr;
		} else {
			curr.next = L1;
			L1.prev = curr;
		}
		return result;
	}
}
