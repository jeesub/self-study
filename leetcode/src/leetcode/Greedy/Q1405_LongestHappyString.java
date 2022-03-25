package leetcode.Greedy;

/**
 * 1405. Longest Happy String.
 * [Greedy]
 * Append max(a, b, c) if it's not 'aaa', 'bbb', and 'ccc'
 * If it's 'aaa', 'bbb', or 'ccc', append the second max(a, b, c)
 * TC: O(n), n = a + b + c
 * SC: O(n)
 * @author Jeesub Lee (jeesubl@andrew.cmu.edu)
 */
public class Q1405_LongestHappyString {
    public String longestDiverseString(int a, int b, int c) {
        StringBuilder sb = new StringBuilder();
        int contA = 0;
        int contB = 0;
        int contC = 0;
        while (a > 0 || b > 0 || c > 0) {
            if (a > 0 && ((a >= b && a >= c && contA < 2) || (contB >= 2 && a >= c) || (contC >= 2 && a >= b))) {
                sb.append('a');
                a--;
                contA++;
                contB = 0;
                contC = 0;
            } else if (b > 0 && ((b >= a && b >= c && contB < 2) || (contA >= 2 && b >= c) || (contC >= 2 && b >= a))) {
                sb.append('b');
                b--;
                contB++;
                contA = 0;
                contC = 0;
            } else if (c > 0 && ((c >= a && c >= b && contC < 2) || (contA >= 2 && c >= b) || (contB >= 2 && c >= a))) {
                sb.append('c');
                c--;
                contC++;
                contA = 0;
                contB = 0;
            } else {
                break;
            }
        }
        return sb.toString();
    }
}
