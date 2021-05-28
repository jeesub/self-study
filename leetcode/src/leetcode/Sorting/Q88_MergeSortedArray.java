package leetcode.Sorting;

import java.util.Arrays;

/**
 * Q88. Merge Sorted Array.
 * [Merge Sort]
 * Merge backwards.
 * nums1 = [1,2,0,0,0]
 *            ^     ^
 *     pointer1     merge pointer
 * nums2 = [2,5,6]
 *              ^
 *              pointer2
 * TC: O(m + n).
 * SC: O(1).
 * @author Jeesub Lee (jeesubl@andrew.cmu.edu)
 */
public class Q88_MergeSortedArray {

    public static void merge(int[] nums1, int m, int[] nums2, int n) {
        if (n == 0) {
            return;
        }
        if (m == 0) {
            System.arraycopy(nums2, 0, nums1, 0, n);
            return;
        }

        int pointer1 = m - 1;
        int pointer2 = n - 1;
        int pointer = nums1.length - 1;
        while (pointer1 >= 0 && pointer2 >= 0) {
            if (nums1[pointer1] >= nums2[pointer2]) {
                nums1[pointer--] = nums1[pointer1--];
            } else {
                nums1[pointer--] = nums2[pointer2--];
            }
        }

        if (pointer2 >= 0) {
            System.arraycopy(nums2, 0, nums1, 0, pointer2 + 1);
        }
    }

    public static void main(String[] args) {
        int[] nums1 = {1, 2, 0, 0, 0};
        int m = 2;
        int[] nums2 = {2, 5, 6};
        int n = 3;
        merge(nums1, m, nums2, n);
        System.out.println(Arrays.toString(nums1));
        // output: [1, 2, 2, 5, 6]
    }

}
