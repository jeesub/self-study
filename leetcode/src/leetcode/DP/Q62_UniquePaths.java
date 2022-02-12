package leetcode.DP;

import java.util.Arrays;

/**
 * 62. Unique Paths.
 * [DP]
 * dp[i][0] = 1, dp[0][j] = 1
 * dp[i][j] = dp[i - 1][j] + dp[i][j - 1]
 *
 *   | 0  1  2  3  4  5  6
 * --+---------------------
 * 0 | 1  1  1  1  1  1  1
 * 1 | 1  2  3  4  5  6  7
 * 2 | 1  3  6 10 15 21 28
 *
 * TC: O(m * n)
 * SC: O(min(m, n))
 * @author Jeesub Lee (jeesubl@andrew.cmu.edu)
 */
public class Q62_UniquePaths {
    public static int uniquePaths(int m, int n) {
        if (n > m) {
            return uniquePaths(n, m);
        }

        int[] dp = new int[n];
        Arrays.fill(dp, 1);
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[j] += dp[j - 1];
            }
        }

        return dp[n - 1];
    }
}
