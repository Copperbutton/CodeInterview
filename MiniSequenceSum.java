package interviewquestions.google;

/**
 * Determine minimum sequence of adjacent values in the input parameter array
 * that is greater than input parameter sum.
 * 
 * Eg Array 2,1,1,4,3,6. and Sum is 8 Answer is 2, because 3,6 is minimum
 * sequence greater than 8.
 */
public class MiniSequenceSum {
    /**
     * This question used a sliding window algorithm, since the data should be
     * subsequence.
     */
    public int findMinSequLen2(int[] array, int sum) {
        int currSum = 0;
        int minLen = -1;
        /**
         * Pay attention to the position of start and end, they should always
         * one more than the boarder
         */
        for (int start = 0, end = 0; end < array.length;) {
            /** End move forward */
            while (end < array.length && currSum < sum)
                currSum += array[end++];
            if (end == array.length && currSum < sum)
                break;

            /** Start move forward */
            while (start <= end && currSum >= sum)
                currSum -= array[start++];

            /**
             * One thing to pay attention, when the window move to the end of
             * array, can not use end - start to range
             */
            int len = end - start + 1;
            minLen = minLen < 0 ? len : Math.min(len, minLen);
        }
        return minLen;
    }

    public int findMinSequLen(int[] array, int sum) {
        if (array == null)
            return -1;
        int minLen = -1;
        int currSum = 0;
        /**
         * In this implementation, end always stopped at the end of the
         * increasing sequence.
         */
        for (int start = 0, end = 0; end < array.length; end++) {
            while (end < array.length && currSum < sum) {
                currSum += array[end];
                if (currSum >= sum)
                    break;
                end++;
            }

            if (end == array.length && currSum < sum)
                break;

            /** Start always stopped at the next of begin */
            while (start <= end && currSum >= sum) {
                currSum -= array[start++];
                if (currSum < sum)
                    break;
            }

            /** Pay attention to the length calculation here */
            int len = end >= start ? end - start + 2 : 1;
            minLen = minLen > 0 ? Math.min(len, minLen) : len;
        }
        return minLen;
    }

}
