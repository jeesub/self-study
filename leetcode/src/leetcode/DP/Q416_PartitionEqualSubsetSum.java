package leetcode.DP;

import java.util.Arrays;

/**
 * 416. Partition Equal Subset Sum.
 * [DP: Knapsack]
 * 1. if sum is odd, return false
 * 2. else, DP
 *
 * nums = [3, 2, 5, 4]
 * dp[i][j] = if we can make j using nums(0..i)
 * dp[i][j] = if dp[i - 1][j] = true ? true
 *            if (nums[i] <= j && nums[i - 1][j - nums[i]] == true) ? true
 *
 *   | 0 1 2 3 4 5 6 7
 * --+-----------------
 * X | T F F F F F F F
 * 3 | T F F T F F F F
 * 2 | T F T T F T F F
 * 5 | T F T T F T F T
 * 4 | T F T T T T T T
 *
 * TC: O(m * n), m = nums.length & n = sum / 2
 * SC: O(m * n) // can improve to O(n)
 * @author Jeesub Lee (jeesubl@andrew.cmu.edu)
 */
public class Q416_PartitionEqualSubsetSum {
    public boolean canPartition(int[] nums) {
        int sum = Arrays.stream(nums).sum();
        if (sum % 2 == 1) {
            return false;
        }

        boolean[][] dp = new boolean[nums.length + 1][sum / 2 + 1];
        dp[0][0] = true;
        for (int i = 1; i < dp.length; i++) {
            for (int j = 0; j < dp[0].length; j++) {
                if (dp[i - 1][j]) {
                    dp[i][j] = true;
                } else if (nums[i - 1] <= j) {
                    dp[i][j] = dp[i - 1][j - nums[i - 1]];
                }
            }
            if (dp[i][dp[0].length - 1]) {
                return true;
            }
        }
        return dp[dp.length - 1][dp[0].length - 1];
    }
}
