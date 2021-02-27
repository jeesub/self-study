package leetcode;
/**
 * Q1335. Minimum Difficulty of a Job Schedule.
 * [DP]
 * dp[i][j]: the min of i works in j days (i >= j)
 * [11, 111] in 2 days -> dp[2][2] = 122
 * [11, 111, 22] in 2 days  -> dp[3][2]
 *     22 should be at the last part.
 *     The last part can be [111, 22] or [22] ([11, 111, 22] is not possible)
 *     a - [11] & [111, 22] -> dp[1][1] + Max of [111, 22]
 *     b - [11, 111] & [22] -> dp[2][1] + Max of [22]
 *     dp[i][j] = min of (a, b)
 * @author Jeesub Lee (jeesubl@andrew.cmu.edu)
 */
public class Q1335_MinimumDifficultyOfAJobSchedule {

    public static int minDifficulty(int[] jobDifficulty, int d) {
        if (d > jobDifficulty.length) {
            return -1;
        }

        int[][] dp = new int[jobDifficulty.length][d];
        dp[0][0] = jobDifficulty[0];
        for (int i = 1; i < jobDifficulty.length; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], jobDifficulty[i]);
        }

        for (int i = 1; i < jobDifficulty.length; i++) {
            for (int j = 1; j <= Math.min(i, d - 1); j++) {
                dp[i][j] = Integer.MAX_VALUE;
                for (int k = j; k <= i; k++) {
                    dp[i][j] = Math.min(dp[i][j], dp[k - 1][j - 1] + getMax(jobDifficulty, k, i));
                }
            }
        }

        return dp[jobDifficulty.length - 1][d - 1];
    }

    private static int getMax(int[] jobDifficulty, int k, int j) {
        int max = 0;
        for (int i = k; i <= j; i++) {
            max = Math.max(max, jobDifficulty[i]);
        }
        return max;
    }

    public static void main(String[] args) {
        int[] jobDifficulty = {11,111,22,222,33,333,44,444};
        int d = 6;
        System.out.println(minDifficulty(jobDifficulty, d));
        // output: 843
    }

}
