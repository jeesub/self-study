package leetcode;

import java.util.Arrays;

/**
 * Q253. Meeting Rooms 2.
 * We need to track the on-meeting rooms.
 * <------------------>
 *   <----->  <----->
 *        <----->
 *            ^
 *            At this point, we know we can use the empty room.
 *            So we don't need the fourth room.
 * If we have a room where it's end is before the current start, it's an empty room.
 * We need to separately track starts and ends.
 * Sort two arrays, starts and ends.
 * Iterate through the starts array.
 * If ends[j] < starts[i], we can reuse the room ends at j.
 * The room ends at j is occupied now, so j++.
 * Else, we need one more room.
 * @author Jeesub Lee (jeesubl@andrew.cmu.edu)
 */
public class Q253_MeetingRooms2 {

    public static int minMeetingRooms(int[][] intervals) {
        int[] start = new int[intervals.length];
        int[] end = new int[intervals.length];
        for (int i = 0; i < intervals.length; i++) {
            start[i] = intervals[i][0];
            end[i] = intervals[i][1];
        }
        Arrays.sort(start);
        Arrays.sort(end);
        int cnt = 0;
        int j = 0;
        for (int i = 0; i < start.length; i++) {
            if (start[i] < end[j]) {
                cnt++;
            } else {
                j++;
            }
        }
        return cnt;
    }

    public static void main(String[] args) {
        int[][] intervals = {{0, 40}, {5, 10}, {15, 20}, {20, 30}};
        System.out.print(minMeetingRooms(intervals));
        // output: 2
    }

}
