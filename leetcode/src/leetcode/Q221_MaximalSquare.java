package leetcode;
/**
 * Maximal Square.
 * [DP]
 * Make a DP table.
 * dp[i][j] contains the valid longest side length.
 * if matrix[i][j] == '1', dp[i][j] = Min of (dp[i - 1][j], dp[i][j - 1], dp[i - 1][j - 1]) + 1
 * Keep a valid longest side length and update if possible while iterating.
 * Return area after iteration.
 * @author Jeesub Lee (jeesubl@andrew.cmu.edu)
 */
public class Q221_MaximalSquare {

    public static int maximalSquare(char[][] matrix) {
        int[][] dp = new int[matrix.length + 1][matrix[0].length + 1];
        int maxSide = 0;
        for (int i = 1; i <= matrix.length; i++) {
            for (int j = 1; j <= matrix[0].length; j++) {
                if (matrix[i - 1][j - 1] == '0') {
                    continue;
                }
                dp[i][j] = Math.min(dp[i - 1][j - 1], Math.min(dp[i - 1][j], dp[i][j - 1])) + 1;
                maxSide = Math.max(maxSide, dp[i][j]);
            }
        }
        return maxSide * maxSide;
    }

	public static void main(String[] args) {
		char[][] matrix = {
				{'1','0','1','0','0'},
				{'1','0','1','1','1'},
				{'1','1','1','1','1'},
				{'1','0','0','1','0'}};
		System.out.print(maximalSquare(matrix));
		// output: 4
	}

}
