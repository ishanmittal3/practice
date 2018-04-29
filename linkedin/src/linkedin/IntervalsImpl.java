package linkedin;

import java.util.ArrayList;
import java.util.List;

public class IntervalsImpl implements Intervals {
    List<Interval> intervals;
    
    @Override
	public void addInterval(int from, int to) {
        if(intervals == null || intervals.isEmpty()) {
            intervals.add(new Interval(from, to));
        } else {
            Interval curr = new Interval(from, to);
            for(Interval intv : intervals) {
                if(isOverlap(curr, intv)) {
                    curr = union(curr, intv);                    
                }
            }
            List<Interval> updated = new ArrayList<Interval>();
            for(Interval intv : intervals) {
                if(!isOverlap(curr, intv)) {
                    updated.add(intv);
                }
            }
            updated.add(curr);
            intervals = updated;            
        }
    }
    
    private Interval union(Interval i1, Interval i2) {
    	return new Interval(Math.min(i1.from, i2.from), 
    						Math.max(i1.to, i2.to));
	}

	boolean isOverlap(Interval i1, Interval i2) {
        return (i1.from >= i2.from && i1.from <= i2.to) ||
        (i2.from >= i1.from && i2.from <= i1.to);
    }

	@Override
	public int getTotalCoveredLength() {
		int result = 0;
		for(Interval intv : intervals) {
			result += intv.to - intv.from;
		}
		return result;
	}
	
	static class Interval {
		int from;
		int to;
		
		Interval(int from, int to) {
			this.from = from;
			this.to = to;
		}
	}
}
