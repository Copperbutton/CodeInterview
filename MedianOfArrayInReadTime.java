package interviewquestions.other;

import java.util.*;

/**
 * Find the median of array in real time.
 * 
 */
public class MedianOfArrayInReadTime {
    PriorityQueue<Integer> minHeap;
    PriorityQueue<Integer> maxHeap;

    public MedianOfArrayInReadTime() {
        minHeap = new PriorityQueue<Integer>();
        maxHeap = new PriorityQueue<Integer>(10, Collections.reverseOrder());
    }

    /**
     * Be careful about add values to the heap, should not have judgement about 
     * */
    public void add(int val) {
        if (minHeap.isEmpty() || val > minHeap.peek())
            minHeap.add(val);
        else
            maxHeap.add(val);
        balance();
    }

    /**
     * When getting median, get it from median or from one of them
     * 
     */
    public float getMedian() {
        if (minHeap.size() == 0 && maxHeap.size() == 0)
            throw new NoSuchElementException("No such element");
        
        if (minHeap.size() == maxHeap.size())
            return ((float)minHeap.peek() + (float)maxHeap.peek()) / 2;
        else {
            return minHeap.size() > maxHeap.size() ? minHeap.peek() : maxHeap
                    .peek();
        }
    }

    /** If size are different more than 1, balances the two heap */
    private void balance() {
        if (minHeap.size() <= maxHeap.size())
            balance(maxHeap, minHeap);
        else
            balance(minHeap, maxHeap);
    }

    /** Balance the two heap by pooling from one and adding to the other. */
    private void balance(PriorityQueue<Integer> bigger,
            PriorityQueue<Integer> smaller) {
        while (bigger.size() - smaller.size() > 1) {
            smaller.add(bigger.poll());
        }
    }
}
