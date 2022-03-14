package leetcode.DP;

/**
 * 312. Burst Balloons.
 * [DP: BTR & Memoization]
 * nums = [3, 1, 5, 8]
 * max = max(burst(3), burst(1), burst(5), burst(8))
 * burstFirst(3) = 1 * 3 * 1 + burst([1, 5, 8])
 * burstFirst(1) = 3 * 1 * 5 + burst([3, 5, 8])
 * burstFirst(5) = 1 * 5 * 8 + burst([3, 1, 8])
 * burstFirst(8) = 5 * 8 * 1 + burst([3, 1, 5])
 * record that the result of recursive function([3, 1, 5, 8]) = max
 *
 * TC: O(n * 2^n)
 * SC: O(n * 2^n)
 *
 * for nums[0, 1, 2, 3, 4, 5],
 * burst([1, 2, 3, 4, 5]) and burst([0, 2, 3, 4, 5]) calculates [3, 4, 5] part again
 * -> not optimal
 *
 * [DP: Backward]
 * Bursting a balloon changes the array -> cannot reuse the calculation.
 * Look backward. Burst a balloon k last.
 * nums = [3, 1, 5, 8]
 * dp[i][j] = max result of burstFirst(i..j)
 * max candidates
 * 1. burstLast(3) + burstFirst([1, 5, 8])
 * 2. burstFirst([3]) + burstLast(1) + burstFirst([5, 8])
 * 3. burstFirst([3, 1]) + burstLast(5) + burstFirst([8])
 * 4. burstFirst([3, 1, 5]) + burstLast(8)
 *
 * burstLast(k) = nums[left boundary - 1] * nums[k] * nums[right boundary + 1]
 * because k is the last balloon
 * burstFirst([3, 1, 5]) can reuse burstFirst([3, 1])
 * because 5 is alive when we calculate burstFirst([3, 1])
 *
 * TC: O(n^3)
 * SC: O(n^2)
 * @author Jeesub Lee (jeesubl@andrew.cmu.edu)
 */
public class Q312_BurstBalloons {
    public int maxCoins(int[] nums) {
        int[] balloons = new int[nums.length + 2];
        balloons[0] = 1;
        balloons[balloons.length - 1] = 1;
        System.arraycopy(nums, 0, balloons, 1, nums.length);

        int[][] dp = new int[balloons.length][balloons.length];
        return burstBalloons(dp, balloons, 1, balloons.length - 2);
    }

    private int burstBalloons(int[][] dp, int[] balloons, int left, int right) {
        if (left > right) {
            return 0;
        }
        if (dp[left][right] > 0) {
            return dp[left][right];
        }

        int max = 0;
        for (int i = left; i <= right; i++) {
            int curr = balloons[left - 1] * balloons[i] * balloons[right + 1]
                    + burstBalloons(dp, balloons, left, i - 1)
                    + burstBalloons(dp, balloons, i + 1, right);
            max = Math.max(max, curr);
        }
        return dp[left][right] = max;
    }
}
