package interviewquestions.other;

public class LongestConsecutiveIncreasingSubstring {
    public String longestIncrSubstring(String str) {
        if (str == null)
            return str;
        int len = str.length();
        boolean[][] inc = new boolean[len][len];
        int maxLen = 1, start = 0;
        for (int i = 0; i < len; i++) {
            for (int j = i; j < len; j++) {
                inc[i][j] = (j == i || str.charAt(j) > str.charAt(j - 1)
                        && inc[i][j - 1]);
                if (inc[i][j] && j - i + 1 > maxLen) {
                    maxLen = j - i + 1;
                    start = i;
                }
            }
        }
        return str.substring(start, start + maxLen);
    }
}
