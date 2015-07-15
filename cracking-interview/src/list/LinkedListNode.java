package list;

import java.util.HashMap;

import static java.lang.Boolean.*;

public class LinkedListNode {
	int data;
	LinkedListNode next;
	
	public LinkedListNode(int data) {
		this.data = data;
	}
	
	public void appendToTail(int newData) {
		LinkedListNode currNode = this;
		while (currNode.next != null) {
			currNode = currNode.next;
		}
		currNode.next = new LinkedListNode(newData);
	}
	
	public static LinkedListNode removeNode(LinkedListNode head, int d) {
	    if (head == null) {
	        return head;
	    }
	    
	    if (head.data == d) {
	        return head.next;
	    }

	    LinkedListNode currNode = head.next;
	    LinkedListNode prevNode = head;
	    
	    while (currNode != null) {
	        if (currNode.data == d) {
	            prevNode.next = currNode.next;
	            return head;
	        }
	        prevNode = currNode;
	        currNode = currNode.next;
	    }
	    
	    return head;
	}
	
	public static void removeDuplicates(LinkedListNode node) {
	    LinkedListNode prevNode = null;
	    HashMap<Integer, Boolean> found = new HashMap<Integer, Boolean>();
	    while (node != null) {
	        if (!found.containsKey(Integer.valueOf(node.data))) {
	            found.put(Integer.valueOf(node.data), TRUE);
	            prevNode = node;
	        }
	        else {
	            prevNode.next = node.next;
	        }
	        node = node.next;
	    }
	}


	public static void removeDuplicates2(LinkedListNode node) {
	    while (node != null) {
	        LinkedListNode prevNode = node;
	        LinkedListNode currNode = node.next;
	        while (currNode != null) {
	            if (currNode.data == node.data) {
	                prevNode.next = currNode.next;
	            }
	            else {
	                prevNode = currNode;
	            }
	            currNode = currNode.next;
	        }
	    }
	}
	
	public static LinkedListNode nthToLast(LinkedListNode head, int n) {
	    if (n < 1) {
	        return null;
	    }    

	    LinkedListNode currNode = head;
	    LinkedListNode prevNode = head;

	    for (int i = 0; i < n-1; i++) {
	        if (currNode == null) {
	            return null;
	        }
	        currNode = currNode.next;
	    }

	    while (currNode.next != null) {
	        currNode = currNode.next;
	        prevNode = prevNode.next;
	    }

	    return prevNode;
	}
	
	public static LinkedListNode addNumbers(LinkedListNode num1, LinkedListNode num2) {
	    if (num1 == null || num2 == null) {
	        return null;
	    }   

	    int sum = num1.data + num2.data;
	    LinkedListNode resultHead = new LinkedListNode(sum%10);
	    int carry = sum/10;
	    num1 = num1.next;
	    num2 = num2.next;

	    LinkedListNode resultNode = resultHead;

	    while (num1 != null && num2 != null) {
	        sum = num1.data + num2.data + carry;
	        resultNode.next = new LinkedListNode(sum%10);
	        resultNode = resultNode.next;
	        carry = sum/10;
	        num1 = num1.next;
	        num2 = num2.next;
	    }
	        
	    while (num1 != null) {
	        sum = num1.data + carry;
	        resultNode.next = new LinkedListNode(sum%10);
	        resultNode = resultNode.next;
	        carry = sum/10;
	        num1 = num1.next;
	    }

	    while (num2 != null) {
	        sum = num2.data + carry;
	        resultNode.next = new LinkedListNode(sum%10);
	        resultNode = resultNode.next;
	        carry = sum/10;
	        num2 = num2.next;
	    }

	    if (carry > 0) {
	        resultNode.next = new LinkedListNode(carry);
	        resultNode = resultNode.next;
	    }

	    return resultHead;
	}
	
	public static LinkedListNode findLoopHead(LinkedListNode head) {
	    LinkedListNode slow = head;
	    LinkedListNode fast = head;

	    while (slow != fast) {
	        slow = slow.next;
	        fast = fast.next.next;
	    }

	    fast = head;

	    while (slow != fast) {
	        slow = slow.next;
	        fast = fast.next;
	    }

	    return slow;
	}
}
