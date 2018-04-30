package graph;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;

/**
 * Given a directed graph, check if it is strongly conntected or not
 * A directed graph is said to be strongly connected if each vertex
 * is reachable from every other vertex
 */
public class StronglyConnected {

    boolean isStronglyConnected(List<Node> nodes) {
        for (Node node : nodes) {
            Set<Node> reachable = getReachable(node);
            if (reachable.size() < nodes.size()) {
                return false;
            }
        }
        return true;
    }

    Set<Node> getReachable(Node node) {
        Stack<Node> stack = new Stack<>();
        Set<Node> reachable = new HashSet<>();
        stack.push(node);
        reachable.add(node);
        while (!stack.empty()) {
            Node top = stack.pop();
            for (Node neighbor : top.neighbors) {
                if (!reachable.contains(neighbor)) {
                    stack.push(neighbor);
                    reachable.add(neighbor);
                }
            }
        }
        return reachable;
    }
}
