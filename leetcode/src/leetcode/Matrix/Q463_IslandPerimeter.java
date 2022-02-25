package leetcode.Matrix;

/**
 * 463. Island Perimeter.
 * [matrix]
 * if grid[i][j] is land, check neighbors
 * TC: O(m*n)
 * SC: O(1)
 * @author Jeesub Lee (jeesubl@andrew.cmu.edu)
 */
public class Q463_IslandPerimeter {
    private static final int[][] DIRS = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    public static int islandPerimeter(int[][] grid) {
        int perimeter = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 0) {
                    continue;
                }
                for (int[] dir : DIRS) {
                    int newRow = i + dir[0];
                    int newCol = j + dir[1];
                    if (newRow < 0 || newCol < 0 || newRow >= grid.length || newCol >= grid[0].length) {
                        perimeter++;
                    } else if (grid[newRow][newCol] == 0) {
                        perimeter++;
                    }
                }
            }
        }
        return perimeter;
    }
}
