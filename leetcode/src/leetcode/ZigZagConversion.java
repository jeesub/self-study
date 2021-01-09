package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * numRows List, pointer, and direction boolean
 * when a pointer hit the top (0), change it to downward
 * when hit the bottom, change it to upward
 * add a char to a right list
 * After all, return the merged result
 * @author jeesublee
 *
 */
public class ZigZagConversion {

	public static String convert(String s, int numRows) {
		if (numRows <= 1) {
			return s;
		}

		List<StringBuilder> list = new ArrayList<>();
		for (int i = 0; i < numRows; i++) {
			list.add(new StringBuilder());
		}

		boolean downward = true;
		int pointer = 0;
		for (int i = 0; i < s.length(); i++) {
			list.get(pointer).append(s.charAt(i));
			if (pointer == 0) {
				downward = true;
			}
			if (pointer >= numRows - 1) {
				downward = false;
			}
			pointer = downward ? pointer + 1 : pointer - 1;
		}
		StringBuilder result = new StringBuilder();
		for (int i = 0; i < numRows; i++) {
			result.append(list.get(i));
		}
		return result.toString();
	}

	public static void main(String[] args) {
		String s = "PAYPALISHIRING";
		int numRows = 3;
		System.out.print(convert(s, numRows));
		// output: "PAHNAPLSIIGYIR"
	}

}
