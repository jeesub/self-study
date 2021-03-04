package leetcode;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Q139. Word Break.
 * [DP]
 * From the end to start, iterate and check if we can make a word.
 * If possible, dp[start of the word] = dp[end of the word + 1]
 * @author Jeesub Lee (jeesubl@andrew.cmu.edu)
 */
public class Q139_WordBreak {

    public static boolean wordBreak(String s, List<String> wordDict) {
        Set<String> wordsSet = buildSet(wordDict);
        boolean[] dp = new boolean[s.length() + 1];
        dp[s.length()] = true;
        
        for (int i = s.length() - 1; i >= 0; i--) {
            for (int j = i + 1; j <= s.length(); j++) {
                String word = s.substring(i, j);
                if (wordsSet.contains(word) && dp[i + word.length()]) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[0];
    }
    
    private static Set<String> buildSet(List<String> wordDict) {
        Set<String> wordsSet = new HashSet<>();
        for (String word : wordDict) {
            wordsSet.add(word);
        }
        return wordsSet;
    }

    public static void main(String[] args) {
        String s = "catsandog";
        List<String> wordDict = List.of("cats", "dog", "sand", "and", "cat");
        System.out.println(wordBreak(s, wordDict));
    }

}
