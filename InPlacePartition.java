package interviewquestions.facebook;

public class InPlacePartition {
    public void partition(int[] array) {
        // First step: find the first partition position
        int left = 0;
        while (left < array.length && array[left] != 0)
            left++;

        // Swap pairs
        for (int right = left + 1; right < array.length; right++) {
            if (array[right] == 0)
                continue;
            int tmp = array[left];
            array[left] = array[right];
            array[right] = tmp;
            left++;
        }
    }

    // Partition the array without interfere the relative order of data, the
    // time complexity is O(n^2);
    public void partitionII(int[] array) {
        // First find the first swap position
        int left = 0;
        while (left < array.length && array[left] > 0)
            left++;

        // Sequential swap
        for (int right = left + 1; right < array.length; right++) {
            if (array[right] < 0)
                continue;

            // sequential swap with left one
            for (int first = right - 1, second = right; first >= left; first--, second--) {
                int tmp = array[second];
                array[second] = array[first];
                array[first] = tmp;
            }

            // left runs to right
            left++;
        }
    }
}
