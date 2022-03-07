package leetcode.Matrix;

/**
 * 304. Range Sum Query 2D - Immutable.
 * [Matrix]
 * Store acc[i][j]
 * acc[i][j] = matrix[i][j] + acc[i - 1][j] + acc[i][j - 1] - acc[i - 1][j - 1]
 * sumRegion(row1, co1, row2, col2)
 * = acc[row2][col2] - acc[row2][col1 - 1] - acc[row1 - 1][col2] + acc[row1 - 1][col1 - 1]
 *
 * Class
 * SC: O(m * n)
 *
 * Constructor
 * TC: O(m * n)
 * SC: O(1)
 *
 * sumRegion
 * TC: O(1)
 * SC: O(1)
 * @author Jeesub Lee (jeesubl@andrew.cmu.edu)
 */
public class Q304_RangeSumQuery2DImmutable {
    class NumMatrix {
        private final int[][] acc;

        public NumMatrix(int[][] matrix) {
            acc = new int[matrix.length + 1][matrix[0].length + 1];
            for (int i = 1; i < acc.length; i++) {
                for (int j = 1; j < acc[0].length; j++) {
                    acc[i][j] = matrix[i - 1][j - 1] + acc[i - 1][j] + acc[i][j - 1] - acc[i - 1][j - 1];
                }
            }
        }

        public int sumRegion(int row1, int col1, int row2, int col2) {
            return acc[row2 + 1][col2 + 1]
                    - acc[row2 + 1][col1]
                    - acc[row1][col2 + 1]
                    + acc[row1][col1];
        }
    }
}
