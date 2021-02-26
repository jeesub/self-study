package leetcode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * Q207. Course Schedule.
 * [Topological Sorting]
 * If a node has 0 indegree, put into a queue.
 * Remove indegrees as much as possible.
 * If we successfully remove every element, we can return true.
 * @author Jeesub Lee (jeesubl@andrew.cmu.edu)
 */
public class Q207_CourseSchedule {

    public static boolean canFinish(int numCourses, int[][] prerequisites) {
        List<List<Integer>> nodes = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            nodes.add(new ArrayList<>());
        }
        int[] indegrees = new int[numCourses];
        for (int[] prerequisite: prerequisites) {
            indegrees[prerequisite[0]]++;
            nodes.get(prerequisite[1]).add(prerequisite[0]);
        }
        
        Deque<Integer> deque = new ArrayDeque<>();
        for (int i = 0; i < indegrees.length; i++) {
            if (indegrees[i] == 0) {
                deque.addLast(i);
            }
        }

        int count = 0;
        while (!deque.isEmpty()) {
            int curr = deque.removeFirst();
            count++;
            for (int next : nodes.get(curr)) {
                if (--indegrees[next] == 0) {
                    deque.addLast(next);
                }
            }
        }
        
        return count == numCourses;
    }

    public static void main(String[] args) {
        int numCourses = 5;
        int[][] prerequisites = {{1,0},{2,0},{2,1},{4,3}};
        System.out.println(canFinish(numCourses, prerequisites));
        // output: true
    }

}
