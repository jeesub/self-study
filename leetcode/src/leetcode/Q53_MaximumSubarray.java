package leetcode;
/**
 * Q53. Maximum Subarray.
 * A pointer iterates through the array and calculate sum.
 * If the previous sum was negative, it is better to restart.
 * If the previous sum was positive, it is better to accumulate.
 * @author Jeesub Lee (jeesubl@andrew.cmu.edu)
 */
public class Q53_MaximumSubarray {

    public static int maxSubArray(int[] nums) {
        int max = Integer.MIN_VALUE;
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            if (sum < 0) {
                sum = 0;
            }
            sum += nums[i];
            max = Math.max(max, sum);
        }
        return max;
    }

    public static void main(String[] args) {
        int[] nums = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        System.out.println(maxSubArray(nums));
        // output: 6
    }

}
