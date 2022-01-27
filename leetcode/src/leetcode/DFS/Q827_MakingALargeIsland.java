package leetcode.DFS;

import java.util.*;

/**
 * 827. Making A Large Island.
 * [DFS]
 * In the first iteration, Build an island size list.
 * [3, 5] // island 0 is size 3. island 1 is size 5.
 * Record every point's island number.
 * [[1, 0],
 *  [0, 1]]
 * (0, 0) is island 0. island numbers = [[0, -1], [-1, -1]], island size list = [1]
 * (1, 1) is island 1. island numbers = [[0, -1], [-1, 1]], island size list = [1, 1]
 * max size = max island's size.
 * In the second iteration, if we are in the ocean, calculate the sum(neighbor islands sizes).
 * max size = max(prev max, sum of neighbor islands sizes).
 * TC: O(n^2)
 * SC: O(n^2)
 * @author Jeesub Lee (jeesubl@andrew.cmu.edu)
 */
public class Q827_MakingALargeIsland {
    private static final int[][] DIRS = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    public static int largestIsland(int[][] grid) {
        List<Integer> sizeList = new ArrayList<>();
        int[][] islandNumbers = new int[grid.length][grid[0].length];
        for (int[] each : islandNumbers) {
            Arrays.fill(each, -1);
        }

        int max = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 0 || islandNumbers[i][j] != -1) {
                    continue;
                }
                islandNumbers[i][j] = sizeList.size();
                int islandSize = dfs(grid, islandNumbers, sizeList.size(), i, j);
                max = Math.max(max, islandSize);
                sizeList.add(islandSize);
            }
        }

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    continue;
                }
                Set<Integer> set = new HashSet<>();
                for (int[] dir : DIRS) {
                    int newRow = i + dir[0];
                    int newCol = j + dir[1];
                    if (!isValid(grid.length, newRow, newCol)) {
                        continue;
                    }
                    if (grid[newRow][newCol] == 0) {
                        continue;
                    }
                    set.add(islandNumbers[newRow][newCol]);
                }

                int connectedSize = 1;
                for (int islandNumber : set) {
                    connectedSize += sizeList.get(islandNumber);
                }
                max = Math.max(max, connectedSize);
            }
        }

        return max;
    }

    private static int dfs(int[][] grid, int[][] islandNumbers, int islandNumber, int row, int col) {
        int sum = 1;
        for (int[] dir : DIRS) {
            int newRow = row + dir[0];
            int newCol = col + dir[1];
            if (!isValid(grid.length, newRow, newCol)) {
                continue;
            }
            if (grid[newRow][newCol] == 0 || islandNumbers[newRow][newCol] != -1) {
                continue;
            }
            islandNumbers[newRow][newCol] = islandNumber;
            sum += dfs(grid, islandNumbers, islandNumber, newRow, newCol);
        }
        return sum;
    }

    private static boolean isValid(int n, int row, int col) {
        if (row < 0 || col < 0 || row >= n || col >= n) {
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        int[][] grid = {{1, 0, 1}, {0, 1, 0}, {0, 0, 1}};
        System.out.println(largestIsland(grid));
        // output: 4
    }
}
