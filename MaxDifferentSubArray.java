package interviewquestions.google;

import java.util.*;

/**
 * Given an array of integers. Find two disjoint contiguous sub-arrays such that
 * the absolute difference between the sum of two sub-array is maximum. The
 * sub-arrays should not overlap.
 * 
 * eg- [2 -1 -2 1 -4 2 8] ans - (-1 -2 1 -4) (2 8), diff = 16
 * 
 * @author zhangxiaokang
 */
public class MaxDifferentSubArray {
    public List<Integer> maxDifferentSubArray(int[] array) {
        List<Integer> maxSubArray = new ArrayList<Integer>();
        int len = array.length;
        if (len < 2)
            return maxSubArray;
        int[][] arraySum = new int[len][len];
        for (int row = len - 1; row >= 0; row--) {
            for (int col = row; col < len; col++) {
                arraySum[row][col] = col > row ? arraySum[row][col - 1]
                        + array[col] : array[col];
            }
        }

        int maxDiff = 0;
        for (int row = len - 1; row >= 0; row--) {
            for (int col = row; col < len; col++) {
                for (int end = col + 1; end < len; end++) {
                    if (Math.abs(arraySum[row][col] - arraySum[col + 1][end]) > maxDiff) {
                        maxDiff = Math.abs(arraySum[row][col]
                                - arraySum[col + 1][end]);
                        maxSubArray.clear();
                        maxSubArray.add(row);
                        maxSubArray.add(col + 1);
                        maxSubArray.add(end);
                    }
                }
            }
        }

        return maxSubArray;
    }

}