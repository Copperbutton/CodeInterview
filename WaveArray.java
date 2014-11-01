package interviewquestions.google;

import java.util.Arrays;

public class WaveArray {
    /**
     * This algorithm works because it starts from beginning, and the previous
     * words can be used.
     */
    public void waveSortArray(int[] array) {
        if (array == null || array.length == 0)
            return;
        for (int i = 0; i < array.length - 1; i++) {
            if (((i & 1) == 0 && array[i] < array[i + 1])
                    || ((i & 1) == 1 && array[i] > array[i + 1])) {
                int tmp = array[i];
                array[i] = array[i + 1];
                array[i + 1] = tmp;
            }
        }
    }

    /**
     * First sort the array, then swipe all pairs. 1, 2, 3, 4 => 2, 1, 4, 3. 1,
     * 2, 3, 4, 5 => 2, 1, 4, 3, 5
     */
    public void waveSortArayWithSort(int[] array) {
        if (array == null || array.length == 0)
            return;
        Arrays.sort(array);
        for (int i = 0; i + 1 < array.length; i += 2) {
            int tmp = array[i];
            array[i] = array[i + 1];
            array[i + 1] = tmp;
        }
    }
}
