package leetcode.List;

import java.util.Arrays;

/**
 * Q1480. Running Sum of 1d Array.
 * Iterate through the nums.
 * runningSum[i] = runningSum[i - 1] + nums[i]
 * TC: O(n), where n is the length of nums
 * SC: O(1)
 * @author Jeesub Lee (jeesubl@andrew.cmu.edu)
 */
public class Q1480_RunningSumOf1dArray {

    public static int[] runningSum(int[] nums) {
        int[] result = new int[nums.length];
        result[0] = nums[0];

        for (int i = 1; i < nums.length; i++) {
            result[i] = result[i - 1] + nums[i];
        }

        return result;
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        System.out.println(Arrays.toString(runningSum(nums)));
        // output: [1, 3, 6, 10, 15, 21, 28, 36, 45, 55]
    }

}
