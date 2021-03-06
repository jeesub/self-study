package leetcode;

import java.util.Arrays;

/**
 * Q80. Remove Duplicates from Sorted Array II.
 * nums [1, 1, 1, 1, 2, 2, 3, 3, 4, 4, 4, 5]
 * Iterate through the array using two pointers.
 * If nums[i + 2] != nums[i], nums[i] should be in the array.
 *     nums[slow pointer++] = nums[i]
 * After iteration, add the last two elements.
 * @author Jeesub Lee (jeesubl@andrew.cmu.edu)
 */
public class Q80_RemoveDuplicatesFromSortedArrayII {

    public static int removeDuplicates(int[] nums) {
        int len = nums.length;
        if (len < 2) {
            return len;
        }
        
        int slow = 0;
        for (int fast = 0; fast < len -2; fast++) {
            if (nums[fast + 2] != nums[fast]) {
                nums[slow++] = nums[fast];
            }
        }
        nums[slow++] = nums[len - 2];
        nums[slow++] = nums[len - 1];
        return slow;
    }

    public static void main(String[] args) {
        int[] nums = {1, 1, 1, 1, 2, 2, 3, 3, 4, 4, 4, 5};
        System.out.println(removeDuplicates(nums));
        System.out.println(Arrays.toString(nums));
        // output: 9, [1, 1, 2, 2, 3, 3, 4, 4, 5, 4, 4, 5]
    }

}
