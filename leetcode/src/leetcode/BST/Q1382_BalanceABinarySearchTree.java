package leetcode.BST;

import java.util.ArrayList;
import java.util.List;

/**
 * 1382. Balance a Binary Search Tree.
 * Inorder traverse and build a ascending order list<TreeNode>.
 * mid is a new root.
 * root.left = mid(0..mid - 1)
 * root.right = (mid + 1.. last)
 * @author Jeesub Lee (jeesubl@andrew.cmu.edu)
 */
public class Q1382_BalanceABinarySearchTree {
    public static TreeNode balanceBST(TreeNode root) {
        List<TreeNode> list = new ArrayList<>();
        inorderTraverse(list, root);
        return buildBalanceBST(list, 0, list.size() - 1);
    }

    private static void inorderTraverse(List<TreeNode> list, TreeNode node) {
        if (node == null) {
            return;
        }
        inorderTraverse(list, node.left);
        list.add(node);
        inorderTraverse(list, node.right);
    }

    private static TreeNode buildBalanceBST(List<TreeNode> list, int start, int end) {
        if (start > end) {
            return null;
        }
        if (start == end) {
            TreeNode leaf = list.get(start);
            leaf.left = null;
            leaf.right = null;
            return leaf;
        }

        int mid = start + (end - start) / 2;
        TreeNode root = list.get(mid);
        root.left = buildBalanceBST(list, start, mid - 1);
        root.right = buildBalanceBST(list, mid + 1, end);
        return root;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(new int[]{1, 2, 3, 4});
        System.out.println(balanceBST(root));
    }
}
