package leetcode.Tree;

/**
 * 101. Symmetric Tree.
 * [Tree & Recursion]
 * get left and right as arguments.
 * base case: left == null && right == null, return true
 * base case2: left == null || right == null, return false
 * if left != right, return false
 * else, return recursive(left.right, right.left) && (left.left, right.right)
 * TC: O(n)
 * SC: O(n)
 * @author Jeesub Lee (jeesubl@andrew.cmu.edu)
 */
public class Q101_SymmetricTree {
    public static boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return true;
        }
        return isSymmetric(root.left, root.right);
    }

    private static boolean isSymmetric(TreeNode left, TreeNode right) {
        if (left == null && right == null) {
            return true;
        }
        if (left == null || right == null) {
            return false;
        }

        if (left.val != right.val) {
            return false;
        }

        return isSymmetric(left.left, right.right) && isSymmetric(left.right, right.left);
    }
}
