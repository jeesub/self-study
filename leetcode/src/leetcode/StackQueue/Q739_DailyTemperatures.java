package leetcode.StackQueue;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/**
 * Q739. Daily Temperatures.
 * [Stack]
 * Stack stores index.
 * While curr temp > peek temp,
 *   result[popped index] = curr index - popped index
 * TC: O(n)
 * SC: O(n)
 * @author Jeesub Lee (jeesubl@andrew.cmu.edu)
 */
public class Q739_DailyTemperatures {

    public static int[] dailyTemperatures(int[] temperatures) {
        int[] result = new int[temperatures.length];
        Deque<Integer> deque = new ArrayDeque<>();
        for (int i = 0; i < temperatures.length; i++) {
            while (!deque.isEmpty() && temperatures[i] > temperatures[deque.peekLast()]) {
                int index = deque.removeLast();
                result[index] = i - index;
            }
            deque.addLast(i);
        }
        return result;
    }

    public static void main(String[] args) {
        int[] temperatures = {73,74,75,71,69,72,76,73};
        System.out.println(Arrays.toString(dailyTemperatures(temperatures)));
        // output: [1, 1, 4, 2, 1, 1, 0, 0]
    }

}
