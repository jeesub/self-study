package leetcode.Windows;

import java.util.Arrays;

/**
 * Q283. Move Zeroes.
 * [Two pointers]
 * Iterate through the given array.
 * If curr is non-zero, copy it to the nums[non-zero index] and increase non-zero index by one.
 * Fill the array with an index equal to or higher than the non-zero index with zero.
 * TC: O(n), where n is the length of an input array.
 * SC: O(1).
 * @author Jeesub Lee (jeesubl@andrew.cmu.edu)
 */
public class Q283_MoveZeroes {

    public static void moveZeroes(int[] nums) {
        int pointer = 0;
        for (int num : nums) {
            if (num == 0) {
                continue;
            }
            nums[pointer++] = num;
        }
        Arrays.fill(nums, pointer, nums.length, 0);
    }

    public static void main(String[] args) {
        int[] nums = {0, 1, 0, 3, 12};
        moveZeroes(nums);
        System.out.println(Arrays.toString(nums));
        // output: [1, 3, 12, 0, 0]
    }

}
