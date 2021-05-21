package leetcode.Tree;

/**
 * Q285. Inorder Successor in BST.
 * 1. Go to find p
 *    while going down, if we need to go to the left, curr is a successor candidate.
 * 2. After find p
 *    If p has right child, we need to dig into it.
 *    Get the most left deepest child of the left child. It is a successor.
 * -> We can merge one and two.
 * If curr < target, go to the left. Curr is a successor candidate.
 * Else, go to the right.
 * @author Jeesub Lee (jeesubl@andrew.cmu.edu)
 */
public class Q285_InorderSuccessorInBST {

    public static TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        TreeNode successor = null;
        TreeNode curr = root;
        while (curr != null) {
            if (curr.val > p.val) {
                successor = curr;
                curr = curr.left;
            } else {
                curr = curr.right;
            }
        }
        return successor;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(2);
        root.left = new TreeNode(1);
        root.right = new TreeNode(3);
        System.out.println(inorderSuccessor(root, root.left).val);
        // output: 2
    }

}
