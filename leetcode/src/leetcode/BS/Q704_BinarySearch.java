package leetcode.BS;

/**
 * 704. Binary Search
 * [Binary Search]
 * mid = start + (end - start) / 2
 * if nums[mid] == target, found it
 * if nums[mid] < target, target is on the right side. left = mid + 1
 * if nums[mid] > target, target is on the left side. right = mid - 1
 * while left <= right
 * TC: O(logN)
 * SC: O(1)
 * @author Jeesub Lee (jeesubl@andrew.cmu.edu)
 */
public class Q704_BinarySearch {
    public static int search(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        int mid;
        while (left <= right) {
            mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                return mid;
            }
            if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] nums = {-10, -7, -5, 0, 3, 9, 12, 17, 21};
        System.out.println(search(nums, 17));
        // output: 7
    }
}
