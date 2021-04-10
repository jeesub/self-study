package leetcode;
/**
 * Q152. Maximum Product Subarray.
 * [DP]
 * Use two dp tables, maxDp and minDp.
 * maxDp keeps max at the point.
 * minDp keeps min at the point.
 * maxDp[i] = max of (maxDp[i - 1] * nums[i], minDp[i - 1] * nums[i], nums[i])
 * minDp[i] = min of (maxDp[i - 1] * nums[i], minDp[i - 1] * nums[i], nums[i])
 * @author Jeesub Lee (jeesubl@andrew.cmu.edu)
 */
public class Q152_MaximumProductSubarray {

    public static int maxProduct(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        int[] maxDp = new int[nums.length];
        int[] minDp = new int[nums.length];
        maxDp[0] = nums[0];
        minDp[0] = nums[0];
        int max = nums[0];
        for (int i = 1; i < nums.length; i++) {
            maxDp[i] = Math.max(nums[i], Math.max(maxDp[i - 1] * nums[i], minDp[i - 1] * nums[i]));
            minDp[i] = Math.min(nums[i], Math.min(maxDp[i - 1] * nums[i], minDp[i - 1] * nums[i]));
            max = Math.max(max, maxDp[i]);
        }
        return max;
    }

    public static void main(String[] args) {
        int[] nums = {2, 3, -2, 4, -1};
        System.out.println(maxProduct(nums));
        // output: 48
    }

}
