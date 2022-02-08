package leetcode.Tree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * 515. Find Largest Value in Each Tree Row.
 * [Tree & BFS]
 * BFS & store max value in each layer.
 * TC: O(n)
 * SC: O(n)
 * @author Jeesub Lee (jeesubl@andrew.cmu.edu)
 */
public class Q515_FindLargestValueInEachTreeRow {
    public static List<Integer> largestValues(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        List<Integer> list = new ArrayList<>();
        Deque<TreeNode> deque = new ArrayDeque<>();
        deque.add(root);
        while (!deque.isEmpty()) {
            int size = deque.size();
            int max = Integer.MIN_VALUE;
            while (size > 0) {
                TreeNode curr = deque.remove();
                if (curr.left != null) {
                    deque.add(curr.left);
                }
                if (curr.right != null) {
                    deque.add(curr.right);
                }
                max = Math.max(max, curr.val);
                size--;
            }
            list.add(max);
        }
        return list;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode("1, 2, 3, null, null, 4, null, null, 6, 5, null, null, null");
        System.out.println(largestValues(root));
        // output: [1, 6, 5]
    }
}
