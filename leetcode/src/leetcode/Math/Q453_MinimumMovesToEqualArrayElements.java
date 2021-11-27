package leetcode.Math;

/**
 * Q453. Minimum Moves to Equal Array Elements.
 *  * [Math]
 *  * increase except for the max == decrease the max
 *  * decrease all numbers.
 *  *
 *  * TC: O(n), where n is the length of nums.
 *  * SC: O(1)
 * @author Jeesub Lee (jeesubl@andrew.cmu.edu)
 */
public class Q453_MinimumMovesToEqualArrayElements {
    public static int minMoves(int[] nums) {
        int min = Integer.MAX_VALUE;
        for (int num : nums) {
            min = Math.min(min, num);
        }

        int count = 0;
        for (int num :nums) {
            count += num - min;
        }

        return count;
    }

    public static void main(String[] args) {
        int[] nums = {8, 5, 6, 8, 5};
        System.out.println(minMoves(nums));
        // output: 7
    }
}
