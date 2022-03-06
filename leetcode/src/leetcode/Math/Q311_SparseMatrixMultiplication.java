package leetcode.Math;

/**
 * 311. Sparse Matrix Multiplication.
 * [Math]
 * result[mat1.length][mat2[0].length]
 * result[i][j] = sum(mat1[i][k] * mat2[k][j])
 * i = (0..mat1.length - 1)
 * j = (0..mat2[0].length - 1)
 * k = (0..mat1[0].length - 1)
 *
 * if mat1[i][k] != 0,
 *     it can contribute to result[i][j] by result[i][j] += mat1[i][k] * mat2[k][j]
 * TC: O(l * m * n)
 * SC: O(1)
 * @author Jeesub Lee (jeesubl@andrew.cmu.edu)
 */
public class Q311_SparseMatrixMultiplication {
    public static int[][] multiply(int[][] mat1, int[][] mat2) {
        int[][] result = new int[mat1.length][mat2[0].length];
        for (int i = 0; i < mat1.length; i++) {
            for (int k = 0; k < mat1[0].length; k++) {
                if (mat1[i][k] == 0) {
                    continue;
                }
                for (int j = 0; j < mat2[0].length; j++) {
                    result[i][j] += mat1[i][k] * mat2[k][j];
                }
            }
        }
        return result;
    }
}
