package interviewquestions.google;

import java.util.Arrays;

/**
 * Given an integer, find the next highest and next lowest integers, with equal
 * number of 1s in their binary representation as the original number. Mainly
 * learned bit manipulation techniques, create some small help function as
 * marcos in C is a very good idea.
 */
public class BinaryPermutation {
    public int findNextBinaryPermutation(int value) {
        int setPos = 0;
        int clearPos = Integer.SIZE;
        for (int i = 0; i < Integer.SIZE; i++) {
            if (getBitOf(value, i) == 0)
                continue;
            for (int j = i + 1; j < Integer.SIZE; j++) {
                if (getBitOf(value, j) == 1)
                    continue;
                if (j < clearPos) {
                    setPos = i;
                    clearPos = j;
                    break;
                }
            }
        }
        if (clearPos == Integer.SIZE)
            return value;

        value = setBitOf(value, setPos, 0);
        return setBitOf(value, clearPos, 1);
    }

    public int findPrevBinaryPermutation(int value) {
        int setPos = 0;
        int clearPos = -1;
        for (int i = 0; i < Integer.SIZE - 1; i++) {
            if (getBitOf(value, i) == 0 && getBitOf(value, i + 1) == 1) {
                clearPos = i;
                setPos = i + 1;
                break;
            }
        }
        if (clearPos == -1)
            return value;

        value = setBitOf(value, setPos, 0);
        return setBitOf(value, clearPos, 1);
    }

    /**
     * Here needs attention, even if number very big, can work here. So better
     * mod with size
     */
    public int getBitOf(int value, int pos) {
        pos %= Integer.SIZE;
        return (value >> pos) & 1;
    }

    public int setBitOf(int value, int pos, int bit) {
        if (bit == 1)
            return value | (1 << pos);
        else
            return value & ~(1 << pos);
    }

    public String printHelper(int value) {
        int[] bitArray = new int[Integer.SIZE];
        for (int i = bitArray.length - 1; i >= 0; i--) {
            bitArray[i] = value & 1;
            value >>= 1;
        }
        return Arrays.toString(bitArray);
    }
}
