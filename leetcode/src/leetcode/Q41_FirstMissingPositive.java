package leetcode;
/**
 * Q41. First Missing Positive.
 * 1. Iterate through the array.
 *    If a number is not between 1 to nums length, change the number to length + 1.
 * 2. Iterate through the array.
 *    If a number is between 1 to nums length, change the number's index's number to negative.
 * 3. Iterate through the array.
 *    If a number is positive, it means we were not able to find the index's number.
 *    Return index + 1.
 * Else, return length + 1.
 * 
 * ex)
 *    [3, 4, -1, 1]
 * -> [3, 4, 5, 1]
 * -> [-3, 4, -5, -1]
 * -> return 2
 * @author Jeesub Lee (jeesubl@andrew.cmu.edu)
 */
public class Q41_FirstMissingPositive {

    public static int firstMissingPositive(int[] nums) {
        int len = nums.length;

        for (int i = 0; i < len; i++) {
            if (!isInBound(len, nums[i])) {
                nums[i] = len + 1;
            }
        }

        for (int i = 0; i < len; i++) {
            int num = Math.abs(nums[i]);
            if (isInBound(len, num)) {
                nums[num - 1] = -1 * Math.abs(nums[num - 1]);
            }
        }

        for (int i = 0; i < len; i++) {
            if (nums[i] > 0) {
                return i + 1;
            }
        }

        return len + 1;
    }

    private static boolean isInBound(int len, int num) {
        return num > 0 && num <= len;
    }

    public static void main(String[] args) {
        int[] nums = {3, 4, -1, 1};
        System.out.println(firstMissingPositive(nums));
        // output: 2
    }

}
