package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * BTR, DP
 * make a dp table (row: edges, column: path)
 * if DFS reaches at the end(index 3), we can know the editDistance
 * record it into the dp table
 * when it back to index 2, we can know the minimum editDistance
 * 0 can go to 2 and 3. dp[2][3] = 0, dp[3][3] = 1. so dp[0][2] = 0 + editDistance itself = 1
 * 1 can go to 2, 3, and 4, dp[2][3] = 0, dp[3][3] = 1, and dp[4][3] = 1. so dp[1][2] = 0 + 1 = 1
 * 2 can go to 0, 1, and 4, dp[0][3] = 1, dp[1][3] = 1, and dp[4][3] = 1. so dp[2][2] = 1 + 1 = 2
 * 3 can go to 0 and 1. dp[0][3] = 1, dp[1][3] = 1. so dp[3][2] = 1 + 1 = 2
 * 4 can go to 1 and 2. dp[1][3] = 1, dp[2][3] = 0. so dp[4][2] = 0 + 0 = 0
 * when we make a dp table, we can find the minimum editDistance
 * we need to return the path, so we need to make a dp table with path
 * let's keep the minimum editDistance and the best next index
 * 
 *     0 3 4 2
 * --+---------
 * 0 |     1 1
 * 1 |     1 1
 * 2 |     2 0
 * 3 |     2 1
 * 4 |     0 1
 * 
 * @author jeesublee
 *
 */
public class TheMostSimilarPathInAGraph {

	public static List<Integer> mostSimilar(int n, int[][] roads, String[] names, String[] targetPath) {
		List<List<Integer>> connections = initConnections(n, roads);
		Pair[][] dp = new Pair[n][targetPath.length];
		Pair dummyStart = new Pair(Integer.MAX_VALUE, Integer.MAX_VALUE);
		for (int i = 0; i < n; i++) {
			Pair startCandidate = dfs(dp, names, targetPath, connections, i, 0);
			if (startCandidate.editDistance < dummyStart.editDistance) {
				dummyStart = new Pair(startCandidate.editDistance, i);
			}
		}
		return buildResult(dummyStart.nextIndex, dp);
	}

	private static Pair dfs(Pair[][] dp, String[] names, String[] targetPath, List<List<Integer>> connections, int curr,
			int ptr) {
		if (dp[curr][ptr] != null) {
			return dp[curr][ptr];
		}

		int diff = names[curr].equals(targetPath[ptr]) ? 0 : 1;

		if (ptr == targetPath.length - 1) {
			return dp[curr][ptr] = new Pair(diff, -1);
		}

		Pair bestNext = new Pair(Integer.MAX_VALUE, Integer.MAX_VALUE);
		int bestNextIndex = 0;
		for (Integer next : connections.get(curr)) {
			Pair candidate = dfs(dp, names, targetPath, connections, next, ptr + 1);
			if (candidate.editDistance < bestNext.editDistance) {
				bestNext = candidate;
				bestNextIndex = next;
			}
		}
		return dp[curr][ptr] = new Pair(bestNext.editDistance + diff, bestNextIndex);
	}

	private static List<List<Integer>> initConnections(int n, int[][] roads) {
		List<List<Integer>> connections = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			connections.add(new ArrayList<>());
		}
		for (int[] road : roads) {
			connections.get(road[0]).add(road[1]);
			connections.get(road[1]).add(road[0]);
		}
		return connections;
	}

	private static List<Integer> buildResult(int index, Pair[][] dp) {
		List<Integer> result = new ArrayList<>();
		for (int i = 0; i < dp[0].length; i++) {
			result.add(index);
			index = dp[index][i].nextIndex;
		}
		return result;
	}

	private static class Pair {
		int editDistance;
		int nextIndex;

		public Pair(int editDistance, int nextIndex) {
			this.editDistance = editDistance;
			this.nextIndex = nextIndex;
		}
	}

	public static void main(String[] args) {
		int n = 5;
		int[][] roads = {{0,2},{0,3},{1,2},{1,3},{1,4},{2,4}};
		String[] names = {"ATL","PEK","LAX","DXB","HND"}; 
		String[] targetPath = {"ATL","DXB","HND","LAX"};
		System.out.print(mostSimilar(n, roads, names, targetPath));
		// output: [0, 2, 4, 2], [0, 3, 0, 2] or [0, 3, 1, 2]
	}

}
