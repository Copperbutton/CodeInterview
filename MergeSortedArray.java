package interviewquestions.facebook;

import java.util.Arrays;

public class MergeSortedArray {
    public int[] mergeSortedArray(int[] array1, int[] array2) {
        if (array1 == null || array2 == null)
            return array1 == null ? array2 : array1;

        int index1 = 0;
        int index2 = 0;
        int index3 = 0;
        int[] newArray = new int[array1.length + array2.length];

        while (index1 < array1.length || index2 < array2.length) {
            int comp = 0;
            if (index1 < array1.length && index2 < array2.length)
                comp = array1[index1] - array2[index2];
            else
                comp = index1 < array1.length ? -1 : 1;

            if (comp < 0) {
                newArray[index3++] = array1[index1++];
            } else if (comp > 0) {
                newArray[index3++] = array2[index2++];
            } else {
                newArray[index3++] = array1[index1++];
                index2++;
            }
        }
        return Arrays.copyOfRange(newArray, 0, index3);
    }
}
