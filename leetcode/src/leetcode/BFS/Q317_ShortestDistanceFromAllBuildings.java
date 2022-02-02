package leetcode.BFS;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * 317. Shortest Distance from All Buildings.
 * [BFS]
 * From all houses, BFS and record the distance.
 * Also track the number of houses reached a point.
 * When we calculate the min distance, consider the number of houses reached the point.
 * TC: O(m^2 * n^2)
 * SC: O(m * n)
 * @author Jeesub Lee (jeesubl@andrew.cmu.edu)
 */
public class Q317_ShortestDistanceFromAllBuildings {
    private static final int[][] DIRS = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    public static int shortestDistance(int[][] grid) {
        List<int[]> houses = buildHouses(grid);
        int[][] accDist = new int[grid.length][grid[0].length];
        int[][] reachedHouses = new int[grid.length][grid[0].length];
        for (int[] house : houses) {
            boolean[][] visited = new boolean[grid.length][grid[0].length];
            Deque<int[]> deque = new ArrayDeque<>();
            deque.add(house);
            int dist = 0;
            while (!deque.isEmpty()) {
                int size = deque.size();
                for (int i = 0; i < size; i++) {
                    int[] curr = deque.remove();
                    int row = curr[0];
                    int col = curr[1];
                    accDist[row][col] += dist;
                    reachedHouses[row][col]++;
                    for (int[] dir : DIRS) {
                        int newRow = row + dir[0];
                        int newCol = col + dir[1];
                        if (isValid(grid, newRow, newCol) && !visited[newRow][newCol]) {
                            visited[newRow][newCol] = true;
                            deque.add(new int[]{newRow, newCol});
                        }
                    }
                }
                dist++;
            }
        }

        int min = Integer.MAX_VALUE;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] != 0 || reachedHouses[i][j] < houses.size()) {
                    continue;
                }
                min = Math.min(min, accDist[i][j]);
            }
        }
        return min == Integer.MAX_VALUE ? -1 : min;
    }

    private static List<int[]> buildHouses(int[][] grid) {
        List<int[]> houses = new ArrayList<>();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    houses.add(new int[]{i, j});
                }
            }
        }
        return houses;
    }

    private static boolean isValid(int[][] grid, int row, int col) {
        if (row < 0 || col < 0 || row >= grid.length || col >= grid[0].length) {
            return false;
        }
        return grid[row][col] == 0;
    }

    public static void main(String[] args) {
        int[][] grid = {{1, 0, 1, 2, 0}, {1, 0, 0, 0, 2}, {0, 1, 1, 2, 0}};
        System.out.println(shortestDistance(grid));
        // output: 8
    }
}
