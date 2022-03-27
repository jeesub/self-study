package leetcode.Tree;

/**
 * 979. Distribute Coins in Binary Tree.
 * [Tree & Recursion]
 * 1. curr.val
 * curr.val == 0 ? we need one coin -> extra coin = -1
 * curr.val == 1 ? extra coin = 0
 * curr.val >= 2 ? we have extra coins to distribute
 *
 * 2. balance (it's balanced if all nodes have one coin)
 * curr.balance + left.balance + right.balance == 0 ? nodes under curr (inclusive) are happy.
 * curr.balance + left.balance + right.balance < 0 ? we need coins
 * curr.balance + left.balance + right.balance > 0 ? we have extra coins
 *
 * 3. information curr needs
 *   1. extra coins from children
 *   2. number of moves from children
 *
 * 4. information curr can return
 *   1. extra coins = left extra coins + right extra coins + curr.val - 1
 *   2. number of moves = left moves + right moves + abs(curr extra coins)
 * TC: O(n)
 * SC: O(h)
 * @author Jeesub Lee (jeesubl@andrew.cmu.edu)
 */
public class Q979_DistributeCoinsInBinaryTree {
    private static final int[] NULL = new int[] {0, 0};

    public int distributeCoins(TreeNode root) {
        return getMoves(root)[1];
    }

    private int[] getMoves(TreeNode node) {
        if (node == null) {
            return NULL;
        }

        int[] fromLeft = getMoves(node.left);
        int[] fromRight = getMoves(node.right);
        int extraCoins = fromLeft[0] + fromRight[0] + node.val - 1;
        int moves = fromLeft[1] + fromRight[1] + Math.abs(extraCoins);
        return new int[] {extraCoins, moves};
    }
}
