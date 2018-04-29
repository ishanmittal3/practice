package graph;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

/**
 * Given a graph, find the arrival and departure times of its vertices in DFS.
 *  Arrival time is the time at which the vertex was explored for the first time
 *  in DFS
 *  Departure time is the time at which we have explored all the neighbors of
 *  the vertex and we are ready to backtrack
 *
 *  example: http://www.techiedelight.com/arrival-departure-time-vertices-dfs/
 */
public class ArrivalDepartureTimes {

    Times getTimes(List<Node> nodes) {
        Map<Node, Integer> arrivals = new HashMap<>();
        Map<Node, Integer> departures = new HashMap<>();
        Stack<Node> stack = new Stack<>();
        int time = 0;

        for (Node node : nodes) {
            if (arrivals.containsKey(node)) {
                continue;
            }
            stack.push(node);
            while (!stack.empty()) {
                Node top = stack.peek();
                if (arrivals.containsKey(top)) {
                    if (!departures.containsKey(node)) {
                        departures.put(node, time++);
                    }
                    stack.pop();
                    continue;
                }
                arrivals.put(top, time++);
                for (Node neighbor : top.neighbors) {
                    stack.push(neighbor);
                }
            }
        }

        return new Times(arrivals, departures);
    }

    class Times {
        Map<Node, Integer> arrivals;
        Map<Node, Integer> departures;

        public Times(Map<Node, Integer> arrivals, Map<Node, Integer> departures) {
            this.arrivals = arrivals;
            this.departures = departures;
        }
    }
}
