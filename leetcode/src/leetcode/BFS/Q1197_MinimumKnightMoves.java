package leetcode.BFS;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Q1197. Minimum Knight Moves.
 * [BFS]
 * Go to possible next positions every time.
 * If a current position is the goal, return the number of layers.
 * Use a HashSet to store visited points and prevent from revisiting.
 * Pruning
 *   Use abs(x) and abs(y) as a target value. We don't need to see all 4 quadrant.
 *   Use x = -2 and y = -2 as lower boundaries.
 *   Use x = targetX + 2 and y = targetY + 2 as upper boundaries.
 * TC: O(abs(x) * abs(y))
 * SC: O(abs(x) * abs(y))
 * @author Jeesub Lee (jeesubl@andrew.cmu.edu)
 */
public class Q1197_MinimumKnightMoves {

    private static final int[][] DIRS = { { 1, 2 }, { 1, -2 }, { -1, 2 }, { -1, -2 }, { 2, 1 }, { 2, -1 }, { -2, 1 },
            { -2, -1 } };

    public static int minKnightMoves(int x, int y) {
        int targetX = Math.abs(x);
        int targetY = Math.abs(y);
        Deque<Point> deque = new ArrayDeque<>();
        Set<Point> seen = new HashSet<>();
        int layer = 0;
        deque.add(new Point(0, 0));
        while (true) {
            int size = deque.size();
            while (size > 0) {
                Point curr = deque.removeFirst();
                if (curr.x == targetX && curr.y == targetY) {
                    return layer;
                }
                for (int[] dir : DIRS) {
                    Point newPair = new Point(curr.x + dir[0], curr.y + dir[1]);
                    if (seen.contains(newPair) || !isValid(targetX, targetY, newPair)) {
                        continue;
                    }
                    seen.add(newPair);
                    deque.add(newPair);
                }
                size--;
            }
            layer++;
        }
    }

    private static boolean isValid(int x, int y, Point pair) {
        return (pair.x >= -2 && pair.y >= -2 && pair.x <= x + 2 && pair.y <= y + 2);
    }

    private static class Point {
        int x;
        int y;

        private Point(int newX, int newY) {
            x = newX;
            y = newY;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }

        @Override
        public boolean equals(Object o) {
            if (!(o instanceof Point)) {
                return false;
            }
            Point other = (Point) o;
            return this.x == other.x && this.y == other.y;
        }
    }

    public static void main(String[] args) {
        System.out.println(minKnightMoves(5, 5));
        // output: 4
    }

}
