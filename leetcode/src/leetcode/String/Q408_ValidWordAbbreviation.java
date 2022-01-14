package leetcode.String;

/**
 * 408. Valid Word Abbreviation.
 * [Two Pointers]
 * internationalization
 * ^ i
 * i12iz4n
 * ^ j
 *
 * if i == word.length() && j == abbr.length(), return true
 * if char[i] == char[j], continue
 * else if char[j] is not a number or a leading zero, return false
 * else, read the number and move the i pointer. i += number
 *
 * TC: O(n), where n = abbr's length
 * SC: O(n)
 * @author Jeesub Lee (jeesubl@andrew.cmu.edu)
 */
public class Q408_ValidWordAbbreviation {
    public static boolean validWordAbbreviation(String word, String abbr) {
        int i = 0;
        int j = 0;
        while (i < word.length() && j < abbr.length()) {
            if (word.charAt(i) == abbr.charAt(j)) {
                i++;
                j++;
                continue;
            }

            if (!Character.isDigit(abbr.charAt(j)) || abbr.charAt(j) == '0') {
                return false;
            }

            int num = 0;
            while (j < abbr.length() && Character.isDigit(abbr.charAt(j))) {
                num *= 10;
                num += Character.getNumericValue(abbr.charAt(j));
                j++;
            }
            i += num;
        }

        return i == word.length() && j == abbr.length();
    }

    public static void main(String[] args) {
        String word = "internationalization";
        String abbr = "i12iz4n";
        System.out.println(validWordAbbreviation(word, abbr));
        // output: true
    }
}
