package interviewquestions.facebook;

public class RecrusiveWildCardMatch {
    public boolean isMatch(String str, String pattern) {
        return isMatch(str, 0, pattern, 0);
    }

    private boolean isMatch(String str, int index1, String pattern, int index2) {
        if (index2 == pattern.length())
            return str.length() == index1;

        if (pattern.charAt(index2) != '*') {
            if (index1 >= str.length()
                    || (pattern.charAt(index2) != '.' && pattern.charAt(index2) != str
                            .charAt(index1)))
                return false;
            else
                return isMatch(str, index1 + 1, pattern, index2 + 1);
        }

        for (int i = index1; i <= str.length(); i++) {
            if (isMatch(str, i, pattern, index2 + 1))
                return true;
        }
        return false;
    }
}
