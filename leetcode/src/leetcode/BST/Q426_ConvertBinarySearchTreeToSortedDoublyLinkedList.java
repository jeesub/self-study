package leetcode.BST;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 426. Convert Binary Search Tree to Sorted Doubly Linked List.
 * [Binary Search Tree]
 * Inorder Traverse while keeping prevTreeNode and currTreeNode.
 * prevTreeNode.right = currTreeNode. currTreeNode.left = prevTreeNode.
 * TC: O(n)
 * SC: O(n)
 * @author Jeesub Lee (jeesubl@andrew.cmu.edu)
 */
public class Q426_ConvertBinarySearchTreeToSortedDoublyLinkedList {
    public static TreeNode treeToDoublyList(TreeNode root) {
        if (root == null) {
            return null;
        }
        Deque<TreeNode> deque = new LinkedList<>();
        pushLeft(root, deque);
        TreeNode head = deque.peekLast();

        TreeNode prev = head;
        TreeNode curr = null;
        while (!deque.isEmpty()) {
            curr = deque.removeLast();
            pushLeft(curr.right, deque);

            curr.left = prev;
            prev.right = curr;
            prev = curr;
        }

        head.left = curr;
        curr.right = head;

        return head;
    }

    private static void pushLeft(TreeNode curr, Deque<TreeNode> deque) {
        while (curr != null) {
            deque.add(curr);
            curr = curr.left;
        }
    }
}
