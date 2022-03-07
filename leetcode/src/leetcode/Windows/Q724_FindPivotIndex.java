package leetcode.Windows;

/**
 * 724. Find Pivot Index.
 * [Windows]
 * nums = [1, 7, 3, 6, 5, 6]
 * accF = [1, 8, 11, 17, 22, 28]
 *                   ^
 * accB = [28, 27, 20, 17, 11, 6]
 *                     ^
 *
 * nums = [1, 7, 3, 6, 5, 6], sum = 28 // set left = 0, right = sum - nums[i]
 *         ^ special case: left = 0, right = sum - nums[i] = 27
 *            ^ left += nums[i - 1] = 1, right -= nums[i] = 20
 *               ^ left = 8, right = 17
 *                  ^ left = 11, right = 11
 * TC: O(n)
 * SC: O(1)
 * @author Jeesub Lee (jeesubl@andrew.cmu.edu)
 */
public class Q724_FindPivotIndex {
    public static int pivotIndex(int[] nums) {
        int left = 0;
        int right = 0;
        for (int i = 1; i < nums.length; i++) {
            right += nums[i];
        }

        if (left == right) {
            return 0;
        }

        for (int i = 1; i < nums.length; i++) {
            left += nums[i - 1];
            right -= nums[i];
            if (left == right) {
                return i;
            }
        }
        return -1;
    }
}
