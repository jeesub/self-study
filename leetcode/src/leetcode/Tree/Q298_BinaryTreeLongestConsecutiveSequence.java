package leetcode.Tree;

/**
 * 298. Binary Tree Longest Consecutive Sequence.
 * [Tree & Recursion & DFS]
 * if curr & curr.child makes consecutive sequence, increase count and move on.
 * TC: O(n)
 * SC: O(n)
 * @author Jeesub Lee (jeesubl@andrew.cmu.edu)
 */
public class Q298_BinaryTreeLongestConsecutiveSequence {
    public static int longestConsecutive(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return dfs(root, 1);
    }

    private static int dfs(TreeNode node, int count) {
        int maxSoFar = count;
        if (node.left != null) {
            if (node.left.val == node.val + 1) {
                maxSoFar = Math.max(maxSoFar, dfs(node.left, count + 1));
            } else {
                maxSoFar = Math.max(maxSoFar, dfs(node.left, 1));
            }
        }
        if (node.right != null) {
            if (node.right.val == node.val + 1) {
                maxSoFar = Math.max(maxSoFar, dfs(node.right, count + 1));
            } else {
                maxSoFar = Math.max(maxSoFar, dfs(node.right, 1));
            }
        }
        return maxSoFar;
    }
}
