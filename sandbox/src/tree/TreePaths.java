package tree;

/*
 * Click `Run` to execute the snippet below!
 */

import java.util.*;

/*
 * To execute Java, please define "static void main" on a class
 * named Solution.
 *
 * If you need more classes, simply define them inline.
 */

/**
 * Zip interview question:

You're working on building a navigation header. The API serving you navigation menu items gives you a… less-than-ideal response structure. The nav items are returned in a flat array. However, the array represents multiple trees of submenus, where each item is a node.
Each node with depth=0 represents a button or link in the main header.
Children of a node are rendered in a submenu when the user clicks on the parent.
Leaf nodes represent actual links, since they do not render a submenu when clicked, and instead navigate to the corresponding page.

You’re tasked with processing this list to find unreachable nodes.

Return a list of ids of unreachable nodes that would not be rendered in submenus. The three items at the bottom of the example input should be failed.
Example output: [15, 13, 18]
Example input:
input = [
  {
    "id": 1,
    "depth": 0,
    "parentId": None,
    "name": "Directory",
  },
  {
    "id": 6,
    "depth": 2,
    "parentId": 3,
    "name": "Requests",
  },
  {
    "id": 2,
    "depth": 0,
    "parentId": None,
    "name": "Help Center",
  },
  {
    "id": 7,
    "depth": 2,
    "parentId": 3,
    "name": "Approvals",
  },
  {
    "id": 3,
    "depth": 1,
    "parentId": 1,
    "name": "Navigate",
  },
  {
    "id": 4,
    "depth": 1,
    "parentId": 2,
    "name": "Help Article 1",
  },
  {
    "id": 5,
    "depth": 1,
    "parentId": 2,
    "name": "Help Article 2",
  },
  {
    "id": 12,
    "depth": 0,
    "parentId": None,
    "name": "Log out",
  },
  {
    "id": 15,
    "depth": 1,
    "parentId": 25,
    "name": "Customer support (deprecated)",
  },
  {
    "id": 13,
    "depth": 1,
    "parentId": None,
    "name": "Home page (deprecated)",
  },
  {
    "id": 18,
    "depth": 2,
    "parentId": 13,
    "name": "Dashboard (deprecated)",
  },
]

Part 2
The output should be a list of strings representing each leaf node, and should include the navigation path to get there:
Example output: ["Directory => Navigate => Requests", "Directory => Navigate => Approvals", "Help Center => Help Article 1", "Help Center => Help Article 2", "Log out"]
 */

public class TreePaths {

    static class Node{
        int id;
        int depth;
        Integer parentId;
        String name;
        public Node(int id, int depth, Integer parentId, String name) {
            this.id = id;
            this.depth = depth;
            this.parentId = parentId;
            this.name = name;
        }
    }

    public static List<List<String>> getUnconnected(List<Node> nodes) {
        // to implement
        Stack<Node> stack = new Stack<>();
        Set<Integer> seen = new HashSet<>();
        Map<Integer, List<Node>> map = new HashMap<>();
        Map<Integer, Node> nodesMap = new HashMap<>();
        for (Node node : nodes) {
            if (node.parentId != null) {
                if (map.containsKey(node.parentId)) {
                    List<Node> children = map.get(node.parentId);
                    children.add(node);
                    map.put(node.parentId, children);
                } else {
                    List<Node> children = new ArrayList<>();
                    children.add(node);
                    map.put(node.parentId, children);
                }
            }
            if (node.depth == 0 && node.parentId == null) {
                stack.add(node);
            }
            nodesMap.put(node.id, node);
        }
        // for (Node node : nodes) {
        //   if (node.depth == 0 && node.parentId == null) {
        //     stack.add(node);
        //   }
        // }
        List<List<String>> paths = new ArrayList<>();
        while (!stack.isEmpty()) {
            Node top = stack.pop();
            seen.add(top.id);
            if (map.containsKey(top.id)) {
                List<Node> children = map.get(top.id);
                for (Node child : children) {
                    stack.push(child);
                }
            } else {
                paths.add(getPath(top, nodesMap));
            }
        }
        List<Integer> rem = new ArrayList<>();
        for (Node node : nodes) {
            if (!seen.contains(node.id)) {
                rem.add(node.id);
            }
        }
        return paths;
    }

    private static List<String> getPath(Node top, Map<Integer, Node> nodesMap) {
        List<String> path = new ArrayList<>();
        Node curr = top;
        while (curr != null) {
            path.add(curr.name);
            if (curr.parentId == null) {
                break;
            }
            curr = nodesMap.get(curr.parentId);
        }
        return reverse(path);
    }

    private static List<String> reverse(List<String> list) {
        List<String> res = new ArrayList<>();
        for (int pos = list.size()-1; pos >= 0; pos--) {
            res.add(list.get(pos));
        }
        return res;
    }

    public static void main(String[] args) {
        Node node1 = new Node(1,0,null, "Directory");
        Node node2 = new Node(6,2,3, "Requests");
        Node node3 = new Node(2,0,null, "Help Center");
        Node node4 = new Node(7,2,3, "Approvals");
        Node node5 = new Node(3,1,1, "Navigate");
        Node node6 = new Node(4,1,2, "Help Article 1");
        Node node7 = new Node(5,1,2, "Help Article 2");
        Node node8 = new Node(12,0,null, "Log out");
        Node node9 = new Node(15,1,25, "Customer support (deprecated)");
        Node node10 = new Node(13,1,null, "Home page (deprecated)");
        Node node11 = new Node(18,2,13, "Dashboard (deprecated)");
        List<Node> list = new ArrayList<>();
        list.add(node1);
        list.add(node2);
        list.add(node3);
        list.add(node4);
        list.add(node5);
        list.add(node6);
        list.add(node7);
        list.add(node8);
        list.add(node9);
        list.add(node10);
        list.add(node11);
        System.out.println(new TreePaths().getUnconnected(list));
        // Expected = 15, 13, 18
    }
}



