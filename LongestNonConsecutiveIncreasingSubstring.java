package interviewquestions.other;

public class LongestNonConsecutiveIncreasingSubstring {
    public int longestNonconsecutiveIncreasingSubstring(String str) {
        if (str == null)
           return 0;
        int len = str.length();
        int[] maxInc = new int[len];
        maxInc[0] = 1;
        int maxLen = 1;
        for (int i = 1; i < len; i ++) {
            for (int j = 0; j < i; j ++) {
                if (str.charAt(i) > str.charAt(j))
                    maxInc[i] = Math.max(maxInc[j] + 1, maxInc[i]);
                maxLen = Math.max(maxInc[i], maxLen);
            }
        }
        return  maxLen;
    }
}
