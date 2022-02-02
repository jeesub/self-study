package leetcode.BST;

/**
 * 270. Closest Binary Search Tree Value.
 * [BST]
 * if (target < curr value),
 *     search left
 * else,
 *     search right
 * TC: O(h)
 * SC: O(1)
 * @author Jeesub Lee (jeesubl@andrew.cmu.edu)
 */
public class Q270_ClosestBinarySearchTreeValue {
    public static int closestValue(TreeNode root, double target) {
        double minDiff = Integer.MAX_VALUE;
        int minVal = Integer.MAX_VALUE;
        while (root != null) {
            if (Math.abs(target - root.val) < minDiff) {
                minDiff = Math.abs(target - root.val);
                minVal = root.val;
            }
            if (target < root.val) {
                root = root.left;
            } else {
                root = root.right;
            }
        }
        return minVal;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(new int[]{4, 2, 1, 3, 5, 6, 7});
        double target = 3.141592;
        System.out.println(closestValue(root, target));
        // output: 3
    }
}
