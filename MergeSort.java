package interviewquestions.other;

/**
 * 
 * Write a Merge sort for an integer array.
 */
public class MergeSort {
    public void mergeSort(int[] data) {
        if (data.length < 2)
            return;
        mergeSort(data, 0, data.length - 1);
    }

    public void mergeSort(int[] data, int first, int last) {
        /**
         * Pay attention to return condition, when have two elements, still
         * needs to sort
         */
        if (last - first < 1)
            return;
        int mid = (first + last) / 2;
        mergeSort(data, first, mid);
        mergeSort(data, mid + 1, last);
        merge(data, first, last);
    }

    public void merge(int[] data, int first, int last) {
        int mid = (first + last) / 2;
        int[] tmp = new int[last - first + 1];
        int i1 = 0, i2 = first, i3 = mid + 1;
        while (i2 <= mid || i3 <= last) {
            if (i2 <= mid && i3 <= last)
                tmp[i1++] = (data[i2] < data[i3]) ? data[i2++] : data[i3++];
            else if (i2 <= mid)
                tmp[i1++] = data[i2++];
            else
                tmp[i1++] = data[i3++];

        }
        for (int i = first, j = 0; i <= last; i++, j++)
            data[i] = tmp[j];
    }
}
