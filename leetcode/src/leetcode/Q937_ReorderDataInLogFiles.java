package leetcode;

import java.util.Arrays;

/**
 * Q937. Reorder Data in Log Files.
 * Create a custom class Log.
 * It has a String list.
 * It has it's original order.
 * Implements compar to.
 * @author Jeesub Lee (jeesubl@andrew.cmu.edu)
 */
public class Q937_ReorderDataInLogFiles {

    public static String[] reorderLogFiles(String[] logs) {
        Log[] computedLogs = initLogs(logs);
        Arrays.sort(computedLogs);
        String[] result = buildResult(computedLogs);
        return result;
    }

    private static Log[] initLogs(String[] logs) {
        Log[] computedLogs = new Log[logs.length];
        for (int i = 0; i < logs.length; i++) {
            String[] newData = logs[i].split(" ");
            computedLogs[i] = new Log(i, newData);
        }
        return computedLogs;
    }

    private static String[] buildResult(Log[] logs) {
        String[] result = new String[logs.length];
        for (int i = 0; i < logs.length; i++) {
            result[i] = logs[i].toString();
        }
        return result;
    }

    private static class Log implements Comparable<Log> {
        private int order;
        private String[] data;
        private boolean isDigitLog;

        public Log(int newOrder, String[] newData) {
            order = newOrder;
            data = newData;
            if (Character.isDigit(data[1].charAt(0))) {
                isDigitLog = true;
            } else {
                isDigitLog = false;
            }
        }

        public int getOrder() {
            return order;
        }

        public String getString(int index) {
            return data[index];
        }

        public boolean isDigitLog() {
            return isDigitLog;
        }

        public boolean isLetterLog() {
            return !isDigitLog;
        }

        public int getLength() {
            return data.length;
        }

        @Override
        public int compareTo(Log other) {
            if (this.isDigitLog() && other.isDigitLog()) {
                return this.getOrder() - other.getOrder();
            }
            if (this.isLetterLog() && other.isDigitLog()) {
                return -1;
            }
            if (this.isDigitLog() && other.isLetterLog()) {
                return 1;
            }
            int len = Math.min(this.getLength(), other.getLength());
            for (int i = 1; i < len; i++) {
                if (this.getString(i).equals(other.getString(i))) {
                    continue;
                }
                return this.getString(i).compareTo(other.getString(i));
            }

            if (this.getLength() < other.getLength()) {
                return -1;
            }
            return this.getString(0).compareTo(other.getString(0));
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            for (String s : data) {
                sb.append(s).append(" ");
            }
            sb.setLength(sb.length() - 1);
            return sb.toString();
        }
    }

    public static void main(String[] args) {
        String[] logs = {"dig1 8 1 5 1","let1 art can","dig2 3 6","let2 own kit dig","let3 art zero"};
        System.out.println(Arrays.toString(reorderLogFiles(logs)));
        // output: ["let1 art can","let3 art zero","let2 own kit dig","dig1 8 1 5 1","dig2 3 6"]
    }

}
