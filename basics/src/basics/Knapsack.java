package basics;

import java.util.Arrays;

/**
 * 
 * int[] votes = {2, 3, 4}
 * dp[votes.length + 1][total number + 1] 
 * => O(N * Sum): pseudo-polynomial
 * 
 * => each cell = sum of ways: not choose + choose(if possible)
 *
 *   | 0  1  2  3  4  5  6  7  8  9
 * X | 1  0  0  0  0  0  0  0  0  0
 * 2 | 1  0  1  0  0  0  0  0  0  0
 * 3 | 1  0  1  1  0  1  0  0  0  0
 * 4 | 1  0  1  1  1  1  1  1  0  1
 * 
 * => sum(dp[3][5] : dp[3][9])
 * 
 * @author jeesublee
 *
 */
public class Knapsack {

	public static int waysToWin(int[] votes) {
		int total = 0;
		for (int vote : votes) {
			total += vote;
		}
		int[][] dp = init(votes, total);

		for (int i = 1; i < dp.length; i++) {
			int vote = votes[i - 1];
			for (int j = 1; j < dp[0].length; j++) {
				dp[i][j] = dp[i - 1][j];
				if (vote <= j) {
					dp[i][j] += dp[i - 1][j - vote];
				}
			}
		}

		print(dp);

		return calculateSum(dp, total);
	}

	private static int[][] init(int[] votes, int total) {
		int[][] dp = new int[votes.length + 1][total + 1];
		for (int row = 0; row < dp.length; row++) {
			dp[row][0] = 1;
		}
		return dp;
	}

	private static int calculateSum(int[][] dp, int total) {
		int min = total / 2 + 1;
		int sum = 0;
		for (int i = min ; i < dp[0].length; i++) {
			sum += dp[dp.length - 1][i];
		}
		return sum;
	}
	
	private static void print(int[][] dp) {
		for (int[] each : dp) {
			System.out.println(Arrays.toString(each));
		}
	}

	public static void main(String[] args) {
		int[] votes = {2, 3, 4};
		System.out.println(waysToWin(votes));
		// output: 4
	}

}
