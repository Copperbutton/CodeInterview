package interviewquestions.google;

import java.util.*;

/**
 * Find indices pair of indices that the gap between min and max are less than
 * K.
 */
public class FindBoundedIndices {
    public List<Integer> findBoundedIndicies(int[] array, int K) {
        List<Integer> indicies = new ArrayList<Integer>();
        if (array == null || array.length == 0 || K < 0)
            return indicies;
        MinMaxQueue<Integer> queue = new MinMaxQueueImp<Integer>();
        for (int left = 0, right = 0; left < array.length; left++) {
            while (right < array.length
                    && (queue.isEmpty() || queue.min() + K >= queue.max()))
                queue.offer(array[right++]);
            /**
             * For window crawling problem should be very careful, since the
             * ending condition can be end of row or violation
             */
            indicies.addAll(generateIndices(array, left,
                    queue.min() + K >= queue.max() ? right : right - 1));
            queue.poll();
        }
        return indicies;
    }

    private List<Integer> generateIndices(int[] array, int left, int right) {
        List<Integer> indicies = new ArrayList<Integer>();
        for (int i = left; i < right; i++) {
            indicies.add(left);
            indicies.add(i);
        }
        return indicies;
    }
}
