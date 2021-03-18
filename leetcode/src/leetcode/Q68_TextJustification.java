package leetcode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * Q68. Text Justification.
 * 1) one word or the last line
 *    one word: Add spaces to make the line length to maxWidth
 *    the last line: Give one space between words and make the line length to maxWidth
 * 2) other case
 *    Iterate through the words array.
 *    Count the width and keep the words in Deque.
 *    If current word cannot be in the same line because of size, build a line.
 * [Build a line]
 * Evenly distribute spaces.
 * @author Jeesub Lee (jeesubl@andrew.cmu.edu)
 */
public class Q68_TextJustification {
    private static final String SPACE = " ";

    public static List<String> fullJustify(String[] words, int maxWidth) {
        List<String> result = new ArrayList<>();
        Deque<String> deque = new ArrayDeque<>();
        deque.add(words[0]);
        int width = words[0].length();
        for (int i = 1; i < words.length; i++) {
            if (width + words[i].length() + deque.size() - 1 >= maxWidth) {
                builJustifiedLine(deque, width, maxWidth, result);
                width = 0;
            }
            deque.add(words[i]);
            width += words[i].length();
        }
        buildLeftJustifiedLine(deque, maxWidth, result);
        return result;
    }

    private static void builJustifiedLine(Deque<String> deque, int width, int maxWidth, List<String> result) {
        if (deque.size() == 1) {
            buildLeftJustifiedLine(deque, maxWidth, result);
            return;
        }
        int totalSpace = maxWidth - width;
        int spaces = totalSpace / (deque.size() - 1);
        int adjustment = totalSpace - spaces * (deque.size() - 1);
        StringBuilder sb = new StringBuilder();
        sb.append(deque.removeFirst());
        while (!deque.isEmpty()) {
            sb.append(SPACE.repeat(spaces));
            if (adjustment > 0) {
                sb.append(SPACE);
                adjustment--;
            }
            sb.append(deque.removeFirst());
        }
        result.add(sb.toString());
    }

    private static void buildLeftJustifiedLine(Deque<String> deque, int maxWidth, List<String> result) {
        StringBuilder sb = new StringBuilder();
        sb.append(deque.removeFirst());
        while (!deque.isEmpty()) {
            sb.append(SPACE).append(deque.removeFirst());
        }
        String rightSpaces = SPACE.repeat(maxWidth - sb.length());
        sb.append(rightSpaces);
        result.add(sb.toString());
    }

    public static void main(String[] args) {
        String[] words = {"I", "am", "working", "on", "text", "justification."};
        int maxWidth = 16;
        System.out.println(fullJustify(words, maxWidth));
        // output:
        // "I  am working on"
        // "text            "
        // "justification.  "
    }

}
