package interviewquestions.facebook;

/**
 * Given a mapping of alphabets to integers as follows:
 * 
 * 1 = A 2 = B 3 = C ... 26 = Z
 * 
 * Given any combination of the mapping numbers as string, return the number of
 * ways in which the input string can be split into sub-strings and represented
 * as character strings. For e.g. given "111" -> "AAA", "AK", "KA" -> 3
 */
public class AlphabeticTable {
    public int combinations(String src) {
        if (src == null || src.length() == 0)
            return 0;
        int len = src.length();
        int[] comb = new int[len + 1];
        comb[len] = 1;
        for (int i = len - 1; i >= 0; i--) {
            if (src.charAt(i) == '0')
                continue;

            comb[i] = comb[i + 1];
            if (i < len - 1) {
                int num = Integer.parseInt(src.substring(i, i + 2));
                if (num > 0 && num < 27)
                    comb[i] += Math.max(i, comb[i + 2]);
            }
        }
        return comb[0];
    }
}
