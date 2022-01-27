package leetcode.Tree;

import java.util.*;

/**
 * 987. Vertical Order Traversal of a Binary Tree.
 * BFS with tracking columns. Build a HashMap<column, min heap(node wrapper)>.
 * Node wrapper contains node, row, and column.
 * Node wrappers in min heap are ordered by 1. row 2. column 3. value.
 * Track the left most column while BFS. Use this for building list part.
 * After BFS, build a list based on the map.
 * TC: O(n * log(k))
 * SC: O(n)
 * @author Jeesub Lee (jeesubl@andrew.cmu.edu)
 */
public class Q987_VerticalOrderTraversalOfABinaryTree {
    public static List<List<Integer>> verticalTraversal(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        Map<Integer, PriorityQueue<Node>> map = new HashMap<>();
        Deque<Node> deque = new ArrayDeque<>();
        deque.add(new Node(root, 0, 0));
        int minCol = 0;
        while (!deque.isEmpty()) {
            Node node = deque.remove();
            if (!map.containsKey(node.col)) {
                map.put(node.col, new PriorityQueue<>());
            }
            map.get(node.col).add(node);

            minCol = Math.min(minCol, node.col);

            TreeNode treeNode = node.treeNode;
            if (treeNode.left != null) {
                deque.add(new Node(treeNode.left, node.row + 1, node.col - 1));
            }
            if (treeNode.right != null) {
                deque.add(new Node(treeNode.right, node.row + 1, node.col + 1));
            }
        }

        List<List<Integer>> result = new ArrayList<>();
        while (map.containsKey(minCol)) {
            List<Integer> list = new ArrayList<>();
            PriorityQueue<Node> minHeap = map.get(minCol);
            while (!minHeap.isEmpty()) {
                list.add(minHeap.poll().treeNode.val);
            }
            result.add(list);
            minCol++;
        }

        return result;
    }

    private static class Node implements Comparable<Node> {
        TreeNode treeNode;
        int row;
        int col;

        private Node(TreeNode newTreeNode, int newRow, int newCol) {
            treeNode = newTreeNode;
            row = newRow;
            col = newCol;
        }

        @Override
        public int compareTo(Node other) {
            if (this.row != other.row) {
                return Integer.compare(this.row, other.row);
            }
            if (this.col != other.col) {
                return Integer.compare(this.col, other.col);
            }
            return Integer.compare(this.treeNode.val, other.treeNode.val);
        }
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode("1, 2, 3, null, null, 4, null, null, 5, 6, null, null, 7, null, null");
        System.out.println(verticalTraversal(root));
        // output: [[3], [2], [1, 4, 6], [5], [7]]
    }
}
