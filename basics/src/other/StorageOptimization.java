package other;

import java.util.ArrayList;
import java.util.List;

/**
 * Storage Optimization.
 * Iterate through 0 to n and 0 to m.
 * If we have to remove a shelf, remove it.
 * After iteration, add the bounds into the lists.
 * In iterations, Find a max width and height.
 * @author Jeesub Lee (jeesubl@andrew.cmu.edu)
 */
public class StorageOptimization {

    public static int findMaximumSpace(int n, int m, int[] h, int[] v) {
        List<Integer> horizontal = new ArrayList<>();
        for (int i = 1, pointer = 0; i <= n; i++) {
            while (pointer < h.length && h[pointer] < i) {
                pointer++;
            }
            if (pointer < h.length && i == h[pointer]) {
                continue;
            }
            horizontal.add(i);
        }
        horizontal.add(n + 1);

        List<Integer> vertical = new ArrayList<>();
        for (int i = 1, pointer = 0; i <= m; i++) {
            while (pointer < v.length && v[pointer] < i) {
                pointer++;
            }
            if (pointer < v.length && i == v[pointer]) {
                continue;
            }
            vertical.add(i);
        }
        vertical.add(m + 1);

        int prev = 0;
        int maxHeight = 0;
        for (int bar : horizontal) {
            int newHeight = bar - prev;
            maxHeight = Math.max(maxHeight, newHeight);
            prev = bar;
        }

        prev = 0;
        int maxWidth = 0;
        for (int bar : vertical) {
            int newWidth = bar - prev;
            maxWidth = Math.max(maxWidth, newWidth);
            prev = bar;
        }

        return maxHeight * maxWidth;
    }

    public static void main(String[] args) {
        int n = 6;
        int m = 6;
        int[] h = {1, 2, 3, 5};
        int[] v = {1, 3, 4};
        System.out.println(findMaximumSpace(n, m, h, v));
        // output: 12
    }

}
