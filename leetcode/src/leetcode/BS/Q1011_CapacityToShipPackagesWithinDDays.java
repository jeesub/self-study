package leetcode.BS;

import java.util.Arrays;

/**
 * 1011. Capacity To Ship Packages Within D Days.
 * [Binary Search]
 * left = max(sum(weights) / days, max weight)
 * right = sum(weights)
 * if mid is valid, right = mid - 1
 * else, left = mid + 1
 * TC: O(n * log(k)), where k is right - left
 * SC: O(1)
 * @author Jeesub Lee (jeesubl@andrew.cmu.edu)
 */
public class Q1011_CapacityToShipPackagesWithinDDays {
    public static int shipWithinDays(int[] weights, int days) {
        int sum = Arrays.stream(weights).sum();
        int max = Arrays.stream(weights).max().getAsInt();
        int left = Math.max(sum / days, max);
        int right = sum;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (isValid(weights, days, mid)) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    private static boolean isValid(int[] weights, int days, int capacity) {
        int sum = 0;
        for (int weight : weights) {
            if (weight > capacity) {
                return false;
            }

            if (sum + weight > capacity) {
                days--;
                sum = weight;
            } else {
                sum += weight;
            }

            if (days <= 0) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int[] weights = {3, 5, 4, 2, 1, 3, 6, 1};
        int days = 5;
        System.out.println(shipWithinDays(weights, days));
        // output: 7
    }
}
