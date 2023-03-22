package graph;

import java.util.*;

public class DeepCopy {

    /*
ThousandEyes interview

     */
// Create a deep copy of a directed graph.
// A deep copy is a copy of the input graph where all objects and their references point to a new instance
// of objects, but the structure and values are the same.

// You may assume there are no repeated edges or self-loops in the graph.
// You may assume the graph is connected and can be visited from the input Node.
// You may NOT assume there are no cycles in the graph.
// You may alter the Solution class as you see fit, including adding helper functions and static variables, but you may not alter the Node class.
// validate your solution




    static class Node {
        public int val;
        public List<Node> neighbors;
        public Node(int val, List<Node> neighbors) {
            this.val = val;
            this.neighbors = neighbors;
        }
        public Node(int val) {
            this.val = val;
            this.neighbors = new ArrayList<>();
        }
    }

    public static Node deepCopy(Node root) {
        Node newRoot = new Node(root.val);
        Queue<Node> q = new ArrayDeque<>();
        //Set<Node> seen = new HashSet<>();
        Map<Node, Node> map = new HashMap<>();
        //map.put(root, newRoot);

        q.add(root);
        while (!q.isEmpty()) {
            Node front = q.poll();
            // if (map.containsKey(front)) {
            //   continue;
            // }
            Node newNode;
            if (map.containsKey(front)) {
                newNode = map.get(front);
            } else {
                newNode = new Node(front.val);
                map.put(front, newNode);
            }
            for (Node neighbor : front.neighbors) {
                if (!map.containsKey(neighbor)) {
                    q.add(neighbor);
                }
                Node copy = map.containsKey(neighbor) ? map.get(neighbor) : new Node(neighbor.val);
                map.put(neighbor, copy);
                newNode.neighbors.add(copy);
            }
            //seen.add(front);
        }

        return map.get(root);
    }

    public static boolean isDeepCopy(Node node1, Node node2) {
        Queue<Node> q1 = new ArrayDeque<>();
        Queue<Node> q2 = new ArrayDeque<>();
        q1.add(node1);
        q2.add(node2);
        Set<Node> seen1 = new HashSet<>();
        Set<Node> seen2 = new HashSet<>();
        while (!q1.isEmpty() && !q2.isEmpty()) {
            Node front1 = q1.poll();
            Node front2 = q2.poll();
            if (seen1.contains(front1) || seen2.contains(front2)) {
                continue;
            }
            if (front1.val != front2.val) {
                return false;
            }
            if (front1 == front2) {
                return false;
            }
            for (Node neighbor1 : front1.neighbors) {
                if (!seen1.contains(neighbor1)) {
                    q1.add(neighbor1);
                }
            }
            for (Node neighbor2 : front2.neighbors) {
                if (!seen2.contains(neighbor2)) {
                    q2.add(neighbor2);
                }
            }
            seen1.add(front1);
            seen2.add(front2);
        }

        return q1.size() == q2.size();
    }


    public static void main(String[] args) {
        Node root = new Node(1);
        Node two = new Node(2);
        Node three = new Node(3);
        root.neighbors.add(two);
        root.neighbors.add(three);
        two.neighbors.add(three);
        three.neighbors.add(root);

        Node copy = deepCopy(root);
        System.out.println(isDeepCopy(root, copy));
        System.out.println(isDeepCopy(root, root));
        System.out.println(isDeepCopy(root, two));
    }
}

