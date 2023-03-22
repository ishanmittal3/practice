package graph;

import java.util.*;

/**
 * Given a 2D array where some positions are occupied by bikes
 * and some by persons, find a bike for a given person.
 * Every person can get a bike.
 * Everyone chooses the bike closes to them,
 * unless there is a contention, in which case,
 * the person closer to the bike chooses it
 */
public class BikeAllocation {

    private Map<int[], int[]> findBike(char[][] grid) {
        List<int[]> persons = getPersons(grid);
        Map<int[], int[]> bikesMap = new HashMap<>();
        List<Queue<int[]>> queues = new ArrayList<>();
        List<boolean[][]> seens = new ArrayList<>();
        for (int[] person : persons) {
            ArrayDeque<int[]> q = new ArrayDeque<>();
            q.add(person);
            q.add(new int[]{-1, -1});
            queues.add(q);
            boolean[][] seen = new boolean[grid.length][grid[0].length];
            seen[person[0]][person[1]] = true;
            seens.add(seen);
        }

        while (bikesMap.size() < persons.size()) { // because every person can find a bike
            for (int i = 0; i < persons.size(); i++) {
                int[] person = persons.get(i);
                if (bikesMap.containsKey(person)) { // this won't work, so we'll need to store the mapping in another way
                    continue;
                }
                boolean[][] seen = seens.get(i);
                Queue<int[]> q = queues.get(i);
                while (!q.isEmpty()) {
                    int[] front = q.poll();
                    if (front[0] == -1) {
                        break;
                    }
                    //seen[front[0]][front[1]] = true;
                    List<int[]> neighbors = getUnseenNeighbors(front, seen);
                    for (int[] neighbor : neighbors) {
                        if (grid[neighbor[0]][neighbor[1]] == 'b') {
                            bikesMap.put(person, neighbor);
                            continue;
                        }
                        q.add(neighbor);
                        seen[neighbor[0]][neighbor[1]] = true;
                    }
                }
                q.add(new int[]{-1,-1});
            }
        }
        return bikesMap;
    }

    private List<int[]> getUnseenNeighbors(int[] front, boolean[][] seen) {
        List<int[]> neighbors = new ArrayList<>();
        int i = front[0];
        int j = front[1];

        return neighbors;
    }

    private List<int[]> getPersons(char[][] grid) {
        List<int[]> persons = new ArrayList<>();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 'p') {
                    persons.add(new int[]{i,j});
                }
            }
        }
        return persons;
    }
}
