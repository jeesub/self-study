package leetcode;
/**
 * Q273. Integer to English Words.
 * Repeat: Read the last 3 digits, add thousands text if needed, and num /= 1000.
 * [Read 3 digits]
 * Read the hundred's number, add 'hundred' and call a function reads ten's number.
 * [Read 2 digits larger than nineteen]
 * Read the ten's number and call a function reads the one's number.
 * [Read a number smaller than twenty]
 * Read from pre-recorded data.
 * @author Jeesub Lee (jeesubl@andrew.cmu.edu)
 */
public class Q273_IntegerToEnglishWords {
    private static final String[] THOUSANDS = {"", "Thousand", "Million", "Billion"};
    private static final String[] TENS = {"", "Ten", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};
    private static final String[] UNDER_TWENTY = {"", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"};

    public static String numberToWords(int num) {
        if (num == 0) {
            return "Zero";
        }

        StringBuilder sb = new StringBuilder();
        int thousands = 0;

        while (num > 0) {
            int lastThreeDigits = num % 1000;
            num /= 1000;

            if (lastThreeDigits == 0) {
                thousands++;
                continue;
            }

            StringBuilder tmpSb = new StringBuilder();
            tmpSb.append(readHundreds(lastThreeDigits));
            if (thousands > 0) {
                tmpSb.append(" ");
                tmpSb.append(THOUSANDS[thousands]);
            }
            if (sb.length() > 0) {
                tmpSb.append(" ");
            }
            thousands++;
            sb.insert(0, tmpSb.toString());
        }
        return sb.toString();
    }

    private static String readHundreds(int num) {
        if (num < 100) {
            return readTens(num);
        }

        StringBuilder sb = new StringBuilder();
        sb.append(UNDER_TWENTY[num / 100]);
        sb.append(" Hundred");
        if (num % 100 != 0) {
            sb.append(" ");
            sb.append(readTens(num % 100));
        }
        return sb.toString();
    }

    private static String readTens(int num) {
        if (num < 20) {
            return readUnderTwenty(num);
        }

        StringBuilder sb = new StringBuilder();
        sb.append(TENS[num / 10]);
        if (num % 10 != 0) {
            sb.append(" ");
            sb.append(readUnderTwenty(num % 10));
        }
        return sb.toString();
    }

    private static String readUnderTwenty(int num) {
        return UNDER_TWENTY[num];
    }

    public static void main(String[] args) {
        int num = 1234567890;
        System.out.println(numberToWords(num));
        // output: One Billion Two Hundred Thirty Four Million Five Hundred Sixty Seven Thousand Eight Hundred Ninety
    }

}
