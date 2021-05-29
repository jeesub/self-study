package leetcode.DP;

import java.util.Arrays;

/**
 * Q322. Coin Change.
 * [DP]
 * coins = [2, 5], amount = 11
 *
 *   | 0  1  2  3  4  5  6  7  8  9  10 11
 * --+-------------------------------------
 * 0 | 0  X  X  X  X  X  X  X  X  X  X  X
 * 2 | 0  X  1  X  2  X  3  X  4  X  5  X
 * 5 | 0  X  1  X  2  1  3  2  4  3  2  4
 *
 * if (j - curr coin) < 0 || dp[i][j - curr coin] == X
 *   dp[i][j] = dp[i - 1][j]
 * else
 *   dp[i][j] = min(dp[i - 1][j], dp[i][j - curr coin] + 1)
 *
 * We can use 1-d array to reduce the space complexity.
 * TC: O(coins * amount)
 * SC: O(amount)
 * @author Jeesub Lee (jeesubl@andrew.cmu.edu)
 */
public class Q322_CoinChange {

    public static int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        
        for (int i = 0; i < coins.length; i++) {
            for (int j = 0; j < dp.length; j++) {
                if (j - coins[i] < 0 || dp[j - coins[i]] == Integer.MAX_VALUE) {
                    continue;
                }
                dp[j] = Math.min(dp[j], dp[j - coins[i]] + 1);
            }
        }
        return dp[amount] == Integer.MAX_VALUE ? -1 : dp[amount];
    }

    public static void main(String[] args) {
        int[] coins = {2, 5};
        int amount = 11;
        System.out.println(coinChange(coins, amount));
        // output: 4
    }

}
