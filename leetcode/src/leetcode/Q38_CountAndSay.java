package leetcode;
/**
 * Q38. Count and Say.
 * A pointer walks through the String.
 * If the current char is same as before, count++.
 * Else, append previous process to a StringBuilder.
 * @author Jeesub Lee (jeesubl@andrew.cmu.edu)
 */
public class Q38_CountAndSay {

    public static String countAndSay(int n) {
        StringBuilder sb = new StringBuilder("1");
        StringBuilder result = build(sb, n - 1);
        return result.toString();
    }

    private static StringBuilder build(StringBuilder sb, int n) {
        if (n == 0) {
            return sb;
        }

        sb = build(sb, n - 1);

        StringBuilder result = new StringBuilder();
        int cnt = 1;
        for (int i = 1; i < sb.length(); i++) {
            if (sb.charAt(i) == sb.charAt(i - 1)) {
                cnt++;
                continue;
            }
            result.append(cnt);
            result.append(sb.charAt(i - 1));
            cnt = 1;
        }
        result.append(cnt);
        result.append(sb.charAt(sb.length() - 1));

        return result;
    }

    public static void main(String[] args) {
        System.out.println(countAndSay(10));
        // output: 13211311123113112211
    }

}
