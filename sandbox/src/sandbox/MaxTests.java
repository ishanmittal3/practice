package sandbox;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.HashSet;

/**
 * We're given a n x 2 2-D array, where each of the n rows represents a test.
 *     The first element of the row is the index of the project in which tests lies, and
 *     the second element is the time it takes for the test to run. In each project, tests
 *     can only run in the order in which they are present in the list; however, tests of
 *     different projects are independent of each other, and can be run in any order.
 *     We have a single machine and only one test can run at a time.
 *     Given the array of tests and the total CPU time available to run the tests,
 *     find the maximum number of tests that can be run.
 *
 *     Your output should be a list of integers, where each index in the list is the
 *     project index, and the value at that index is the number of tests that should
 *     be run in that project. For example, if you have 3 projects, then the output
 *     [2, 0, 1] indicates that you'll run 2 tests from the first project,
 *     0 from the second and 1 from the third.
 */
public class MaxTests {

    public static final int MAX_TIME = 20;

    public static void main(String[] args) {
        int[][] testDetails = {
                {3, 8},
                {1, 4},
                {2, 5},
                {2, 10},
                {4, 12},
                {1, 3},
                {2, 7},
                {3, 14},
                {3, 2},
                {4, 1}
        };
        int testTime = 20;
        System.out.println(getNumTests(testDetails, testTime));
        // Expected Output: [2, 1, 1, 0]
    }

    // 1: ,
    // 2: , 10, 7
    // 3: 8, 14, 2
    // 4: 12, 1

    // 2, 1, 0, 0
    // 12

    private static List<Integer> getNumTests(int[][] testDetails, int totalTime) {
        int numProjects = getNumProjects(testDetails);
        List<Queue<Integer>> queues = new ArrayList<>();
        for (int pos = 0; pos < numProjects; pos++) {
            queues.add(new ArrayDeque<>());
        }
        for (int[] test : testDetails) {
            int numProject = test[0] - 1;
            int timeTaken = test[1];
            queues.get(numProject).add(timeTaken);
        }
        return getNumTests(queues, totalTime);
    }

    private static int getNumProjects(int[][] testDetails) {
        Set<Integer> projects = new HashSet<>();
        for (int[] test : testDetails) {
            projects.add(test[0]);
        }
        return projects.size();
    }

    private static List<Integer> getNumTests(List<Queue<Integer>> testTimes, int totalTime) {
        List<Integer> numTests = new ArrayList<>();
        for (int pos = 0; pos < testTimes.size(); pos++) {
            numTests.add(0);
        }
        int time = 0;
        while (time < totalTime) {
            List<Integer> currTimes = getTimes(testTimes);
            int minIndex = getMinIndex(currTimes);
            int testTime = testTimes.get(minIndex).poll();
            if (time + testTime <= totalTime) {
                time += testTime;
            } else {
                break;
            }
            numTests.set(minIndex, numTests.get(minIndex) + 1);
        }
        return numTests;
    }

    private static List<Integer> getTimes(List<Queue<Integer>> testTimes) {
        List<Integer> times = new ArrayList<>();
        for (Queue<Integer> queue : testTimes) {
            if (queue.isEmpty()) {
                times.add(MAX_TIME);
            } else {
                times.add(queue.peek());
            }
        }
        return times;
    }

    private static int getMinIndex(List<Integer> times) {
        int minIndex = 0;
        for (int index = 1; index < times.size(); index++) {
            if (times.get(index) < times.get(minIndex)) {
                minIndex = index;
            }
        }
        return minIndex;
    }
}
