package leetcode.Matrix;

/**
 * 240. Search a 2D Matrix II.
 * [Matrix]
 * from the left bottom,
 * if curr == target, return true
 * if curr < target, j++
 * if curr > target, i--
 *
 * TC: O(m + n)
 * SC: O(1)
 * @author Jeesub Lee (jeesubl@andrew.cmu.edu)
 */
public class Q240_SearchA2DMatrixII {
    public static boolean searchMatrix(int[][] matrix, int target) {
        int i = matrix.length - 1;
        int j = 0;
        while (i >= 0 && j < matrix[0].length) {
            if (matrix[i][j] == target) {
                return true;
            } else if (matrix[i][j] < target) {
                j++;
            } else {
                i--;
            }
        }
        return false;
    }
}
