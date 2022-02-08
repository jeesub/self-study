package leetcode.MapSet;

import java.util.*;

/**
 * 398. Random Pick Index
 * [HashMap]
 * Map<number, List<index>>
 * Constructor: build a map
 * TC: O(n)
 * SC: O(n)
 *
 * pick(): list.get(rand(list's size))
 * TC: O(1)
 * SC: O(1)
 * @author Jeesub Lee (jeesubl@andrew.cmu.edu)
 */
public class Q398_RandomPickIndex {
    private Random rand = new Random();
    private Map<Integer, List<Integer>> map = new HashMap<>();

    public Q398_RandomPickIndex(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            if (!map.containsKey(nums[i])) {
                map.put(nums[i], new ArrayList<>());
            }
            map.get(nums[i]).add(i);
        }
    }

    public int pick(int target) {
        List<Integer> list = map.get(target);
        int randomNum = rand.nextInt(list.size());
        return list.get(randomNum);
    }
}
