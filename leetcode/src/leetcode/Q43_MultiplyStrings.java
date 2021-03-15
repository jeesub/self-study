package leetcode;
/**
 * Q43. Multiply Strings.
 * [Calculation]
 * Make an int array, length of num1.len + num2.len.
 * As we do multiply, from end to start, calculate and update every position.
 * 
 *   123
 *  x456
 * -----
 *    18
 *   12-
 *   6--
 *   15-
 *  10--
 *  5---
 *  12--
 *  8---
 * 4----
 * 
 * [Building String from int arr]
 * If we have leading zero, do not append a string builder.
 * If we have zero length string builder, it means the result is 0.
 * @author Jeesub Lee (jeesubl@andrew.cmu.edu)
 */
public class Q43_MultiplyStrings {

    public static String multiply(String num1, String num2) {
        int[] arr1 = buildIntArray(num1);
        int[] arr2 = buildIntArray(num2);
        int[] result = new int[arr1.length + arr2.length];
        for (int i = arr1.length - 1; i >= 0; i--) {
            for (int j = arr2.length - 1; j >= 0; j--) {
                int pos = i + j + 1;
                int res = arr1[i] * arr2[j] + result[pos];
                int carry = res / 10;
                result[pos] = res % 10;
                result[pos - 1] += carry;
            }
        }
        return buildString(result);
    }

    private static int[] buildIntArray(String num) {
        int[] arr = new int[num.length()];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (num.charAt(i) - '0');
        }
        return arr;
    }

    private static String buildString(int[] arr) {
        StringBuilder sb = new StringBuilder();
        boolean leadingZero = true;
        for (int num : arr) {
            if (leadingZero && num == 0) {
                continue;
            }
            leadingZero = false;
            sb.append(num);
        }
        return sb.length() == 0 ? "0" : sb.toString();
    }

    public static void main(String[] args) {
        String num1 = "123456789";
        String num2 = "987654321";
        System.out.println(multiply(num1, num2));
        // output: "121932631112635269"
    }

}
