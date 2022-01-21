package leetcode.BFS;

import java.util.PriorityQueue;

/**
 * 778. Swim in Rising Water.
 * [BFS & MinHeap]
 * BFS using a min heap until reaching the goal.
 * Keep track and update the max depth.
 * TC: O(N^2logN)
 * SC: O(N^2)
 * @author Jeesub Lee (jeesubl@andrew.cmu.edu)
 */
public class Q778_SwimInRisingWater {
    private static final int[][] DIRS = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    public static int swimInWater(int[][] grid) {
        PriorityQueue<int[]> minHeap = new PriorityQueue<>((a, b) -> {
            return Integer.compare(grid[a[0]][a[1]], grid[b[0]][b[1]]);
        });
        boolean[][] visited = new boolean[grid.length][grid[0].length];
        visited[0][0] = true;
        int max = grid[0][0];
        minHeap.offer(new int[] {0, 0});
        int i = 0;
        int j = 0;
        while (!minHeap.isEmpty()) {
            int[] curr = minHeap.poll();
            i = curr[0];
            j = curr[1];
            max = Math.max(max, grid[i][j]);
            if (i == grid.length - 1 && j == grid[0].length - 1) {
                return max;
            }
            for (int[] dir : DIRS) {
                int row = i + dir[0];
                int col = j + dir[1];
                if (row < 0 || col < 0 || row >= grid.length || col >= grid[0].length) {
                    continue;
                }
                if (visited[row][col]) {
                    continue;
                }
                visited[row][col] = true;
                minHeap.offer(new int[] {row, col});
            }
        }
        return max;
    }

    public static void main(String[] args) {
        int[][] grid = {{0, 2, 7}, {3, 1, 8}, {4, 6, 5}};
        System.out.println(swimInWater(grid));
        // output: 6
    }
}
