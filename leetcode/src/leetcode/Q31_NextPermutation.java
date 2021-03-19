package leetcode;

import java.util.Arrays;

/**
 * Q31. Next Permutation.
 * 1 2 5 3 1 -> 1 3 1 2 5
 * 531 is descending and 25 is ascending.
 * We need to change 2 to the next large number.
 * Next should be ascending order.
 * If a number is descending, reverse it.
 * @author Jeesub Lee (jeesubl@andrew.cmu.edu)
 */
public class Q31_NextPermutation {

    public static void nextPermutation(int[] nums) {
        int newPosition = getNewPosition(nums);
        if (newPosition == -1) {
            reverse(nums, 0, nums.length - 1);
            return;
        }

        int swapPosition = getSwapPosition(nums, newPosition);
        swap(nums, newPosition, swapPosition);
        reverse(nums, newPosition + 1, nums.length - 1);
    }

    private static int getNewPosition(int[] nums) {
        int newPosition = -1;
        for (int i = nums.length - 2; i >= 0; i--) {
            if (nums[i] < nums[i + 1]) {
                newPosition = i;
                break;
            }
        }
        return newPosition;
    }

    private static int getSwapPosition(int[] nums, int newPosition) {
        int val = nums[newPosition];
        int swapPosition = newPosition + 1;
        for (int i = newPosition + 1; i < nums.length; i++) {
            if (nums[i] > val && nums[i] <= nums[swapPosition]) {
                swapPosition = i;
            }
        }
        return swapPosition;
    }

    private static void reverse(int[] nums, int start, int end) {
        while (start < end) {
            swap(nums, start++, end--);
        }
    }

    private static void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    public static void main(String[] args) {
        int[] nums = {1, 4, 3, 2};
        nextPermutation(nums);
        System.out.print(Arrays.toString(nums));
        // output: [2, 1, 3, 4]
    }

}
