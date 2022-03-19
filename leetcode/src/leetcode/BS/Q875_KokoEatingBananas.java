package leetcode.BS;

import java.util.Arrays;

/**
 * 875. Koko Eating Bananas.
 * [Binary Search]
 * Search between 1 to max(piles).
 * TC: O(nlogk)
 * SC: O(1)
 * @author Jeesub Lee (jeesubl@andrew.cmu.edu)
 */
public class Q875_KokoEatingBananas {
    public int minEatingSpeed(int[] piles, int h) {
        int left = 1;
        int right = Arrays.stream(piles).max().getAsInt();
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (isValid(piles, h, mid)) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    private boolean isValid(int[] piles, int h, int k) {
        int time = 0;
        for (int pile : piles) {
            time += pile / k;
            if (pile % k != 0) {
                time++;
            }
        }
        return time <= h;
    }
}
