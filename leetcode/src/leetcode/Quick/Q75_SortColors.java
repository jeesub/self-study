package leetcode.Quick;

/**
 * 75. Sort Colors.
 * [Dual Pivot Sort]
 * while (mid <= rp)
 *   if nums[mid] == 0, swap(mid, lp), mid++, lp++
 *   if nums[mid] == 1, mid++
 *   if nums[mid] == 2, swap(mid, rp), rp--
 * TC: O(n)
 * SC: O(1)
 * @author Jeesub Lee (jeesubl@andrew.cmu.edu)
 */
public class Q75_SortColors {
    public static void sortColors(int[] nums) {
        int left = 0;
        int mid = 0;
        int right = nums.length - 1;
        while (mid <= right) {
            if (nums[mid] == 0) {
                swap(nums, left++, mid++);
            } else if (nums[mid] == 1) {
                mid++;
            } else {
                swap(nums, mid, right--);
            }
        }
    }

    private static void swap(int[] nums, int i, int j) {
        if (i == j) {
            return;
        }
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}
