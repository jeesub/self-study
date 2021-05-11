package leetcode.BTR;
/**
 * Q79. Word Search.
 * DFS & BTR
 * when we find the word, return true
 * @author jeesublee
 */
public class Q79_WordSearch {
	private static int[][] DIRS = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

	public static boolean exist(char[][] board, String word) {
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				if (board[i][j] == word.charAt(0)) {
					boolean[][] visited = new boolean[board.length][board[0].length];
					visited[i][j] = true;
					if (dfs(board, word, visited, 0, i, j)) {
						return true;
					}
				}
			}
		}
		return false;
	}

	private static boolean dfs(char[][] board, String word, boolean[][] visited, int ptr, int i, int j) {
		if (board[i][j] != word.charAt(ptr) || ptr >= word.length()) {
			return false;
		}
		if (ptr == word.length() - 1) {
			return true;
		}
	
		for (int[] dir : DIRS) {
			int nextI = i + dir[0];
			int nextJ = j + dir[1];
			if (isValid(board, visited, nextI, nextJ)) {
				visited[nextI][nextJ] = true;
				if (dfs(board, word, visited, ptr + 1, nextI, nextJ)) {
					return true;
				}
				visited[nextI][nextJ] = false;
			}
		}
		return false;
	}

	private static boolean isValid(char[][] board, boolean[][] visited, int i, int j) {
		if (i < 0 || j < 0 || i >= board.length || j >= board[0].length) {
			return false;
		}
		if (visited[i][j]) {
			return false;
		}
		return true;
	}

	public static void main(String[] args) {
		char[][] board = {{'A','B','C','E'},{'S','F','C','S'},{'A','D','E','E'}};
		String word = "ABCCED";
		System.out.print(exist(board, word));
		// output: true
	}

}
