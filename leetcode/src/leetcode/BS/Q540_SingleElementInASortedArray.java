package leetcode.BS;

/**
 * 540. Single Element in a Sorted Array.
 * [Binary Search]
 * [1, 1, 2, 2, 3]
 * [1, 1, 2, 3, 3]
 * [1, 2, 2, 3, 3]
 * keep mid even.
 * if (nums[mid] != nums[mid + 1]),
 *   the single element is on the left side (including mid)
 * if (nums[mid] == nums[mid + 1]),
 *   the single element is on the right side (excluding mid + 1)
 * TC: O(logN)
 * SC: O(1)
 * @author Jeesub Lee (jeesubl@andrew.cmu.edu)
 */
public class Q540_SingleElementInASortedArray {
    public static int singleNonDuplicate(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        int mid;
        while (left < right) {
            mid = left + (right - left) / 2;
            mid = mid % 2 == 0 ? mid : mid - 1;
            if (nums[mid] != nums[mid + 1]) {
                right = mid;
            } else {
                left = mid + 2;
            }
        }
        return nums[left];
    }

    public static void main(String[] args) {
        int[] nums = {1, 1, 2, 2, 3, 3, 4, 4, 5, 6, 6};
        System.out.println(singleNonDuplicate(nums));
        // output: 5
    }
}
