package leetcode.Greedy;

import java.util.Arrays;

/**
 * Q621. Task Scheduler.
 * [Greedy]
 * total running time = all tasks' running time + idle time
 * 0 <= idle time <= (the most frequent task's frequency - 1) * n
 *
 * 1. Make an array stores char and frequency.
 * 2. Sort the array.
 * 3. Calculate the idle time.
 *    the initial idle time = (the most frequent task's frequency - 1) * n
 *    fill the idle time with possible tasks
 *    we can fill with min of (curr freq, max freq - 1)
 *    ex) tasks = ["A","A","A","B","B","B"] and n = 2
 *       -> A - idle - idle - A - idle - idle - A
 *       -> A - B - idle - A - B - idle - A
 * 4. return the length of idle + the length of tasks
 * @author Jeesub Lee (jeesubl@andrew.cmu.edu)
 */
public class Q621_TaskScheduler {
    private static final int LENGTH = 26;

    public static int leastInterval(char[] tasks, int n) {
        int[] frequencies = buildFrequenciesArray(tasks);
        int maxFreq = frequencies[LENGTH - 1];
        int numOfIdles = (maxFreq - 1) * n;

        for (int i = LENGTH - 2; i >= 0; i--) {
            if (numOfIdles <= 0 || frequencies[i] == 0) {
                break;
            }
            numOfIdles -= Math.min(frequencies[i], maxFreq - 1);
        }

        numOfIdles = Math.max(numOfIdles, 0);
        return tasks.length + numOfIdles;
    }

    private static int[] buildFrequenciesArray(char[] tasks) {
        int[] frequencies = new int[LENGTH];
        for (char task : tasks) {
            frequencies[task - 'A']++;
        }
        Arrays.sort(frequencies);
        return frequencies;
    }

    public static void main(String[] args) {
        char[] tasks = {'A', 'A', 'A', 'B', 'B', 'B'};
        int n = 2;
        System.out.println(leastInterval(tasks, n));
        // output: 8
    }

}
