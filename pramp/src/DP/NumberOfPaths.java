package DP;

import java.util.Arrays;

/**
 * Number of Paths.
 *    | 0 1 2 3 4 (n = 5)
 *  --+-------------
 *  0 | 1 1 1 1 1
 *  1 | 0 1 2 3 4
 *  2 | 0 0 2 5 9
 *  3 | 0 0 0 5 14
 *  4 | 0 0 0 0 14 // dp[i][j] = dp[i - 1][j] + dp[i][j - 1]
 * can improve space complexity by using 1D array.
 * TC: O(n^2)
 * SC: O(n)
 * @author Jeesub Lee (jeesubl@andrew.cmu.edu)
 */
public class NumberOfPaths {
    static int numOfPathsToDest(int n) {
        int[] dp = new int[n];
        Arrays.fill(dp, 1);

        for (int i = 1; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                dp[j] = dp[j - 1] + dp[j];
            }
        }

        return dp[n - 1];
    }

    public static void main(String[] args) {
        System.out.println(numOfPathsToDest(5));
        // output: 14
    }
}
