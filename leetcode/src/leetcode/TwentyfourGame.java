package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * BTR
 * pick two numbers and calculate 
 * repeat until we only have one number
 * 
 * base case: a list has only one element
 * if the number is 24, return true
 * 
 * pick 2 numbers from a list and calculate it
 * calculation: a + b, a - b, b - a, a * b, a / b (if b != 0), b /a (if a != 0)
 * make a new list using the result of calculation and remained elements
 * iterate until we have one element in a list
 * 
 * @author jeesublee
 *
 */
public class TwentyfourGame {
	private static final Double EPSILON = 1e-4;

	public static boolean judgePoint24(int[] nums) {
		List<Double> path = new ArrayList<>();
		for (int num : nums) {
			path.add((double) num);
		}
		return judgePoint24(path);
	}

	private static boolean judgePoint24(List<Double> path) {
		if (path.size() == 1) {
			return Math.abs(path.get(0) - 24.0) < EPSILON;
		}

		for (int i = 0; i < path.size(); i++) {
			for (int j = i + 1; j < path.size(); j++) {
				List<Double> nextPath = new ArrayList<>();
				for (int k = 0; k < path.size(); k++) {
					if (k == i || k == j) {
						continue;
					}
					nextPath.add(path.get(k));
				}
				List<Double> cals = calculate(path.get(i), path.get(j));
				for (double cal : cals) {
					nextPath.add(cal);
					if (judgePoint24(nextPath)) {
						return true;
					}
					nextPath.remove(nextPath.size() - 1);
				}
			}
		}

		return false;
	}

	private static List<Double> calculate(double a, double b) {
		List<Double> res = new ArrayList<>();
		res.add(a + b);
		res.add(a - b);
		res.add(b - a);
		res.add(a * b);
		if (b != 0) {
			res.add(a / b);
		}
		if (a != 0) {
			res.add(b / a);
		}
		return res;
	}

	public static void main(String[] args) {
		int[] nums = {4, 1, 8, 7};
		System.out.print(judgePoint24(nums));
		// output: true
	}

}
