package leetcode.Tree;

/**
 * 109. Convert Sorted List to Binary Search Tree.
 * [Tree]
 * Build TreeNodes by inorder traversal.
 * 1. Find the total number of nodes.
 * 2. Middle one is a root, left are left children, and right are right children.
 * 3. Recursively build left first, mid next, and right later to make nodes in inorder.
 * TC: O(n)
 * SC: O(logn)
 * @author Jeesub Lee (jeesubl@andrew.cmu.edu)
 */
public class Q109_ConvertSortedListToBinarySearchTree {
    private ListNode currListNode;

    public TreeNode sortedListToBST(ListNode head) {
        currListNode = head;

        int count = 0;
        ListNode walker = head;
        while (walker != null) {
            count++;
            walker = walker.next;
        }

        return buildTreeNodes(0, count - 1);
    }

    private TreeNode buildTreeNodes(int left, int right) {
        if (left > right) {
            return null;
        }

        int mid = left + (right - left) / 2;
        TreeNode leftChild = buildTreeNodes(left, mid - 1);
        TreeNode currTreeNode = new TreeNode(currListNode.val);
        currListNode = currListNode.next;
        TreeNode rightChild = buildTreeNodes(mid + 1, right);

        currTreeNode.left = leftChild;
        currTreeNode.right = rightChild;
        return currTreeNode;
    }

    private static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
    }
}