package leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * Q715. Range Module.
 * TreeSet<Range>
 * Range contains start and end points.
 * Implement comparable ordered by end point and start point.
 * 
 * [AddRange]
 * <--> <--> <-->  | <-------> |   <--> <--> <-->
 *   <========>    |   <===>   | <================>
 * Find a sorted set of overlapped ranges in a treeset.
 * Ideally, using a subSet is the best way but it is hard to define the last element.
 * So, use tailSet instead of subSet.
 * Update left as a minimum value and right as a maximum value.
 * Remove overlapped ranges from a treeset.
 * 
 * [QueryRange]
 * <------->
 *   <===>
 * Find an overlapped range.
 * If the overlapped range cover the input range, return true.
 *
 * [RemoveRange]
 * <--> <--> <-->  | <-------> |   <--> <--> <-->
 *   <========>    |   <===>   | <================>
 * Find overlapped ranges in a treeset.
 * If we need to make new ranges, make and keep it.
 * New ranges can be placed at the most left or the most right.
 * Remove overlapped ranges from a treeset.
 * @author Jeesub Lee (jeesubl@andrew.cmu.edu)
 */
public class Q715_RangeModule {
    TreeSet<Range> ranges;

    public Q715_RangeModule() {
        ranges = new TreeSet<Range>();
    }

    public void addRange(int left, int right) {
        SortedSet<Range> sortedSet = ranges.tailSet(new Range(-1, left));
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
