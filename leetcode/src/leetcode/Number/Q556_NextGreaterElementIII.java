package leetcode.Number;

/**
 * 556. Next Greater Element III.
 * [Number]
 * 12 -> 21
 * 21 -> X
 * 1986 -> 6189
 *  ^ find peak value backward
 *    ^ find the first number bigger than nums[peak index - 1] backward
 * swap(nums[peak index - 1], big number in back) // 1986 -> 6981
 * reverse(peak index..last) // 6981 -> 6189
 * TC: O(n)
 * SC: O(n)
 * @author Jeesub Lee (jeesubl@andrew.cmu.edu)
 */
public class Q556_NextGreaterElementIII {
    public static int nextGreaterElement(int n) {
        char[] nums = Integer.toString(n).toCharArray();

        int peak = findPeakIndex(nums);
        if (peak == -1) {
            return -1;
        }

        placeNextBig(nums, peak - 1);
        reverse(nums, peak);

        long result = Long.parseLong(new String(nums));
        if (result > Integer.MAX_VALUE) {
            return -1;
        }
        return (int) result;
    }

    private static int findPeakIndex(char[] nums) {
        for (int i = nums.length - 1; i > 0; i--) {
            if (nums[i - 1] < nums[i]) {
                return i;
            }
        }
        return -1;
    }

    private static void placeNextBig(char[] nums, int numToSwap) {
        for (int i = nums.length - 1; i >= numToSwap; i--) {
            if (nums[i] > nums[numToSwap]) {
                swap(nums, i, numToSwap);
                return;
            }
        }
    }

    private static void reverse(char[] nums, int start) {
        for (int i = start, j = nums.length - 1; i < j; i++, j--) {
            swap(nums, i, j);
        }
    }

    private static void swap(char[] nums, int i, int j) {
        if (i == j) {
            return;
        }
        char tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}
