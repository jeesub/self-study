package leetcode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Q1028. Recover a Tree From Preorder Traversal.
 * 1. read Node from input String
 *  - pointer
 *  - read a number of dash
 *  - read numbers
 * 
 * 2. reconstruct TreeNode by using Node from (1)
 *  - Create a Node from (1)
 *  - get a parent TreeNode of the node we created
 *  - the parent Node is in a Stack
 *  - stack size <= a number of dash
 *  - put the Node into the stack
 * 
 * @author jeesublee
 */
public class Q1028_RecoverATreeFromPreorderTraversal {

	public static TreeNode recoverFromPreorder(String S) {
		Deque<TreeNode> stack = new ArrayDeque<>();

		for (int ptr = 0; ptr < S.length();) {
			int depth = 0;
			int value = 0;
			while (S.charAt(ptr) == '-') {
				depth++;
				ptr++;
			}
			while (ptr < S.length() && S.charAt(ptr) != '-') {
				value = value * 10 + Integer.parseInt(S.charAt(ptr) + "");
				ptr++;
			}

			TreeNode newNode = new TreeNode(value);
			while (stack.size() > depth) {
				stack.removeLast();
			}
			if (!stack.isEmpty()) {
				TreeNode parentNode = stack.peekLast();
				if (parentNode.left == null) {
					parentNode.left = newNode;
				} else {
					parentNode.right = newNode;
				}
			}
			stack.addLast(newNode);
		}

		return stack.peekFirst();
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

		@Override
		public String toString() {
			if (this.left == null && this.right == null) {
				return Integer.toString(this.val);
			}
			return this.val + " - " + this.left + " - " + this.right;
		}
	}
	public static void main(String[] args) {
		String S = "1-2--3--4-5--6--7";
		System.out.println(recoverFromPreorder(S));
		// Output: [1,2,5,3,4,6,7]
	}
}
