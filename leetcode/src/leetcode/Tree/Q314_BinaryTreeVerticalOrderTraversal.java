package leetcode.Tree;

import java.util.*;

/**
 * 314. Binary Tree Vertical Order Traversal.
 * [Tree & BFS]
 * BFS and build a map<column, value>. Also tracking the min column and max column
 * Build a list accordingly.
 * TC: O(n)
 * SC: O(n)
 * @author Jeesub Lee (jeesubl@andrew.cmu.edu)
 */
public class Q314_BinaryTreeVerticalOrderTraversal {
    public static List<List<Integer>> verticalOrder(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }

        Map<Integer, List<Integer>> map = new HashMap<>();
        Deque<Node> deque = new LinkedList<>();
        deque.add(new Node(root, 0));
        int minColumn = 0;
        int maxColumn = 0;
        while (!deque.isEmpty()) {
            Node node = deque.remove();
            TreeNode treeNode = node.treeNode;
            if (!map.containsKey(node.column)) {
                map.put(node.column, new ArrayList<>());
            }
            map.get(node.column).add(treeNode.val);
            if (treeNode.left != null) {
                deque.add(new Node(treeNode.left, node.column - 1));
                minColumn = Math.min(minColumn, node.column - 1);
            }
            if (treeNode.right != null) {
                deque.add(new Node(treeNode.right, node.column + 1));
                maxColumn = Math.max(maxColumn, node.column + 1);
            }
        }

        List<List<Integer>> result = new ArrayList<>();
        for (int i = minColumn; i <= maxColumn; i++) {
            if (!map.containsKey(i)) {
                continue;
            }
            result.add(map.get(i));
        }

        return result;
    }

    private static class Node {
        private TreeNode treeNode;
        private int column;

        private Node(TreeNode newTreeNode, int newColumn) {
            treeNode = newTreeNode;
            column = newColumn;
        }
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode("3, 9, 4, null, null, 0, null, null, 8, 1, null, null, 7, null, null");
        System.out.println(verticalOrder(root));
        // output: [[4], [9], [3, 0, 1], [8], [7]]
    }
}
