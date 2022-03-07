package leetcode.Tree;

/**
 * 333. Largest BST Subtree.
 * [Tree]
 * We need information <boolean if child is BST, int smallest, int largest> from children to determine if current node can be a root of BST or not.
 * Curr is a root of BST if following conditions are true.
 *     1. left is null or BST
 *     2. if left is not null, left is BST && left's largest < curr's val
 *     3. right is null or BST
 *     4. if right is not null, right is BST && curr's val < right's smallest
 *
 * Optimization)
 *     If curr can be a root of BST, calculate the result.
 *     Else, return the <invalid min, invalid max, max count from child> // parent never can be a BST
 *     Null data can be <MAX, MIN, 0> // always valid
 *     Invalid data can be <MIN, MAX, X> // alwyas invalid
 *
 * TC: O(n)
 * SC: O(n)
 * @author Jeesub Lee (jeesubl@andrew.cmu.edu)
 */
public class Q333_LargestBSTSubtree {
    private static final Data NULL_DATA = new Data(Integer.MAX_VALUE, Integer.MIN_VALUE, 0);
    public static int largestBSTSubtree(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return dfs(root).count;
    }

    private static Data dfs(TreeNode node) {
        if (node == null) {
            return NULL_DATA;
        }

        Data fromLeft = dfs(node.left);
        Data fromRight = dfs(node.right);
        if (fromLeft.largest < node.val && node.val < fromRight.smallest) {
            int smallest = node.left == null ? node.val : fromLeft.smallest;
            int largest = node.right == null ? node.val : fromRight.largest;
            return new Data(smallest, largest, fromLeft.count + fromRight.count + 1);
        }
        return new Data(Integer.MIN_VALUE, Integer.MAX_VALUE, Math.max(fromLeft.count, fromRight.count));
    }

    private static class Data {
        int smallest;
        int largest;
        int count;

        private Data(int newSmallest, int newLargest, int newCount) {
            smallest = newSmallest;
            largest = newLargest;
            count = newCount;
        }
    }
}
