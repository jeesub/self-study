package leetcode.DP;

/**
 * 926. Flip String to Monotone Increasing.
 * [Pointer]
 * case 1. make all numbers 0s
 * case 2. make all numbers 1s
 * case 3. iterate through the elements
 *         and make all left numbers 0s, and make all right numbers 1s
 *
 * Count 1s number in the first iteration and check the case 1 and case 2.
 * In the second iteration, check the case 3.
 * 010110
 *   ^
 *   numbers to flip = prevOnes + nextZeros = 1 + 1 = 2
 *   result = max(result, total numbers to flip)
 *
 * TC: O(n)
 * SC: O(1)
 *
 * [DP]
 * dp[i] = minimum number of flips
 * if curr == 1, dp[i] = dp[i - 1]
 * if curr == 0, dp[i] = min(dp[i - 1] + 1, number of prev ones)
 *
 *  | 0 1 0 1 1 0
 * -+-------------
 *  | 0 0 1 1 1 2
 *              ^ min(dp[4] + 1 = 2, num of prev ones = 3) = 2
 *
 * TC: O(n)
 * SC: O(n) & can improve to O(1)
 * @author Jeesub Lee (jeesubl@andrew.cmu.edu)
 */
public class Q926_FlipStringToMonotoneIncreasing {
    public static int minFlipsMonoIncrPointer(String s) {
        int totalOnes = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '1') {
                totalOnes++;
            }
        }
        int result = Math.min(totalOnes, s.length() - totalOnes);

        int prevOnes = 0;
        int nextZeros = s.length() - totalOnes;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '0') {
                nextZeros--;
            }
            result = Math.min(result, prevOnes + nextZeros);
            if (s.charAt(i) == '1') {
                prevOnes++;
            }
        }
        return result;
    }

    public static int minFlipsMonoIncr(String s) {
        int prevOnes = 0;
        int minFlip = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '1') {
                prevOnes++;
                continue;
            }
            minFlip = Math.min(minFlip + 1, prevOnes);
        }
        return minFlip;
    }

    public static void main(String[] args) {
        String s = "010110";
        System.out.println(minFlipsMonoIncrPointer(s));
        System.out.println(minFlipsMonoIncr(s));
        // output: 2
    }
}
