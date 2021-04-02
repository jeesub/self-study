package other;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * JSON Validation.
 * 1. {, } should be matched.
 * 2. ", " should be matched.
 * 3. [, ] should be matched.
 * 4. Between key and value, there should be :. 
 * @author Jeesub Lee (jeesubl@andrew.cmu.edu)
 */
public class JsonValidation {

    public static boolean validateJson(String s) {
        Deque<Character> deque = new ArrayDeque<>();
        char[] input = s.toCharArray();
        for (int i = 0; i < input.length; i++) {
            char c = input[i];
            System.out.print(c);
            switch (c) {
                case '{' :
                    deque.addLast(c);
                    break;
                case '}' :
                    if (!deque.isEmpty() && deque.peekLast() == ':') {
                        deque.removeLast();
                    }
                    if (deque.isEmpty() || deque.removeLast() != '{') {
                        return false;
                    }
                    break;
                case '\"' :
                    if (!deque.isEmpty() && deque.peekLast() == '\"') {
                        deque.removeLast();
                    } else {
                        deque.addLast(c);
                    }
                    break;
                case '[' :
                    deque.addLast(c);
                    break;
                case ']' :
                    while (!deque.isEmpty() && deque.peekLast() == ',') {
                        deque.removeLast();
                    }
                    if (deque.isEmpty() || deque.removeLast() != '[') {
                        return false;
                    }
                    break;
                case ',' :
                    if (!deque.isEmpty() && deque.peekLast() == ':') {
                        deque.removeLast();
                        break;
                    }
                    deque.addLast(c);
                    break;
                case ':' :
                    deque.addLast(c);
                    break;
                default :
                    break;
            }
        }
        return deque.isEmpty();
    }

    public static void main(String[] args) {
        String s = "{\"key1\":\"value1\",\"key2\":[\"e1\",\"e2\",\"e3\"],\"key3\":{\"key4\":\"value4\"}}";
        System.out.println(validateJson(s));
    }

}
