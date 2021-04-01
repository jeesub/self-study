package iterator;

import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;

public class FindKthElementInSortedLists {

    public static int find(List<List<Integer>> lists, int k) {
        PriorityQueue<Node> minHeap = new PriorityQueue<>();
        for (List<Integer> list : lists) {
            Iterator<Integer> itr = list.iterator();
            minHeap.offer(new Node(itr.next(), itr));
        }

        Node curr = null;
        while (!minHeap.isEmpty() && k > 0) {
            curr = minHeap.poll();
            if (curr.hasNext()) {
                minHeap.offer(new Node(curr.itr.next(), curr.itr));
            }
            k--;
        }
        return k > 0 ? -1 : curr.val;
    }

    private static class Node implements Comparable<Node> {
        int val;
        Iterator<Integer> itr;

        private Node(int newVal, Iterator<Integer> newItr) {
            val = newVal;
            itr = newItr;
        }

        private boolean hasNext() {
            return itr.hasNext();
        }

        @Override
        public int compareTo(Node other) {
            return Integer.compare(this.val, other.val);
        }
    }

    public static void main(String[] args) {
        List<List<Integer>> lists = List.of(List.of(1, 4, 9, 11), List.of(3, 6, 7), List.of(2, 10, 12), List.of(5, 8, 13));
        System.out.println(find(lists, 13));
        // output: 13
    }

}
