package leetcode.Windows;

import java.util.Arrays;

/**
 * 1094. Car Pooling.
 * [Windows]
 * 1. Build arrays <num passengers change, location>
 * 2. Sort two arrays by 1. location 2. destination first
 * 3. Iterate through the array and check if we can finish the trip within the capacity.
 * TC: O(nlogn)
 * SC: O(n)
 * @author Jeesub Lee (jeesubl@andrew.cmu.edu)
 */
public class Q1094_CarPooling {
    public boolean carPooling(int[][] trips, int capacity) {
        int[][] passengers = new int[trips.length * 2][2];
        for (int i = 0; i < trips.length; i++) {
            passengers[2 * i][0] = trips[i][0];
            passengers[2 * i][1] = trips[i][1];
            passengers[2 * i + 1][0] = -trips[i][0];
            passengers[2 * i + 1][1] = trips[i][2];
        }
        Arrays.sort(passengers, (a, b) -> {
            if (a[1] != b[1]) {
                return Integer.compare(a[1], b[1]);
            }
            return Integer.compare(a[0], b[0]);
        });

        int num = 0;
        for (int i = 0; i < passengers.length; i++) {
            num += passengers[i][0];
            if (num > capacity) {
                return false;
            }
        }

        return true;
    }
}
