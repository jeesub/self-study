package leetcode.Tree;

/**
 * 543. Diameter of Binary Tree.
 * max candidate 1. left + right + curr
 * max candidate 2. max from left or max from right excluding curr
 * compare 1. max from left
 *         2. max from right
 *         3. left + right + curr
 * Send upward 1. max(left + curr, right + curr) 2. max so far
 * @author Jeesub Lee (jeesubl@andrew.cmu.edu)
 */
public class Q543_DiameterOfBinaryTree {
    private static final int[] NULL = new int[]{0, 0};

    public static int diameterOfBinaryTree(TreeNode root) {
        return getLength(root)[1];
    }

    private static int[] getLength(TreeNode root) {
        if (root == null) {
            return NULL;
        }
        int[] left = getLength(root.left);
        int[] right = getLength(root.right);
        int maxSinglePath = Math.max(left[0], right[0]) + 1;
        int currMax = Math.max(left[1], right[1]);
        currMax = Math.max(currMax, left[0] + right[0]);
        return new int[]{maxSinglePath, currMax};
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode("1, 2, 3, 4, null, null, null, 5, null, null, 6, null, 7, null, null");
        System.out.println(diameterOfBinaryTree(root));
        // output: 5
    }
}
