package leetcode.Tree;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 1110. Delete Nodes And Return Forest.
 * [Tree & DFS & Recursion]
 * Put to_delete into a HashSet<Integer>.
 * if we need to delete curr,
 *     need to cut the connections
 *     1. (curr.parent, curr) // cutting by returning null
 *     2. (curr, curr.left)  // curr.left = null
 *     3. (curr, curr.right) // curr.right = null
 * if we don't need to delete curr,
 *     if curr is a new root,
 *         add curr to the result list
 * call the recursion with curr.left & curr.right
 * TC: O(n)
 * SC: O(h)
 * @author Jeesub Lee (jeesubl@andrew.cmu.edu)
 */
public class Q1110_DeleteNodesAndReturnForest {
    public static List<TreeNode> delNodes(TreeNode root, int[] to_delete) {
        Set<Integer> set = new HashSet<>();
        for (int num : to_delete) {
            set.add(num);
        }

        List<TreeNode> list = new ArrayList<>();
        dfs(set, list, root, true);
        return list;
    }

    private static TreeNode dfs(Set<Integer> set, List<TreeNode> list, TreeNode curr, boolean isNewRoot) {
        if (curr == null) {
            return null;
        }

        if (set.contains(curr.val)) {
            dfs(set, list, curr.left, true);
            dfs(set, list, curr.right, true);
            curr.left = null;
            curr.right = null;
            return null;
        } else {
            if (isNewRoot) {
                list.add(curr);
            }
            curr.left = dfs(set, list, curr.left, false);
            curr.right = dfs(set, list, curr.right, false);
            return curr;
        }
    }
}
