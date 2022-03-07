package leetcode.DP;

/**
 * 983. Minimum Cost For Tickets.
 * [DP]
 * days = [1,4,6,7,8,20]
 * costs = [2, 7, 15]
 *
 *            v        v     v  v  v                                   v
 *       | 0  1  2  3  4  5  6  7  8  9  10 11 12 13 14 15 16 17 18 19 20
 * ------+----------------------------------------------------------------
 * spent | 0  2  2  2  4  4  6  8  9  9  9  9  9  9  9  9  9  9  9  9  11
 *
 * dp[i] =
 *     if don't have to buy a ticket, dp[i - 1]
 *     if have to buy a ticket, min among
 *         1. dp[i - 1] + costs[1-day]
 *         2. dp[i - 7] + costs[7-day]     // if i < 7, costs[7-day]
 *         3. dp[i - 30] + costs[30-day]   // if i < 30, costs[30-day]
 * TC: O(max days)
 * SC: O(max days)
 * @author Jeesub Lee (jeesubl@andrew.cmu.edu)
 */
public class Q983_MinimumCostForTickets {
    public int mincostTickets(int[] days, int[] costs) {
        int[] dp = new int[days[days.length - 1] + 1];
        int dayIndex = 0;
        for (int i = 1; i < dp.length; i++) {
            if (days[dayIndex] != i) {
                dp[i] = dp[i - 1];
                continue;
            }
            dayIndex++;
            dp[i] = dp[i - 1] + costs[0];
            if (i >= 7) {
                dp[i] = Math.min(dp[i], dp[i - 7] + costs[1]);
            } else {
                dp[i] = Math.min(dp[i], costs[1]);
            }
            if (i >= 30) {
                dp[i] = Math.min(dp[i], dp[i - 30] + costs[2]);
            } else {
                dp[i] = Math.min(dp[i], costs[2]);
            }
        }
        return dp[dp.length - 1];
    }
}
