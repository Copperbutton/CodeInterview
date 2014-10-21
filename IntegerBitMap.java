package interviewquestions.google;

/**
 * Implement a bitmap that can store four states: 0, 1, > 1;
 * Pay attention to integer minus.
 */
public class IntegerBitMap {
    int[] data;
    int itemSize;

    public IntegerBitMap() {
        itemSize = Integer.SIZE / 2;
        int count = (int) (((long) Integer.MAX_VALUE - (long) Integer.MIN_VALUE) / itemSize);
        data = new int[count];
    }

    public void add(int val) {
        int count = get(val);
        if (count == 2)
            return;
        add(val, count + 1);
    }

    private void add(int val, int count) {
        int intIndex = intIndex(val);
        int bitIndex = bitIndex(val);
        int value = data[intIndex] & ~(3 << bitIndex * 2);
        value |= (count << (bitIndex * 2));
        data[intIndex] = value;
    }

    public int get(int val) {
        return get(data[intIndex(val)], bitIndex(val));
    }

    /**
     * Pay attention to the right shift operation, can be logic right shift or
     * right shift
     */
    private int get(int storeInt, int bitIndex) {
        return (storeInt << (Integer.SIZE - (bitIndex + 1) * 2)) >>> (Integer.SIZE - 2);
    }

    public int intIndex(int val) {
        long index = (long) val - (long) Integer.MIN_VALUE;
        return (int) (index / itemSize);
    }

    private int bitIndex(int val) {
        long index = (long) val - (long) Integer.MIN_VALUE;
        return (int) (index % itemSize);
    }

}
