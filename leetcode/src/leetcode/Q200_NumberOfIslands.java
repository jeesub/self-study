package leetcode;
/**
 * Q200. Number of Islands.
 * make a boolean[][] seen, int cnt
 * for every (row, col)
 * if unseen and 1 -> cnt++
 *    start dfs to 4 directions
 * if seen -> continue
 * if 0 -> continue
 * @author jeesublee
 */
public class Q200_NumberOfIslands {
	private static int[][] DIRS = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

	public static int numIslands(char[][] grid) {
		int cnt = 0;
		boolean[][] seen = new boolean[grid.length][grid[0].length];
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[0].length; j++) {
				if (seen[i][j] == false && grid[i][j] == '1') {
					seen[i][j] = true;
					dfs(i, j, grid, seen);
					cnt++;
				}
			}
		}
		return cnt;
	}
	
	private static void dfs(int i, int j, char[][] grid, boolean[][] seen) {
		for (int[] dir : DIRS) {
			int row = i + dir[0];
			int col = j + dir[1];
			if (isValid(row, col, grid, seen)) {
				seen[row][col] = true;
				dfs(row, col, grid, seen);
			}
		}
	}
	
	private static boolean isValid(int row, int col, char[][] grid, boolean[][] seen) {
		return row >= 0 && col >= 0 && row < seen.length && col < seen[0].length && seen[row][col] == false
				&& grid[row][col] == '1';
	}

	public static void main(String[] args) {
		char[][] grid = {
				{'1', '1', '0', '1', '0'},
				{'1', '1', '0', '1', '1'},
				{'0', '0', '1', '0', '1'},
				{'0', '1', '0', '1', '1'}
		};
		System.out.print(numIslands(grid));
		// output: 4
	}

}
