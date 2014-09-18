package interviewquestions.other;

/**
 * 
 * Write a heap sort function for an integer array.
 */
public class HeapSort {
    public void moveDown(int[] heap, int first, int last) {
        int largest = first * 2 + 1;
        while (largest <= last) {
            /** Comparing the two children of the first, first the larger one */
            if (largest < last && heap[largest] < heap[largest + 1])
                largest++;

            /**
             * Comparing current value with with bigger children, if bigger,
             * swap and update the begin index, else exit the move;
             */
            if (heap[first] < heap[largest]) {
                int tmp = heap[first];
                heap[first] = heap[largest];
                heap[largest] = tmp;
                first = largest;
                largest = first * 2 + 1;
            } else
                largest = last + 1;
        }
    }

    public void buildHeap(int[] array) {
        /**
         * From the last node that is not leaf node, to the first node, build up
         * heap from bottom up. Create smallest heap, then make bigger and make
         * bigger heap compatible with smaller one.
         */
        for (int i = array.length / 2 - 1; i >= 0; i--) {
            moveDown(array, i, array.length - 1);
        }
    }

    public void heapSort(int[] array) {
        buildHeap(array);
        /**
         * After building up head, the biggest one is on the first, then swap
         * the first one one with the last one, the sift the heap. Swap until
         * the first one.
         */
        for (int i = array.length - 1; i >= 0; i--) {
            int tmp = array[0];
            array[0] = array[i];
            array[i] = tmp;
            moveDown(array, 0, i - 1);
        }
    }
}
