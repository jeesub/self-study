package leetcode.BST;

/**
 * @author Jeesub Lee (jeesubl@andrew.cmu.edu)
 */
public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int newVal) {
        val = newVal;
    }

    TreeNode(int[] values) {
        this.val = values[0];
        for (int i = 1; i < values.length; i++) {
            addNode(this, values[i]);
        }
    }

    private void addNode(TreeNode node, int newVal) {
        if (node.val == newVal) {
            throw new RuntimeException("It can have unique values only");
        }
        if (node.val < newVal) {
            if (node.right == null) {
                node.right = new TreeNode(newVal);
            } else {
                addNode(node.right, newVal);
            }
        } else {
            if (node.left == null) {
                node.left = new TreeNode(newVal);
            } else {
                addNode(node.left, newVal);
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("preorder: [");
        preorderTraverse(this, sb);
        sb.setLength(sb.length() - 2);
        sb.append("], inorder: [");
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
