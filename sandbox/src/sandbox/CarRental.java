package sandbox;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;

public class CarRental {

	/**
	 * Given a list of intervals for which cars are rented,
	 * what's the minimum number of cars required in inventory
	 * or
	 * find the maximum number of cars rented at a time
	 * 
	 * In this solution, since @see PriorityQueue is based on a heap,
	 * inserts and polls are O(log n). So the overall order would be (n log n) 
	 */
	int getMaxCars(List<Interval> intervals) {
		PriorityQueue<Interval> startQ = new PriorityQueue<>(
				new Comparator<Interval>() {
					@Override
					public int compare(Interval i1, Interval i2) {
						return i1.start - i2.start;
					}					
				});
		startQ.addAll(intervals);
		
		PriorityQueue<Interval> endQ = new PriorityQueue<>(
				new Comparator<Interval>() {
					@Override
					public int compare(Interval i1, Interval i2) {
						return i1.end - i2.end;
					}					
				});
		endQ.addAll(intervals);
		
		Set<Integer> times = new HashSet<>();
		for (Interval i : intervals) {
			times.add(i.start);
			times.add(i.end);
		}
		List<Integer> allTimes = new ArrayList<>(times);
		Collections.sort(allTimes);
		int currCars = 0;
		int maxCars = 0;
		
		for (int time : allTimes) {
			while (startQ.peek().start == time) {
				startQ.poll();
				currCars++;
			}
			while (endQ.peek().end == time) {
				endQ.poll();
				currCars--;
			}
			maxCars = Math.max(currCars, maxCars);
		}
		return maxCars;
	}
	
	class Interval {
		int start; 
		int end;
	}
}
