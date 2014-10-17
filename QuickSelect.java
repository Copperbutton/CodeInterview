package interviewquestions.google;

/**
 * Implement a quick select, which can select the kth number from an array. And
 * run in time O(n).
 */
public class QuickSelect {
    public int quickSelect(int[] array, int K) {
        int first = 0;
        int last = array.length - 1;
        if (first == last)
            return array[first];
        while (first <= last) {
            int pivotIndex = partition(array, first, last);
            if (K - 1 == pivotIndex)
                return array[K - 1];
            else if (K - 1< pivotIndex)
                last = pivotIndex - 1;
            else
                first = pivotIndex + 1;
        }
        return -1;
    }

    public int partition(int[] array, int first, int last) {
        /**
         * The lower starts from start + 1, where the first is used to hide the
         * pivot
         */
        int lower = first + 1;
        int upper = last;
        swap(array, first, (first + last) / 2);
        int bound = array[first];
        while (lower <= upper) {
            while (lower < upper && array[lower] <= bound)
                lower++;
            while (array[upper] > bound)
                upper--;
            if (lower < upper)
                swap(array, lower++, upper--);
            else
                lower++;
        }
        swap(array, first, upper);
        return upper;
    }

    public void swap(int[] array, int first, int second) {
        int tmp = array[first];
        array[first] = array[second];
        array[second] = tmp;
    }

}
