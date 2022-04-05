package leetcode.Tree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 545. Boundary of Binary Tree.
 * [Tree]
 * root.left side: preorder traversal
 * root.left side: preorder traversal, but go to the right child first and reverse it
 * TC: O(n)
 * SC: O(n)
 * @author Jeesub Lee (jeesubl@andrew.cmu.edu)
 */
public class Q545_BoundaryOfBinaryTree {
    public List<Integer> boundaryOfBinaryTree(TreeNode root) {
        List<Integer> leftTraversal = new ArrayList<>();
        leftTraversal.add(root.val);
        if (root.left != null) {
            leftTraverse(leftTraversal, root.left, true);
        }
        List<Integer> rightTraversal = new ArrayList<>();
        if (root.right != null) {
            rightTraverse(rightTraversal, root.right, true);
        }
        Collections.reverse(rightTraversal);
        leftTraversal.addAll(rightTraversal);
        return leftTraversal;
    }

    private void leftTraverse(List<Integer> list, TreeNode node, boolean isMostLeft) {
        if (isMostLeft || (node.left == null && node.right == null)) {
            list.add(node.val);
        }
        if (node.left != null) {
            leftTraverse(list, node.left, isMostLeft);
        }
        if (node.right != null) {
            if (node.left == null) {
                leftTraverse(list, node.right, isMostLeft);
            } else {
                leftTraverse(list, node.right, false);
            }
        }
    }

    private void rightTraverse(List<Integer> list, TreeNode node, boolean isMostRight) {
        if (isMostRight || (node.left == null && node.right == null)) {
            list.add(node.val);
        }
        if (node.right != null) {
            rightTraverse(list, node.right, isMostRight);
        }
        if (node.left != null) {
            if (node.right == null) {
                rightTraverse(list, node.left, isMostRight);
            } else {
                rightTraverse(list, node.left, false);
            }
        }
    }
}
