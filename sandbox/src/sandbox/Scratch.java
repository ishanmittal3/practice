package sandbox;

// Visually, this would look like:
//    0  1  2  3  4  5  6
// A           1  1  1
// B     1           1
// C  1  1           1  1

//  input: [ [ [ 3, 5 ] ], [ [ 1, 1 ], [ 5, 5 ] ], [ [ 0, 1 ], [ 5, 6 ] ] ]

/*
 * Click `Run` to execute the snippet below!
 */

import java.io.*;
import java.util.*;

/*
 * To execute Java, please define "static void main" on a class
 * named Solution.
 *
 * If you need more classes, simply define them inline.
 */

class Scratch {
    public static void main(String[] args) {
        List<int[]> A = Arrays.asList(new int[]{3,5});
        List<int[]> B = Arrays.asList(new int[]{1,1}, new int[]{5,5});
        List<int[]> C = Arrays.asList(new int[]{0,1}, new int[]{5,6});
        System.out.println(days(Arrays.asList(A,B,C)));
    }

    public static int days(List<List<int[]> > input) {
        List<int[]> intervals = new ArrayList<>();
        for (List<int[]> i : input) {
            intervals.addAll(i);
        }
        if (intervals.size() == 0) {
            return 0;
        }
        Collections.sort(intervals, (a,b) -> (a[0] - b[0]));
        List<int[]> merged = new ArrayList<>();
        int[] curr = intervals.get(0);
        for (int pos = 1; pos < intervals.size(); pos++) {
            int[] interval = intervals.get(pos);
            if (interval[0] > curr[1]) {
                merged.add(curr);
                curr = interval;
            } else {
                curr = new int[]{curr[0], Math.max(curr[1], interval[1])};
            }
        }
        merged.add(curr);
        int numDays = 0;
        for (int[] interval : merged) {
            numDays += interval[1] - interval[0] + 1;
        }
        return numDays;
    }


}



