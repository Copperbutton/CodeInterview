package interviewquestions.google;

/**
 * Find the longest subsequence of an string that has two unique character. This
 * questions can also be sovled with sliding window, but more tricky.
 */
public class LongestSubsuequence {
    public String findLongestSubStr(String str) {
        if (str == null)
            return null;

        int[] found = new int[256];
        int start = 0, end = 0;
        int minStart = 0, maxLen = 0;
        int count = 0;
        while (end < str.length()) {
            while (end < str.length()) {
                /**
                 * Key1: decide where end should stop, it stopped at the
                 * beginning of the third unique char, since we will break here,
                 * the found will not ++. If not, will have problem in count ++.
                 */
                if (found[str.charAt(end)] == 0 && count == 2)
                    break;
                found[str.charAt(end)]++;
                if (found[str.charAt(end)] == 1)
                    count++;
                end++;
            }

            /** If not found any more, should break here. */
            if (count < 2 && end == str.length())
                break;

            /**
             * Notice that length calculation is different here, in previous
             * example, will have to check if end at end of string.
             */
            int len = end - start;
            if (len > maxLen) {
                minStart = start;
                maxLen = len;
            }

            /**
             * Key 2: start wills top at the first char that makes the count =
             * 1.
             * */
            while (count > 1 && start < end) {
                found[str.charAt(start)]--;
                if (found[str.charAt(start)] == 0)
                    count--;
                start++;
            }
        }
        return str.substring(minStart, minStart + maxLen);
    }

}
