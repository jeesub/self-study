package leetcode.Math;

/**
 * 171. Excel Sheet Column Number.
 * [Math]
 * A = 1, B = 2, C = 3 ...
 * ABC
 *   ^ 26^0 * C
 *  ^ 26^1 * B
 * ^ 26^2 * A
 * TC: O(n)
 * SC: O(1)
 * @author Jeesub Lee (jeesubl@andrew.cmu.edu)
 */
public class Q171_ExcelSheetColumnNumber {
    private static final int BASE_NUM = 26;

    public int titleToNumber(String columnTitle) {
        int sum = 0;
        for (int i = 0; i < columnTitle.length(); i++) {
            char c = columnTitle.charAt(i);
            sum *= BASE_NUM;
            sum += c - 'A' + 1;
        }
        return sum;
    }
}
