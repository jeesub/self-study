package leetcode.Graph;

/**
 * 277. Find the Celebrity.
 * [Graph]
 * person[i] is a candidate
 * if person[i] knows person[j],
 *     person[i] is not a celebrity.
 *     next celebrity candidate = person[j]
 *     i = j, j = j + 1
 * if person[i] doesn't know person[j],
 *     person[j] is not a celebrity.
 *     j++
 * if j >= n, break
 *
 * if there is a celebrity, it should be person[i]
 * person[i] doesn't know person[i + 1]..person[n - 1]
 * check if person[i] doesn't konw person[0]..person[i - 1]
 * check if person[0]..person[n - 1] know person[i]
 * TC: O(n)
 * SC: O(1)
 * @author Jeesub Lee (jeesubl@andrew.cmu.edu)
 */
public class Q277_FindTheCelebrity {
    public int findCelebrity(int n) {
        int celeb = 0;
        for (int i = 1; i < n; i++) {
            if (knows(celeb, i)) {
                celeb = i;
            }
        }

        for (int i = 0; i < celeb; i++) {
            if (knows(celeb, i)) {
                return -1;
            }
        }

        for (int i = 0; i < n; i++) {
            if (!knows(i, celeb)) {
                return -1;
            }
        }

        return celeb;
    }

    private boolean knows(int a, int b) {
        return false;
    }
}
