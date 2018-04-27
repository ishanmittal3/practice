package sandbox;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given a m by n board where each cell is 1(alive) or 0(dead).
 * If a cell is alive and has 2 or 3 live neighbors, it remains alive otherwise it becomes dead.
 * If a cell is dead and has exact 3 live neighbors, it becomes alive otherwise it remains dead.
 *
 * A cell can have at most 8 neighbors
 */
public class GameOfLife {

    boolean[][] getNext(boolean[][] matrix) {
        int X = matrix[0].length;
        int Y = matrix.length;
        boolean[][] next = new boolean[X][Y];

        for (int x = 0; x < X; x++) {
            for (int y = 0; y < Y; y++) {
                List<Node> neighbors = getNeighbors(x, y, X, Y);
                int countAlive = countAlive(neighbors, matrix);
                if (matrix[x][y]) {
                    if (countAlive < 2 || countAlive > 3) {
                        next[x][y] = false;
                    } else {
                        next[x][y] = true;
                    }
                } else {
                    if (countAlive == 3) {
                        next[x][y] = true;
                    }
                }
            }
        }

        return next;
    }

    List<Node> getNeighbors(int x, int y, int X, int Y) {
        List<Node> list = Arrays.asList(new Node(x, y - 1), new Node(x, y + 1), new Node(x - 1, y),
                new Node(x - 1, y - 1), new Node(x - 1, y + 1), new Node(x + 1, y),
                new Node(x + 1, y - 1), new Node(x + 1, y + 1));

        List<Node> neighbors = new ArrayList<>();
        for (Node node : list) {
            if (node.x >= 0 && node.x < X && node.y >= 0 && node.y < Y) {
                neighbors.add(node);
            }
        }
        return neighbors;
    }


    int countAlive(List<Node> nodes, boolean[][] matrix) {
        int count = 0;
        for (Node node : nodes) {
            if (matrix[node.x][node.y]) {
                count++;
            }
        }
        return count;
    }

    class Node {
        int x;
        int y;

        Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
