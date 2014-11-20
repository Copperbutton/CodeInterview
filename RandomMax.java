package interviewquestions.facebook;

import java.util.Random;

/**
 * Given an array of integers. We have to find the max element of the array,
 * which is at multiple places in the array and return any one of the indices
 * randomly.
 */
public class RandomMax {
    public int randMax(int[] array) {
        int max = Integer.MIN_VALUE;
        int count = 0;
        // Get max value as well as their max at one time
        for (int val : array) {
            if (val > max) {
                max = val;
                count = 1;
            } else if (val == max) {
                count++;
            }
        }

        // Generate a random number [0, count)
        Random rand = new Random();
        int index = rand.nextInt(count);

        // get the random index max number
        count = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i] == max) {
                if (index + 1 == ++count)
                    return i;
            }
        }
        return -1;
    }
}
