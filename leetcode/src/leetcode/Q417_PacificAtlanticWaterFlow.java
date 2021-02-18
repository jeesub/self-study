package leetcode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;

/**
 * Q417. Pacific Atlantic Water Flow.
 * @author Jeesub Lee (jeesubl@andrew.cmu.edu)
 */
public class Q417_PacificAtlanticWaterFlow {

	private static int[][] DIR = {{-1, 0}, {0, -1}, {1, 0}, {0, 1}};
	
	public static List<List<Integer>> pacificAtlantic(int[][] matrix) {
		if (matrix.length == 0) {
			return new ArrayList<>();
		}
		Deque<int[]> pacQ = initPacQ(matrix);
		Deque<int[]> atlQ = initAtlQ(matrix);
		boolean[][] pacBool = new boolean[matrix.length][matrix[0].length];
		boolean[][] atlBool = new boolean[matrix.length][matrix[0].length];
		boolean[][] pacVisited = new boolean[matrix.length][matrix[0].length];
		boolean[][] atlVisited = new boolean[matrix.length][matrix[0].length];
		bfs(matrix, pacQ, pacBool, pacVisited);
		bfs(matrix, atlQ, atlBool, atlVisited);
		List<List<Integer>> result = buildResult(pacBool, atlBool);
		return result;
	}
	
	private static Deque<int[]> initPacQ(int[][] matrix) {
		Deque<int[]> pacQ = new ArrayDeque<>();
		for (int row = 0; row < matrix.length; row++) {
			pacQ.add(new int[] { row, 0 });
		}
		for (int col = 0; col < matrix[0].length; col++) {
			pacQ.add(new int[] { 0, col });
		}
		return pacQ;
	}
	
	private static Deque<int[]> initAtlQ(int[][] matrix) {
		Deque<int[]> atlQ = new ArrayDeque<>();
		for (int row = 0; row < matrix.length; row++) {
			atlQ.add(new int[] { row, matrix[0].length - 1 });
		}
		for (int col = 0; col < matrix[0].length; col++) {
			atlQ.add(new int[] { matrix.length - 1, col });
		}
		return atlQ;
	}
	
	private static void bfs(int[][] matrix, Deque<int[]> Q, boolean[][] boolArr, boolean[][] visited) {
		while (!Q.isEmpty()) {
			int[] point = Q.poll();
			int row = point[0];
			int col = point[1];
			visited[row][col] = true;
			boolArr[row][col] = true;
	
			for (int[] next : DIR) {
				int nextRow = row + next[0];
				int nextCol = col + next[1];
				if (isValid(matrix, nextRow, nextCol) && !visited[nextRow][nextCol]
						&& matrix[row][col] <= matrix[nextRow][nextCol]) {
					Q.offer(new int[] { nextRow, nextCol });
				}
			}
		}
	}
	
	private static boolean isValid(int[][] matrix, int row, int col) {
		if (row < 0 || row >= matrix.length || col < 0 || col >= matrix[0].length) {
			return false;
		}
		return true;
	}
	
	private static List<List<Integer>> buildResult(boolean[][] pacBool, boolean[][] atlBool) {
		List<List<Integer>> result = new ArrayList<>();
		for (int row = 0; row < pacBool.length; row++) {
			for (int col = 0; col < pacBool[0].length; col++) {
				if (pacBool[row][col] && atlBool[row][col]) {
					result.add(Arrays.asList(row, col));
				}
			}
		}
		return result;
	}

	public static void main(String[] args) {
		int[][] matrix = {{1,2,2,3,5},{3,2,3,4,4},{2,4,5,3,1},{6,7,1,4,5},{5,1,1,2,4}};
		List<List<Integer>> result = pacificAtlantic(matrix);
		System.out.println(result);
		// output: [[0, 4], [1, 3], [1, 4], [2, 2], [3, 0], [3, 1], [4, 0]]
	}

}
