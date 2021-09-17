package leetcode.MapSet;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Q356. Line Reflection.
 * [Map & Set]
 * Map<points y value, Set<x values>>
 * Find max x and min x. sumX = (max x) + (min x)
 * All points should have a pair point having x value of (sumX - x)
 * TC: O(n), where n is the number of points
 * SC: O(n), where n is the number of points
 * @author Jeesub Lee (jeesubl@andrew.cmu.edu)
 */
public class Q356_LineReflection {

    public static boolean isReflected(int[][] points) {
        Map<Integer, Set<Integer>> map = new HashMap<>();
        int maxX = Integer.MIN_VALUE;
        int minX = Integer.MAX_VALUE;
        for (int[] point : points) {
            if (!map.containsKey(point[1])) {
                map.put(point[1], new HashSet<>());
            }
            map.get(point[1]).add(point[0]);
            maxX = Math.max(maxX, point[0]);
            minX = Math.min(minX, point[0]);
        }

        int sumX = minX + maxX;
        for (int[] point : points) {
            Set<Integer> set = map.get(point[1]);
            if (!set.contains(sumX - point[0])) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        int[][] points = {{6, 0}, {2, 1}, {4, 1}, {0, 0}, {6, 0}, {3, 0}};
        System.out.println(isReflected(points));
        // output: true
    }

}
