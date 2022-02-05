package leetcode.Tree;

/**
 * [Tree & DFS]
 * acc = acc * 10 + curr.val
 * if (curr.left == null && curr.right == null)
 *     totalSum += acc
 * if (left!= null)
 *     dfs(left, acc)
 * if (right != null)
 *     dfs(right, acc)
 * TC: O(n)
 * SC: O(h)
 * @author Jeesub Lee (jeesubl@andrew.cmu.edu)
 */
public class Q129_SumRootToLeafNumbers {
    private static int totalSum;

    public static int sumNumbers(TreeNode root) {
        totalSum = 0;
        dfs(root, 0);
        return totalSum;
    }

    private static void dfs(TreeNode curr, int acc) {
        acc = acc * 10 + curr.val;
        if (curr.left == null && curr.right == null) {
            totalSum += acc;
        }
        if (curr.left != null) {
            dfs(curr.left, acc);
        }
        if (curr.right != null) {
            dfs(curr.right, acc);
        }
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode("1, 2, null, null, null");
        System.out.println(sumNumbers(root));
        // output: 12
    }
}
