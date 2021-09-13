package leetcode.Windows;
/**
 * Q718. Maximum Length of Repeated Subarray.
 * [Windows]
 * [1, 2, 3, 2, 1]
 *             [3, 2, 1, 4, 7]  // len = 0
 *
 * [1, 2, 3, 2, 1]
 *          [3, 2, 1, 4, 7]     // len = 0
 *
 * [1, 2, 3, 2, 1]
 *       [3, 2, 1, 4, 7]        // len = 3
 *
 * [1, 2, 3, 2, 1]
 *    [3, 2, 1, 4, 7]           // len = 0
 *
 * ...
 *
 *          [1, 2, 3, 2, 1]
 * [3, 2, 1, 4, 7]              // len = 0
 *
 *             [1, 2, 3, 2, 1]
 * [3, 2, 1, 4, 7]              // len = 0
 * TC: O(m * n), where m and n are the lengths of input nums1 and nums2
 * SC: O(1)
 *
 * [DP]
 * if nums1[i] == nums2[j], dp[i][j] = dp[i - 1] + dp[j - 1]
 * TC: O(m * n), where m and n are the lengths of input nums1 and nums2
 * SC: O(m * n), where m and n are the lengths of input nums1 and nums2
 * @author Jeesub Lee (jeesubl@andrew.cmu.edu)
 */
public class Q718_MaximumLengthOfRepeatedSubarray {

    public static int findLength(int[] nums1, int[] nums2) {
        if (nums1.length < nums2.length) {
            return findLength(nums2, nums1);
        }

        int max = 0;
        for (int gap = 0; gap < nums1.length; gap++) {
            int currMax = 0;
            for (int i = gap, j = 0; i < nums1.length && j < nums2.length; i++, j++) {
                if (nums1[i] != nums2[j]) {
                    currMax = 0;
                    continue;
                }
                currMax++;
                max = Math.max(max, currMax);
            }
        }
        for (int gap = 1; gap < nums2.length; gap++) {
            int currMax = 0;
            for (int i = 0, j = gap; i < nums1.length && j < nums2.length; i++, j++) {
                if (nums1[i] != nums2[j]) {
                    currMax = 0;
                    continue;
                }
                currMax++;
                max = Math.max(max, currMax);
            }
        }

        return max;
    }

    public static int findLengthDP(int[] nums1, int[] nums2) {
        int[][] dp = new int[nums1.length + 1][nums2.length + 1];
        int max = 0;

        for (int i = 0; i < nums1.length; i++) {
            for (int j = 0; j < nums2.length; j++) {
                if (nums1[i] != nums2[j]) {
                    continue;
                }
                dp[i + 1][j + 1] = dp[i][j] + 1;
                max = Math.max(max, dp[i + 1][j + 1]);
            }
        }

        return max;
    }

    public static void main(String[] args) {
        int[] nums1 = {1, 2, 3, 2, 1};
        int[] nums2 = {3, 2, 1, 1, 2};
        System.out.println(findLength(nums1, nums2));
        System.out.println(findLengthDP(nums1, nums2));
        // output: 3
    }

}
