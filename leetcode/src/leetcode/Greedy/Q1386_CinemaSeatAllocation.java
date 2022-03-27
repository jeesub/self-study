package leetcode.Greedy;

import java.util.HashMap;
import java.util.Map;

/**
 * 1386. Cinema Seat Allocation.
 * [Greedy & HashMap]
 * Build Map<row, boolean[3] availability>.
 * availability = [true if [2, 3, 4, 5] is empty,
 *                 true if [4, 5, 6, 7] is empty,
 *                 true if [6, 7, 8, 9] is empty]
 * Max group num = 2 * n.
 * if map.contains row,
 * availability[0] && availability[2], continue
 * availability[0] || availability[1] || availability[2], group num-- & continue
 * group num -= 2
 * TC: O(m + k), m = reservedSeats.length, k = num of rows in reservedSeats
 * SC: O(k)
 * @author Jeesub Lee (jeesubl@andrew.cmu.edu)
 */
public class Q1386_CinemaSeatAllocation {
    public int maxNumberOfFamilies(int n, int[][] reservedSeats) {
        Map<Integer, boolean[]> map = new HashMap<>();
        for (int[] each : reservedSeats) {
            if (each[1] == 1 || each[1] == 10) {
                continue;
            }
            if (!map.containsKey(each[0])) {
                map.put(each[0], new boolean[3]);
            }
            boolean[] unavailable = map.get(each[0]);
            if (each[1] == 2 || each[1] == 3) {
                unavailable[0] = true;
            } else if (each[1] == 4 || each[1] == 5) {
                unavailable[0] = true;
                unavailable[1] = true;
            } else if (each[1] == 6 || each[1] == 7) {
                unavailable[1] = true;
                unavailable[2] = true;
            } else {
                unavailable[2] = true;
            }
        }

        int group = 2 * n;
        for (int key : map.keySet()) {
            boolean[] unavailable = map.get(key);
            if (!unavailable[0] && !unavailable[2]) {
                continue;
            }
            if (!unavailable[0] || !unavailable[1] || !unavailable[2]) {
                group--;
            } else {
                group -= 2;
            }
        }
        return group;
    }
}
