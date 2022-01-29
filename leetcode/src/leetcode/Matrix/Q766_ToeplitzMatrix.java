package leetcode.Matrix;

/**
 * 766. Toeplitz Matrix.
 * [Matrix]
 * check all row and col where row >= 1 && col >= 1.
 * TC: O(m * n)
 * SC: O(1)
 * @author Jeesub Lee (jeesubl@andrew.cmu.edu)
 */
public class Q766_ToeplitzMatrix {
    public static boolean isToeplitzMatrix(int[][] matrix) {
        for (int i = 1; i < matrix.length; i++) {
            for (int j = 1; j < matrix[0].length; j++) {
                if (matrix[i][j] == matrix[i - 1][j - 1]) {
                    continue;
                }
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int[][] matrix = {{0, 1, 2, 3}, {3, 0, 1, 2}, {2, 3, 0, 1}};
        System.out.println(isToeplitzMatrix(matrix));
        // output: true
    }
}
