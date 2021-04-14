package leetcode;
/**
 * Q1567. Maximum Length of Subarray With Positive Product.
 * [DP]
 * pos[i] = max length of positive sequence include nums[i]
 * neg[i] = max length of negative sequence include nums[i]
 * case 1. nums[i] == 0 -> pos[i] = 0, neg[i] = 0
 * case 2. nums[i] > 0 -> pos[i] = pos[i - 1] + 1
 *                        neg[i - 1] > 0 ? neg[i] = neg[i - 1] + 1 : 0
 * case 3. nums[i] < 0 -> neg[i] = pos[i - 1] + 1
 *                        neg[i - 1] > 0 ? pos[i] = neg[i - 1] + 1 : 0
 * @author Jeesub Lee (jeesubl@andrew.cmu.edu)
 */
public class Q1567_MaximumLengthOfSubarrayWithPositiveProduct {

    public static int getMaxLen(int[] nums) {
        int[] pos = new int[nums.length];
        int[] neg = new int[nums.length];
        int max = 0;
        if (nums[0] > 0) {
            pos[0] = 1;
            max = 1;
        }
        if (nums[0] < 0) {
            neg[0] = 1;
        }
        
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == 0) {
                pos[i] = 0;
                neg[i] = 0;
                continue;
            }
            if (nums[i] > 0) {
                pos[i] = pos[i - 1] + 1;
                neg[i] = neg[i - 1] > 0 ? neg[i - 1] + 1 : 0;
            }
            if (nums[i] < 0) {
                neg[i] = pos[i - 1] + 1;
                pos[i] = neg[i - 1] > 0 ? neg[i - 1] + 1 : 0;
            }
            max = Math.max(max, pos[i]);
        }
        return max;
    }

    public static void main(String[] args) {
        int[] nums = {1, -2, -3, 4, -5, 6, -7, -8};
        System.out.println(getMaxLen(nums));
        // output: 7
    }

}
