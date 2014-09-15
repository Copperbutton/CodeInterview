package interviewquestions.other;

/**
 * This is an extension to the previous version with only stars, described in my
 * other post: Wildcard Match: Star Only. This time the strings may contain one
 * or more special characters * and ?, where a * can match to 0 or more
 * consecutive characters in the other string, while a ? can match to exactly
 * one character. For example, *ab*cd?y and ?a*dxy match:
 */
public class WieldcardMatchingSymmetric {
    public boolean isMatch(String str1, String str2) {
        int len1 = str1.length(), len2 = str2.length();
        boolean[][] match = new boolean[len1 + 1][len2 + 1];
        match[len1][len2] = true;
        match[len1 - 1][len2] = str1.charAt(len1 - 1) == '*';
        match[len1][len2 - 1] = str2.charAt(len2 - 1) == '*';
        for (int i = len1 - 1; i >= 0; i--) {
            for (int j = len2 - 1; j >= 0; j--) {
                if (str1.charAt(i) != '*' && str2.charAt(j) != '*') {
                    match[i][j] = ((str1.charAt(i) == str2.charAt(j)
                            || str1.charAt(i) == '?' || str2.charAt(j) == '?'))
                            && match[i + 1][j + 1];
                } else {
                    match[i][j] = (match[i + 1][j] || match[i][j + 1]);
                }
            }
        }
        return match[0][0];
    }
}
