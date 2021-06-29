package leetcode.Trie;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Q212. Word Search II.
 * [Trie, DFS, and BTR]
 * Store a Map<Character, Trie> and String word in Trie.
 * DFS & BTR.
 * TC: O(m * 3^l), where m is the number of elements in the board and l is the length of the longest word
 * SC: O(n), where n is the number of letters
 * @author Jeesub Lee (jeesubl@andrew.cmu.edu)
 */
public class Q212_WordSearchII {

    private static final int[][] DIRS = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };

    public static List<String> findWords(char[][] board, String[] words) {
        Trie root = buildTrie(words);
        List<String> result = new ArrayList<>();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (root.children.containsKey(board[i][j])) {
                    boolean[][] visited = new boolean[board.length][board[0].length];
                    visited[i][j] = true;
                    dfs(board, visited, result, root.children.get(board[i][j]), i, j);
                }
            }
        }
        return result;
    }

    private static class Trie {
        Map<Character, Trie> children;
        String word;

        private Trie() {
            children = new HashMap<>();
            word = null;
        }
    }

    private static Trie buildTrie(String[] words) {
        Trie root = new Trie();
        for (String word : words) {
            expandTrie(root, word, 0);
        }
        return root;
    }

    private static void expandTrie(Trie curr, String word, int pointer) {
        if (pointer >= word.length()) {
            curr.word = word;
            return;
        }

        if (!curr.children.containsKey(word.charAt(pointer))) {
            curr.children.put(word.charAt(pointer), new Trie());
        }
        expandTrie(curr.children.get(word.charAt(pointer)), word, pointer + 1);
    }

    private static void dfs(char[][] board, boolean[][] visited, List<String> result, Trie curr, int row, int col) {
        if (curr.word != null) {
            result.add(curr.word);
            curr.word = null;
        }

        for (int[] dir : DIRS) {
            int newRow = row + dir[0];
            int newCol = col + dir[1];
            if (!isValid(board, newRow, newCol) || visited[newRow][newCol]) {
                continue;
            }
            if (!curr.children.containsKey(board[newRow][newCol])) {
                continue;
            }
            visited[newRow][newCol] = true;
            dfs(board, visited, result, curr.children.get(board[newRow][newCol]), newRow, newCol);
            visited[newRow][newCol] = false;
        }
    }

    private static boolean isValid(char[][] board, int row, int col) {
        return row >= 0 && col >= 0 && row < board.length && col < board[0].length;
    }

    public static void main(String[] args) {
        char[][] board = {{'o','a','a','n'},{'e','t','a','e'},{'i','h','k','r'},{'i','f','l','v'}};
        String[] words = {"oath","pea","eat","rain"};
        System.out.println(findWords(board, words));
        // output: [oath, eat]
    }

}
