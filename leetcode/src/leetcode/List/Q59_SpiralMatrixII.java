package leetcode.List;

import java.util.Arrays;

/**
 * 59. Spiral Matrix II.
 * [List]
 * Use upperBound, lowerBound, leftBound, and rightBound.
 * Fill the array while bounds are not same.
 * TC: O(n^2)
 * SC: O(1)
 * @author Jeesub Lee (jeesubl@andrew.cmu.edu)
 */
public class Q59_SpiralMatrixII {
    public static int[][] generateMatrix(int n) {
        int[][] result = new int[n][n];
        int upper = 0;
        int left = 0;
        int lower = n - 1;
        int right = n - 1;
        int count = 1;

        while (upper <= lower && left <= right) {
            for (int j = left; j <= right; j++) {
                result[upper][j] = count++;
            }
            upper++;

            for (int i = upper; i <= lower; i++) {
                result[i][right] = count++;
            }
            right--;

            for (int j = right; j >= left; j--) {
                result[lower][j] = count++;
            }
            lower--;

            for (int i = lower; i >= upper; i--) {
                result[i][left] = count++;
            }
            left++;
        }

        return result;
    }

    public static void main(String[] args) {
        int[][] result = generateMatrix(4);
        for (int[] each : result) {
            System.out.println(Arrays.toString(each));
        }
        // output:
        // [1, 2, 3, 4]
        // [12, 13, 14, 5]
        // [11, 16, 15, 6]
        // [10, 9, 8, 7]
    }
}
