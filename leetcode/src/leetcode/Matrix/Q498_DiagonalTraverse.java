package leetcode.Matrix;

import java.util.Arrays;

/**
 * 498. Diagonal Traverse.
 * [Matrix]
 * 1. i + j = 0 // i--, j++
 * 2. i + j = 1 // i++, j--
 * 3. i + j = 2 // i--, j++
 * ...
 * m * n. i + j = m * n - 1
 *
 * int sum = 0..(m * n - 1)
 *     if sum is even,
 *         if sum < m, initial i = sum, initial j = 0
 *         else, initial i = m - 1, initial j = sum - m + 1
 *         result[index++] = mat[i--][j++]
 *     if sum is odd,
 *         if sum < n, initial i = 0, initial j = sum
 *         else, initial j = n - 1, initial i = sum - n + 1
 *         result[index++] = mat[i++][j--]
 * TC: O(m * n)
 * SC: O(1)
 * @author Jeesub Lee (jeesubl@andrew.cmu.edu)
 */
public class Q498_DiagonalTraverse {
    public static int[] findDiagonalOrder(int[][] mat) {
        int m = mat.length;
        int n = mat[0].length;
        int[] result = new int[m * n];
        int index = 0;
        int i;
        int j;
        for (int sum = 0; sum < m * n; sum++) {
            if ((sum & 1) == 0) {
                if (sum < m) {
                    i = sum;
                    j = 0;
                } else {
                    i = m - 1;
                    j = sum - m + 1;
                }
                while (i >= 0 && j < n) {
                    result[index++] = mat[i--][j++];
                }
            } else {
                if (sum < n) {
                    i = 0;
                    j = sum;
                } else {
                    i = sum - n + 1;
                    j = n - 1;
                }
                while (i < m && j >= 0) {
                    result[index++] = mat[i++][j--];
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[][] mat = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}};
        System.out.println(Arrays.toString(findDiagonalOrder(mat)));
        // output: [1, 2, 5, 9, 6, 3, 4, 7, 10, 11, 8, 12]
    }
}
