package leetcode.Tree;

/**
 * [Tree & DFS]
 * Inorder traverse.
 * If curr < low, we don't have to go further left.
 * If curr > high, stop the traversal.
 * TC: O(n)
 * SC: O(n)
 *
 * [Tree & Recursion]
 * base case: curr == null, return 0
 * if (curr > high), return recursive left recursion
 * if (curr < low), return right recursion
 * else, return left recursion + right recursion + curr.val
 * TC: O(n)
 * SC: O(n)
 * @author Jeesub Lee (jeesubl@andrew.cmu.edu)
 */
public class Q938_RangeSumOfBST {
    public static int rangeSumBST(TreeNode root, int low, int high) {
        if (root == null) {
            return 0;
        }

        if (root.val > high) {
            return rangeSumBST(root.left, low, high);
        }
        if (root.val < low) {
            return rangeSumBST(root.right, low, high);
        }
        return root.val
                + rangeSumBST(root.left, low, high)
                + rangeSumBST(root.right, low, high);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode("10, 5, 3, null, null, 7, null, null, 15, null, 18, null, null");
        System.out.println(rangeSumBST(root, 7, 15));
        // output: 32
    }
}
