package leetcode.Tree;

import java.util.ArrayList;
import java.util.List;

/**
 * [Tree & DFS & Recursion]
 * base case: if curr is null, return -1
 * curr node's index = max(left's, right's) + 1
 * list.get(node's index).add(node.val)
 * return node's index + 1
 * TC: O(n)
 * SC: O(n)
 * @author Jeesub Lee (jeesubl@andrew.cmu.edu)
 */
public class Q366_FindLeavesOfBinaryTree {
    public static List<List<Integer>> findLeaves(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        getHeight(result, root);
        return result;
    }

    private static int getHeight(List<List<Integer>> result, TreeNode node) {
        if (node == null) {
            return -1;
        }
        int height = Math.max(getHeight(result, node.left), getHeight(result, node.right)) + 1;
        if (result.size() == height) {
            result.add(new ArrayList<>());
        }
        result.get(height).add(node.val);
        return height;
    }
}
