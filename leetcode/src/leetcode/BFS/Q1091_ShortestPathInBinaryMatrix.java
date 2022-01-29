package leetcode.BFS;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 1091. Shortest Path in Binary Matrix.
 * [BFS]
 * Store all valid neighbor points into a queue.
 * Increase a number of path and add all neighbors into the queue
 *     while we are not at the goal or queue is not empty.
 * TC: O(n)
 * SC: O(n)
 * @author Jeesub Lee (jeesubl@andrew.cmu.edu)
 */
public class Q1091_ShortestPathInBinaryMatrix {
    private static final int[][] DIRS
            = {{-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 1}, {1, -1}, {1, 0}, {1, 1}};

    public static int shortestPathBinaryMatrix(int[][] grid) {
        int n = grid.length;

        if (grid[0][0] == 1 || grid[n - 1][n - 1] == 1) {
            return -1;
        }

        Deque<int[]> deque = new ArrayDeque<>();
        deque.add(new int[]{0, 0});
        boolean[][] visited = new boolean[n][n];
        visited[0][0] = true;
        int layer = 1;
        while (!deque.isEmpty()) {
            int size = deque.size();
            while (size > 0) {
                int[] curr = deque.remove();
                if (curr[0] == n - 1 && curr[1] == n - 1) {
                    return layer;
                }
                for (int[] dir : DIRS) {
                    int newRow = curr[0] + dir[0];
                    int newCol = curr[1] + dir[1];
                    if (newRow < 0 || newCol < 0 || newRow >= n || newCol >= n) {
                        continue;
                    }
                    if (visited[newRow][newCol] || grid[newRow][newCol] == 1) {
                        continue;
                    }
                    deque.add(new int[]{newRow, newCol});
                    visited[newRow][newCol] = true;
                }
                size--;
            }
            layer++;
        }
        return -1;
    }

    public static void main(String[] args) {
        int[][] grid = {{0, 1, 1}, {0, 0, 0}, {1, 1, 0}};
        System.out.println(shortestPathBinaryMatrix(grid));
        // output: 3
    }
}
