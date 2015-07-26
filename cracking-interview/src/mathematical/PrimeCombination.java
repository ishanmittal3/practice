package mathematical;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * Design an algorithm to find the kth number 
 * such that the only prime factors are 3, 5, and 7
 */
public class PrimeCombination {

	int getCombination(int k) {
		Queue<Integer>[] queues = new Queue[] {
				new ArrayDeque<Integer>(),
				new ArrayDeque<Integer>(),
				new ArrayDeque<Integer>()
		};
		queues[0].add(3);
		queues[1].add(5);
		queues[2].add(7);

		int curr = 1;
		for(int index = 1; index < k; index++) {
			int minIndex = min(queues);
			curr = queues[minIndex].poll();
			switch(minIndex) {
			case 0:
				queues[0].add(curr * 3);
			case 1:
				queues[1].add(curr * 5);
			case 2:
				queues[2].add(curr * 7);
			}
		}
		return curr;
	}

	private int min(Queue<Integer>[] queues) {
		int min = Integer.MAX_VALUE;
		for(Queue<Integer> q : queues) {
			min = Math.min(min, q.peek());
		}
		return min;
	}
}
