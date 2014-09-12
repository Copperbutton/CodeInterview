/**
 * Write a function return an integer that satisfies the following conditions:
 * 1) positive integer 2) no repeated digits, eg., 123 (valid), 122 (invalid) 3)
 * incremental digit sequence, eg., 1234 (valid) 1243(invalid) 4) the returned
 * integer MUST be the smallest one that greater than the input. eg., input=987,
 * return=1023
 * 
 * function signature could be like this: String nextInteger(String input)
 */
package interviewquestions.google;

import java.util.Arrays;

public class NextInteger {
    public String nextInteger(String inte) {
        int len = inte.length();
        char[] chs = inte.toCharArray();
        char[] res = new char[len + 1];
        if (len > 0 && chs[0] + len - 1 <= '9'
                && findNextInteger(chs, res, 0, false))
            return new String(Arrays.copyOfRange(res, 1, len + 1));
        for (int i = 0; i < len + 1; i++)
            res[i] = (char) ('1' + i);
        return new String(res);
    }

    private boolean findNextInteger(char[] chs, char[] res, int index,
            boolean incOrder) {
        if (index == chs.length) {
            return true;
        }
        char base = (char) (res[index] + 1);
        if (!incOrder) {
            base = (char) Math.max(base, chs[index]);
            if (index == chs.length - 1)
                base = (char) Math.max(base, chs[index] + 1);
        }

        boolean found = false;
        for (char ch = base; ch <= '9'; ch++) {
            if (!incOrder && ch > chs[index])
                incOrder = true;
            res[index + 1] = ch;
            if (findNextInteger(chs, res, index + 1, incOrder)) {
                found = true;
                break;
            }
        }
        return found;
    }
}
