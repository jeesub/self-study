package leetcode;
/**
 * 
 * DP table stores the maximum width of square include myself
 * DP[i][j] = if (matrix[i][j] == 1) ? min of left, top, left-top + 1 : 0
 * if DP[i][j] > maxNum ? update maxNum
 * return maxNum ^ 2
 *
 * matrix table
 * 1 0 1 0 0
 * 1 0 1 1 1
 * 1 1 1 1 1
 * 1 0 0 1 0
 * 
 * DP table
 * 1 0 1 0 0
 * 1 0 1 1 1 
 * 1 1 1 2 2 (min of (left: 2, top: 1, left-top: 1) + 1)
 * 1 0 0 1 0
 * 
 * @author jeesublee
 *
 */
public class MaximalSquare {

	public static int maximalSquare(char[][] matrix) {
		int max = 0;
		int[][] dp = new int[matrix.length][matrix[0].length];
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[0].length; j++) {
				if (matrix[i][j] == '0') {
					continue;
				}
				if (i == 0 || j == 0) {
					dp[i][j] = Character.getNumericValue(matrix[i][j]);
					max = Math.max(max, dp[i][j]);
					continue;
				}
				dp[i][j] = Math.min(dp[i - 1][j - 1], Math.min(dp[i - 1][j], dp[i][j - 1])) + 1;
				max = Math.max(max, dp[i][j]);
			}
		}
		return max * max;
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
