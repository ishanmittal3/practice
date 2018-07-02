package graph;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Queue;
import java.util.Random;

public class Minesweeper {

    private static void click(int x, int y, boolean[][] open, int[][] matrix) throws Exception {
        if (open[x][y]) {
            return;
        }
        Queue<List<Integer>> queue = new ArrayDeque<>();
        queue.add(Arrays.asList(x, y));
        while (!queue.isEmpty()) {
            List<Integer> front = queue.poll();
            open[front.get(0)][front.get(y)] = true;
            if (matrix[front.get(0)][front.get(1)] == -1) {
                throw new Exception("Game over");
            }
            if (matrix[front.get(0)][front.get(1)] == 0) {
                List<List<Integer>> neighbors = getNeighbors(matrix.length-1, matrix[0].length-1,
                    front.get(0), front.get(1));
                queue.addAll(neighbors);
            }
        }
    }

    private static class Node {
        int x;
        int y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node node = (Node) o;
            return x == node.x &&
                y == node.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }

    private static int[][] getMatrix(int X, int Y, int numBombs) {
        int[][] matrix = new int[X][Y];
        List<Integer> locations = getLocations(X*Y - 1, numBombs);
        for (int location : locations) {
            int x = location / Y;
            int y = location % Y;
            matrix[x][y] = -1;
        }

        for (int location : locations) {
            int x = location / Y;
            int y = location % Y;
            List<List<Integer>> neighbors = getNeighbors(X, Y, x, y);
            for (List<Integer> neighbor : neighbors) {
                if (matrix[neighbor.get(0)][neighbor.get(1)] != -1) {
                    matrix[neighbor.get(0)][neighbor.get(1)]++;
                }
            }
        }
        return matrix;
    }

    private static List<List<Integer>> getNeighbors(int X, int Y, int x, int y) {
        List<List<Integer>> locations = Arrays.asList(Arrays.asList(x,y-1),
            Arrays.asList(x,y+1), Arrays.asList(x-1,y-1), Arrays.asList(x-1,y),
            Arrays.asList(x-1,y+1), Arrays.asList(x+1,y-1), Arrays.asList(x+1,y),
            Arrays.asList(x+1,y+1));
        List<List<Integer>> neighbors = new ArrayList<>();
        for (List<Integer> location : locations) {
            if (location.get(0) >= 0 && location.get(0) < X
                && location.get(1) >= 0 && location.get(1) < Y) {
                neighbors.add(location);
            }
        }
        return neighbors;
    }

    // 0,1,2,3,4,5,6,7,8
    // 0,1,2,7,8,5,6,3,4]
    // 0,1,2,7,6,5,8,3,4

    private static List<Integer> getLocations(int size, int numBombs) {
        List<Integer> locations = new ArrayList<>();
        for (int pos = 0; pos < size; pos++) {
            locations.add(pos);
        }
        int last = size-1;
        for (int bomb = 0; bomb < numBombs; bomb++) {
            int rand = (new Random()).nextInt(last + 1 - bomb);
            Collections.swap(locations, rand, last);
            last--;
        }
        return locations.subList(size - numBombs, size-1);
    }
}
