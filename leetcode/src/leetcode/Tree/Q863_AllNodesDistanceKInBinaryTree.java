package leetcode.Tree;

import java.util.ArrayList;
import java.util.List;

/**
 * 863. All Nodes Distance K in Binary Tree
 * 1. Find the target node.
 *    If found it, start searching the children nodes.
 *    And return the distance to the parent node.
 * 2. If the previous node is left, search right children.
 *    If the previous node is right, search left children.
 * TC: O(n), where n is the number of nodes
 * SC: O(n), where n is the number of nodes
 * @author Jeesub Lee (jeesubl@andrew.cmu.edu)
 */
public class Q863_AllNodesDistanceKInBinaryTree {

    public static List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
        List<Integer> list = new ArrayList<>();
        findTarget(root, target, k, list);
        return list;
    }

    private static int findTarget(TreeNode node, TreeNode target, int k, List<Integer> list) {
        if (node == null) {
            return Integer.MAX_VALUE;
        }
        if (node.val == target.val) {
            search(node, k, list);
            return 1;
        }

        int leftDistance = findTarget(node.left, target, k, list);
        int rightDistance = findTarget(node.right, target, k, list);

        int distance = Math.min(leftDistance, rightDistance);

        if (distance == k) {
            list.add(node.val);
            return Integer.MAX_VALUE;
        }

        if (leftDistance != distance) {
            search(node.left, k - distance - 1, list);
        }
        if (rightDistance != distance) {
            search(node.right, k - distance - 1, list);
        }
        return distance == Integer.MAX_VALUE ? Integer.MAX_VALUE : distance + 1;
    }

    private static void search(TreeNode node, int leftK, List<Integer> list) {
        if (node == null) {
            return;
        }
        if (leftK == 0) {
            list.add(node.val);
            return;
        }
        search(node.left, leftK - 1, list);
        search(node.right, leftK - 1, list);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode("3,5,6,null,null,2,7,null,null,4,null,null,1,0,null,null,8,null,null");
        TreeNode target = root.left;
        int k = 2;
        System.out.println(distanceK(root, target, k));
        // output: [7, 4, 1]
    }

}
