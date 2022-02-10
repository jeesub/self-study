package leetcode.Tree;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * 103. Binary Tree Zigzag Level Order Traversal.
 * [Tree & BFS]
 * Use two stacks.
 * if curr node is on an even layer, put left child first and put right child later.
 * if curr node is on an odd layer, put right child first and put left child later.
 * TC: O(n)
 * SC: O(n)
 * @author Jeesub Lee (jeesubl@andrew.cmu.edu)
 */
public class Q103_BinaryTreeZigzagLevelOrderTraversal {
    public static List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        List<List<Integer>> result = new ArrayList<>();
        Deque<TreeNode> deque = new LinkedList<>();
        deque.add(root);
        boolean isEven = true;
        while (!deque.isEmpty()) {
            List<Integer> currList = new ArrayList<>();
            Deque<TreeNode> nextDeque = new LinkedList<>();
            while (!deque.isEmpty()) {
                TreeNode curr = deque.removeLast();
                currList.add(curr.val);
                if (isEven && curr.left != null) {
                    nextDeque.add(curr.left);
                }
                if (isEven && curr.right != null) {
                    nextDeque.add(curr.right);
                }
                if (!isEven && curr.right != null) {
                    nextDeque.add(curr.right);
                }
                if (!isEven && curr.left != null) {
                    nextDeque.add(curr.left);
                }
            }
            deque = nextDeque;
            result.add(currList);
            isEven = !isEven;
        }
        return result;
    }
}
