package leetcode.Tree;

import java.util.List;
import java.util.PriorityQueue;

/**
 * 1522. Diameter of N-Ary Tree.
 * [Tree & Recursion]
 * Pair<longest single path, max diameter so far>
 * current longest single path = max(single path from children) + 1
 * current max diameter so far
 *   = max(max from children, diameter including curr)
 * TC: O(n)
 * SC: O(n)
 * @author Jeesub Lee (jeesubl@andrew.cmu.edu)
 */
public class Q1522_DiameterOfNAryTree {
    public static int diameter(Node root) {
        if (root == null) {
            return 0;
        }
        return getLongestDiameter(root).diameter;
    }

    private static Pair getLongestDiameter(Node node) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        int maxDiameter = 0;
        for (Node child : node.children) {
            if (child == null) {
                continue;
            }
            Pair pair = getLongestDiameter(child);
            minHeap.add(pair.path);
            if (minHeap.size() > 2) {
                minHeap.poll();
            }
            maxDiameter = Math.max(maxDiameter, pair.diameter);
        }
        int secondMaxPath = minHeap.isEmpty() ? 0 : minHeap.poll();
        int firstMaxPath = minHeap.isEmpty() ? 0 : minHeap.poll();

        int newPath = Math.max(firstMaxPath, secondMaxPath) + 1;
        int newDiameter = Math.max(maxDiameter, firstMaxPath + secondMaxPath);
        return new Pair(newPath, newDiameter);
    }

    private static class Pair {
        int path;
        int diameter;

        private Pair(int newPath, int newDiameter) {
            path = newPath;
            diameter = newDiameter;
        }
    }

    private static class Node {
        public int val;
        public List<Node> children;
    }
}
