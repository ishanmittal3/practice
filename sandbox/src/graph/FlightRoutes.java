package graph;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

/**
 * Given the following flight routes, print all possible routes between the airports C and D
 *
 *   A <----> B
 *   A <----> C
 *   A <----> D
 *   B <----> C
 *   B <----> D
 * Expected output:
 *
 *
 *   C,A,B,D,
 *   C,A,D,
 *   C,B,A,D,
 *   C,B,D,
 */
public class FlightRoutes {

    List<List<Character>> getRoutes(Map<Character, List<Character>> neighbors, char start, char end) {
        List<List<Character>> routes = new ArrayList<>();

        Stack<Node> stack = new Stack<>();
        List<Character> path = new ArrayList<>();
        path.add(start);
        Set<Character> visited = new HashSet<>();
        visited.add(start);
        Node node = new Node(start, path, visited);
        stack.push(node);
        while (!stack.empty()) {
            Node top = stack.pop();
            if (top.curr == end) {
                routes.add(top.path);
                continue;
            }
            for (char neighbor : neighbors.get(top.curr)) {
                if (!visited.contains(neighbor)) {
                    List<Character> newPath = new ArrayList<>(top.path);
                    newPath.add(neighbor);
                    Set<Character> newVisited = new HashSet<>(top.visited);
                    newVisited.add(neighbor);
                    stack.push(new Node(neighbor, newPath, newVisited));
                }
            }
        }
        return routes;
    }

    class Node {
        char curr;
        List<Character> path;
        Set<Character> visited;

        public Node(char curr, List<Character> path, Set<Character> visited) {
            this.curr = curr;
            this.path = path;
            this.visited = visited;
        }
    }
}
