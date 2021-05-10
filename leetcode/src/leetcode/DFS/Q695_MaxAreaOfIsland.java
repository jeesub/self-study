package leetcode.DFS;

public class Q695_MaxAreaOfIsland {
    private static int[][] DIRS = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    public static int maxAreaOfIsland(int[][] grid) {
        boolean[][] seen = new boolean[grid.length][grid[0].length];
        int maxArea = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1 && !seen[i][j]) {
                    maxArea = Math.max(maxArea, dfs(grid, seen, i, j));
                }
            }
        }
        return maxArea;
    }

    private static int dfs(int[][] grid, boolean[][] seen, int row, int col) {
        if (!isValid(grid, seen, row, col)) {
            return 0;
        }
        seen[row][col] = true;
        int currentMax = 1;
        for (int[] dir : DIRS) {
            int newRow = row + dir[0];
            int newCol = col + dir[1];
            currentMax += dfs(grid, seen, newRow, newCol);
        }
        return currentMax;
    }

    private static boolean isValid(int[][] grid, boolean[][] seen, int row, int col) {
        if (row < 0 || col < 0 || row >= grid.length || col >= grid[0].length) {
            return false;
        }
        if (grid[row][col] == 0 || seen[row][col]) {
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        int[][] grid = {{0,0,1,0,0,0,0,1,0,0,0,0,0},
                        {0,0,0,0,0,0,0,1,1,1,0,0,0},
                        {0,1,1,0,1,0,0,0,0,0,0,0,0},
                        {0,1,0,0,1,1,0,0,1,0,1,0,0},
                        {0,1,0,0,1,1,0,0,1,1,1,0,0},
                        {0,0,0,0,0,0,0,0,0,0,1,0,0},
                        {0,0,0,0,0,0,0,1,1,1,0,0,0},
                        {0,0,0,0,0,0,0,1,1,0,0,0,0}};
        System.out.println(maxAreaOfIsland(grid));
        // output: 6
    }

}
