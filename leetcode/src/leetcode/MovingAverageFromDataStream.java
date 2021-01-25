package leetcode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 
 * class has
 * 1. int queue
 * 2. int windows size (set at initializing)
 * 3. int sum so far
 * 
 * add new value to the sum
 * if the queue size > windows size, remove the first one
 * remove the value from sum so far
 * 
 * @author jeesublee
 *
 */
public class MovingAverageFromDataStream {

	class MovingAverage {
		private Deque<Integer> queue;
		private int size;
		private int sum;

		/** Initialize your data structure here. */
		public MovingAverage(int size) {
			this.queue = new ArrayDeque<>();
			this.size = size;
			this.sum = 0;
		}

		public double next(int val) {
			queue.addLast(val);
			sum += val;
			if (queue.size() > size) {
				sum -= queue.removeFirst();
			}
			return (double) sum / (double) queue.size();
		}
	}

}
