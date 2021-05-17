package leetcode.String;

import java.util.Arrays;

/**
 * Q443. String Compression.
 * Use two pointers.
 * The faster pointer iterates.
 * If char at slower pointer != char at faster pointer,
 *   record the count.
 *   If count == 1 ? do not record the count.
 *   record the char at faster pointer at the slower pointer's position.
 * After iteration, record the last part.
 * @author Jeesub Lee (jeesubl@andrew.cmu.edu)
 */
public class Q443_StringCompression {

    public static int compress(char[] chars) {
        if (chars == null || chars.length == 0) {
            return 0;
        }
        int slow = 0;
        int count = 1;
        for (int fast = 1; fast < chars.length; fast++) {
            if (chars[slow] == chars[fast]) {
                count++;
                continue;
            }

            if (count != 1) {
                String countString = String.valueOf(count);
                for (int i = 0; i < countString.length(); i++) {
                    slow++;
                    chars[slow] = countString.charAt(i);
                }
            }
            slow++;
            chars[slow] = chars[fast];
            count = 1;
        }

        if (count != 1) {
            String countString = String.valueOf(count);
            for (int i = 0; i < countString.length(); i++) {
                slow++;
                chars[slow] = countString.charAt(i);
            }
        }

        return slow + 1;
    }

    public static void main(String[] args) {
        char[] chars = {'a','b','b','b','b','b','b','b','b','b','b','b','b'};
        System.out.println(compress(chars));
        System.out.println(Arrays.toString(chars));
        // output: 4
        // [a, b, 1, 2, b, b, b, b, b, b, b, b, b]
    }

}
