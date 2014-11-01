package interviewquestions.google;

import java.util.Arrays;

/**
 * Assume number are all non negative.
 */
public class KSwapMax {
    public int findMaxWithKSwap(int num, int K) {
        if (num < 10 || K == 0)
            return num;

        int[] digits = convertToDigits(num);
        for (int i = 0; i < K; i++) {
            if (!swapToMax(digits))
                break;
        }
        return convertToInt(digits);
    }

    private int[] convertToDigits(int num) {
        int maxInLen = 10;
        int[] digits = new int[maxInLen];
        int index = maxInLen - 1;
        while (num > 0) {
            digits[index--] = num % 10;
            num /= 10;
        }
        return Arrays.copyOfRange(digits, index + 1, maxInLen);
    }

    /**
     * Should consider Integer.MAX_VALUE here. If overflow, should return the
     * max number.
     */
    private int convertToInt(int[] digits) {
        int num = 0;
        for (int dig : digits) {
            if (num != 0 && Integer.MAX_VALUE / num < 10
                    || (Integer.MAX_VALUE /10  == num && dig == 8))
                return Integer.MAX_VALUE;

            num = num * 10 + dig;
        }
        return num;
    }

    private boolean swapToMax(int[] digits) {
        int right = digits.length - 1;
        int left = digits.length - 1;
        for (int end = digits.length - 1; end > 0; end--) {
            for (int start = end - 1; start >= 0; start--) {
                if (digits[start] < digits[end] && start <= left) {
                    if (start < left) {
                        right = end;
                        left = start;
                        /** left = start */
                    } else if (digits[end] > digits[right]) {
                        right = end;
                    }
                }
            }
        }
        if (right == left)
            return false;

        int tmp = digits[right];
        digits[right] = digits[left];
        digits[left] = tmp;
        return true;
    }

    /** The following code are not efficient. */
    private boolean swapToMax(int[] digits, int start, int end) {
        int max = getLeftMaxIndex(digits, start, end);
        int min = getRightMinIndex(digits, start, max);
        if (max <= min)
            return false;
        int tmp = digits[max];
        digits[max] = digits[min];
        digits[min] = tmp;
        return true;
    }

    private int getLeftMaxIndex(int[] digits, int start, int end) {
        int index = end;
        while (start < end) {
            if (digits[--end] > digits[index])
                index = end;
        }
        return index;
    }

    private int getRightMinIndex(int[] digits, int start, int end) {
        int index = start;
        while (start < end) {
            if (digits[++start] < digits[index])
                index = start;
        }
        return start;
    }

}
