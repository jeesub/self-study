package leetcode.StackQueue;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/**
 * [Stack]
 * To collide,
 * condition 1. previous asteroid should be positive
 * condition 2. current asteroid should be negative
 *
 * Iterate through the input array.
 * 1. curr is positive -> cannot meet
 * if (curr > 0), offer and continue
 * 2. break every smaller asteroids if curr can break previous asteroids
 * While (stack is not empty && peek value is positive && abs(peek value) < abs(curr value)), pop.
 * 3. cannot meet
 * if (stack is empty || peek value is negative),
 * offer and continue.
 * 4. both should explode
 * if (abs(curr) == abs(peek value)), pop and continue.
 * 5. curr should explode
 * continue.
 *
 * TC: O(n)
 * SC: O(n)
 * @author Jeesub Lee (jeesubl@andrew.cmu.edu)
 */
public class Q735_AsteroidCollision {
    public static int[] asteroidCollision(int[] asteroids) {
        Deque<Integer> deque = new ArrayDeque<>();
        for (int curr : asteroids) {
            if (curr > 0) {
                deque.addLast(curr);
                continue;
            }

            while (!deque.isEmpty()
                    && deque.peekLast() > 0
                    && Math.abs(deque.peekLast()) < Math.abs(curr)) {
                deque.removeLast();
            }
            if (deque.isEmpty() || deque.peekLast() < 0) {
                deque.addLast(curr);
                continue;
            }
            if (Math.abs(deque.peekLast()) == Math.abs(curr)) {
                deque.removeLast();
            }
        }

        int[] result = new int[deque.size()];
        for (int i = 0; i < result.length; i++) {
            result[i] = deque.remove();
        }

        return result;
    }

    public static void main(String[] args) {
        int[] asteroids = {2, 1, -2, 3, 4};
        System.out.println(Arrays.toString(asteroidCollision(asteroids)));
        // output: [3, 4]
    }
}
