package leetcode.Windows;

import java.util.HashMap;
import java.util.Map;

/**
 * 523. Continuous Subarray Sum.
 * [Windows]
 * nums = [23, 2, 6, 4, 7], k = 6
 * acc%6 = [5, 1, 1, 5, 0]
 * sum(0..j) - sum(0..i) = sum(i + 1..j) = n * k
 * find where sum(0..j) % k = sum(0..i) % k
 *
 * Iterate through the array.
 * Track the oldest (acc % k)'s index. Also track the default sum(nothing) = 0.
 * If we have seen the curr acc sum && curr index > prev index + 1, return true.
 * TC: O(n)
 * SC: O(min(n, k))
 * @author Jeesub Lee (jeesubl@andrew.cmu.edu)
 */
public class Q523_ContinuousSubarraySum {
    public static boolean checkSubarraySum(int[] nums, int k) {
        Map<Integer, Integer> accIndexMap = new HashMap<>();
        accIndexMap.put(0, -1);
        int acc = 0;
        for (int i = 0; i < nums.length; i++) {
            acc = (acc + nums[i]) % k;
            if (!accIndexMap.containsKey(acc)) {
                accIndexMap.put(acc, i);
                continue;
            }
            if (i > accIndexMap.get(acc) + 1) {
                return true;
            }
        }
        return false;
    }
}
