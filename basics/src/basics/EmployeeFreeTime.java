package basics;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Employee Free Time.
 * input: [[[1,3], [9,12], [[2,4]], [[6,8]]]
 * output: [[4,6], [8,9]]
 * MinHeap<Node(Interval, iterator)> ordered by start time.
 * Offers iterators of working times.
 * Keep the prev interval.
 * If curr interval can merge to prev, merge it.
 * Else, Add to a list.
 * After all, return gaps of the list.
 * @author Jeesub Lee (jeesubl@andrew.cmu.edu)
 */
public class EmployeeFreeTime {

    public static List<List<Integer>> findFreeTime(List<List<List<Integer>>> workingTimes) {
        PriorityQueue<Node> minHeap = new PriorityQueue<>();
        for (List<List<Integer>> each : workingTimes) {
            Iterator<List<Integer>> itr = each.iterator();
            List<Integer> first = itr.next();
            Node node = new Node(first.get(0), first.get(1), itr);
            minHeap.add(node);
        }

        List<List<Integer>> allWorkingTimes = new ArrayList<>();
        Node prev = minHeap.peek();
        while (!minHeap.isEmpty()) {
            Node curr = minHeap.poll();
            if (curr.itr.hasNext()) {
                List<Integer> next = curr.itr.next();
                minHeap.add(new Node(next.get(0), next.get(1), curr.itr));
            }
            if (prev.end >= curr.start) {
                prev.end = curr.end;
            } else {
                allWorkingTimes.add(List.of(prev.start, prev.end));
                prev = curr;
            }
        }
        allWorkingTimes.add(List.of(prev.start, prev.end));
        return buildFreeTime(allWorkingTimes);
    }

    private static List<List<Integer>> buildFreeTime(List<List<Integer>> times) {
        List<List<Integer>> result = new ArrayList<>();
        int end = times.get(0).get(1);
        for (int i = 1; i < times.size(); i++) {
            int start = times.get(i).get(0);
            result.add(List.of(end, start));
            end = times.get(i).get(1);
        }
        return result;
    }

    private static class Node implements Comparable<Node> {
        int start;
        int end;
        Iterator<List<Integer>> itr;
        public Node(int newStart, int newEnd, Iterator<List<Integer>> newItr) {
            start = newStart;
            end = newEnd;
            itr = newItr;
        }

        @Override
        public int compareTo(Node other) {
            if (this.start != other.start) {
                return Integer.compare(this.start, other.start);
            }
            return Integer.compare(this.end, other.end);
        }
    }

    public static void main(String[] args) {
        // [[[1,3], [9,12], [[2,4]], [[6,8]]]
        List<List<List<Integer>>> workingTimes = new ArrayList<>();
        workingTimes.add(List.of(List.of(1, 3), List.of(9, 12)));
        workingTimes.add(List.of(List.of(2, 4)));
        workingTimes.add(List.of(List.of(6, 8)));
        System.out.println(findFreeTime(workingTimes));
        // output: [[4,6], [8,9]]
    }

}
