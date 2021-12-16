package leetcode.Tree;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 958. Check Completeness of a Binary Tree.
 * [Tree]
 * Iterate by layer and put elements in a queue.
 * If an element from the queue is null, every following element should be null.
 * @author Jeesub Lee (jeesubl@andrew.cmu.edu)
 */
public class Q958_CheckCompletenessOfABinaryTree {
    public static boolean isCompleteTree(TreeNode root) {
        Deque<TreeNode> deque = new LinkedList<>();
        deque.add(root);
        while (!deque.isEmpty() && deque.peek() != null) {
            TreeNode curr = deque.remove();
            deque.add(curr.left);
            deque.add(curr.right);
        }

        while (!deque.isEmpty() && deque.peek() == null) {
            deque.remove();
        }

        return deque.isEmpty();
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode("1, 2, 4, null, null, 5, null, null, 3, 6, null, null, null");
        System.out.println(isCompleteTree(root));
        // output: true
        TreeNode root2 = new TreeNode("1, 2, 4, null, null, 5, null, null, 3, null, 7, null, null");
        System.out.println(isCompleteTree(root2));
        // output: false
    }
}
