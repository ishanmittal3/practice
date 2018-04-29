package graph;

import java.util.List;
import java.util.Objects;

public class Node {

    int value;
    List<Node> neighbors;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node node = (Node) o;
        return value == node.value &&
            Objects.equals(neighbors, node.neighbors);
    }

    @Override
    public int hashCode() {

        return Objects.hash(value, neighbors);
    }
}
