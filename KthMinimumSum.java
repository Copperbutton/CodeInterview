package interviewquestions.google;

import java.util.*;

/**
 * Given two sorted array in ascending order with same length N, calculate the
 * first K min a[i]+b[j]. time complexty O(N). some misunderstood first K, to
 * put it straight, to find the Kth min, not the first min
 */
public class KthMinimumSum {
    public int findKthMinSum(int[] array1, int[] array2, int K) {
        if (array1 == null || array2 == null || array1.length == 0
                || array2.length == 0 || K <= 0)
            return -1;
        if (K >= array1.length * array2.length)
            return array1[array1.length - 1] + array2[array2.length - 1];

        PriorityQueue<Integer> heap = new PriorityQueue<Integer>(K,
                Collections.reverseOrder());
        for (int i = 0; i < array1.length; i++) {
            for (int j = 0; j < array2.length; j++) {
                int sum = array1[i] + array2[j];
                if (heap.size() < K)
                    heap.offer(sum);
                else {
                    if (j == 0 && sum > heap.peek())
                        break;
                    if (sum < heap.peek()) {
                        heap.poll();
                        heap.offer(sum);
                    }
                }
            }
        }
        return heap.peek();
    }

}
