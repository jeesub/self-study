package leetcode;

import java.util.PriorityQueue;

public class MergeKSortedLists {

	public ListNode mergeKLists(ListNode[] lists) {
		PriorityQueue<ListNode> minHeap = new PriorityQueue<>(
				(node1, node2) -> (Integer.compare(node1.val, node2.val)));
		for (ListNode node : lists) {
			if (node == null)
				continue;
			minHeap.offer(node);
		}

		ListNode head = minHeap.poll();
		if (head != null && head.next != null) {
			minHeap.offer(head.next);
		}

		ListNode curr = head;
		while (!minHeap.isEmpty()) {
			ListNode nextNode = minHeap.poll();
			if (nextNode.next != null) {
				minHeap.offer(nextNode.next);
			}
			curr.next = nextNode;
			curr = nextNode;
		}
		return head;
	}

	public static class ListNode {
		int val;
		ListNode next;

		ListNode() {
		}

		ListNode(int val) {
			this.val = val;
		}

		ListNode(int val, ListNode next) {
			this.val = val;
			this.next = next;
		}
	}

	public static void main(String[] args) {
		MergeKSortedLists solution = new MergeKSortedLists();
		ListNode head1 = new ListNode(1);
		head1.next = new ListNode(4);
		head1.next.next = new ListNode(5);
		ListNode head2 = new ListNode(1);
		head2.next = new ListNode(3);
		head2.next.next = new ListNode(4);
		ListNode head3 = new ListNode(2);
		head3.next = new ListNode(6);
		ListNode[] input = {head1, head2, head3};

		ListNode head = solution.mergeKLists(input);
		while (head != null) {
			System.out.print(head.val + " ");
			head = head.next;
		}
		// Output: [1,1,2,3,4,4,5,6]
	}

}
