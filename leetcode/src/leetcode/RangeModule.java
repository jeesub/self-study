package leetcode;

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
        Range input = new Range(left, right);
        // DEBUGGING
        // System.out.println("addRange " + input);
        Range overlapped = ranges.floor(input);
        if (overlapped != null && input.getLeft() <= overlapped.getRight()) {
            input.setLeft(Math.min(input.getLeft(), overlapped.getLeft()));
            input.setRight(Math.max(input.getRight(), overlapped.getRight()));
            ranges.remove(overlapped);
        }
        overlapped = ranges.ceiling(input);
        while (overlapped != null) {
            if (overlapped.getRight() < input.getLeft() || overlapped.getLeft() > input.getRight()) {
                break;
            }
            input.setLeft(Math.min(input.getLeft(), overlapped.getLeft()));
            input.setRight(Math.max(input.getRight(), overlapped.getRight()));
            ranges.remove(overlapped);
            overlapped = ranges.ceiling(input);
        }
        ranges.add(input);
        // DEBUGGING
        // System.out.println(ranges);
    }

    public boolean queryRange(int left, int right) {
        Range input = new Range(left, right);
        // DEBUGGING
        // System.out.println("queryRange " + input);
        Range overlapped = ranges.floor(input);
        if (overlapped == null) {
            return false;
        }
        return overlapped.getLeft() <= input.getLeft() && input.getRight() <= overlapped.getRight();
    }

    public void removeRange(int left, int right) {
        Range input = new Range(left, right);
        // DEBUGGING
        // System.out.println("removeRange " + input);
        Range leftOverlapped = ranges.floor(input);
        if (leftOverlapped != null && input.getLeft() < leftOverlapped.getRight()) {
            // <-->   have to keep a left part <->
            //   <==>
            if (leftOverlapped.getLeft() < input.getLeft()) {
                Range newRange = new Range(leftOverlapped.getLeft(), input.getLeft());
                ranges.add(newRange);
            }
            //   <--> have to keep a right part <->
            // <==>
            if (input.getRight() < leftOverlapped.getRight()) {
                Range newRange = new Range(input.getRight(), leftOverlapped.getRight());
                ranges.add(newRange);
            }
            ranges.remove(leftOverlapped);
        }
        Range overlapped = ranges.ceiling(input);
        while (overlapped != null) {
            if (input.getRight() < overlapped.getRight()) {
                break;
            }
            ranges.remove(overlapped);
            overlapped = ranges.ceiling(input);
        }
        Range rightOverlapped = ranges.ceiling(input);
        if (rightOverlapped != null && rightOverlapped.getLeft() < input.getRight()) {
            Range newRange = new Range(input.getRight(), rightOverlapped.getRight());
            ranges.add(newRange);
            ranges.remove(rightOverlapped);
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

        public void setLeft(int newLeft) {
            this.left = newLeft;
        }

        public void setRight(int newRight) {
            this.right = newRight;
        }

        @Override
        public int compareTo(Range other) {
            if (this.getLeft() != other.getLeft()) {
                return this.getLeft() - other.getLeft();
            }
            return other.getRight() - this.getRight();
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
