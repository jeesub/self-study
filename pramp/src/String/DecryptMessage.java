package String;

/**
 * Decrypt Message.
 * [String Serialize & Deserialize]
 *
 * a-z: 97 - 122
 *
 * enc[n] = dec[n] + enc[n - 1] - 26x // 97 <= enc[n] <= 122, n > 0
 * dec[n] = enc[n] - enc[n - 1] + 26x // 97 <= dec[n] <= 122, n > 0
 *
 * enc[0] = dec[0] + 1
 * if (enc[0] > 122), enc[0] = enc[0] - 26
 *
 * dec[0] = enc[0] - 1
 * if (dec[0] < 97), dec[0] = dec[0] + 26
 *
 * TC: O(n)
 * SC: O(n)
 * @author Jeesub Lee (jeesubl@andrew.cmu.edu)
 */
public class DecryptMessage {
    static String decrypt(String word) {
        if (word.length() == 0) {
            return "";
        }

        StringBuilder sb = new StringBuilder();

        int first = word.charAt(0) - 1;
        if (first < 97) {
            first += 26;
        }
        sb.append((char) first);

        for (int i = 1; i < word.length(); i++) {
            int dec = word.charAt(i) - word.charAt(i - 1);
            while (dec < 97) {
                dec += 26;
            }
            sb.append((char) dec);
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        String word = "dnotq";
        System.out.println(decrypt(word));
        // output: crime
    }
}
