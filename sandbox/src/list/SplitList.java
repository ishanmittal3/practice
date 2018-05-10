package list;

import java.util.Arrays;
import java.util.List;

/**
 * Given a linked list, split it into two lists where each list contains
 * alternating elements from the original list
 */
public class SplitList {

    List<Node> split(Node head) {
        if (head == null) {
            return null;
        }
        if (head.next == null) {
            return Arrays.asList(head, null);
        }

        Node result1 = head;
        Node result2 = head.next;

        Node node1 = result1;
        Node node2 = result2;
        while ((node1 != null) && (node2 != null)) {
            node1.next = node2.next;
            if (node2.next != null) {
                node2.next = node2.next.next;
            }
            node1 = node1.next;
            node2 = node2.next;
        }

        return Arrays.asList(result1, result2);
    }
}
