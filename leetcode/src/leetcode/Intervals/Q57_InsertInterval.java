package leetcode.Intervals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 57. Insert Interval.
 * [Intervals]
 * Iterate through the intervals using a pointer.
 * Step 1. Add intervals before the newInterval.
 * Step 2. Merge overlaps.
 * Step 3. Add the updated newInterval.
 * Step 4. Add intervals after the newInterval.
 * @author Jeesub Lee (jeesubl@andrew.cmu.edu)
 */
public class Q57_InsertInterval {
    public static int[][] insert(int[][] intervals, int[] newInterval) {
        List<int[]> result = new ArrayList<>();
        int ptr = 0;

        while (ptr < intervals.length && !overlap(intervals[ptr], newInterval) && intervals[ptr][0] < newInterval[0]) {
            result.add(intervals[ptr++]);
        }

        while (ptr < intervals.length && overlap(intervals[ptr], newInterval)) {
            newInterval[0] = Math.min(newInterval[0], intervals[ptr][0]);
            newInterval[1] = Math.max(newInterval[1], intervals[ptr][1]);
            ptr++;
        }
        result.add(newInterval);

        while (ptr < intervals.length) {
            result.add(intervals[ptr++]);
        }

        return result.toArray(new int[result.size()][2]);
    }

    private static boolean overlap(int[] a, int[] b) {
        if (a[1] < b[0] || b[1] < a[0]) {
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        int[][] intervals = {{0, 1}, {2, 3}, {4, 5}, {6, 7}, {8, 9}};
        int[] newInterval = {3, 7};
        int[][] newIntervals = insert(intervals, newInterval);
        for (int[] interval : newIntervals) {
            System.out.print(Arrays.toString(interval) + " ");
        }
        System.out.println("");
        // output: [0, 1] [2, 7] [8, 9]
    }
}
