package sorting;

import java.util.List;
import java.util.PriorityQueue;

public class OverlappingTimeIntervals {
	static class Event implements Comparable<Event> {
		int time;
		boolean isStart;
		int bandwidth;
		
	Event(int time, boolean isStart, int bandwidth) {
		this.time = time;
		this.isStart = isStart;
		this.bandwidth = bandwidth;
	}
		
		@Override
		public int compareTo(Event event) {
			int compareTime = Integer.compare(time, event.time);
			return compareTime != 0 ? compareTime :
				!event.isStart ? -1 : 1;
		}
	}
	
	static class Usage {
		int startTime;
		int endTime;
		int bandwidth;
	}
	
	int getPeakBandwidth(List<Usage> usages) {
		PriorityQueue<Event> pq = new PriorityQueue<Event>();
		for(Usage usage : usages) {
			pq.add(new Event(usage.startTime, true, usage.bandwidth));
			pq.add(new Event(usage.endTime, false, usage.bandwidth));
		}
		int peakBandwidth = 0;
		int bandwidth = 0;
		while(!pq.isEmpty()) {
			Event event = pq.poll();
			if(event.isStart) {
				bandwidth += event.bandwidth;
			} else {
				bandwidth -= event.bandwidth;
			}
			if(peakBandwidth < bandwidth) {
				peakBandwidth = bandwidth;
			}
		}
		return peakBandwidth;
	}
}
