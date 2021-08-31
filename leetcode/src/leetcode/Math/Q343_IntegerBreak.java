package leetcode.Math;
/**
 * Q343. Integer Break.
 * [Math]
 * Evenly distribute the number into 2 to n elements.
 * We can break if current result is less than prev result.
 * ex) n = 10
 * # of elements = 2) 5 * 5 = 25
 * # of elements = 3) 3 * 3 * 4 = 36
 * # of elements = 4) 2 * 2 * 3 * 3 = 36
 * # of elements = 5) 2 * 2 * 2 * 2 * 2 = 25 // No need to go further
 * # of elements = 6) 1 * 1 * 2 * 2 * 2 * 2 = 16
 * TC: O(n)
 * SC: O(1)
 * @author Jeesub Lee (jeesubl@andrew.cmu.edu)
 */
public class Q343_IntegerBreak {

    public static int integerBreak(int n) {
        int max = 0;
        for (int i = 2; i <= n; i++) {
            int distributedNum = n / i;
            int numOfLargeNum = n - i * distributedNum;
            int numOfSmallNum = i - numOfLargeNum;
            
            int newMax = 1;
            while (numOfSmallNum-- > 0) {
                newMax *= distributedNum;
            }
            while (numOfLargeNum-- > 0) {
                newMax *= (distributedNum + 1);
            }
            if (newMax < max) {
                return max;
            }
            max = newMax;
        }
        return max;
    }

    public static void main(String[] args) {
        int n = 10;
        System.out.println(integerBreak(n));
        // output: 36
    }

}
