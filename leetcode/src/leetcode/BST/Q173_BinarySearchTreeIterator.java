package leetcode.Tree;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 173. Binary Search Tree Iterator.
 * [Tree & Stack]
 * Put every left line into a stack.
 * next(): pop from the stack. If it has a right child, put every left line into a stack.
 * @author Jeesub Lee (jeesubl@andrew.cmu.edu)
 */
public class Q173_BinarySearchTreeIterator {
    private Deque<TreeNode> deque = new ArrayDeque<>();

    public Q173_BinarySearchTreeIterator(TreeNode root) {
        pushLeft(root);
    }

    /*
    TC: O(n), amortized O(1)
    */
    public int next() {
        TreeNode curr = deque.removeLast();
        pushLeft(curr.right);
        return curr.val;
    }

    /*
    TC: O(1)
    */
    public boolean hasNext() {
        return !deque.isEmpty();
    }

    private void pushLeft(TreeNode node) {
        while (node != null) {
            deque.add(node);
            node = node.left;
        }
    }
}
