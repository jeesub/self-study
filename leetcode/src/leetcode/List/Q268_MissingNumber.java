package leetcode.List;

import java.util.Arrays;

/**
 * 268. Missing Number.
 * [List]
 * sum(0..n) - sum(nums) = missing number
 * TC: O(n)
 * SC: O(1)
 * @author Jeesub Lee (jeesubl@andrew.cmu.edu)
 */
public class Q268_MissingNumber {
    public static int missingNumber(int[] nums) {
        int goal = nums.length * (nums.length + 1) / 2;
        int sum = Arrays.stream(nums).sum();
        return goal - sum;
    }
}
