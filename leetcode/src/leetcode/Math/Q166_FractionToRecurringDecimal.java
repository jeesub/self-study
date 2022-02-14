package leetcode.Math;

import java.util.HashMap;
import java.util.Map;

/**
 * 166. Fraction to Recurring Decimal.
 * [Math]
 * 1. get the integer part by (numerator / denominator)
 *    need to handle negative numbers
 *    need to handle invalid denominator (0)
 *    if numerator == 0, the answer will always be 0
 *    need to handle Integer.MIN_VALUE (it'll cause overflow) -> use long
 * 2. remainder = numerator % denominator
 *    if remainder == 0, we don't have the point part. Return.
 *    we should remember the remainder in Map<remainder, index>
 *    while (remainder != 0)
 *      remainder *= 10
 *      if (map.containsKey(remainder)),
 *        // it's repeating
 *        insert parentheses and return.
 *      map.put(remainder, position)
 *      while (remainder >= denominator),
 *        number to append++
 *        remainder -= denominator
 *
 *       0.012
 *     --------
 * 333 ) 4.0
 *       4.00
 *       3.33
 *       ----
 *         670
 *         666
 *         ---
 *           40 <- we have seen it
 * TC: O(k), where k is the length of the point part to calculate
 * SC: O(i + k), where i is the length of the integer part
 * @author Jeesub Lee (jeesubl@andrew.cmu.edu)
 */
public class Q166_FractionToRecurringDecimal {
    public String fractionToDecimal(int numerator, int denominator) {
        if (denominator == 0) {
            throw new RuntimeException("denominator cannot be 0");
        }
        if (numerator == 0) {
            return "0";
        }
        long num = Math.abs((long) numerator);
        long den = Math.abs((long) denominator);
        StringBuilder sb = new StringBuilder();
        if ((numerator < 0 && denominator > 0) || (numerator > 0 && denominator < 0)) {
            sb.append("-");
        }
        sb.append(num / den);

        long remainder = num % den;
        if (remainder == 0) {
            return sb.toString();
        }

        sb.append(".");
        Map<Long, Integer> map = new HashMap<>();
        while (remainder != 0) {
            remainder *= 10;
            if (map.containsKey(remainder)) {
                sb.insert(map.get(remainder), "(");
                sb.append(")");
                return sb.toString();
            } else {
                map.put(remainder, sb.length());
            }
            int quotient = 0;
            while (remainder >= den) {
                quotient++;
                remainder -= den;
            }
            sb.append(quotient);
        }
        return sb.toString();
    }
}
