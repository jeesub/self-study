package leetcode;
/**
 * Q29. Divide Two Integers.
 * Divide Two Integers without using multiplication, division, and mod operator.
 * 
 * check if the result should be positive or negativce
 * make dividend and divisor negative numbers to handle the Integer.MIN_VALUE
 * 
 * [BRUTE FORCE]
 * do (dividend - divisor) while dividend <= divisor and quotient--
 * return quotient with sign
 *
 * [OPTIMIZATION]
 * use double while loop
 * while dividend <= divisor
 *     do (dividend - doubled divisor) while dividend <= doubled divisor
 *     doubled divisor is doubled every time
 *     double quotient, too
 * @author Jeesub Lee (jeesubl@andrew.cmu.edu)
 */
public class Q29_DivideTwoIntegers {

    public static int divide(int dividend, int divisor) {
        if (dividend == Integer.MIN_VALUE && divisor == -1) {
            return Integer.MAX_VALUE;
        }
        boolean isNegative = false;
        if ((dividend < 0 && divisor > 0) || (dividend > 0 && divisor < 0)) {
            isNegative = true;
        }
        if (dividend > 0) {
            dividend = -dividend;
        }
        if (divisor > 0) {
            divisor = -divisor;
        }
        int quotient = 0;
        int halfLimit = Integer.MIN_VALUE / 2;
        while (dividend <= divisor) {
            int doubledQuotient = -1;
            int doubledDivisor = divisor;
            while (dividend <= doubledDivisor) {
                dividend -= doubledDivisor;
                quotient += doubledQuotient;
                if (doubledDivisor <= halfLimit) {
                    break;
                }
                doubledDivisor += doubledDivisor;
                doubledQuotient += doubledQuotient;
            }
        }
        return isNegative ? quotient : -quotient;
    }

    public static void main(String[] args) {
        int dividend = 10;
        int divisor = 3;
        System.out.print(divide(dividend, divisor));
        // output: 3
    }

}
