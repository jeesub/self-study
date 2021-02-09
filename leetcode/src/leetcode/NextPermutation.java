package leetcode;

import java.util.Arrays;

/**
 * Next Permutation.
 * 1234 -> 1243
 * 1342 -> 1423
 * 1432 -> 2134
 * 
 * Look backwards.
 * When we find nums[i - 1] < nums[i], we can swap nums[i - 1] with the next number.
 * Beyond i - 1, it should be ascending order.
 * Reverse (i - 1 .. end)
 * If we fail to find the a right point, reverse the nums.
 * @author Jeesub Lee (jeesubl@andrew.cmu.edu)
 */
public class NextPermutation {

    public static void nextPermutation(int[] nums) {
        int pointer = nums.length - 2;
        while (pointer >= 0) {
            if (nums[pointer] < nums[pointer + 1]) {
                break;
            }
            pointer--;
        }
        if (pointer == -1) {
            reverse(nums, 0);
            return;
        }
        int nextPointer = nums.length - 1;
        while (nums[nextPointer] <= nums[pointer]) {
            nextPointer--;
        }
        swap(nums, pointer, nextPointer);
        reverse(nums, pointer + 1);
    }

    private static void reverse(int[] nums, int i) {
        int j = nums.length - 1;
        while (i < j) {
            swap(nums, i, j);
            i++;
            j--;
        }
        return;
    }

    private static void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
        return;
    }

    public static void main(String[] args) {
        int[] nums = {1, 4, 3, 2};
        nextPermutation(nums);
        System.out.print(Arrays.toString(nums));
        // output: [2, 1, 3, 4]
    }

}
