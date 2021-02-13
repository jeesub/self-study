package leetcode;

import java.util.Arrays;

/**
 * Find First and Last Position of Element in Sorted Array.
 * [BS]
 * Make two methods, find first and find last.
 * find first: binary search the first element
 * find last: binary search the last element
 * @author Jeesub Lee (jeesubl@andrew.cmu.edu)
 */
public class FindFirstAndLastPositionOfElementInSortedArray {

    public static int[] searchRange(int[] nums, int target) {
        if (nums.length == 0) {
            return new int[] { -1, -1 };
        }
        int[] result = new int[2];
        result[0] = findFirst(nums, target);
        result[1] = findLast(nums, target);
        return result;
    }

    private static int findFirst(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        int mid;
        while (left <= right) {
            mid = left + (right - left) / 2;
            if (nums[mid] >= target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return (left < nums.length && nums[left] == target) ? left : -1;
    }

    private static int findLast(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        int mid;
        while (left <= right) {
            mid = left + (right - left + 1) / 2;
            if (nums[mid] <= target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return (right >= 0 && nums[right] == target) ? right : -1;
    }

    public static void main(String[] args) {
        int[] nums = {1,2,3,4,5,5,5,5,6,6,7,7,8,9,10};
        int target = 5;
        System.out.print(Arrays.toString(searchRange(nums, target)));
        // output: [4, 7]
    }

}
