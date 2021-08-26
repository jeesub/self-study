package leetcode.Math;
/**
 * Q1822. Sign of the Product of an Array.
 * Iterate through the nums.
 * if curr num == 0, return 0
 * if curr num > 0, continue
 * if curr num < 0, record negative
 * TC: O(n), where n is the length of nums
 * SC: O(1)
 * @author Jeesub Lee (jeesubl@andrew.cmu.edu)
 */
public class Q1822_SignOfTheProductOfAnArray {

    public static int arraySign(int[] nums) {
        boolean isPositive = true;

        for (int num : nums) {
            if (num == 0) {
                return 0;
            }

            if (num > 0) {
                continue;
            }

            isPositive = !isPositive;
        }

        return isPositive ? 1 : -1;
    }

    public static void main(String[] args) {
        int[] nums = {-4, -3, -2, -1, 1, 2, 3, 4, 5, 6, 7};
        System.out.println(arraySign(nums));
        // output: 1
    }

}
