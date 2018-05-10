package tree;

import java.util.Arrays;
import java.util.List;

/**
 * Given a BST, find floor and ceil of a given key in it
 */
public class FloorCeil {
    
    List<Integer> getFloorCeil(Node root, int key) {
        int floor = root.value;
        int ceil = root.value;
        Node curr = root;
        while (curr != null) {
            if (curr.value == key) {
                break;
            }
            if (key < curr.value) {
                ceil = curr.value;
                curr = curr.left;
            } else {
                floor = curr.value;
                curr = curr.right;
            }
        }
        return Arrays.asList(floor, ceil);
    }
}
