package leetcode;
/**
 * Q415. Add Strings.
 * Pointer1 starts at num1's end and decreases.
 * Pointer2 starts at num2's end and decreases.
 * while (num1 has elements || num2 has elements || we have a carry)
 *     append the one-digit of calculated number to the result
 *     get a new carry and pass it to the next loop
 * After all, return the reversed result.
 * @author Jeesub Lee (jeesubl@andrew.cmu.edu)
 */
public class Q415_AddStrings {

    public static String addStrings(String num1, String num2) {
        StringBuilder revResult = new StringBuilder();
        int carry = 0;
        int pointer1 = num1.length() - 1;
        int pointer2 = num2.length() - 1;
        while (pointer1 >= 0 || pointer2 >= 0 || carry != 0) {
            int curr1 = pointer1 >= 0 ? 
                Character.getNumericValue(num1.charAt(pointer1)) : 0;
            int curr2 = pointer2 >= 0 ? 
                Character.getNumericValue(num2.charAt(pointer2)) : 0;
            int curr = curr1 + curr2 + carry;
            
            carry = curr / 10;
            curr = curr % 10;
            revResult.append(String.valueOf(curr));
            
            pointer1--;
            pointer2--;
        }
        return revResult.reverse().toString();
    }

    public static void main(String[] args) {
        String num1 = "314159265358979";
        String num2 = "271828182845904523536";
        System.out.println(addStrings(num1, num2));
        // output: 271828497005169882515
    }

}
