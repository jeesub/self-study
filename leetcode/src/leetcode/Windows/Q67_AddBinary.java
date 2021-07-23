package leetcode.Windows;
/**
 * Q67. Add Binary.
 * [Two Pointers]
 * Calculate one by one backwards
 * TC: O(n), where n is the longest string input
 * SC: O(n), where n is the longest string input
 * @author Jeesub Lee (jeesubl@andrew.cmu.edu)
 */
public class Q67_AddBinary {

    public static String addBinary(String a, String b) {
        StringBuilder sb = new StringBuilder();
        int carry = 0;
        int i = a.length() - 1;
        int j = b.length() - 1;
        while (i >= 0 || j >= 0) {
            if (i >= 0 && a.charAt(i) == '1') {
                carry++;
            }
            if (j >= 0 && b.charAt(j) == '1') {
                carry++;
            }

            if ((carry & 1) == 0) {
                sb.append('0');
            } else {
                sb.append('1');
            }

            carry /= 2;
            i--;
            j--;
        }

        if (carry == 1) {
            sb.append('1');
        }

        return sb.reverse().toString();
    }

    public static void main(String[] args) {
        String a = "1010";
        String b = "1111";
        System.out.println(addBinary(a, b));
        // output: 11001
    }

}
