package leetcode.BS;

/**
 * 1060. Missing Element in Sorted Array.
 * [Binary Search]
 * if (nums[j] - nums[i] == j - i), no missing numbers
 * # of missing numbers = nums[j] - nums[i] - j + i
 *
 * find the left most index where missing # <= k
 *
 * if (current # of missing number < k), left = mid - 1
 * if (k <= current # of missing number), right = mid - 1
 *
 * TC: O(logN)
 * SC: O(1)
 * @author Jeesub Lee (jeesubl@andrew.cmu.edu)
 */
public class Q1060_MissingElementInSortedArray {
    public static int missingElement(int[] nums, int k) {
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            int missing = nums[mid] - nums[0] - mid;
            if (missing < k) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        int leftMissing = nums[right] - nums[0] - right;
        return nums[right] + k - leftMissing;
    }
}
