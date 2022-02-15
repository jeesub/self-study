package leetcode.LinkedList;

/**
 * 202. Happy Number.
 * [Cycle Detection]
 * Walker walks & Runner runs.
 * if runner == 1, return true.
 * if runner == walker, return false.
 * SC: O(1)
 * @author Jeesub Lee (jeesubl@andrew.cmu.edu)
 */
public class Q202_HappyNumber {
    public static boolean isHappy(int n) {
        int walker = n;
        int runner = n;
        while (true) {
            walker = cal(walker);
            runner = cal(cal(runner));
            if (runner == 1) {
                return true;
            }
            if (walker == runner) {
                return false;
            }
        }
    }

    private static int cal(int input) {
        int output = 0;
        while (input > 0) {
            output += (input % 10) * (input % 10);
            input /= 10;
        }
        return output;
    }
}
