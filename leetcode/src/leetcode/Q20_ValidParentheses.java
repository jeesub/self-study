package leetcode;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

/**
 * Q20. Valid Parentheses.
 * use Stack
 * if opener, addLast
 * if closer, peek and see if it is the valid pair
 *   if so, pop and continue
 *   if not, return false
 * after all, if stack is empty, return true
 * 
 * @author jeesublee
 */
public class Q20_ValidParentheses {

	public static boolean isValid(String s) {
		Deque<Character> deque = new ArrayDeque<>();
		Map<Character, Character> map = initMap();
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if (isOpener(c)) {
				deque.add(c);
			} else if (!deque.isEmpty() && map.get(c) == deque.peekLast()){
				deque.removeLast();
			} else {
				return false;
			}
		}
		return deque.isEmpty();
	}

	private static Map<Character, Character> initMap() {
		Map<Character, Character> map = new HashMap<>();
		map.put(')', '(');
		map.put(']', '[');
		map.put('}', '{');
		return map;
	}

	private static boolean isOpener(char c) {
		return c == '(' || c == '[' || c == '{';
	}

	public static void main(String[] args) {
		String s = "()[]{}";
		System.out.print(isValid(s));
	}

}
