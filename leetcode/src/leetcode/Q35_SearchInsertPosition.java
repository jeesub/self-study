package leetcode;

import java.util.Arrays;

/**
 * Q35. Search Insert Position.
 * Binary search
 * @author Jeesub Lee (jeesubl@andrew.cmu.edu)
 */
public class Q35_SearchInsertPosition {

    public static int searchInsert(int[] nums, int target) {
        int index = Arrays.binarySearch(nums, target);
        return index >= 0 ? index : Math.abs(index + 1);
    }

    public static void main(String[] args) {
        int[] nums = {1, 3, 5, 8};
        int target = 7;
        System.out.println(searchInsert(nums, target));
        // output: 3
    }

}
