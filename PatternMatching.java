package interviewquestions.facebook;

public class PatternMatching {
    public boolean isMatch(String str, String pattern) {
        if (str == null || pattern == null)
            return false;
        return isMatch(str, 0, pattern, 0);
    }

    private boolean isMatch(String str, int index1, String pattern, int index2) {
        if (index2 == pattern.length())
            return str.length() == index1;

        // If first character is */+, must bee *+ in a row, then can match any
        if (pattern.charAt(index2) == '*' || pattern.charAt(index2) == '+')
            return index2 > 0
                    && (pattern.charAt(index2 - 1) == '*' || pattern
                            .charAt(index2 - 1) == '+');

        // If only last char or next char not * or +
        if (index2 == pattern.length() - 1
                || (pattern.charAt(index2 + 1) != '*' && pattern
                        .charAt(index2 + 1) != '+')) {
            return index1 < str.length()
                    && str.charAt(index1) == pattern.charAt(index2)
                    && isMatch(str, index1 + 1, pattern, index2 + 1);
        
        //Next char not * or +
        } else if (pattern.charAt(index2 + 1) == '*') {
            // Zero match
            if (isMatch(str, index1, pattern, index2 + 2))
                return true;
        }
        
        // Both + and * needs to consider match one or more char
        while (index1 < str.length() && str.charAt(index1) == pattern.charAt(index2)) {
            if (isMatch(str, ++ index1, pattern, index2 + 2))
                return true;
        }
        return false;
    }
}
