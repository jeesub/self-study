package leetcode.BS;

/**
 * 1539. Kth Missing Positive Number.
 * [Binary Search]
 *  0  1  2  3  4
 * [2, 3, 4, 7, 11]
 *  ^ missing 1 // val - index - 1
 *     ^ missing 1
 *        ^ missing 1
 *           ^ missing 3 // val - index - 1 = 7 - 3 - 1 = 3
 *              ^ missing 6 // val - index - 1 = 11 - 4 - 1 = 6
 * k = 5 is between index 3 and index 4
 *
 * while left <= right,
 *     if missing(mid) < k,
 *         left = mid + 1
 *     else,
 *         right = mid - 1
 * After BS, the right pointer will be at the index we are looking for.
 * kthPositive = arr[right] - missing(right) + k
 *             = arr[right] - (arr[right] - right - 1) + k
 *             = right + k + 1
 * TC: O(logN)
 * SC: O(1)
 * @author Jeesub Lee (jeesubl@andrew.cmu.edu)
 */
public class Q1539_KthMissingPositiveNumber {
    public static int findKthPositive(int[] arr, int k) {
        int left = 0;
        int right = arr.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (arr[mid] - mid - 1 < k) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return right + k + 1;
    }

    public static void main(String[] args) {
        int[] arr = {2, 3, 4, 7, 11};
        int k = 5;
        System.out.println(findKthPositive(arr, k));
        // output: 9
    }
}
