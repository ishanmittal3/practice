package heap;

import java.util.List;
import java.util.PriorityQueue;

public class MergeSortedArrays {

	static class QEntry implements Comparable<QEntry>{
		int[] nums;
		int index;
		
		QEntry(int[] nums, int index) {
			super();
			this.nums = nums;
			this.index = index;
		}
		
		@Override
		public int compareTo(QEntry qe) {
			return Integer.compare(this.nums[this.index], qe.nums[qe.index]);
		}
	}
	
	int[] merge(List<int[]> arrays) {
		PriorityQueue<QEntry> queue = new PriorityQueue<QEntry>();
		int size = 0;
		for(int[] array : arrays) {
			queue.add(new QEntry(array, 0));
			size += array.length;
		}
		int[] result = new int[size];
		int pos = 0;
		while(!queue.isEmpty()) {
			QEntry qe = queue.poll();
			result[pos++] = qe.nums[qe.index++];
			if(qe.index < qe.nums.length) {
				queue.add(qe);
			}
		}
		return result;
	}
}
