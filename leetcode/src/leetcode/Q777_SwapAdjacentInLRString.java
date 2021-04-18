package leetcode;
/**
 * Q777. Swap Adjacent in LR String.
 * 1. L and R cannot be swapped. The order should be same in the start and the end.
 * 2 - 1. end = XXXR: possible starts = (RXXX, XRXX, XXRX, XXXR)
 *        -> R in the start should be on the left side of the end's R
 * 2 - 2. end = LXXX: possible starts = (LXXX, XLXX, XXLX, XXXL)
 *        -> L in the end should be on the left side of the start's L
 * @author Jeesub Lee (jeesubl@andrew.cmu.edu)
 */
public class Q777_SwapAdjacentInLRString {

    public static boolean canTransform(String start, String end) {
        if (!start.replace("X", "").equals(end.replace("X", ""))) {
            return false;
        }

        char[] s = start.toCharArray();
        char[] e = end.toCharArray();

        int j = 0;
        for (int i = 0; i < e.length; i++) {
            if (e[i] == 'R') {
                while (s[j] != 'R') {
                    j++;
                }
                if (j > i) {
                    return false;
                }
                j++;
            }
        }

        j = 0;
        for (int i = 0; i < s.length; i++) {
            if (s[i] == 'L') {
                while (e[j] != 'L') {
                    j++;
                }
                if (j > i) {
                    return false;
                }
                j++;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        String start = "RXXLRXRXL";
        String end = "XRLXXRRLX";
        System.out.println(canTransform(start, end));
        // output: true
    }

}
