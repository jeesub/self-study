package leetcode.Intervals;

import java.util.ArrayList;
import java.util.List;

/**
 * 986. Interval List Intersections.
 * [Interval & Two Pointers]
 * Use two pointers i (first list pointer) & j (second list pointer)
 * if (first's start <= second's end && second's start <= first's end),
 *   // they have overlap
 *   intersaction
 *     = [max(first's start, second's start), min(first's end, second's end)]
 * if (first[i]'s end < second[j]'s end),
 *   i++
 * else,
 *   j++
 *
 * <condition of no overlap>
 * first's end < second's start || second's end < first's start
 *
 * <condition of overlap>
 * = !(condition of no overlap)
 * = !(first's end < second's start || second's end < first's start)
 * = first's end >= second's start && second's end >= first's start
 * = first's start <= second's end && second's start <= first's end
 *
 * TC: O(m + n)
 * SC: O(m + n)
 * @author Jeesub Lee (jeesubl@andrew.cmu.edu)
 */
public class Q986_IntervalListIntersections {
    public static int[][] intervalIntersection(int[][] firstList, int[][] secondList) {
        List<int[]> list = new ArrayList<>();
        int i = 0;
        int j = 0;
        while (i < firstList.length && j < secondList.length) {
            int[] first = firstList[i];
            int[] second = secondList[j];
            if (first[0] <= second[1] && second[0] <= first[1]) {
                int[] interval = new int[2];
                interval[0] = Math.max(first[0], second[0]);
                interval[1] = Math.min(first[1], second[1]);
                list.add(interval);
            }
            if (first[1] < second[1]) {
                i++;
            } else {
                j++;
            }
        }

        int[][] result = new int[list.size()][2];
        for (int k = 0; k < result.length; k++) {
            result[k] = list.get(k);
        }
        return result;
    }
}
