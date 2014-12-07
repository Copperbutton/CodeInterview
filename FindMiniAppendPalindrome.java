package interviewquestions.facebook;

public class FindMiniAppendPalindrome {
    public int miniAppendPalindrome(String str) {
        if (str == null || str.length() == 0)
            return 0;
        int len = str.length();
        boolean[] isPalindrome = new boolean[len];
        int last = len - 1;
        for (int i = len - 1; i >= 0; i--) {
            boolean isSubPalindrome = true;
            for (int j = i; j < len; j++) {
                boolean tmp = isPalindrome[j];
                isPalindrome[j] = (str.charAt(i) == str.charAt(j)) && (i + 2 > j || isSubPalindrome);
                isSubPalindrome = tmp;
            }
            if (isPalindrome[len - 1])
                last = i;
        }
        return last;
    }

}
