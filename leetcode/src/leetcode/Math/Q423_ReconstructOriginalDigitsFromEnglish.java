package leetcode.Math;
/**
 * Q423. Reconstruct Original Digits from English.
 * [Math]
 * zero, one, two, three, four, five, six, seven, eight, nine
 * Check unique characters first.
 * z: zero
 * w: two
 * u: four
 * x: six
 * g: eight
 * f: four, five // already checked four in 'u'. We can get number of fives
 *    -> five
 * v: five, seven // already checked five in 'f'.
 *    -> seven
 * h: three, eight // already checked eight in 'g'.
 *    -> three
 * s: six, seven // already checked six and seven.
 *    -> null
 * r: zero, three, four
 *    -> null
 * t: two, three, eight
 *    -> null
 * i: five, six, eight, nine // checked five, six, and eight.
 *    -> nine
 * n: one, seven, nine // checked seven and nine.
 *    -> one
 * // We have zero to nine.
 * o: zero, one, two, four
 * e: zero, one, three, five, seven, eight, nine
 * TC: O(n), where n is the length of input string
 * SC: O(1)
 * @author Jeesub Lee (jeesubl@andrew.cmu.edu)
 */
public class Q423_ReconstructOriginalDigitsFromEnglish {

    public static String originalDigits(String s) {
        int[] chars = countChars(s);
        int[] counts = countNums(chars);
        return buildResult(counts);
    }

    private static int[] countChars(String s) {
        int[] chars = new int[26];
        for (int i = 0; i < s.length(); i++) {
            chars[s.charAt(i) - 'a']++;
        }
        return chars;
    }

    private static int[] countNums(int[] chars) {
        int[] counts = new int[10];
        counts[0] = chars['z' - 'a'];
        counts[2] = chars['w' - 'a'];
        counts[4] = chars['u' - 'a'];
        counts[6] = chars['x' - 'a'];
        counts[8] = chars['g' - 'a'];
        counts[5] = chars['f' - 'a'] - counts[4];
        counts[7] = chars['v' - 'a'] - counts[5];
        counts[3] = chars['h' - 'a'] - counts[8];
        counts[9] = chars['i' - 'a'] - counts[5] - counts[6] - counts[8];
        counts[1] = chars['n' - 'a'] - counts[7] - 2 * counts[9];
        return counts;
    }

    private static String buildResult(int[] counts) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < counts.length; i++) {
            for (int j = 0; j < counts[i]; j++) {
                sb.append(i);
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        String s = "oneonetwothreefourfievsixsevneeightnienorze";
        System.out.println(originalDigits(s));
        // output: "01123456789"
    }

}
