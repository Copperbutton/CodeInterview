package interviewquestions.google;

import java.util.Arrays;

/**
 * Given two strings a and b, find whether any anagram of string a is a
 * sub-string of string b. For eg: if a = xyz and b = afdgzyxksldfm then the
 * program should return true.
 */
public class AnagramSubString {
    public boolean isAnagramSubString(String src, String target) {
        if (src == null || target == null || src.length() < target.length())
            return false;

        /** Use one array to store expected, another to store found */
        int[] expected = new int[256];
        int[] found = new int[256];
        for (int i = 0; i < target.length(); i++)
            expected[target.charAt(i)]++;

        for (int i = 0; i <= src.length() - target.length(); i++) {
            for (int j = 0; j < target.length(); j++) {
                found[src.charAt(i + j)]++;
                if (found[src.charAt(i + j)] > expected[src.charAt(i + j)])
                    break;
                /** If not break until end, return true */
                if (j == target.length() - 1)
                    return true;
            }
            /** Clean up found record every time i increase */
            Arrays.fill(found, 0);
        }
        return false;
    }
}
