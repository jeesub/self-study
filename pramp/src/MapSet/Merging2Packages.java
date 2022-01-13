package MapSet;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * [Map]
 * arr = [4, 6, 10, 15, 16],  lim = 21
 * Map<weight, index>
 * 4: 0  // have we seen (21 - 4) = 17 ? no
 * 6: 1  // have we seen (21 - 6) = 15 ? no
 * 10: 2 // have we seen (21 - 10) = 11 ? no
 * 15: 3 // have we seen (21 - 15) = 6 ? yes -> return [curr, map.get(6)'s index]
 * TC: O(n)
 * SC: O(n)
 * @author Jeesub Lee (jeesubl@andrew.cmu.edu)
 */
public class Merging2Packages {
    static int[] getIndicesOfItemWeights(int[] arr, int limit) {
        if (arr.length < 2) {
            return new int[0];
        }

        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            if (map.containsKey(limit - arr[i])) {
                return new int[] {i, map.get(limit - arr[i])};
            }
            map.put(arr[i], i);
        }
        return new int[0];
    }

    public static void main(String[] args) {
        int[] arr = {1, 4, 2, 3, 10, 4};
        int limit = 8;
        System.out.println(Arrays.toString(getIndicesOfItemWeights(arr, limit)));
        // output: [5, 1]
    }
}
