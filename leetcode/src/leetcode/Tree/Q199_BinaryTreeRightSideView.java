package leetcode.Tree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * Q199. Binary Tree Right Side View.
 * [Tree & BFS]
 * BFS levels one by one from right to left.
 * In every level, add the first node to the result list.
 * TC: O(n), where n is the number of nodes
 * SC: O(d), where d is the diameter of a tree
 * @author Jeesub Lee (jeesubl@andrew.cmu.edu)
 */
public class Q199_BinaryTreeRightSideView {

    public static List<Integer> rightSideView(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }

        List<Integer> result = new ArrayList<>();
        Deque<TreeNode> deque = new ArrayDeque<>();
        deque.add(root);
        while (!deque.isEmpty()) {
            result.add(deque.peek().val);
            int count = deque.size();
            while (count > 0) {
                TreeNode curr = deque.remove();
                if (curr.right != null) {
                    deque.add(curr.right);
                }
                if (curr.left != null) {
                    deque.add(curr.left);
                }
                count--;
            }
        }

        return result;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode("1,2,null,5,6,null,null,null,3,null,4,null,null");
        System.out.println(rightSideView(root));
        // output: [1, 3, 4, 6]
    }

}
