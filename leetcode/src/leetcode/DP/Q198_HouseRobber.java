package leetcode.DP;

/**
 * 198. House Robber.
 * [DP]
 * At the current point, the best amount is dp[i - 2] + nums[i] or dp[i - 1]
 * dp[0] = nums[0]
 * dp[1] = max(nums[0], nums[1])
 * dp[i] = max(dp[i - 2] + nums[i], dp[i - 1]), where i >= 2
 * TC: O(n)
 * SC: O(n)
 *
 * [DP, Optimized Space]
 * At the point,
 * currMax = max(prevPrevMax + nums[i], prevMax)
 * prevPrevMax = prevMax
 * pervMax = currMax
 * TC: O(n)
 * SC: O(1)
 * @author Jeesub Lee (jeesubl@andrew.cmu.edu)
 */
public class Q198_HouseRobber {
    public static int rob(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);
        for (int i = 2; i < dp.length; i++) {
            dp[i] = Math.max(dp[i - 2] + nums[i], dp[i - 1]);
        }
        return dp[dp.length - 1];
    }

    public static int robOptimized(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }
        int prevPrevMax = nums[0];
        int prevMax = Math.max(nums[0], nums[1]);
        int currMax = prevMax;
        for (int i = 2; i < nums.length; i++) {
            currMax = Math.max(prevPrevMax + nums[i], prevMax);
            prevPrevMax = prevMax;
            prevMax = currMax;
        }
        return currMax;
    }

    public static void main(String[] args) {
        int[] nums = {2, 1, 1, 2};
        System.out.println(rob(nums));
        System.out.println(robOptimized(nums));
        // output: 4
    }
}
