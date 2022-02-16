package leetcode.DP;

/**
 * 279. Perfect Squares.
 * [DP]
 * dp[n] = min(dp[k] + dp[n - k])
 * we need to look up where k is a perfect square
 * dp[0] = 1
 * dp[1] = dp[1] + dp[0] = 0 + 1 = 1
 * dp[2] = dp[1] + dp[1] = 1 + 1 = 2
 * dp[3] = dp[1] + dp[2] = 1 + 2 = 3
 * dp[4] = dp[4] + dp[0] = 0 + 1 = 1
 * dp[5] = dp[1] + dp[4] = 1 + 1 = 2
 * dp[6] = dp[1] + dp[5] = 1 + 2 = 3
 * TC: O(nlog(sqrt(n)))
 * SC: O(n)
 * @author Jeesub Lee (jeesubl@andrew.cmu.edu)
 */
public class Q279_PerfectSquares {
    public static int numSquares(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 1;
        for (int i = 1; i < dp.length; i++) {
            int min = Integer.MAX_VALUE;
            for (int j = 1; j * j <= i; j++) {
                int k = j * j;
                min = Math.min(min, dp[k] + dp[i - k]);
            }
            dp[i] = min;
        }
        return dp[n];
    }
}
