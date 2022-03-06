package leetcode.Tree;

/**
 * 1644. Lowest Common Ancestor of a Binary Tree II
 * [Tree & Recursion]
 * In a helper method,
 * if curr is one of the targets, return curr
 * if result(left) != null && result(right) != null, return curr
 * if result(left) == null && result(right) == null, return null
 * else, return non-null result from a child
 *
 * if the result from helper method is p,
 *     check if q is one of the descendant of p
 * if the result from helper method is q,
 *     check if p is one of the descendant of q
 * TC: O(n)
 * SC: O(h)
 * @author Jeesub Lee (jeesubl@andrew.cmu.edu)
 */
public class Q1644_LowestCommonAncestorOfABinaryTreeII {
    public static TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        TreeNode result = findLCA(root, p, q);
        if (result == p) {
            if (findLCA(p.left, p, q) != null || findLCA(p.right, p, q) != null) {
                return p;
            } else {
                return null;
            }
        }
        if (result == q) {
            if (findLCA(q.left, p, q) != null || findLCA(q.right, p, q) != null) {
                return q;
            } else {
                return null;
            }
        }
        return result;
    }

    private static TreeNode findLCA(TreeNode node, TreeNode p, TreeNode q) {
        if (node == null) {
            return null;
        }

        if (node == p || node == q) {
            return node;
        }

        TreeNode fromLeft = findLCA(node.left, p, q);
        TreeNode fromRight = findLCA(node.right, p, q);
        if (fromLeft != null && fromRight != null) {
            return node;
        }
        if (fromLeft == null && fromRight == null) {
            return null;
        }
        return fromLeft != null ? fromLeft : fromRight;
    }
}
