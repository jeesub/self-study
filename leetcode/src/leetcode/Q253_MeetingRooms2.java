package leetcode;

import java.util.Arrays;

/**
 * Q253. Meeting Rooms 2.
 * Returns the minimum number of meeting rooms required.
 * Make two arrays and each stores starts and ends.
 * Sort both arrays and check overlaps.
 * 
 * Iterate through the sorted start array.
 * And keep the end pointer.
 * If start[start pointer] < end[end pointer], we need a new room.
 * counter++
 * Else, it means that one meeting has finished and a room is vacant. We can use the room.
 * end pointer++
 *
 * ex)
 * intervals = [[0, 40], [5, 10], [15, 20], [20, 30]]
 * sorted start = [0, 5, 15, 20]
 * sorted end = [10, 20, 30, 40]
 * Start pointer starts at (0) and end pointer starts at (10)
 * 0 to 10 takes one meeting room.
 * -> cnt++, start pointer++
 * 5 is smaller than 10. So it nees a new meeting room.
 * -> cnt++, start pointer++
 * 15 is larger than 10. So it can use the existing room.
 * -> start pointer++, end pointer++
 * 20 is larger than or equal to 20. So it can use the existing room.
 * -> start pointer++, end pointer++
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
