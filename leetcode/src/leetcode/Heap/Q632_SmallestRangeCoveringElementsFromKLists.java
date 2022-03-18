package leetcode.Heap;

import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * 632. Smallest Range Covering Elements from K Lists.
 * [Min Heap & Iterator]
 * Put <num, iterators> from nums into a min heap, and remember max value.
 * Update result array if possible. (range: min heap.poll()'s value - max)
 * Update max if possible and put next number and iterator into the min heap.
 * TC: O(n*logk), n = num of elements, k = nums.size()
 * SC: O(k)
 * @author Jeesub Lee (jeesubl@andrew.cmu.edu)
 */
public class Q632_SmallestRangeCoveringElementsFromKLists {
    public int[] smallestRange(List<List<Integer>> nums) {
        PriorityQueue<Node> minHeap = new PriorityQueue<>();
        int max = Integer.MIN_VALUE;
        for (List<Integer> list : nums) {
            max = Math.max(max, list.get(0));
            Iterator<Integer> iterator = list.iterator();
            minHeap.offer(new Node(iterator.next(), iterator));
        }

        int[] result = {minHeap.peek().val, max};
        while (true) {
            Node minNode = minHeap.poll();
            if (result[1] - result[0] > max - minNode.val) {
                result[0] = minNode.val;
                result[1] = max;
            }
            if (!minNode.iterator.hasNext()) {
                break;
            }
            int nextVal = minNode.iterator.next();
            max = Math.max(max, nextVal);
            minHeap.offer(new Node(nextVal, minNode.iterator));
        }
        return result;
    }

    private static class Node implements Comparable<Node> {
        int val;
        Iterator<Integer> iterator;

        private Node(int newVal, Iterator<Integer> newIterator) {
            val = newVal;
            iterator = newIterator;
        }

        @Override
        public int compareTo(Node other) {
            return Integer.compare(this.val, other.val);
        }
    }
}
