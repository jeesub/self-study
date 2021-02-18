package leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * Q380. Insert Delete GetRandom O(1).
 * User HashMap and ArrayList to store the data.
 * HashMap contains (int val, int index).
 * ArrayList contains (int val).
 * 
 * [Insert]
 * if Map has val, return false
 * Add val into the ArrayList.
 * Add val and index into the Map.
 * return true
 *
 * [Remove]
 * if Map doesn't have val, return false
 * Remove the val from Map.
 * Remove the val from ArrayList.
 * if the val was not the last element of the ArrayList,
 *     move the last element to the position of the deleted val
 * 
 * [GetRandom]
 * Get a random number (0..length of the list]
 * return a number at the random index.
 * @author Jeesub Lee (jeesubl@andrew.cmu.edu)
 */
public class Q380_InsertDeleteGeRandomO1 {
    Random random;
    Map<Integer, Integer> map;
    List<Integer> list;

    public Q380_InsertDeleteGeRandomO1() {
        random = new Random();
        map = new HashMap<>();
        list = new ArrayList<>();
    }

    public boolean insert(int val) {
        if (map.containsKey(val)) {
            return false;
        }
        list.add(val);
        map.put(val, list.size() - 1);
        return true;
    }

    public boolean remove(int val) {
        if (!map.containsKey(val)) {
            return false;
        }
        int index = map.remove(val);
        if (index < list.size() - 1) {
            list.set(index, list.get(list.size() - 1));
            map.put(list.get(index), index);
        }
        list.remove(list.size() - 1);
        return true;
    }

    public int getRandom() {
        int index = random.nextInt(list.size());
        return list.get(index);
    }
}
