package leetcode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Q394. Decode String.
 * use a character stack
 * iterate through the input string and put characters into the stack
 * if a character is ']', pop from stack and decode
 * after decoding, re-input the built string into the stack
 * @author jeesublee
 *
 */
public class Q394_DecodeString {

	public static String decodeString(String s) {
		Deque<Character> deque = new ArrayDeque<>();
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if (c == ']') {
				// 1. get every characters
				StringBuilder reversed = new StringBuilder();
				while (!deque.isEmpty() && deque.peekLast() != '[') {
					reversed.append(deque.removeLast());
				}
				// 2. skip the open bracket
				deque.removeLast();
				// 3. get a number of repeat
				int num = 0;
				int base = 1;
				while (!deque.isEmpty() && Character.isDigit(deque.peekLast())) {
					num += (deque.removeLast() - '0') * base;
					base *= 10;
				}
				// 4. decode
				StringBuilder decoded = new StringBuilder();
				while (num > 0) {
					decoded.append(reversed);
					num--;
				}
				// 5. input every decoded characters into the stack
				for (int j = decoded.length() - 1; j >= 0; j--) {
					deque.addLast(decoded.charAt(j));
				}
			} else {
				deque.addLast(c);
			}
		}
		StringBuilder result = new StringBuilder();
		while (!deque.isEmpty()) {
			result.append(deque.removeFirst());
		}
		return result.toString();
	}

	public static void main(String[] args) {
		String s = "2[abc]3[cd]ef";
		System.out.print(decodeString(s));
		// output: "abcabccdcdcdef"
	}

}
