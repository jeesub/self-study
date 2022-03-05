package leetcode.Tree;

/**
 * 865. Smallest Subtree with all the Deepest Nodes.
 * [Tree & Recursion]
 * base case: curr == null, return null & -1
 * if (left.dist == right.dist), return curr & dist + 1
 * if (left.dist > right.dist), return left & left.dist + 1
 * if (left.dist < right.dist), return right & right.dist + 1
 * TC: O(n)
 * SC: O(n)
 * @author Jeesub Lee (jeesubl@andrew.cmu.edu)
 */
public class Q865_SmallestSubtreeWithAllTheDeepestNodes {
    private static final Pair NULL = new Pair(null, -1);

    public static TreeNode subtreeWithAllDeepest(TreeNode root) {
        return getSubtree(root).treeNode;
    }

    private static Pair getSubtree(TreeNode curr) {
        if (curr == null) {
            return NULL;
        }

        Pair leftPair = getSubtree(curr.left);
        Pair rightPair = getSubtree(curr.right);
        if (leftPair.dist == rightPair.dist) {
            return new Pair(curr, leftPair.dist + 1);
        } else if (leftPair.dist > rightPair.dist) {
            return new Pair(leftPair.treeNode, leftPair.dist + 1);
        } else {
            return new Pair(rightPair.treeNode, rightPair.dist + 1);
        }
    }

    private static class Pair {
        TreeNode treeNode;
        int dist;

        private Pair(TreeNode newTreeNode, int newDist) {
            treeNode = newTreeNode;
            dist = newDist;
        }
    }
}
