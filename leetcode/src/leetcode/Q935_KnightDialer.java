package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Q935. Knight Dialer.
 * DP
 * keep the information of movable links
 * 0 -> 4, 6
 * 1 -> 6, 8
 * 2 -> 7, 9
 * 3 -> 4, 8
 * 4 -> 3, 9, 0
 * 5 -> none
 * 
 * make a DP table
 *   | 0  1  2  ..
 ----+-------------
 * 0 | 0  1  from 4 or 6 -> dp[4][i - 1] + dp[6][i - 1]
 * 1 | 0  1  from 6 or 8 -> dp[6][i - 1] + dp[8][i - 1]
 * 2 | 0  1  from 7 or 9 -> dp[7][i - 1] + dp[9][i - 1]
 * 3 | 0  1  from 4 or 8 -> dp[4][i - 1] + dp[8][i - 1]
 * 4 | 0  1  from 3, 9, or 0 -> dp[3][i - 1] + dp[9][i - 1] + dp[0][i - 1]
 * 5 | 0  1  unable to get here -> 0
 * ...
 * 
 * @author jeesublee
 */
public class Q935_KnightDialer {

	public static int knightDialer(int n) {
		List<List<Integer>> links = new ArrayList<>();
		links.add(Arrays.asList(4, 6)); // 0
		links.add(Arrays.asList(6, 8)); // 1
		links.add(Arrays.asList(7, 9)); // 2
		links.add(Arrays.asList(4, 8)); // 3
		links.add(Arrays.asList(0, 3, 9)); // 4
		links.add(Arrays.asList()); // 5
		links.add(Arrays.asList(0, 1, 7)); // 6
		links.add(Arrays.asList(2, 6)); // 7
		links.add(Arrays.asList(1, 3)); // 8
		links.add(Arrays.asList(2, 4)); // 9
		int mod = (int) Math.pow(10, 9) + 7;
		int[][] dp = new int[10][n + 1];

		for (int num = 0; num < 10; num++) {
			dp[num][1] = 1;
		}

		for (int i = 2; i <= n; i++) {
			for (int num = 0; num < 10; num++) {
				for (int prev : links.get(num)) {
					dp[num][i] += dp[prev][i - 1];
					dp[num][i] %= mod;
				}
			}
		}

		int res = 0;
		for (int num = 0; num < 10; num++) {
			res += dp[num][n];
			res %= mod;
		}
		return res;
	}

	public static void main(String[] args) {
		int n = 3131;
		System.out.print(knightDialer(n));
		// output: 136006598
	}

}
