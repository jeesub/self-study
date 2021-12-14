package leetcode.BS;

/**
 * 162. Find Peak Element.
 * [Binary Search]
 * if right side can have a peak number(nums[mid] < nums[mid + 1]),
 * go to the right side.
 * @author Jeesub Lee (jeesubl@andrew.cmu.edu)
 */
public class Q162_FindPeakElement {
    public static int findPeakElement(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] < nums[mid + 1]) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4, 5, 3};
        System.out.println(findPeakElement(nums));
        // output: 4
    }
}
