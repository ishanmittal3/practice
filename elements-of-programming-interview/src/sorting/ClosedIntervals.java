package sorting;

import java.util.ArrayList;
import java.util.List;

public class ClosedIntervals {
	static class Interval {
		int start;
		int end;
		
		Interval(int start, int end) {
			this.start = start;
			this.end = end;
		}
	}
	
	List<Interval> union(Interval[] intervals, Interval newInterval) {
		List<Interval> result = new ArrayList<Interval>();
		int pos = 0;
		Interval unionInterval = null;
		while(pos < intervals.length) {
			Interval interval = intervals[pos];
			if(isIntersection(interval, newInterval)) {
				unionInterval = union(interval, newInterval);
				break;
			} else {
				result.add(interval);
			}
			pos++;
		}
		if(unionInterval == null) {
			unionInterval = newInterval;
		}
		while(pos < intervals.length && isIntersection(intervals[pos], unionInterval)) {
			unionInterval = union(intervals[pos], unionInterval);
			pos++;
		}
		result.add(unionInterval);
		while(pos < intervals.length) {
			result.add(intervals[pos++]);
		}
		return result;
	}

	private Interval union(Interval interval1, Interval interval2) {
		if(interval1 == null || interval2 == null || !isIntersection(interval1, interval2)) {
			return null;
		}
		return new Interval(Math.min(interval1.start, interval2.start),
							Math.max(interval1.end, interval2.end));
	}

	private boolean isIntersection(Interval interval1, Interval interval2) {
		return (interval1.start <= interval2.start && interval1.end >= interval2.start) ||
			   (interval2.start <= interval1.start && interval2.end >= interval1.start);
	}
}
