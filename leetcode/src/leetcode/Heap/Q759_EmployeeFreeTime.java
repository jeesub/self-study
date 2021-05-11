package leetcode.Heap;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Q759. Employee Free Time.
 * [MinHeap]
 * Store Intervals in a min heap.
 * Pop and get gaps.
 * TC: O(nlogn), where n is the length of intervals
 * SC: O(n), where n is the length of intervals
 * @author Jeesub Lee (jeesubl@andrew.cmu.edu)
 */
public class Q759_EmployeeFreeTime {

    public static List<Interval> employeeFreeTime(List<List<Interval>> schedule) {
        PriorityQueue<Interval> minHeap = new PriorityQueue<>(new IntervalComp());
        for (List<Interval> employeeTime : schedule) {
            for (Interval interval : employeeTime) {
                minHeap.add(interval);
            }
        }

        if (minHeap.isEmpty()) {
            return new ArrayList<Interval>();
        }

        int prevEnd = minHeap.poll().end;
        List<Interval> freeTime = new ArrayList<>();
        while (!minHeap.isEmpty()) {
            Interval curr = minHeap.poll();
            if (curr.start > prevEnd) {
                freeTime.add(new Interval(prevEnd, curr.start));
            }
            prevEnd = Math.max(prevEnd, curr.end);
        }
        return freeTime;
    }

    private static class IntervalComp implements Comparator<Interval> {
        @Override
        public int compare(Interval first, Interval second) {
            if (first.start != second.start) {
                return Integer.compare(first.start, second.start);
            }
            return Integer.compare(first.end, second.end);
        }
    }

    private static class Interval {
        public int start;
        public int end;

        public Interval(int newStart, int newEnd) {
            start = newStart;
            end = newEnd;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("[").append(start).append(", ");
            sb.append(end).append("]");
            return sb.toString();
        }
    }

    public static void main(String[] args) {
        // [[[1,3],[6,7]],[[2,4]],[[2,5],[9,12]]]
        List<List<Interval>> schedule = new ArrayList<>();
        schedule.add(List.of(new Interval(1, 3), new Interval(6, 7)));
        schedule.add(List.of(new Interval(2, 4)));
        schedule.add(List.of(new Interval(2, 5), new Interval(9, 12)));
        System.out.println(employeeFreeTime(schedule));
        // output: [[5, 6], [7, 9]]
    }

}
