package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Q22. Generate Parentheses.
 * BTR
 * track the opened parentheses number and remained number
 * We can open when remained opener number > 0
 * remained opener number decrease when we open
 * We can close when remained closer number > 0
 * remained closer number increase when we open
 * remained closer number decrease when we close
 * if the number of opener and closer are zero, add it into the result
 * 
 * @author jeesublee
 */
public class Q22_GenerateParentheses {

	public static List<String> generateParenthesis(int n) {
		List<String> result = new ArrayList<>();
		buildParenthesis(new StringBuilder(), result, n, 0);
		return result;
	}

	private static void buildParenthesis(StringBuilder path, List<String> result, int opener, int closer) {
		if (opener == 0 && closer == 0) {
			result.add(path.toString());
			return;
		}
		int size = path.length();
		if (opener > 0) {
			path.append("(");
			buildParenthesis(path, result, opener - 1, closer + 1);
		}
		path.setLength(size);
		if (closer > 0) {
			path.append(")");
			buildParenthesis(path, result, opener, closer - 1);
		}
		return;
	}

	public static void main(String[] args) {
		System.out.print(generateParenthesis(3));
		// output: ["((()))","(()())","(())()","()(())","()()()"]
	}

}
