package leetcode.DFS;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/**
 * 286. Walls and Gates.
 * [BFS]
 * BFS from all gates.
 * rooms[newRow][newCol] = rooms[row][col] + 1
 * TC: O(m * n)
 * SC: O(m * n)
 * @author Jeesub Lee (jeesubl@andrew.cmu.edu)
 */
public class Q286_WallsAndGates {
    private static final int[][] DIRS = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    public static void wallsAndGates(int[][] rooms) {
        int m = rooms.length;
        int n = rooms[0].length;
        Deque<int[]> deque = putAllGates(rooms);
        while (!deque.isEmpty()) {
            int[] curr = deque.remove();
            int row = curr[0];
            int col = curr[1];
            for (int[] dir : DIRS) {
                int newRow = row + dir[0];
                int newCol = col + dir[1];
                if (newRow < 0 || newCol < 0 || newRow >= m || newCol >= n) {
                    continue;
                }
                if (rooms[newRow][newCol] == -1 || rooms[newRow][newCol] == 0) {
                    continue;
                }
                if (rooms[newRow][newCol] != Integer.MAX_VALUE) {
                    continue;
                }
                rooms[newRow][newCol] = rooms[row][col] + 1;
                deque.add(new int[]{newRow, newCol});
            }
        }
    }

    private static Deque<int[]> putAllGates(int[][] rooms) {
        Deque<int[]> deque = new ArrayDeque<>();
        for (int i = 0; i < rooms.length; i++) {
            for (int j = 0; j < rooms[0].length; j++) {
                if (rooms[i][j] == 0) {
                    deque.add(new int[]{i, j});
                }
            }
        }
        return deque;
    }

    public static void main(String[] args) {
        int[][] rooms = {
                {Integer.MAX_VALUE, -1, 0},
                {Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE},
                {0, -1, 0}
        };
        wallsAndGates(rooms);
        for (int[] each : rooms) {
            System.out.println(Arrays.toString(each));
        }
        // output: [[2, -1, 0], [1, 2, 1], [0, -1, 0]]
    }
}
