package interviewquestions.google;

import java.util.*;

/**
 * we will name a number "aggregated number" if this number has the following
 * attribute: just like the Fibonacci numbers 1,1,2,3,5,8,13.....
 * 
 * the digits in the number can divided into several parts, and the later part
 * is the sum of the former parts.
 * 
 * like 112358, because 1+1=2, 1+2=3, 2+3=5, 3+5=8 122436, because 12+24=36
 * 1299111210, because 12+99=111, 99+111=210 112112224, because 112+112=224 so
 * can you provide a function to check whether this number is aggregated number
 * or not?
 */
public class AggregratedNumber {
    public boolean isAggregratedNumber(String num) {
        if (num == null || num.length() < 3)
            return false;
        int len = num.length();
        boolean[] isAggregrate = new boolean[len + 1];
        isAggregrate[len] = true;
        for (int index = len - 3; index >= 0; index--) {
            for (int next = index + 3; next <= len; next++) {
                boolean valid = isOneAggregratedNumber(num.substring(index,
                        next));
                if (valid && isOverLapValid(isAggregrate, index + 1, next)) {
                    isAggregrate[index] = valid;
                    break;
                }
            }
        }

        return isAggregrate[0];
    }

    public boolean isOverLapValid(boolean isAggregrate[], int begin, int end) {
        for (int index = begin; index <= end; index++) {
            if (isAggregrate[index])
                return true;
        }
        return false;
    }

    public boolean isOneAggregratedNumber(String num) {
        return isOneAggregratedNumber(num, 0, new LinkedList<Integer>());
    }

    public boolean isOneAggregratedNumber(String num, int start,
            LinkedList<Integer> path) {
        if (start == num.length() || path.size() == 3) {
            if (start != num.length())
                return false;
            return isAggregrated(path);
        }

        for (int index = start; index < num.length(); index++) {
            /**
             * If encountered format error, means no need to search deeper
             */
            try {
                String substr = num.substring(start, index + 1);
                int value = Integer.parseInt(substr);
                path.add(value);
            } catch (NumberFormatException e) {
                return false;
            }
            if (isOneAggregratedNumber(num, index + 1, path))
                return true;
            path.removeLast();
        }
        return false;
    }

    public boolean isAggregrated(List<Integer> path) {
        if (path.size() != 3)
            return false;
        return path.get(0) + path.get(1) == path.get(2);
    }

    /** Iterative method to work out the aggregate number check */
    public boolean isOneAggregratedNumberItera(String num) {
        for (int i = 1; i < num.length() - 1; i++) {
            for (int j = i + 1; j < num.length(); j++) {
                try {
                    int num1 = Integer.parseInt(num.substring(0, i));
                    int num2 = Integer.parseInt(num.substring(i, j));
                    int num3 = Integer.parseInt(num.substring(j));
                    if (num1 + num2 == num3)
                        return true;
                } catch (NumberFormatException e) {
                    /** Ignore */
                }
            }
        }
        return false;

    }
}
