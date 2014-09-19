package interviewquestions.other;

/**
 * Write a quick sort function for integer array.
 */
public class QuickSort {

    public void quickSort(int[] data) {
        if (data.length < 2)
            return;
        
        /** process the data */
        int max = 0;
        for (int i = 0; i < data.length; i ++)
            max = data[max] < data[i] ? i : max;
        swap(data, max, data.length - 1);
        quickSort(data, 0, data.length - 1);
    }

    public void quickSort(int[] data, int start, int last) {
        int lower = start + 1, upper = last;
        /** choose (lower + upper)/2 as bound, swap it with the start one */
        swap(data, start, (lower + upper) / 2);
        int bound = data[start];
        while (lower <= upper) {
            while (lower < last && data[lower] < bound)
                lower++;
            while (data[upper] > bound)
                upper--;
            if (lower < upper)
                swap(data, lower++, upper--);
            else
                lower++;
        }
        swap(data, start, upper);
        /**
         * Pay attention that the final pivot is in upper, so we decided the
         * boundary of next sort by upper.
         */
        if (start < upper - 1)
            quickSort(data, start, upper - 1);
        if (lower < last)
            quickSort(data, upper + 1, last);
    }

    public void swap(int[] data, int indx1, int indx2) {
        int tmp = data[indx1];
        data[indx1] = data[indx2];
        data[indx2] = tmp;
    }
}
