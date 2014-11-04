package interviewquestions.google;

public class SingleInteger {
    public int findSingleNumber(int[] array) {
        int ones = 0;
        int twos = 0;
        for (int val : array) {
            ones = (ones ^ val) & ~twos;
            twos = (twos ^ val) & ~ones;
        }
        return ones;
    }

    /**
     * The following algorithm use 2 bit to store the count number of given bit.
     * One thing to pay attention to is that the values stores on msInt and lsInt
     * are double offset, so if we want to retrieve it back, we should shift
     * twice.
     */
    public int findSingleNumberII(int[] array) {
        if (array == null || array.length == 0 || array.length % 3 != 1)
            throw new IllegalArgumentException(
                    "Error: given input has problem.");

        int msInt = 0;
        int lsInt = 0;
        for (int val : array) {
            for (int i = 0; i < Integer.SIZE; i++) {
                if (((val >> i) & 1) == 0)
                    continue;
                if (i < Integer.SIZE / 2) {
                    int count = (((lsInt >> 2 * i) & 3) + ((val >> i) & 1)) % 3;
                    lsInt &= ~(3 << 2 * i);
                    lsInt |= (count << i * 2);
                } else {
                    int j = i - Integer.SIZE / 2;
                    int count = (((msInt >> 2 * j) & 3) + ((val >> j) & 1)) % 3;
                    msInt &= ~(3 << 2 * j);
                    msInt |= (count << j * 2);
                }
            }
        }

        int val = 0;
        for (int i = 0; i < Integer.SIZE; i++) {
            if (i < Integer.SIZE / 2) {
                val |= (((lsInt >> 2 * i) & 1) << i);
            } else {
                int j = i - Integer.SIZE / 2;
                val |= ((((msInt >> 2 * j) & 1) << j) << Integer.SIZE / 2);
            }
        }
        return val;
    }
}
