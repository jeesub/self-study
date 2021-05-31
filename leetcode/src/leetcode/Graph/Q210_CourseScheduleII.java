package leetcode.Graph;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;

/**
 * Q210. Course Schedule II.
 * [Topological Sorting]
 * Node<num, inDegree>
 * Start from nodes with in-degree 0.
 * Reduce the next node's in-degree by one.
 * If the next node's in-degree is 0, it is able to take the course.
 * If we can iterate through the all nodes, it is possible to finish all courses.
 * 0 -> 1 -> 3
 * 0 -> 2 -> 3
 * <0, 0>, <1, 1>, <2, 1>, <3, 2>
 * 0 -> 1 : update <1, 1> to <1, 0>
 * 0 -> 2 : update <2, 1> to <2, 0>
 * Now we can take courses 1 and 2.
 * 1 -> 3 : update <3, 2> to <3, 1>
 * 2 -> 3 : update <3, 1> to <3, 0>
 * Now we can take couse 3.
 *
 * Instead of making nodes, we can use
 * int[] inDegrees: keep track of in-degrees
 * List<List<Integer>> edges: keep track of edges to the next nodes
 * @author Jeesub Lee (jeesubl@andrew.cmu.edu)
 */
public class Q210_CourseScheduleII {

    public static int[] findOrder(int numCourses, int[][] prerequisites) {
        int[] inDegrees = new int[numCourses];
        List<List<Integer>> edges = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            edges.add(new ArrayList<>());
        }
        for (int[] prerequisite : prerequisites) {
            inDegrees[prerequisite[0]]++;
            edges.get(prerequisite[1]).add(prerequisite[0]);
        }

        Deque<Integer> deque = new ArrayDeque<>();
        for (int i = 0; i < numCourses; i++) {
            if (inDegrees[i] == 0) {
                deque.add(i);
            }
        }

        int[] result = new int[numCourses];
        int index = 0;
        while (!deque.isEmpty()) {
            int curr = deque.remove();
            result[index++] = curr;
            for (Integer each : edges.get(curr)) {
                inDegrees[each]--;
                if (inDegrees[each] == 0) {
                    deque.add(each);
                }
            }
        }

        if (index < numCourses) {
            return new int[0];
        }
        return result;
    }

    public static void main(String[] args) {
        int numCourses = 4;
        int[][] prerequisites = {{1,0},{2,0},{3,1},{3,2}};
        System.out.println(Arrays.toString(findOrder(numCourses, prerequisites)));
        // output: [0, 1, 2, 3]
    }

}
