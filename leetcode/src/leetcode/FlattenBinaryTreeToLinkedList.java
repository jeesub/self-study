package leetcode;
/**
exist left -> get left.mostRight
    left.mostRight.right = node.right
    node.right = node.left
    node.left = null
*/
public class FlattenBinaryTreeToLinkedList {

	public void flatten(TreeNode root) {
		TreeNode curr = root;
		while (curr != null) {
			if (curr.left != null) {
				TreeNode mostRight = getMostRight(curr.left);
				mostRight.right = curr.right;
				curr.right = curr.left;
				curr.left = null;
			}
			curr = curr.right;
		}
	}

	private TreeNode getMostRight(TreeNode node) {
		while (node.right != null) {
			node = node.right;
		}
		return node;
	}

	public static class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode() {
		}

		TreeNode(int val) {
			this.val = val;
		}

		TreeNode(int val, TreeNode left, TreeNode right) {
			this.val = val;
			this.left = left;
			this.right = right;
		}
	}

	public static void main(String[] args) {
		TreeNode root = new TreeNode(1);
		root.left = new TreeNode(2);
		root.left.left = new TreeNode(3);
		root.left.right = new TreeNode(4);
		root.right = new TreeNode(5);
		root.right.right = new TreeNode(6);
		FlattenBinaryTreeToLinkedList solution = new FlattenBinaryTreeToLinkedList();
		solution.flatten(root);
		TreeNode curr = root;
		while (curr != null) {
			System.out.print(curr.val + " ");
			curr = curr.right;
		}
	}

}
