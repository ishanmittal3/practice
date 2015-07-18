package linkedLists;

public class Merge {
	public static Node merge(Node L1, Node L2) {
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
		
		Node curr = result;
		while(L1 != null && L2 != null) {
			if(L1.data < L2.data) {
				curr.next = L1;
				L1 = L1.next;
			} else {
				curr.next = L2;
				L2 = L2.next;
			}
			curr = curr.next;
		}
		
		if(L1 == null) {
			curr.next = L2;
		} else {
			curr.next = L1;
		}
		
		return result;
	}
}
