package leetcode.Tree;

/**
 * 108. Convert Sorted Array to Binary Search Tree.
 * [Tree & Recursion]
 * current node = new node(nums[mid])
 * node.left = recursive call(left part of nums)
 * node.right = recursive call(right part of nums)
 * base case: length is 0
 * @author Jeesub Lee (jeesubl@andrew.cmu.edu)
 */
public class Q108_ConvertSortedArrayToBinarySearchTree {
    public static TreeNode sortedArrayToBST(int[] nums) {
        return buildBST(nums, 0, nums.length - 1);
    }

    private static TreeNode buildBST(int[] nums, int left, int right) {
        if (left > right) {
            return null;
        }

        int mid = left + (right - left) / 2;
        TreeNode curr = new TreeNode(nums[mid]);
        if (left <= mid - 1) {
            curr.left = buildBST(nums, left, mid - 1);
        }
        if (mid + 1 <= right) {
            curr.right = buildBST(nums, mid + 1, right);
        }
        return curr;
    }

    public static void main(String[] args) {
        int[] nums = {0, 1, 2, 3, 4, 5, 6};
        System.out.println(sortedArrayToBST(nums));
    }
}
