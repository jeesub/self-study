package leetcode;

import java.util.Arrays;

/***
 * Q568. Maximum Vacation Days.
 * DFS with memoization
 * memoization array: cities * weeks
 * 
 * @author jeesublee
 */
public class Q568_MaximumVacationDays {

	public static int maxVacationDays(int[][] flights, int[][] days) {
		int[][] dp = init(flights.length, days[0].length);
		return dfs(flights, days, dp, 0, 0);
	}

	private static int[][] init(int cities, int weeks) {
		int[][] dp = new int[cities][weeks];
		for (int[] each : dp) {
			Arrays.fill(each, -1);
		}
		return dp;
	}

	private static int dfs(int[][] flights, int[][] days, int[][] dp, int city, int week) {
		if (week >= days[0].length) {
			return 0;
		}
		if (dp[city][week] != -1) {
			return dp[city][week];
		}

		int maxDays = 0;
		for (int nextCity = 0; nextCity < flights.length; nextCity++) {
			if (flights[city][nextCity] != 0 || nextCity == city) {
				int newMaxDays = days[nextCity][week] + dfs(flights, days, dp, nextCity, week + 1);
				maxDays = Math.max(maxDays, newMaxDays);
			}
		}
		return dp[city][week] = maxDays;
	}

	public static void main(String[] args) {
		int[][] flights = {{0,1,1},{1,0,1},{1,1,0}};
		int[][] days = {{1,3,1},{6,0,3},{3,3,3}};
		System.out.println(maxVacationDays(flights, days));
		// output: 12
	}

}
