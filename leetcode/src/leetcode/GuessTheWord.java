package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 
 * first, get the result with a word on the list
 * pick a word that has as many common letters as possible with words in the list 
 * we'll get the result from 0 to 6
 * if the result is 6, we found it!
 * else, make a new list using the word we used and the number we got from the master.guess()
 * ex) if we got 2 from master.guess("abcdef"), make a new list that has 2-matches with "abcdef"
 * do the same thing again until a size of the list is 1
 * @author jeesublee
 *
 */
public class GuessTheWord {
	// dummy Master
	interface Master {
		String secret = "abcdef";
		public default int guess(String word) {
			return countMatches(secret, word);
		}
	}

	public static void findSecretWord(String[] wordlist, Master master) {
		List<String> list = Arrays.asList(wordlist);

		while (list.size() > 1) {
			String guessedWord = getTheBestCandidate(list);
			int matches = master.guess(guessedWord);
			list = getMatches(guessedWord, matches, list);
		}
		master.guess(list.get(0));
	}

	private static String getTheBestCandidate(List<String> list) {
		String bestString = "";
		int bestNum = Integer.MIN_VALUE;
		for (String s : list) {
			int newNum = 0;
			for (String o : list) {
				newNum += countMatches(s, o);
			}
			if (newNum > bestNum) {
				bestNum = newNum;
				bestString = s;
			}
		}
		return bestString;
	}

	private static List<String> getMatches(String guessedWord, int matches, List<String> list) {
		List<String> tmpList = new ArrayList<>();
		for (String s : list) {
			int cnt = countMatches(guessedWord, s);
			if (cnt == matches) {
				tmpList.add(s);
			}
		}
		return tmpList;
	}

	private static Integer countMatches(String a, String b) {
		int cnt = 0;
		for (int i = 0; i < a.length(); i++) {
			if (a.charAt(i) == b.charAt(i)) {
				cnt++;
			}
		}
		return cnt;
	}
}
