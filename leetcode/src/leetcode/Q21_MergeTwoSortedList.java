package leetcode;
/**
 * Q21. Merge Two Sorted List.
 * use two pointers and new ListNode
 * compare the value of two nodes
 * if l1.val < l2.val, add l1 to new ListNode and l1 = l1.next
 * while l1 != null & l2 != null
 * @author jeesublee
 */
public class Q21_MergeTwoSortedList {

	public static ListNode mergeTwoList(ListNode l1, ListNode l2) {
		ListNode dummyHead = new ListNode(-1);
		ListNode curr = dummyHead;
		while (l1 != null && l2 != null) {
			if (l1.val < l2.val) {
				curr.next = new ListNode(l1.val);
				curr = curr.next;
				l1 = l1.next;
			} else {
				curr.next = new ListNode(l2.val);
				curr = curr.next;
				l2 = l2.next;
			}
		}
		while (l1 != null) {
			curr.next = new ListNode(l1.val);
			curr = curr.next;
			l1 = l1.next;
		}
		while (l2 != null) {
			curr.next = new ListNode(l2.val);
			curr = curr.next;
			l2 = l2.next;
		}
		return dummyHead.next;
	}

	private static class ListNode {
		int val;
		ListNode next;
		ListNode(int val) {
			this.val = val;
		}
		@Override
		public String toString() {
			StringBuilder sb = new StringBuilder();
			ListNode curr = this;
			while (curr != null) {
				sb.append(curr.val);
				sb.append(" - ");
				curr = curr.next;
			}
			sb.setLength(sb.length() - 2);
			return sb.toString();
		}
	}

	public static void main(String[] args) {
		ListNode l1 = new ListNode(1);
		l1.next = new ListNode(2);
		l1.next.next = new ListNode(4);
		ListNode l2 = new ListNode(1);
		l2.next = new ListNode(3);
		l2.next.next = new ListNode(4);
		System.out.print(mergeTwoList(l1, l2));
		// output: 1 - 1 - 2 - 3 - 4 - 4
	}

}
