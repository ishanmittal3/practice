package list;

import java.util.ArrayList;
import java.util.List;

public class Intersection {

    /**
     * Given two sorted linked lists, find their intersection
     */
    Node getIntersection(Node list1, Node list2) {
        List<Integer> intersection = new ArrayList<>();
        Node node1 = list1;
        Node node2 = list2;
        while ((node1 != null) && (node2 != null)) {
            if (node1.value == node2.value) {
                intersection.add(node1.value);
            }
            if (node1.value <= node2.value) {
                node1 = node1.next;
            }
            if (node2.value <= node1.value) {
                node2 = node2.next;
            }
        }

        if (intersection.isEmpty()) {
            return null;
        }
        Node result = new Node(intersection.get(0));
        Node curr = result;
        for (int pos = 1; pos < intersection.size(); pos++) {
            curr.next = new Node(intersection.get(pos));
            curr = curr.next;
        }
        return result;
    }
}
