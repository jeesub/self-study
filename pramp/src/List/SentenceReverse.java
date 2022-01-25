package List;

import java.util.Arrays;

/**
 * [List]
 * 1. Reverse the input array.
 * 2. Reverse each word.
 * Reverse: Swap(start, end) while start++ < end--
 * @author Jeesub Lee (jeesubl@andrew.cmu.edu)
 */
public class SentenceReverse {
    private static final char SPACE = ' ';

    static char[] reverseWords(char[] arr) {
        reverseArray(arr, 0, arr.length - 1);
        int start = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == SPACE) {
                reverseArray(arr, start, i - 1);
                start = i + 1;
            }
        }
        reverseArray(arr, start, arr.length - 1);
        return arr;
    }

    private static void reverseArray(char[] arr, int start, int end) {
        while (start < end) {
            char tmp = arr[start];
            arr[start] = arr[end];
            arr[end] = tmp;
            start++;
            end--;
        }
    }

    public static void main(String[] args) {
        char[] arr = {'a', ' ', 'b', 'c', 'd', ' ', 'e'};
        System.out.println(Arrays.toString(reverseWords(arr)));
        // output: [e,  , b, c, d,  , a]
    }
}
