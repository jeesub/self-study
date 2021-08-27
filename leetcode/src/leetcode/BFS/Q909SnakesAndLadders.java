package leetcode.BFS;

import java.util.Deque;
import java.util.LinkedList;

/**
 * Q909. Snakes and Ladders.
 * [BFS]
 * In each round of BFS, find the next step and add to a deque.
 * If the deque is empty and we didn't reach the end point, return -1.
 * If we reached the end point, return the number of layers.
 * TC: O(n^2), where n is the number of elements
 * SC: O(n), where n is the number of elements
 * @author Jeesub Lee (jeesubl@andrew.cmu.edu)
 */
public class Q909SnakesAndLadders {

    public static int snakesAndLadders(int[][] board) {
        int length = board.length;
        int target = length * length;
        Deque<Integer> deque = new LinkedList<>();
        deque.add(1);
        boolean[] visited = new boolean[target + 1];
        visited[1] = true;
        int layers = 0;

        while (!deque.isEmpty()) {
            int size = deque.size();
            while (size-- > 0) {
                int curr = deque.remove();
                if (curr == target) {
                    return layers;
                }

                int upperBound = Math.min(curr + 6, target);
                for (int i = curr + 1; i <= upperBound; i++) {
                    if (visited[i]) {
                        continue;
                    }
                    
                    visited[i] = true;
                    int[] coordinate = getCoordinate(i, length);
                    if (board[coordinate[0]][coordinate[1]] < 0) {
                        deque.add(i);
                    } else {
                        deque.add(board[coordinate[0]][coordinate[1]]);
                    }
                }
            }
            layers++;
        }

        return -1;
    }

    private static int[] getCoordinate(int num, int length) {
        num--;
        int rowFromBottom = num / length;
        int row = length - rowFromBottom - 1;
        int col = num % length;
        if (rowFromBottom % 2 == 1) {
            col = length - col - 1;
        }
        return new int[] { row, col };
    }

    public static void main(String[] args) {
        int[][] board = {
                {-1, -1, -1, -1, -1, -1},
                {-1, -1, -1, -1, -1, -1},
                {-1, -1, -1, -1, -1, -1},
                {-1, 35, -1, -1, 13, -1},
                {-1, -1, -1, -1, -1, -1},
                {-1, 15, -1, -1, -1, -1}};
        System.out.println(snakesAndLadders(board));
        // output: 4
    }

}
