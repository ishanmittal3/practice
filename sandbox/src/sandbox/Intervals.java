package sandbox;

import java.util.ArrayList;
import java.util.List;

public class Intervals {

    public static void main(String args[] ) throws Exception {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT */
        List<Interval> int1 = new ArrayList<>();
        int1.add(new Interval(0, 2));
        int1.add(new Interval(5, 10));
        int1.add(new Interval(16, 20));
        int1.add(new Interval(25, 26));
        int1.add(new Interval(28, 30));

        List<Interval> int2 = new ArrayList<>();
        int2.add(new Interval(1, 5));
        int2.add(new Interval(11, 12));
        int2.add(new Interval(14, 18));
        int2.add(new Interval(20, 23));

        System.out.println(intersect(int1, int2));
        System.out.println(or(int1, int2));
    }

    /**
     * Given 2 sets of intervals.
     *
     * Interval is defined with left and right border and discrete points, like [2, 3], [0, 0], etc.
     *
     * Set of intervals is non intersected set of sorted intervals, for example: [0, 0], [2, 2], [5, 10] is a valid set of intervals, but :
     * * [0, 0], [1, 2] is not valid, because you can write it as [0, 2].
     * * [0, 2], [1, 5] is not valid as well, since these two intervals intersect.
     *
     * You need to find the AND operation of these two sets. For example:
     *
     * 1st set: [0, 2], [5, 10], [16, 20] [25, 26] [28, 30]
     * 2nd set: [1, 5], [11, 12], [14, 18] [20, 23]
     *
     * AND Result: [1, 2], [5, 5], [16, 18], [20, 20]
     * @param intervals1
     * @param intervals2
     * @return
     */
    static List<Interval> intersect(List<Interval> intervals1, List<Interval> intervals2) {
        int pos1 = 0;
        int pos2 = 0;
        List<Interval> result = new ArrayList<>();
        while ((pos1 < intervals1.size()) && (pos2 < intervals2.size())) {
            Interval int1 = intervals1.get(pos1);
            Interval int2 = intervals2.get(pos2);
            Interval intersection = getIntersection(int1, int2);
            if (intersection != null) {
                result.add(intersection);
            }
            if (int1.end < int2.end) {
                pos1++;
            } else {
                pos2++;
            }
        }
        return result;
    }

    static Interval getIntersection(Interval int1, Interval int2) {
        if (int1.start <= int2.start) {
            if (int1.end < int2.start) {
                return null;
            } else {
                int start = int2.start;
                int end = Math.min(int1.end, int2.end);
                return new Interval(start, end);
            }
        } else {
            return getIntersection(int2, int1);
        }
    }

    static List<Interval> or(List<Interval> intervals1, List<Interval> intervals2) {
        int pos1 = 0;
        int pos2 = 0;
        Interval curr = intervals1.get(0);
        List<Interval> result = new ArrayList<>();
        while ((pos1 < intervals1.size()) && (pos2 < intervals2.size())) {
            Interval int1 = intervals1.get(pos1);
            Interval int2 = intervals2.get(pos2);
            Interval or1 = or(curr, int1);
            Interval or2 = or(curr, int2);
            if (int1.start < int2.start) {
                if (or1 == null) {
                    result.add(curr);
                    curr = or(int1, int2);
                    if (curr == null) {
                        result.add(int1);
                        curr = int2;
                    }
                } else {
                    curr = or1;
                    or2 = or(curr, int2);
                    if (or2 == null) {
                        result.add(curr);
                        curr = int2;
                    }
                }
            } else {
                if (or2 == null) {
                    result.add(curr);
                    curr = or(int2, int1);
                    if (curr == null) {
                        result.add(int2);
                        curr = int1;
                    }
                } else {
                    curr = or2;
                    or2 = or(curr, int1);
                    if (or2 == null) {
                        result.add(curr);
                        curr = int1;
                    }
                }
            }
            if (int1.end < int2.end) {
                pos1++;
            } else {
                pos2++;
            }
        }
        while (pos1 < intervals1.size()) {
            result.add(intervals1.get(pos1++));
        }
        while (pos2 < intervals2.size()) {
            result.add(intervals2.get(pos2++));
        }
        return result;
    }

    static Interval or(Interval int1, Interval int2) {
        if (int1.start <= int2.start) {
            if (int2.start - int1.end > 1) {
                return null;
            } else {
                return new Interval(int1.start, Math.max(int1.end, int2.end));
            }
        } else {
            return or(int2, int1);
        }
    }

    static class Interval {
        int start;
        int end;

        Interval(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public String toString() {
            return "[" + start + ", " + end + "]";
        }
    }
}
