package leetcode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Q 994. Rotting Oranges.
 * [BFS with layers]
 * Starts BFS from every rotten orange.
 * Records layers.
 * If we found every fresh orange, return the depth of layers.
 * Else, return -1.
 * Before start, if we don't have any fresh orange, return 0.
 * @author Jeesub Lee (jeesubl@andrew.cmu.edu)
 */
public class Q994_RottingOranges {
    private static final int[][] DIRS = { { 1, 0 }, { 0, 1 }, { -1, 0 }, { 0, -1 } };

    public static int orangesRotting(int[][] grid) {
        int fresh = countFresh(grid);
        if (fresh == 0) {
            return 0;
        }

        Deque<int[]> deque = findRottens(grid);

        int minutes = -1;
        boolean[][] seen = new boolean[grid.length][grid[0].length];
        while (!deque.isEmpty()) {
            int size = deque.size();
            while (size > 0) {
                int[] rotten = deque.removeFirst();
                for (int[] dir : DIRS) {
                    int newRow = rotten[0] + dir[0];
                    int newCol = rotten[1] + dir[1];
                    if (newRow < 0 || newCol < 0 || newRow >= grid.length || newCol >= grid[0].length) {
                        continue;
                    }
                    if (seen[newRow][newCol]) {
                        continue;
                    }
                    if (grid[newRow][newCol] == 1) {
                        seen[newRow][newCol] = true;
                        deque.addLast(new int[] { newRow, newCol });
                        fresh--;
                    }
                }
                size--;
            }
            minutes++;
        }

        return fresh == 0 ? minutes : -1;
    }

    private static int countFresh(int[][] grid) {
        int fresh = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    fresh++;
                }
            }
        }
        return fresh;
    }

    private static Deque<int[]> findRottens(int[][] grid) {
        Deque<int[]> deque = new ArrayDeque<>();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 2) {
                    deque.addLast(new int[] { i, j });
                }
            }
        }
        return deque;
    }

    public static void main(String[] args) {
        int[][] grid = {{2,1,1},{1,1,0},{0,1,1}};
        System.out.println(orangesRotting(grid));
        // output: 4
    }

}
