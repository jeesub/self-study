package leetcode.Windows;

/**
 * 246. Strobogrammatic Number.
 * [String & Two Pointers]
 * 0 <-> 0, 1 <-> 1, 8 <-> 8
 * 6 <-> 9
 * iterate through the num using two pointers(i = 0, j = length - 1) while i <= j
 * if num[i] is '0', '1', or '8', check if num[i] == num[j]
 * if num[i] is '6' check if num[j] == '9'
 * if num[i] is '9' check if num[j] == '6'
 * else, return false
 * i++, j--
 * TC: O(n)
 * SC: O(1)
 * @author Jeesub Lee (jeesubl@andrew.cmu.edu)
 */
public class Q246_StrobogrammaticNumber {
    public static boolean isStrobogrammatic(String num) {
        for (int i = 0, j = num.length() - 1; i <= j; i++, j--) {
            char c1 = num.charAt(i);
            char c2 = num.charAt(j);
            if ((c1 == '0' || c1 == '1' || c1 == '8') && (c1 == c2)) {
                continue;
            } else if ((c1 == '6' && c2 == '9') || (c1 == '9' && c2 == '6')) {
                continue;
            }
            return false;
        }
        return true;
    }
}
