package leetcode.Tree;

/**
 * 98. Validate Binary Search Tree
 * [Tree & Recursion]
 * valid condition
 * 1. all left children is smaller than curr
 * 2. all right children is larger than curr
 *
 * base case: curr == null ? return true
 * return false if curr.val <= min || curr.val >= max
 * return true if isValid(left, min, curr.val) && isValid(right, curr.val, max)
 * TC: O(n)
 * SC: O(n)
 * @author Jeesub Lee (jeesubl@andrew.cmu.edu)
 */
public class Q98_ValidateBinarySearchTree {
    public static boolean isValidBST(TreeNode root) {
        return isValid(root.left, null, root.val)
                && isValid(root.right, root.val, null);
    }

    private static boolean isValid(TreeNode curr, Integer min, Integer max) {
        if (curr == null) {
            return true;
        }

        if ((min != null && curr.val <= min) || (max != null && curr.val >= max)) {
            return false;
        }

        return isValid(curr.left, min, curr.val) && isValid(curr.right, curr.val, max);
    }
}
