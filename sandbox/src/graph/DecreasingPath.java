package graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DecreasingPath {

    /**
     * Given a 2D matrix containing heights,
     * find the longest path of decreasing numbers
     * e.g.
     * [9 8 7]        [9 8 7]
     * [4 5 6]   or   [2 1 6]
     * [3 2 1]        [3 4 5]
     * should give [9 8 7 6 5 4 3 2 1]
     */
    public List<Position> getLongestPath(int[][] heights) {
        Map<Position, List<Position>> paths = new HashMap<>();
        List<Position> maxPath = new ArrayList<>();
        for (int x = 0; x < heights[0].length; x++) {
            for (int y = 0; y < heights.length; y++) {
                Position start = new Position(x, y);
                List<Position> path = getLongestPath(heights, start, paths);
                if (path.size() > maxPath.size()) {
                    maxPath = path;
                }
            }
        }
        return maxPath;
    }

    private List<Position> getLongestPath(int[][] heights, Position start,
                                          Map<Position, List<Position>> paths) {
        if (paths.containsKey(start)) {
            return paths.get(start);
        }
        List<Position> path = new ArrayList<>();
        path.add(start);
        List<Position> maxPath = new ArrayList<>();
        for (Position neighbor : getNeighbors(heights, start)) {
            /* Since we're going in the decreasing direction,
             there won't be a repetition in the current path.
             That's why, we don't need a visited Set
             */
            if (heights[neighbor.x][neighbor.y] < heights[start.x][start.y]) {
                List<Position> currPath = getLongestPath(heights, neighbor, paths);
                if (currPath.size() > maxPath.size()) {
                    maxPath = currPath;
                }
            }
        }
        path.addAll(maxPath);
        paths.put(start, path);
        return path;
    }

    private List<Position> getNeighbors(int[][] heights, Position start) {
        int x = start.x;
        int y = start.y;
        int X = heights[0].length;
        int Y = heights.length;
        List<Position> neighbors = new ArrayList<>();
        if (x > 0) {
            neighbors.add(new Position(x-1, y));
        }
        if (y > 0) {
            neighbors.add(new Position(x, y-1));
        }
        if (x < X-1) {
            neighbors.add(new Position(x+1, y));
        }
        if (y < Y-1) {
            neighbors.add(new Position(x, y+1));
        }
        return neighbors;
    }
}
