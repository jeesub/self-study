package list;
/**
 * Max Contiguous Subarray Sum.
 * Input: [-2, 1, -3, 4, -1, 2, 1, -5, 4]
 * Output: 6
 * [DP]
 * dp[i] = max at the point i
 *       = max of (dp[i - 1] + nums[i], nums[i])
 * int max = max so far
 *         = max of (max, dp[i])
 * @author Jeesub Lee (jeesubl@andrew.cmu.edu)
 */
public class MaxContiguousSubarraySum {

    public static int getMaxSum(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        int max = Integer.MIN_VALUE;
        for (int i = 1; i < nums.length; i++) {
            dp[i] = Math.max(dp[i - 1] + nums[i], nums[i]);
            max = Math.max(max, dp[i]);
        }
        return max;
    }

    public static void main(String[] args) {
        int[] nums = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        System.out.println(getMaxSum(nums));
        // output: 6
    }

}
