package leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * Range Module.
 * TreeSet<Range>
 * Range contains start and end points.
 * Implement comparable ordered by start point and end point.
 * 
 * [AddRange]
 * <--> <--> <-->  | <-------> |   <--> <--> <-->
 *   <========>    |   <===>   | <================>
 * Find overlapped ranges in a treeset with start point.
 * Merge overlapped ranges and add into a treeset.
 * Remove merged ranges from a treeset.
 * 
 * [QueryRange]
 * <------->
 *   <===>
 * Find an overlapped range with start point.
 * If the overlapped range cover the input range, return true.
 *
 * [RemoveRange]
 * <--> <--> <-->  | <-------> |   <--> <--> <-->
 *   <========>    |   <===>   | <================>
 * Find overlapped ranges in a treeset with start point.
 * Make new ranges and keep the unremoved ranges at start and end if necessary.
 * Remove overlapped ranges from a treeset.
 * @author Jeesub Lee (jeesubl@andrew.cmu.edu)
 */
public class RangeModule {
    TreeSet<Range> ranges;

    public RangeModule() {
        ranges = new TreeSet<Range>();
    }

    public void addRange(int left, int right) {
        SortedSet<Range> sortedSet = ranges.tailSet(new Range(-1, left - 1));
        List<Range> removeList = new ArrayList<>();
        for (Range range : sortedSet) {
            if (range.getLeft() > right) {
                break;
            }
            left = Math.min(left, range.getLeft());
            right = Math.max(right, range.getRight());
            removeList.add(range);
        }
        for (Range range : removeList) {
            ranges.remove(range);
        }
        ranges.add(new Range(left, right));
        // DEBUGGING
        // System.out.println(ranges);
    }

    public boolean queryRange(int left, int right) {
        Range range = ranges.ceiling(new Range(-1, right));
        if (range == null) {
            return false;
        }
        return range.getLeft() <= left && range.getRight() >= right;
    }

    public void removeRange(int left, int right) {
        SortedSet<Range> sortedSet = ranges.tailSet(new Range(-1, left));
        List<Range> removeList = new ArrayList<>();
        List<Range> addList = new ArrayList<>();
        for (Range range : sortedSet) {
            if (range.getLeft() >= right) {
                break;
            }
            if (range.getLeft() < left) {
                addList.add(new Range(range.getLeft(), left));
            }
            if (range.getRight() > right) {
                addList.add(new Range(right, range.getRight()));
            }
            removeList.add(range);
        }
        for (Range range : removeList) {
            ranges.remove(range);
        }
        for (Range range : addList) {
            ranges.add(range);
        }
        // DEBUGGING
        // System.out.println(ranges);
    }

    private class Range implements Comparable<Range> {
        private int left;
        private int right;

        public Range(int newLeft, int newRight) {
            left = newLeft;
            right = newRight;
        }

        public int getLeft() {
            return left;
        }

        public int getRight() {
            return right;
        }

        @Override
        public int compareTo(Range other) {
            if (this.getRight() != other.getRight()) {
                return this.getRight() - other.getRight();
            }
            return this.getLeft() - other.getLeft();
        }

        @Override
        public boolean equals(Object other) {
            if (!(other instanceof Range)) {
                return false;
            }
            Range o = (Range) other;
            return this.getLeft() == o.getLeft() && this.getRight() == o.getRight();
        }

        @Override
        public String toString() {
            return "Range (" + this.getLeft() + ", " + this.getRight() + ")";
        }
    }
}
