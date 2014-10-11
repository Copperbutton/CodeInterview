package interviewquestions.google;

import java.util.Arrays;

/**
 * Given an unsorted array of integers, you need to return maximum possible n
 * such that the array consists at least n values greater than or equals to n.
 * Array can contain duplicate values. Sample input : [1, 2, 3, 4] -- output : 2
 * Sample input : [900, 2, 901, 3, 1000] -- output: 3
 */

public class MaxPossible {
    /**
     * A solution with time complexity of O(nlog(n))
     */
    public int findMaxPossible(int[] array) {
        Arrays.sort(array);
        int index = array.length - 1;
        while (index >= 0 && array[index] >= array.length - index)
            index--;
        return array.length - index - 1;
    }

    /**
     * A solution that using extra memory but decrease the time complexity to
     * O(n).
     * 
     */
    public int findMaxPossibleWithMemory(int[] array) {
        int len = array.length;
        int[] count = new int[len + 1];

        /**
         * Count all numbers on the array, if greater than the length, store it
         * on length, else if greater than 0, store it. This solution is good
         * because the question only requires greater.
         * 
         */
        for (int i = 0; i < len; i++) {
            if (array[i] >= len)
                count[len]++;
            else if (array[i] > 0)
                count[array[i]]++;
        }

        /**
         * Accumulate, and find from the right to left. Because on count[index]
         * right part, all number are greater or equal to index. And we decrease
         * index from total to bottom, if any higher full fill, then that's what
         * we want.
         */
        for (int index = len, total = 0; index >= 0; index--) {
            total += count[index];
            if (total >= index)
                return index;
        }

        return 0;
    }
}
