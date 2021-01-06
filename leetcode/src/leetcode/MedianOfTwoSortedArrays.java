package leetcode;
/**
 * 
 * Binary Search
 * 
 * private class: Find the k-th number
 * 
 * ex) k = 4
 * nums1 [1, 3, 8, 12]
 *           ^
 * nums2 [2, 4, 9, 13]
 *           ^
 * 
 * if possible, we will explore from start point to k / 2 - 1
 * if (k / 2 - 1) is out of bound, explore to the last position
 * 
 * If nums1[ptr1] < nums2[ptr2], do recursion with
 * 		new ptr1 = new ptr1 + 1
 * 		new ptr2 = ptr2
 * 		new k is k - length of explored part
 * 
 * @author jeesublee
 *
 */
public class MedianOfTwoSortedArrays {

	public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
		int totalLength = nums1.length + nums2.length;
		int k = totalLength / 2 + 1;
		double res = (double) findKth(nums1, nums2, 0, 0, k);
		if (totalLength % 2 == 0) {
			res += (double) findKth(nums1, nums2, 0, 0, k - 1);
			res /= 2;
		}
		return res;
	}

	private static int findKth(int[] nums1, int[] nums2, int ptr1, int ptr2, int k) {
		if (ptr1 >= nums1.length) {
			return nums2[ptr2 + k - 1];
		}
		if (ptr2 >= nums2.length) {
			return nums1[ptr1 + k - 1];
		}
		if (k == 1) {
			return Math.min(nums1[ptr1], nums2[ptr2]);
		}

		int newPtr1 = Math.min(ptr1 + k / 2 - 1, nums1.length - 1);
		int newPtr2 = Math.min(ptr2 + k / 2 - 1, nums2.length - 1);

		if (nums1[newPtr1] < nums2[newPtr2]) {
			int compared = newPtr1 - ptr1 + 1;
			return findKth(nums1, nums2, newPtr1 + 1, ptr2, k - compared);
		} else {
			int compared = newPtr2 - ptr2 + 1;
			return findKth(nums1, nums2, ptr1, newPtr2 + 1, k - compared);
		}
	}

	public static void main(String[] args) {
		int[] nums1 = {1, 3};
		int[] nums2 = {2, 7};
		System.out.print(findMedianSortedArrays(nums1, nums2));
		// output: 2.5
	}

}
