package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

/**
 * 
 * solution 1. for loop
 * sort the intervals by start
 * newStart = curr.start
 * if curr.end >= next.start? newEnd = max(curr.end, next.end)
 * else, add to result (start so far, end so far)
 *
 * solution 2. minHeap
 * put every intervals into minHeap, ordered by start
 * remove one and start to peek
 * while curr.end >= next.start, new end = max(curr.end, next.end)
 * add to result
 * while minHeap is not empty
 * 
 * @author jeesublee
 *
 */
public class MergeIntervals {

	public static int[][] mergeForLoop(int[][] intervals) {
		if (intervals.length == 0) {
			return new int[0][2];
		}
		Arrays.sort(intervals, (a, b) -> (a[0] - b[0]));
		List<int[]> result = new ArrayList<>();
		int start = intervals[0][0];
		int end = intervals[0][1];
		for (int i = 1; i < intervals.length; i++) {
			if (end >= intervals[i][0]) {
				end = Math.max(end, intervals[i][1]);
			} else {
				result.add(new int[] { start, end });
				start = intervals[i][0];
				end = intervals[i][1];
			}
		}
		if (result.isEmpty() || end != result.get(result.size() - 1)[1]) {
			result.add(new int[] { start, end });
		}
		int[][] resultArray = new int[result.size()][2];
		return result.toArray(resultArray);
	}

	public static int[][] mergeMinHeap(int[][] intervals) {
		PriorityQueue<int[]> minHeap = new PriorityQueue<>((a, b) -> (a[0] - b[0]));
		for (int[] interval : intervals) {
			minHeap.add(interval);
		}

		List<int[]> result = new ArrayList<>();
		while (!minHeap.isEmpty()) {
			int[] curr = minHeap.remove();
			while (!minHeap.isEmpty() && curr[1] >= minHeap.peek()[0]) {
				curr[1] = Math.max(curr[1], minHeap.peek()[1]);
				minHeap.remove();
			}
			result.add(curr);
		}

		int[][] resultArray = new int[result.size()][2];
		return result.toArray(resultArray);
	}

	public static void main(String[] args) {
		int[][] intervals = {{1,3},{2,6},{8,10},{15,18}};
		for (int[] each : mergeForLoop(intervals)) {
			System.out.print(Arrays.toString(each));
		}
		System.out.print("\n");
		for (int[] each : mergeMinHeap(intervals)) {
			System.out.print(Arrays.toString(each));
		}
		// output: [1, 6], [8, 10], [15, 18]
	}

}
