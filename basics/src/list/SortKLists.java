package list;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Sort K Lists.
 * @author Jeesub Lee (jeesubl@andrew.cmu.edu)
 */
public class SortKLists {
    public static List<Integer> sort(List<List<Integer>> lists) {
        List<Integer> result = new ArrayList<>();
        PriorityQueue<Node> minHeap = new PriorityQueue<>();
        for (List<Integer> list : lists) {
            Iterator<Integer> itr = list.iterator();
            minHeap.offer(new Node(itr.next(), itr));
        }

        while (!minHeap.isEmpty()) {
            Node node = minHeap.poll();
            result.add(node.val);
            if (node.hasNext()) {
                minHeap.offer(new Node(node.itr.next(), node.itr));
            }
        }
        return result;
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
        List<List<Integer>> lists = List.of(List.of(1, 4, 9), List.of(3, 6, 7), List.of(2, 5, 8, 10, 12), List.of(1, 5, 6, 8));
        System.out.println(sort(lists));
    }
}
