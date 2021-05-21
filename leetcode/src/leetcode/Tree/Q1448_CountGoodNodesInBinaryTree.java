package leetcode.Tree;

public class Q1448_CountGoodNodesInBinaryTree {

    private static int count = 0;

    public static int goodNodes(TreeNode root) {
        countGoodNodes(root, root.val);
        return count;
    }

    private static void countGoodNodes(TreeNode node, int maxSoFar) {
        if (node == null) {
            return;
        }

        if (node.val >= maxSoFar) {
            count++;
        }

        maxSoFar = Math.max(maxSoFar, node.val);
        countGoodNodes(node.left, maxSoFar);
        countGoodNodes(node.right, maxSoFar);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(1);
        root.left.left = new TreeNode(3);
        root.right = new TreeNode(4);
        root.right.left = new TreeNode(1);
        root.right.right = new TreeNode(5);
        System.out.println(goodNodes(root));
        // output: 4
    }

}
