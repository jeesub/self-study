package leetcode;
/**
 * Search in Rotated Sorted Array.
 * [BS]
 * Use left, mid, and right pointers.
 * if mid == target, return mid
 * if (left <= mid), left part is sorted
 *     if (left <= target && target < mid), target might be in a left side
 *     else, target might be in a right side
 * else, right part is sorted
 *     if (mid < target && target <= right), target might be in a right side
 *     else, target might be in a left side
 * @author Jeesub Lee (jeesubl@andrew.cmu.edu)
 */
public class SearchInRotatedSortedArray {

    public static int search(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        int mid;
        while (left <= right) {
            mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                return mid;
            }
            if (nums[left] <= nums[mid]) {
                if (nums[left] <= target && target < nums[mid]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            } else {
                if (nums[mid] < target && target <= nums[right]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] nums = {4,5,6,7,8,0,1,2};
        int target = 6;
        System.out.print(search(nums, target));
        // output: 2
    }

}
