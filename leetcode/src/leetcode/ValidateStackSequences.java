package leetcode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 
 * use a stack
 * iterate through the pushed array and check if we need to pop or not
 * put the current value into the stack
 * if popped[pointer] == stack's peek value, pop
 * until stack is not empty and popped[pointer] != stack's peek value
 * 
 * pushed = [0,1,2,3,4], popped = [0,3,2,4,1]
 * popped pointer = 0
 * 
 * * i = 0, stack = [0]
 *   peeked value 0 == popped[pointer(0)] 0 -> pop, pointer++ = 1
 *   stack is empty
 * * i = 1, stack = [1]
 *   peeked value 1 != popped[pointer(1)] 3
 * * i = 2, stack = [1,2]
 *   peeked value 2 != popped[pointer(1)] 3
 * * i = 3, stack = [1,2,3]
 *   peeked value 3 == popped[pointer(1)] 3 -> pop, pointer++ = 2
 *   peeked value 2 == popped[pointer(2)] 2 -> pop, pointer++ = 3
 *   peeked value 1 != popped[pointer(3)] 4
 * * i = 4, stack = [1, 4]
 *   peeked value 4 == popped[pointer(3)] 4 -> pop, pointer++ = 4
 *   peeked value 1 == popped[pointer(4)] 1 -> pop, pointer++ = 5
 *   stack is empty
 * @author jeesublee
 *
 */
public class ValidateStackSequences {
	public static boolean validateStackSequences(int[] pushed, int[] popped) {
		Deque<Integer> stack = new ArrayDeque<>();
		int pointer = 0;
		for (int num : pushed) {
			stack.addLast(num);
			while (!stack.isEmpty() && stack.peekLast() == popped[pointer]) {
				stack.removeLast();
				pointer++;
			}
		}
		return stack.isEmpty();
	}

	public static void main(String[] args) {
		int[] pushed = {0,1,2,3,4};
		int[] popped = {0,3,2,4,1};
		System.out.print(validateStackSequences(pushed, popped));
		// output: true
	}
}
