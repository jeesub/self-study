package leetcode.Windows;

/**
 * 977. Squares of a Sorted Array.
 * [Windows]
 * nums = [-4,-1,0,3,10]
 *         ^ i        ^ j
 * squares[k--] = bigger(sq(i), sq(j))
 * TC: O(n)
 * SC: O(1)
 * @author Jeesub Lee (jeesubl@andrew.cmu.edu)
 */
public class Q977_SquaresOfASortedArray {
    public static int[] sortedSquares(int[] nums) {
        int[] squares = new int[nums.length];
        int i = 0;
        int j = nums.length - 1;
        for (int k = squares.length - 1; k >= 0; k--) {
            if (Math.abs(nums[i]) < Math.abs(nums[j])) {
                squares[k] = nums[j] * nums[j];
                j--;
            } else {
                squares[k] = nums[i] * nums[i];;
                i++;
            }
        }
        return squares;
    }
}
