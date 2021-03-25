package dp;

import java.util.Arrays;

/**
 * Count of Subset Sum.
 * [DP]
 * Make an int DP table that stores.
 * DP[i, j] means the possible ways to make sum j with nums(0..i).
 * Return the count of the last column's true.
 *    | 0 1 2 3 4
 * ---+-----------
 *  0 | 1 0 0 0 0
 *  1 | 1 1 0 0 0
 *  1 | 1 2 1 0 0
 *  2 | 1 2 2 2 1
 *  3 | 1 2 2 3 3 (dp[4,4] + dp[3,1])
 * @author Jeesub Lee (jeesubl@andrew.cmu.edu)
 */
public class CountOfSubsetSum {

    public static int countSum(int[] nums, int sum) {
        int[][] dp = new int[nums.length + 1][sum + 1];
        for (int i = 0; i < dp.length; i++) {
            dp[i][0] = 1;
        }

        for (int i = 1; i <= nums.length; i++) {
            for (int j = 1; j <= sum; j++) {
                if (nums[i - 1] > j) {
                    dp[i][j] = dp[i - 1][j];
                    continue;
                }
                dp[i][j] = dp[i - 1][j] + dp[i - 1][j - nums[i - 1]];
            }
        }
        for (int i = 0; i < dp.length; i++) {
            System.out.println(Arrays.toString(dp[i]));
        }
        return dp[nums.length][sum];
    }

    public static void main(String[] args) {
        int[] nums = {1, 1, 2, 3};
        int sum = 4;
        System.out.println(countSum(nums, sum));
    }

}
