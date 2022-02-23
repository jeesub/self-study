package leetcode.List;

/**
 * 605. Can Place Flowers.
 * [List]
 * count continuous zero
 * case 1. leading zero or last zero
 *   num of possible flowers = num of zeros / 2
 * case 2. zeros between ones
 *   num of possible flowers = (num of zeros - 1) / 2
 *
 * iterate through the flowerbed
 * num of zeros = 1 // to handle a leading zero
 * if curr == 0,
 *   num of zeros++
 * if curr == 1,
 *   n -= (num of zeros - 1) / 2
 *   if n <= 0, return true // pruning
 *   num of zeros = 0 // reset
 * after iteration, n -= num of zeros / 2 // to deal with an ending zero
 * return n <= 0
 * TC: O(n)
 * SC: O(1)
 * @author Jeesub Lee (jeesubl@andrew.cmu.edu)
 */
public class Q605_CanPlaceFlowers {
    public static boolean canPlaceFlowers(int[] flowerbed, int n) {
        int zeros = 1;
        for (int plot : flowerbed) {
            if (plot == 0) {
                zeros++;
            } else {
                n -= (zeros - 1) / 2;
                if (n <= 0) {
                    return true;
                }
                zeros = 0;
            }
        }
        n -= zeros / 2;
        return n <= 0;
    }
}
