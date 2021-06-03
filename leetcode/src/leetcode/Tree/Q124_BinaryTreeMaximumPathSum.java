package leetcode.Tree;
/**
 * Q124. Binary Tree Maximum Path Sum.
 * [DP, Recursion]
 * At a current node, Max is
 * 1. The previous max
 * 2. Itself
 * 3. Left path + itself
 * 4. Right path + itself
 * 5. Left path + Right path + itself
 * We can handle cases 2-5 with
 *   max(left path, 0) + max(right path, 0) + current val.
 *
 * One more information we need is one path max value.
 * One path max is
 * 1. Itself
 * 2. Left path + itself
 * 3. Right path + itself
 * We can handle it with
 *   max(max(left path, 0), max(right path, 0)) + current val.
 *
 * TC: O(n), where n is the number of nodes.
 * SC: O(n), where n is the number of nodes.
 * @author Jeesub Lee (jeesubl@andrew.cmu.edu)
 */
public class Q124_BinaryTreeMaximumPathSum {

    public static int maxPathSum(TreeNode root) {
        return getInfo(root).max;
    }

    private static Info getInfo(TreeNode node) {
        if (node == null) {
            return new Info(Integer.MIN_VALUE, Integer.MIN_VALUE);
        }

        Info leftInfo = getInfo(node.left);
        Info rightInfo = getInfo(node.right);
        int leftPathMax = Math.max(leftInfo.pathMax, 0);
        int rightPathMax = Math.max(rightInfo.pathMax, 0);

        int max = Math.max(leftInfo.max, rightInfo.max);
        max = Math.max(max, leftPathMax + rightPathMax + node.val);

        int pathMax = Math.max(leftPathMax, rightPathMax) + node.val;

        return new Info(max, pathMax);
    }

    private static class Info {
        int max;
        int pathMax;

        private Info(int newMax, int newPathMax) {
            max = newMax;
            pathMax = newPathMax;
        }
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode("-10,9,null,null,20,15,null,null,7,null,null");
        System.out.println(maxPathSum(root));
        // output: 42
    }

}
