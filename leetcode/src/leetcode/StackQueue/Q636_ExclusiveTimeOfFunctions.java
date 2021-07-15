package leetcode.StackQueue;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;

/**
 * Q636. Exclusive Time of Functions.
 * [Stack]
 * Use a Stack<Integer id> to keep track of ids of running processes.
 * Only start nodes go into the stack.
 * Keep the int prevTimestamp to calculate the running time.
 * Iterate through the logs.
 *   If the curr log is start,
 *     record the up time of the previous running log
 *     and add the current log to the stack.
 *   If the log is end,
 *     record the uptime of the current process
 *     and remove the start log from the stack.
 *   Update the prevTimestamp.
 * TC: O(n), where n is the number of logs
 * SC: O(n), where n is the number of logs
 * @author Jeesub Lee (jeesubl@andrew.cmu.edu)
 */
public class Q636_ExclusiveTimeOfFunctions {
    private static final String START = "start";

    public static int[] exclusiveTime(int n, List<String> logs) {
        Deque<Integer> deque = new ArrayDeque<>();
        int[] exclusiveTime = new int[n];
        int prevTimestamp = 0;

        for (String each : logs) {
            String[] splitted = each.split(":");
            boolean currIsStart = splitted[1].equals(START) ? true : false;
            int currId = Integer.valueOf(splitted[0]);
            int currTimestamp = Integer.valueOf(splitted[2]);

            if (currIsStart) {
                if (!deque.isEmpty()) {
                    int upTime = currTimestamp - prevTimestamp;
                    exclusiveTime[deque.peekLast()] += upTime;
                }
                deque.addLast(currId);
            } else {
                deque.removeLast();
                int upTime = currTimestamp - prevTimestamp + 1;
                exclusiveTime[currId] += upTime;
            }
            prevTimestamp = currIsStart ? currTimestamp : currTimestamp + 1;
        }

        return exclusiveTime;
    }

    public static void main(String[] args) {
        int n = 2;
        List<String> logs = List.of("0:start:0","0:start:2","0:end:5","1:start:6","1:end:6","0:end:7");
        System.out.println(Arrays.toString(exclusiveTime(n, logs)));
        // output: [7, 1]
    }

}
