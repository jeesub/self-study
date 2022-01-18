package DFS;

/**
 * [DFS]
 * Iterate though the array.
 * If curr == 0 || we have seen it, continue.
 * Else, dfs and count++.
 *
 * dfs: walk through 4 neighbor 1s
 *   seen[row][col] = true
 *   do not go if new coordinate is out of bound || seen || 0
 *
 * TC: O(m * n)
 * SC: O(m * n)
 * @author Jeesub Lee (jeesubl@andrew.cmu.edu)
 */
public class IslandCount {
    private static final int[][] DIRS = { {1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    static int getNumberOfIslands(int[][] binaryMatrix) {
        boolean[][] seen = new boolean[binaryMatrix.length][binaryMatrix[0].length];
        int count = 0;
        for (int i = 0; i < binaryMatrix.length; i++) {
            for (int j = 0; j < binaryMatrix[0].length; j++) {
                if (binaryMatrix[i][j] == 0 || seen[i][j]) {
                    continue;
                }
                dfs(binaryMatrix, seen, i, j);
                count++;
            }
        }
        return count;
    }

    private static void dfs(int[][] binaryMatrix, boolean[][] seen, int row, int col) {
        seen[row][col] = true;
        for (int[] dir : DIRS) {
            int newRow = row + dir[0];
            int newCol = col + dir[1];
            if (newRow < 0 || newCol < 0 || newRow >= binaryMatrix.length || newCol >= binaryMatrix[0].length) {
                continue;
            }
            if (binaryMatrix[newRow][newCol] == 0 || seen[newRow][newCol]) {
                continue;
            }
            dfs(binaryMatrix, seen, newRow, newCol);
        }
    }

    public static void main(String[] args) {
        int[][] binaryMatrix = {{0, 1, 0, 1}, {1, 1, 0, 0}};
        System.out.println(getNumberOfIslands(binaryMatrix));
        // output: 2
    }
}
