package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Q1229. Meeting Scheduler.
 * Sort slots1 and slots2.
 * Use two pointers.
 * If slot1 and slot2 have common, see if they have enough common duration.
 *     [Check if they have common]
 *     There can be 6 cases
 *     <--->         |  <--->    |  <----->  |   <--->   |    <--->  |         <--->  
 *            <--->  |    <--->  |   <--->   |  <----->  |  <--->    |  <--->         
 *     Except for the first and the last case, they have common.
 * 
 *     [Get common]
 *     It can be min(end1, end2) - max(start1, start2).
 * If not, move one of slot pointer that has a later end.
 * @author Jeesub Lee (jeesubl@andrew.cmu.edu)
 */
public class Q1229_MeetingScheduler {

    public static List<Integer> minAvailableDuration(int[][] slots1, int[][] slots2, int duration) {
        Arrays.sort(slots1, (a, b) -> (a[0] - b[0]));
        Arrays.sort(slots2, (a, b) -> (a[0] - b[0]));
        int i = 0;
        int j = 0;
        List<Integer> result = new ArrayList<>();
        while (i < slots1.length && j < slots2.length) {
            int[] slot1 = slots1[i];
            int[] slot2 = slots2[j];
            if (haveCommon(slot1, slot2) && haveResult(slot1, slot2, duration)) {
                buildResult(slot1, slot2, duration, result);
                break;
            }
            if (slot1[1] < slot2[1]) {
                i++;
            } else {
                j++;
            }
        }
        return result;
    }

    private static boolean haveCommon(int[] slot1, int[] slot2) {
        return !(slot1[1] < slot2[0]) || (slot2[1] < slot1[0]);
    }

    private static boolean haveResult(int[] slot1, int[] slot2, int duration) {
        int possibleDuration = Math.min(slot1[1], slot2[1]) - Math.max(slot1[0], slot2[0]);
        return possibleDuration >= duration;
    }

    private static void buildResult(int[] slot1, int[] slot2, int duration, List<Integer> result) {
        int start = Math.max(slot1[0], slot2[0]);
        int end = start + duration;
        result.add(start);
        result.add(end);
    }

    public static void main(String[] args) {
        int[][] slots1 = {{10, 50}, {60, 120}, {140, 210}};
        int[][] slots2 = {{0, 15}, {60, 70}};
        int duration = 8;
        System.out.println(minAvailableDuration(slots1, slots2, duration));
        // output: [60, 68]
    }

}
