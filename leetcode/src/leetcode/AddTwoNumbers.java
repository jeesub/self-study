package leetcode;

public class AddTwoNumbers {

	public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
		ListNode dummyHead = new ListNode(0);
		ListNode curr = dummyHead;
		ListNode node1 = l1;
		ListNode node2 = l2;
		int carry = 0;

		while (node1 != null || node2 != null) {
			int val1 = (node1 != null) ? node1.val : 0;
			int val2 = (node2 != null) ? node2.val : 0;
			int sum = val1 + val2 + carry;
			carry = sum / 10;

			curr.next = new ListNode(sum % 10);
			curr = curr.next;

			if (node1 != null) {
				node1 = node1.next;
			}
			if (node2 != null) {
				node2 = node2.next;
			}
		}

		if (carry > 0) {
			curr.next = new ListNode(carry);
		}

		return dummyHead.next;
	}

	public static void main(String[] args) {
		AddTwoNumbers solution = new AddTwoNumbers();

		ListNode l1 = new ListNode(2);
		l1.next = new ListNode(4);
		l1.next.next = new ListNode(3);

		ListNode l2 = new ListNode(5);
		l2.next = new ListNode(6);
		l2.next.next = new ListNode(4);

		ListNode result = solution.addTwoNumbers(l1, l2);
		while (result != null) {
			System.out.print(result.val);
			result = result.next;
		}
	}
	
	public static class ListNode {
		int val;
		ListNode next;
		ListNode() {}
		ListNode(int val) { this.val = val; }
		ListNode(int val, ListNode next) { this.val = val; this.next = next; }
	}

}
