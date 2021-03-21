package leetcode;
/**
 * Q415. Add Strings.
 * Use two pointers.
 * Iterate from end to start while num1 isn't finished or num2 isn't finished, or carry is not zero.
 * Append to stringbuiler and after iteration, reverse it.
 * @author Jeesub Lee (jeesubl@andrew.cmu.edu)
 */
public class Q415_AddStrings {

    public static String addStrings(String num1, String num2) {
        StringBuilder sb = new StringBuilder();
        int ptr1 = num1.length() - 1;
        int ptr2 = num2.length() - 1;
        int carry = 0;
        while (ptr1 >= 0 || ptr2 >= 0 || carry != 0) {
            int cur1 = ptr1 >= 0 ? (int) Character.getNumericValue(num1.charAt(ptr1)) : 0;
            int cur2 = ptr2 >= 0 ? (int) Character.getNumericValue(num2.charAt(ptr2)) : 0;
            int sum = cur1 + cur2 + carry;
            carry = sum / 10;
            sb.append(sum % 10);
            ptr1--;
            ptr2--;
        }
        return sb.reverse().toString();
    }

    public static void main(String[] args) {
        String num1 = "314159265358979";
        String num2 = "271828182845904523536";
        System.out.println(addStrings(num1, num2));
        // output: 271828497005169882515
    }

}
