package graph;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BipartiteGraph {

    boolean isBipartite(List<Edge> edges) {
        Set<Integer> set1 = new HashSet<>();
        Set<Integer> set2 = new HashSet<>();

        for (Edge edge : edges) {
            int v1 = edge.v1;
            int v2 = edge.v2;
            if (set1.contains(v1)) {
                if (set1.contains(v2)) {
                    return false;
                } else {
                    set2.add(v2);
                    continue;
                }
            }
            if (set2.contains(v1)) {
                if (set2.contains(v2)) {
                    return false;
                } else {
                    set1.add(v2);
                    continue;
                }
            }
            if (set1.contains(v2)) {
                set2.add(v1);
                continue;
            }
            if (set2.contains(v2)) {
                set1.add(v1);
                continue;
            }
            set1.add(v1);
            set2.add(v2);
        }

        return true;
    }

    class Edge {
        int v1;
        int v2;
    }
}
