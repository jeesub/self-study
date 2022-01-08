package leetcode.Trie;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 1268. Search Suggestions System.
 * [Trie & DFS]
 * @author Jeesub Lee (jeesubl@andrew.cmu.edu)
 */
public class Q1268_SearchSuggestionsSystem {
    private static final int NUM_OF_CHARS = 26;

    public static List<List<String>> suggestedProducts(String[] products, String searchWord) {
        Trie root = buildTrie(products);
        List<List<String>> result = new ArrayList<>();
        for (int i = 1; i <= searchWord.length(); i++) {
            result.add(searchWithPrefix(root, searchWord.substring(0, i)));
        }
        return result;
    }

    private static Trie buildTrie(String[] products) {
        Trie root = new Trie();
        for (String word : products) {
            Trie curr = root;
            for (int i = 0; i < word.length(); i++) {
                int index = word.charAt(i) - 'a';
                if (curr.children.get(index) == null) {
                    curr.children.set(index, new Trie());
                }
                curr = curr.children.get(index);
            }
            curr.isWord = true;
        }
        return root;
    }

    private static List<String> searchWithPrefix(Trie root, String prefix) {
        Trie start = root;
        for (int i = 0; i < prefix.length(); i++) {
            int index = prefix.charAt(i) - 'a';
            if (start.children.get(index) == null) {
                return new ArrayList<>();
            }
            start = start.children.get(index);
        }
        List<String> searchResult = new ArrayList<>();
        StringBuilder sb = new StringBuilder(prefix);
        dfs(searchResult, start, sb);
        return searchResult;
    }

    private static void dfs(List<String> searchResult, Trie curr, StringBuilder sb) {
        if (searchResult.size() >= 3) {
            return;
        }

        if (curr.isWord) {
            searchResult.add(sb.toString());
        }

        for (int i = 0; i < NUM_OF_CHARS; i++) {
            if (curr.children.get(i) == null) {
                continue;
            }
            dfs(searchResult, curr.children.get(i), sb.append((char) ('a' + i)));
            sb.setLength(sb.length() - 1);
        }
    }

    private static class Trie {
        private List<Trie> children = Arrays.asList(new Trie[NUM_OF_CHARS]);
        private boolean isWord;
    }

    public static void main(String[] args) {
        String[] products = {"pen", "app", "apple", "pineapple", "application", "all"};
        String searchWord = "apple";
        System.out.println(suggestedProducts(products, searchWord));
        // output: [[all, app, apple], [app, apple, application], [app, apple, application], [apple, application], [apple]]
    }
}
