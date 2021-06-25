package leetcode.List;

import java.util.ArrayList;
import java.util.List;

/**
 * Q412. Fizz Buzz.
 * % 15 == 0 ? "FizzBuzz"
 * % 3 == 0 ? "Fizz"
 * % 5 == 0 ? "Buzz" 
 * else, i
 * TC: O(n), where n is the input integer
 * SC: O(1)
 * @author Jeesub Lee (jeesubl@andrew.cmu.edu)
 */
public class Q412_FizzBuzz {
    public static final String FIZZBUZZ = "FizzBuzz";
    public static final String FIZZ = "Fizz";
    public static final String BUZZ = "Buzz";

    public static List<String> fizzBuzz(int n) {
        List<String> result = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            if (i % 15 == 0) {
                result.add(FIZZBUZZ);
                continue;
            }
            if (i % 3 == 0) {
                result.add(FIZZ);
                continue;
            }
            if (i % 5 == 0) {
                result.add(BUZZ);
                continue;
            }
            result.add(String.valueOf(i));
        }
        return result;
    }

    public static void main(String[] args) {
        int n = 30;
        System.out.println(fizzBuzz(n));
        // output: [1, 2, Fizz, 4, Buzz, Fizz, 7, 8, Fizz, Buzz, 11, Fizz, 13, 14, FizzBuzz, 16, 17, Fizz, 19, Buzz, Fizz, 22, 23, Fizz, Buzz, 26, Fizz, 28, 29, FizzBuzz]
    }

}
