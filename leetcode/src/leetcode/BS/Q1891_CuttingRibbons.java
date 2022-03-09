package leetcode.BS;

import java.util.Arrays;

/**
 * 1891. Cutting Ribbons.
 * [Binary Search]
 * BS between 0 and max(ribbons)
 * if valid(mid), left = mid + 1
 * if invalid(mid), right = mid - 1
 * return right
 * valid? sum(ribbon / length) >= k // if length == 0, return true
 * use long to handle the overflow
 * TC: O(n * logk), where k = max(ribbons)
 * SC: O(1)
 * @author Jeesub Lee (jeesubl@andrew.cmu.edu)
 */
public class Q1891_CuttingRibbons {
    public static int maxLength(int[] ribbons, int k) {
        int left = 0;
        int right = Arrays.stream(ribbons).max().getAsInt();
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (valid(ribbons, k, mid)) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return right;
    }

    private static boolean valid(int[] ribbons, int k, int length) {
        if (length == 0) {
            return true;
        }

        long count = 0;
        for (int ribbon : ribbons) {
            count += ribbon / length;
        }
        return count >= k;
    }

    public static void main(String[] args) {
        int[] ribbons = {3, 5, 7, 9};
        int k = 5;
        System.out.println(maxLength(ribbons, k));
        // output: 3
    }
}
