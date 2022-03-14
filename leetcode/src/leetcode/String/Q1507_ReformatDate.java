package leetcode.String;

import java.util.HashMap;
import java.util.Map;

/**
 * 1507. Reformat Date.
 * [String]
 * 1. Split the String by spaces.
 * 2. Read the year.
 * 3. Read the month and use a HashMap<String Month, Number Month> to convert.
 * 4. Read the day and make sure it has two digits.
 * @author Jeesub Lee (jeesubl@andrew.cmu.edu)
 */
public class Q1507_ReformatDate {
    private static Map<String, String> monthMap;
    private static final String DASH = "-";

    public Q1507_ReformatDate() {
        if (monthMap == null) {
            monthMap = new HashMap<>();
            monthMap.put("Jan", "01");
            monthMap.put("Feb", "02");
            monthMap.put("Mar", "03");
            monthMap.put("Apr", "04");
            monthMap.put("May", "05");
            monthMap.put("Jun", "06");
            monthMap.put("Jul", "07");
            monthMap.put("Aug", "08");
            monthMap.put("Sep", "09");
            monthMap.put("Oct", "10");
            monthMap.put("Nov", "11");
            monthMap.put("Dec", "12");
        }
    }

    public String reformatDate(String date) {
        String[] input = date.split(" ");
        StringBuilder sb = new StringBuilder();
        sb.append(input[2]);
        sb.append(DASH);
        sb.append(monthMap.get(input[1]));
        sb.append(DASH);
        if (input[0].length() == 4) {
            sb.append(input[0].substring(0, 2));
        } else {
            sb.append("0").append(input[0].substring(0, 1));
        }
        return sb.toString();
    }
}
