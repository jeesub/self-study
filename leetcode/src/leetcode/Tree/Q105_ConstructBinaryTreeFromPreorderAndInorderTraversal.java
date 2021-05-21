package leetcode.Tree;

/**
 * Q105. Construct Binary Tree from Preorder and Inorder Traversal.
 * preorder = [3 || 9 || 20, 15, 7]
 *             ^ root
 *                  ^ left child (in indorder, it's on the left side of 3)
 *                       ^ right children (it's on the right side of left deepest child)
 * inorder =  [9 || 3 || 15, 20, 7]
 *                  ^ root
 *             ^ left deepest child (the first element of the inorder)
 * @author Jeesub Lee (jeesubl@andrew.cmu.edu)
 */
public class Q105_ConstructBinaryTreeFromPreorderAndInorderTraversal {

    public static TreeNode buildTree(int[] preorder, int[] inorder) {
        return buildTree(preorder, 0, inorder, 0, inorder.length - 1);
    }

    private static TreeNode buildTree(int[] pre, int preIdx, int[] in, int inStart, int inEnd) {
        if (inStart > inEnd) {
            return null;
        }

        TreeNode root = new TreeNode(pre[preIdx]);

        int inIdx = find(in, pre[preIdx]);
        root.left = buildTree(pre, preIdx + 1, in, inStart, inIdx - 1);
        root.right = buildTree(pre, preIdx + inIdx - inStart + 1, in, inIdx + 1, inEnd);

        return root;
    }

    private static int find(int[] arr, int value) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == value) {
                return i;
            }
        }
        throw new RuntimeException("Wrong preorder and inorder");
    }

    public static void main(String[] args) {
        int[] preorder = {3, 9, 20, 15, 7};
        int[] inorder = {9, 3, 15, 20, 7};
        System.out.println(buildTree(preorder, inorder));
    }

}
