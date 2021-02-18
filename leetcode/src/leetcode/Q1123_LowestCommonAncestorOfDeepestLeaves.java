package leetcode;

/**
 * Q1123. Lowest Common Ancestor of Deepest Leaves.
 * @author Jeesub Lee (jeesubl@andrew.cmu.edu)
 */
public class Q1123_LowestCommonAncestorOfDeepestLeaves {

	public static TreeNode getLCA(TreeNode root) {
		return exploreTree(root).node;
	}

	public static Pair exploreTree(TreeNode node) {
		if (node == null) {
			return new Pair(null, 0);
		}

		Pair left = exploreTree(node.left);
		Pair right = exploreTree(node.right);

		if (left.depth > right.depth) {
			return new Pair(left.node, left.depth + 1);
		}
		if (left.depth < right.depth) {
			return new Pair(right.node, right.depth + 1);
		}
		return new Pair(node, left.depth + 1);
	}

	public static class Pair {
		TreeNode node;
		int depth;

		public Pair(TreeNode node, int depth) {
			this.node = node;
			this.depth = depth;
		}
	}

	public static class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;
		TreeNode() {}
		TreeNode(int val) { this.val = val; }
		TreeNode(int val, TreeNode left, TreeNode right) {
			this.val = val;
			this.left = left;
			this.right = right;
		}
	}

	public static void main(String[] args) {
		TreeNode root = new TreeNode(3);
		root.left = new TreeNode(5);
		root.left.left = new TreeNode(6);
		root.left.right = new TreeNode(2);
		root.left.right.left = new TreeNode(7);
		root.left.right.right = new TreeNode(4);
		root.right = new TreeNode(1);
		root.right.left = new TreeNode(0);
		root.right.right = new TreeNode(8);

		System.out.println(getLCA(root).val);
		// output: 2
	}
}
