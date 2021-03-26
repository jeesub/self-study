package leetcode;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Q939. Minimum Area Rectangle.
 * Keep points in a set.
 * Compare two points if x1 != x2 && y1 != y2, it can be diagonal.
 * Check if we have the other two points.
 * If so, update min area.
 * @author Jeesub Lee (jeesubl@andrew.cmu.edu)
 */
public class Q939_MinimumAreaRectangle {

    public static int minAreaRect(int[][] points) {
        Set<Point> pointSet = buildSet(points);
        int minArea = Integer.MAX_VALUE;
        for (int i = 0; i < points.length; i++) {
            Point point1 = Point.of(points[i][0], points[i][1]);
            for (int j = i + 1; j < points.length; j++) {
                Point point2 = Point.of(points[j][0], points[j][1]);
                if (point1.equals(point2)) {
                    continue;
                }
                if (point1.x == point2.x || point1.y == point2.y) {
                    continue;
                }
                Point candidate1 = Point.of(point1.x, point2.y);
                Point candidate2 = Point.of(point2.x, point1.y);
                if (pointSet.contains(candidate1) && pointSet.contains(candidate2)) {
                    int candidateArea = Math.abs(point1.x - point2.x) * Math.abs(point1.y - point2.y);
                    minArea = Math.min(minArea, candidateArea);
                }
            }
        }
        return minArea == Integer.MAX_VALUE ? 0 : minArea;
    }

    private static Set<Point> buildSet(int[][] points) {
        Set<Point> pointSet = new HashSet<>();
        for (int[] point : points) {
            pointSet.add(Point.of(point[0], point[1]));
        }
        return pointSet;
    }

    private static final class Point {
        int x;
        int y;

        private Point(int newX, int newY) {
            x = newX;
            y = newY;
        }

        private static Point of(int newX, int newY) {
            return new Point(newX, newY);
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
        int[][] points = {{1, 1}, {1, 3}, {2, 2}, {2, 4}, {3, 1}, {3, 3}};
        System.out.println(minAreaRect(points));
        // output: 4
    }

}
