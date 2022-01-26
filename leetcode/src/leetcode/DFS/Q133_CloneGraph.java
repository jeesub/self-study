package leetcode.DFS;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 133. Clone Graph.
 * [DFS & Recursion]
 * Walk through all the possible paths and connect copied nodes.
 * Keep track of nodes using a Map<original node, copied node>.
 * If map contains key(node), we have seen it. Return a value.
 * Else, dfs. Add the next node's copied node to the neighbors list.
 * TC: O(e + v)
 * SC: O(e)
 * @author Jeesub Lee (jeesubl@andrew.cmu.edu)
 */
public class Q133_CloneGraph {
    public static Node cloneGraph(Node node) {
        if (node == null) {
            return null;
        }

        Map<Node, Node> map = new HashMap<>();
        return dfs(map, node);
    }

    private static Node dfs(Map<Node, Node> map, Node node) {
        if (map.containsKey(node)) {
            return map.get(node);
        }

        Node copied = new Node(node.val);
        map.put(node, copied);
        for (Node neighbor : node.neighbors) {
            copied.neighbors.add(dfs(map, neighbor));
        }
        return copied;
    }

    private static class Node {
        public int val;
        public List<Node> neighbors;
        public Node(int newVal) {
            val = newVal;
            neighbors = new ArrayList<Node>();
        }
    }
}
