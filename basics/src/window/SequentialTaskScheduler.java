package window;

import java.util.HashMap;
import java.util.Map;

/**
 * Sequential Task Scheduler.
 * [HashMap]
 * HashMap stores the recent index of a certain character.
 * curr index - recent index < cooldown ? cannot move on.
 * @author Jeesub Lee (jeesubl@andrew.cmu.edu)
 */
public class SequentialTaskScheduler {

    public static int getTotalTime(String tasks, int cooldown) {
        Map<Character, Integer> map = new HashMap<>();
        int count = 0;
        for (char c : tasks.toCharArray()) {
            if (map.containsKey(c)) {
                while (count - map.get(c) <= cooldown) {
                    count++;
                }
            }
            map.put(c, count++);
        }
        return count;
    }

    public static void main(String[] args) {
        String tasks = "ABACCA";
        int cooldown = 2;
        System.out.println(getTotalTime(tasks, cooldown));
        // output: 9
        int cooldown2 = 3;
        System.out.println(getTotalTime(tasks, cooldown2));
        // output: 11
    }

}
