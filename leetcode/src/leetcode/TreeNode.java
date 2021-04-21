package leetcode;
/**
 * TreeNode.
 * @author Jeesub Lee (jeesubl@andrew.cmu.edu)
 */
public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    public TreeNode() {
        
    }

    public TreeNode(int newVal) {
        val = newVal;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("preorder: [");
        preorderTraverse(this, sb);
        sb.setLength(sb.length() - 2);
        sb.append("] , inorder: [");
        inorderTraverse(this, sb);
        sb.setLength(sb.length() - 2);
        sb.append("]");
        return sb.toString();
    }

    private void preorderTraverse(TreeNode node, StringBuilder sb) {
        if (node == null) {
            return;
        }

        sb.append(node.val);
        sb.append(", ");
        preorderTraverse(node.left, sb);
        preorderTraverse(node.right, sb);
    }

    private void inorderTraverse(TreeNode node, StringBuilder sb) {
        if (node == null) {
            return;
        }

        inorderTraverse(node.left, sb);
        sb.append(node.val);
        sb.append(", ");
        inorderTraverse(node.right, sb);
    }
}
