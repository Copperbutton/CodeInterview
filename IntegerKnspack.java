package interviewquestions.other;

import java.util.Arrays;

/**
 * Knspack question: try to put the maximal weight of food into a pack that can
 * only hold C weight of food.
 */
public class IntegerKnspack {
    /**
     * The basic idea of this algorithm is that: for any stone, there are two
     * possibilities: can be included into the pack, can not be included into
     * the pack. For those that cannot be included into the pack, we ignore
     * them. For those that can be included into the pack, we compare whether
     * adding them to the pack result in the optimal result or not adding them
     * result in an optimal result.
     */
    public int putKnspack(int[] weights, int capacity) {
        if (weights == null || weights.length == 0 || capacity <= 0)
            return 0;
        int len = weights.length;
        int[][] weightMatrix = new int[capacity + 1][len + 1];

        for (int row = 1; row <= capacity; row++) {
            for (int col = 1; col <= len; col++) {
                if (weights[col - 1] > row)
                    weightMatrix[row][col] = weightMatrix[row][col - 1];
                else {
                    weightMatrix[row][col] = Math
                            .max(weightMatrix[row][col - 1], weights[col - 1] + weightMatrix[row - weights[col - 1]][col - 1]);
                }
            }
        }
        return weightMatrix[capacity][len];
    }

    public int knspackWithMostValue(int[] weights, int[] value, int capacity) {
        if (weights == null || weights.length == 0 || value == null
                || value.length == 0 || value.length != weights.length
                || capacity <= 0)
            return 0;

        int len = weights.length;
        int[][] valueMatrix = new int[capacity + 1][len + 1];
        for (int row = 1; row <= capacity; row++) {
            for (int col = 1; col <= len; col++) {
                if (weights[col - 1] > row)
                    valueMatrix[row][col] = valueMatrix[row][col - 1];
                else
                    valueMatrix[row][col] = Math.max(valueMatrix[row - weights[col - 1]][col - 1] + value[col - 1], valueMatrix[row][col - 1]);
            }
        }
        return valueMatrix[capacity][len];
    }
}
