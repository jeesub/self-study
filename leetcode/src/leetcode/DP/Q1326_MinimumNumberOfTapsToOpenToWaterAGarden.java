package leetcode.DP;

/**
 * 1326. Minimum Number of Taps to Open to Water a Garden.
 * [Greedy]
 * 1. Make intervals (0..n + 1)
 * 2. Sort the intervals by 1. start asc 2. end dsc
 * 3. Choose the next interval using greedy algorithm
 *    (next start <= curr end & max end)
 * TC: O(nlogn)
 * SC: O(n)
 *
 * [Optimization: DP]
 * dp[i] = max reachable index from i
 * Iterate through the ranges and update dp table. dp[start] = max(dp[start], end)
 * Iterate through the dp table and choose the next interval using greedy algorithm.
 * TC: O(n)
 * SC: O(n)
 * @author Jeesub Lee (jeesubl@andrew.cmu.edu)
 */
public class Q1326_MinimumNumberOfTapsToOpenToWaterAGarden {
    public int minTaps(int n, int[] ranges) {
        int[] dp = new int[n + 1];
        for (int i = 0; i < ranges.length; i++) {
            if (ranges[i] == 0) {
                continue;
            }
            int start = Math.max(0, i - ranges[i]);
            int end = i + ranges[i];
            dp[start] = Math.max(dp[start], end);
        }

        int curr = dp[0];
        int next = dp[0];
        int count = 1;
        for (int i = 0; i < dp.length; i++) {
            next = Math.max(next, dp[i]);
            if (i < curr) {
                continue;
            }
            if (curr < i) {
                return - 1;
            }
            if (i < dp.length - 1) {
                curr = next;
                count++;
            }
        }
        return count;
    }
}
