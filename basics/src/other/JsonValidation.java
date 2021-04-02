package other;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * JSON Validation.
 * {, }, [, ], and " should be matched.
 * : should come between key and value.
 * , should come between elements in an array, or key value pairs.
 * @author Jeesub Lee (jeesubl@andrew.cmu.edu)
 */
public class JsonValidation {

    public static boolean validateJson(String s) {
        Deque<Character> deque = new ArrayDeque<>();
        char[] input = s.toCharArray();
        for (int i = 0; i < input.length; i++) {
            char c = input[i];
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
        String s1 = "{\"key1\":\"value1\",\"key2\":[\"e1\",\"e2\",\"e3\"],\"key3\":{\"key4\":\"value4\"}}";
        String s2 = "{\"key1\":\"value1\",\"key2\":[{\"arrkey1\":\"arrval1\",\"arrkey2\":\"arrval2\"},\"e2\",\"e3\"],\"key3\":{\"key4\":\"value4\"},\"key5\":\"value6\"}";
        String s3 = "{\"key1\":\"value1\",\"key2\":[{\"arrkey1\":\"arrval1\",\"arrkey2\":\"arrval2\"},\"e2\",\"e3\"],\"key3\":{\"key4\":\"value4\"}}}";
        String s4 = "{{\"key1\":\"value1\",\"key2\":[{\"arrkey1\":\"arrval1\",\"arrkey2\":\"arrval2\"},\"e2\",\"e3\"],\"key3\":{\"key4\":\"value4\"},\"key5\":\"value6\"}";
        System.out.println(validateJson(s1)); // output: true
        System.out.println(validateJson(s2)); // output: true
        System.out.println(validateJson(s3)); // output: false
        System.out.println(validateJson(s4)); // output: false
    }

}
