package leetcode.Tree;
/**
 * Q236. Lowest Common Ancestor of a Binary Tree.
 * [Tree]
 * Recursion.
 * base case 1. curr == null
 *   return null
 * base case 2. curr == p || curr == q
 *   return curr
 *   curr is LCA or one of the nodes.
 * recursive case 1. left == null && right == null
 *   return null
 *   curr's subtree doesn't have target nodes.
 * recursive case 2. left != null && right != null
 *   return curr
 *   curr's left subtree has one and right subtree has one.
 *   It means, curr is LCA.
 * recursive case 3. one of children != null
 *   return non-null node
 *   non-null node might be LCA or one of the targets.
 * @author Jeesub Lee (jeesubl@andrew.cmu.edu)
 */
public class Q236_LowestCommonAncestorOfABinaryTree {

    public static TreeNode lowestCommonAncestor(TreeNode curr, TreeNode p, TreeNode q) {
        // base case 1
        if (curr == null) {
            return null;
        }
        // base case 2
        if (curr.val == p.val || curr.val == q.val) {
            return curr;
        }

        TreeNode leftResult = lowestCommonAncestor(curr.left, p, q);
        TreeNode rightResult = lowestCommonAncestor(curr.right, p, q);

        // recursive case 1
        if (leftResult == null && rightResult == null) {
            return null;
        }
        // recursive case 2
        if (leftResult != null && rightResult != null) {
            return curr;
        }
        // recursive case 3
        return leftResult != null ? leftResult : rightResult;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode("3,5,6,null,null,2,7,null,null,4,null,null,1,0,null,null,8,null,null");
        TreeNode p = new TreeNode(5);
        TreeNode q = new TreeNode(1);
        System.out.println(lowestCommonAncestor(root, p, q).val);
        // output: 3
    }

}
