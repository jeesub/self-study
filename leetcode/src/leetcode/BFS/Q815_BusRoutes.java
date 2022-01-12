package leetcode.BFS;

import java.util.*;

/**
 * 815. Bus Routes.
 * [BFS]
 * [[1, 2, 3], [3, 4, 5], [5, 6, 7], [1, 8, 9], [4, 8, 9]], 1 -> 7
 *
 * start from [1, 2, 3] or [1, 8, 9]   // can reach [1, 2, 3, 8, 9] in 1 step
 * can move to [3, 4, 5] or [4, 8, 9]  // can reach [4, 5] in 2 steps
 * can move to [5, 6, 7]               // can reach [6, 7] in 3 steps
 *
 * Sort each route to use binary search.
 * Track the current reachable points using a stack.
 * In one layer search, if we have new reachable points, add to the stack.
 * If we got the target, return the # of layers.
 * If the stack is empty, but we didn't see the target, we cannot get to the target.
 *
 * TC: O(M * NlogN), M = routes.length, N = route.length
 * SC: O(M * N)
 * @author Jeesub Lee (jeesubl@andrew.cmu.edu)
 */
public class Q815_BusRoutes {
    public static int numBusesToDestination(int[][] routes, int source, int target) {
        if (source == target) {
            return 0;
        }
        for (int[] route : routes) {
            Arrays.sort(route);
        }
        boolean[] seenRoutes = new boolean[routes.length];
        Set<Integer> seenStop = new HashSet<>();
        Deque<Integer> deque = new LinkedList<>();
        update(deque, seenStop, seenRoutes, routes, source);
        int num = 1;
        while (!deque.isEmpty()) {
            int size = deque.size();
            for (int i = 0; i < size; i++) {
                int curr = deque.remove();
                if (curr == target) {
                    return num;
                }
                update(deque, seenStop, seenRoutes, routes, curr);
            }
            num++;
        }
        return -1;
    }

    private static void update(Deque<Integer> deque, Set<Integer> seenStop, boolean[] seenRoutes, int[][] routes, int source) {
        for (int i = 0; i < routes.length; i++) {
            if (seenRoutes[i] || Arrays.binarySearch(routes[i], source) < 0) {
                continue;
            }
            for (int stop : routes[i]) {
                if (seenStop.contains(stop)) {
                    continue;
                }
                deque.add(stop);
                seenStop.add(stop);
            }
            seenRoutes[i] = true;
        }
    }

    public static void main(String[] args) {
        int[][] routes = {{1, 2 ,3}, {3, 4, 5}, {5, 6, 7}, {1, 8, 9}, {4, 8, 9}};
        int source = 1;
        int target = 7;
        System.out.println(numBusesToDestination(routes, source, target));
        // output: 3
    }
}
