package leetcode.String;

/**
 * 616. Add Bold Tag in String.
 * [String]
 * Iterate through the string and check if we have (s.startsWith(word, i)).
 * If so, mark the interval as a bold.
 * TC: O(m * n * l), m = s.length(), n = words.length, l = maxLength(word)
 * SC: O(m)
 * @author Jeesub Lee (jeesubl@andrew.cmu.edu)
 */
public class Q616_AddBoldTagInString {
    public static String addBoldTag(String s, String[] words) {
        boolean[] bold = new boolean[s.length()];
        for (int i = 0; i < s.length(); i++) {
            int end = -1;
            for (String word : words) {
                if (!s.startsWith(word, i)) {
                    continue;
                }
                end = Math.max(end, i + word.length() - 1);
            }
            if (end == -1) {
                continue;
            }
            for (int k = i; k <= end; k++) {
                bold[k] = true;
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0, j = 0; i < s.length(); i++) {
            if (!bold[i]) {
                sb.append(s.charAt(i));
                continue;
            }
            j = i;
            while (j < s.length() && bold[j]) {
                j++;
            }
            sb.append("<b>");
            sb.append(s.substring(i, j));
            sb.append("</b>");
            i = j - 1;
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        String s = "ThisIsAnExampleString";
        String[] words = {"Exam", "ample", "Str", "ring"};
        System.out.println(addBoldTag(s, words));
        // output: ThisIsAn<b>ExampleString</b>
    }
}
