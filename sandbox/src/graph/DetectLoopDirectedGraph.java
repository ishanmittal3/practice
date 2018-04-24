package graph;

import java.util.*;

public class DetectLoopDirectedGraph {

    /**
     * Given a set of courses and their dependencies,
     * find if those courses can be finished
     * @param k the courses number from 0 to k
     * @param preReqs an array of dependencies
     *                for example:
     *                [[0,1], [0,2], [1,2]]
     */
    boolean canFinish(int k, int[][] preReqs) {
        Map<Integer, Set<Integer>> deps = getDeps(k, preReqs);
        Set<Integer> visited = new HashSet<>();
        for (int course = 0; course <= k; course++) {
            if (visited.contains(course)) {
                continue;
            }
            Stack<Node> stack = new Stack<>();
            stack.push(new Node(course));
            while (!stack.empty()) {
                Node top = stack.pop();
                visited.add(top.value);
                for (int neighbor : deps.get(course)) {
                    if (visited.contains(neighbor)) {
                        continue;
                    }
                    if (top.visited.contains(neighbor)) {
                        return false;
                    }
                    Set<Integer> newVisited = new HashSet<>(top.visited);
                    newVisited.add(neighbor);
                    stack.push(new Node(newVisited, neighbor));
                }
            }
        }
        return true;
    }

    private Map<Integer, Set<Integer>> getDeps(int k, int[][] preReqs) {
        Map<Integer, Set<Integer>> depsMap = new HashMap<>();
        for (int course = 0; course < k; course++) {
            depsMap.put(course, new HashSet<>());
        }
        for (int[] dep : preReqs) {
            Set<Integer> currDeps = depsMap.get(dep[0]);
            currDeps.add(dep[1]);
        }
        return depsMap;
    }

    class Node {
        Set<Integer> visited;
        int value;

        public Node(Set<Integer> visited, int value) {
            this.visited = visited;
            this.value = value;
        }

        public Node(int value) {
            Set<Integer> visited = new HashSet<>();
            visited.add(value);
            this.visited = visited;
            this.value = value;
        }
    }
}
