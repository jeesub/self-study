package leetcode.DP;

/**
 * 828. Count Unique Characters of All Substrings of a Given String.
 * [DP]
 * dp[i] = newly added number because of s.charAt(i)
 *
 * ABABAC
 *
 * === Newly Added Substrings & Characters (Allow Duplicate Characters) ===
 * A       : A                       // dp[0] = prev + (i + 1) = 0 + 1 = 1
 * AB      : AB, B                   // dp[1] = dp[0] + (i + 1) = 1 + 2 = 3
 * ABA     : ABA, BA, A              // dp[2] = dp[1] + (i + 1) = 3 + 3 = 6
 * ABAB    : ABAB, BAB, AB, B        // dp[3] = dp[2] + (i + 1) = 6 + 4 = 10
 * ABABA   : ABABA, BABA, ABA, BA, A // dp[4] = 10 + 5 = 15
 * ABABAC  : ABABAC, BABAC, ABAC, BAC, AC, C // dp[5] = 15 + 6 = 21
 *
 * === Considering Uniqueness ===
 * A       : A             // dp[0] = prev + (i + 1) = 0 + 1 = 1
 *                         // current repeatedOcc[A] += firstOcc[A] = 0
 *                         // firstOcc[A] = 1 - (firstOcc[A] + prev repeatedOcc[A]) = 1
 *                         // -> repeatedOcc[A] += firstOcc[A]
 *                         // -> firstOcc[A] = (i + 1) - repeatedOcc[A]
 *
 * AB      : AB, B         // dp[1] = dp[0] + (i + 1) = 1 + 2 = 3
 *                         // repeatedOcc[B] += firstOcc[B] = 0
 *                         // firstOcc[B] = 2 - repeated[B] = 2
 *
 * ABA     : ABA, BA, A    // dp[2] = dp[1] + (i + 1) = 3 + 3 = 6
 *        -> B, BA, A      // second occurrence of A in one place
 *                         // dp[2] = dp[2] - 2 * firstOcc[A] = 6 - 2 = 4
 *                         // repeatedOcc[A] += firstOcc[A] = 1
 *                         // fistOcc[A] = 3 - repeatedOcc[A] = 2
 *
 * ABAB    : ABAB, BAB, AB, B
 *        -> 0, A, AB, B   // dp[3] = dp[2] + (i + 1) = 4 + 4 = 8
 *                         // second occurrence of B in two places
 *                         // dp[3] = dp[3] - 2 * firstOcc[B] = 4
 *                         // repeatedOcc[B] += firstOcc[B] = 2
 *                         // firstOcc[B] = 4 - repeatedOcc[B] = 2
 *
 * ABABA   : ABABA, BABA, ABA, BA, A
 *        -> 0, 0, B, BA, A // dp[4] = dp[3] + (i + 1) = 4 + 5 = 9
 *                          // second occurrence of A in two places
 *                          // third+ occurence of A in one place
 *                          // dp[4] = dp[4] - 2 * firstOcc[A] - repeatedOcc[A] = 4
 *                          // repeatedOcc[A] += firstOcc[A] = 3
 *                          // firstOcc[A] = 5 - repeatedOcc[A] = 2
 *
 * ABABAC  : ABABAC, BABAC, ABAC, BAC, AC, C
 *        -> C, C, BC, BAC, AC, C // dp[5] = dp[4] + (i + 1) = 4 + 6 = 10
 *                                // no previous occurence of C
 *                                // dp[5] = 10 - 2 * 0 - 0 = 10
 *                                // repeatedOcc[C] += firstOcc[C] = 0
 *                                // firstOcc[C] = 6 - repeatedOcc[C] = 6
 * Second occurrence: -2
 * Third+ occurrence: -1
 *
 *    | A B A B A C
 * ---+--------------
 *    | 1 3 4 4 4 10 -> total: 26
 *
 * can improve SC from O(n) to O(1) by using variables instead of table.
 * Iterate through the input string.
 * Keep track of
 *   1. accumulated number of unique characters
 *   2. number of unique characters in the previous i
 *   3. firstOcc[A-Z] = num of first occurrence of A-Z
 *   4. repeatedOcc[A-Z] = num of second+ occurence of A-Z
 *
 * TC: O(n)
 * SC: O(1)
 * @author Jeesub Lee (jeesubl@andrew.cmu.edu)
 */
public class Q828_CountUniqueCharactersOfAllSubstringsOfAGivenString {
    public static int uniqueLetterString(String s) {
        int acc = 0;
        int prev = 0;
        int[] firstOcc = new int[26];
        int[] repeatedOcc = new int[26];
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            prev += i + 1;
            prev -= 2 * firstOcc[c - 'A'];
            prev -= repeatedOcc[c - 'A'];
            repeatedOcc[c - 'A'] += firstOcc[c - 'A'];
            firstOcc[c - 'A'] = i + 1 - repeatedOcc[c - 'A'];
            acc += prev;
        }
        return acc;
    }

    public static void main(String[] args) {
        String s = "JEESUB";
        System.out.println(uniqueLetterString(s));
        // output: 40
    }
}
