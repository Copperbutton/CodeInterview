package interviewquestions.facebook;

/**
 * Retrieve the number of palindromes in a string.
 */
public class NumOfPalindrome {
    public int numOfPalindrome(String src) {
        if (src == null || src.length() == 0)
            return 0;
        int len = src.length();
        boolean[] isPalindrome = new boolean[len];
        int count = 0;
        for (int i = len - 1; i >= 0; i--) {
            boolean prev = isPalindrome[i];
            for (int j = i; j < len; j++) {
                boolean current = isPalindrome[i];
                isPalindrome[j] = src.charAt(i) == src.charAt(j)
                        && (i + 2 > j || prev);
                count += isPalindrome[j] ? 1 : 0;
                prev = current;
            }
        }
        
        return count;
    }
}
