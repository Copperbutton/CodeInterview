package interviewquestions.google;

import java.util.*;

/**
 * There are n bombs in a big circle,and each bomb has a value and a 'effect
 * range'.If you detonated a bomb,you will get this bomb's value,but a bomb can
 * have effect on the neighbors which the distance(difference between index)
 * between them is smaller than the 'effect range'.It's say that the neighbor
 * bomb will be destoryed and you could not get their values. You will given
 * each bomb's value and the 'effect range',and you need calculate the max value
 * you can get. eg. n=3 index 0's bomb' v is 2,range is 0(will not effect
 * others).and v[1]=1,r[1]=1,v[2]=3,r[2]=1; this case's max value is 5.(detonate
 * the 0's and than the 2's).
 */
public class DetonateBomb {
    public int detonateBomb(int[] value, int[] effect) {
        if (effect == null && value != null || effect.length != value.length)
            throw new IllegalArgumentException(
                    "Error: given value and effect not match in length");

        if (value == null || value.length == 0)
            return 0;
        boolean[] valid = new boolean[value.length];
        Arrays.fill(valid, true);
        return searchBombOption(value, effect, valid);
    }

    private int searchBombOption(int[] value, int[] effect, boolean[] valid) {
        if (!isAnyValid(valid))
            return 0;

        int maxValue = 0;
        for (int i = 0; i < value.length; i++) {
            if (!valid[i])
                continue;
            valid[i] = false;

            /**
             * Instead of correct the valid values directly, we used a list for
             * recording the corrected position, for later recovery.
             */
            List<Integer> denotatedList = denotateBomb(valid, i, effect[i]);
            for (int index : denotatedList)
                valid[index] = false;
            maxValue = Math.max(maxValue,
                    searchBombOption(value, effect, valid) + value[i]);
            valid[i] = true;
            for (int index : denotatedList)
                valid[index] = true;
        }

        return maxValue;
    }

    /**
     * Since the bomb are organized as circle, we needs to round up the index
     * value.
     */
    private List<Integer> denotateBomb(boolean[] valid, int bomb,
            int effectRange) {
        List<Integer> result = new ArrayList<Integer>();
        for (int count = 1; count <= effectRange; count++) {
            int right = (bomb + count) % valid.length;
            /** Right part bomb */
            if (valid[right])
                result.add(right);

            /** Left part */
            int left = (bomb - count + valid.length) % valid.length;
            if (valid[left])
                result.add(left);
        }

        return result;
    }

    private boolean isAnyValid(boolean[] valid) {
        for (boolean v : valid)
            if (v)
                return true;
        return false;
    }

}
