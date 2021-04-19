package leetcode;

import java.util.Map;
import java.util.TreeMap;

/**
 * Q729. My Calendar I.
 * TreeMap<start, end>
 * [Check intersection]
 * floor(start).end > curr.start ? intersect
 * lower(end).end > curr.start ? intersect
 * @author Jeesub Lee (jeesubl@andrew.cmu.edu)
 */
public class Q729_MyCalendarI {

    private static class MyCalendar {
        TreeMap<Integer, Integer> treeMap;
        public MyCalendar() {
            treeMap = new TreeMap<>();
        }
        
        public boolean book(int start, int end) {
            if (isOverlapped(start, end)) {
                return false;
            }
            treeMap.put(start, end);
            return true;
        }
        
        private boolean isOverlapped(int start, int end) {
            Map.Entry<Integer, Integer> prevOfStart = treeMap.floorEntry(start);
            if (prevOfStart != null && prevOfStart.getValue() > start) {
                return true;
            }
            Map.Entry<Integer, Integer> prevOfEnd = treeMap.lowerEntry(end);
            if (prevOfEnd != null && prevOfEnd.getValue() > start) {
                return true;
            }
            return false;
        }
    }

    public static void main(String[] args) {
        MyCalendar cal = new MyCalendar();
        System.out.println(cal.book(10, 20));
        System.out.println(cal.book(15, 25));
        System.out.println(cal.book(5, 10));
        System.out.println(cal.book(20, 30));
        // output: true false true true
    }

}
