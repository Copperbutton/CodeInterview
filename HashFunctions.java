package interviewquestions.other;

/**
 * Some exercise on hash function.
 * 
 */

public class HashFunctions {
    /**
     * The reason to select 31 as the table length: 1) 31 is an prime value, so
     * when user doing mode out on it, they have no common factor except for the
     * multiple of 31. 2) 31 is the number less 1 than the power of 2, so when
     * the doing mode, it can be replaced with a shift and a subtract.
     */
    private static final int M = 31;

    private static final int R = 17;

    /**
     * The reason to and the value with 0x7fffffff so that the 32 bit singened
     * number can turn to 31 bit unsigned number. Why not using Math.abs(value)?
     * because, Math.abs can produce negative value for Integer.MIN_VALUE. Since
     * the absolute value of it can not be presented with in one integer.
     */
    public int hashCode(int value) {
        return (value & 0x7fffffff) % M;
    }

    /**
     * Hash Code for multiple integers. Modular hash on each of the values.
     */
    public int hashCode(int... values) {
        int hashCode = 0;
        for (int v : values)
            hashCode = (hashCode * R + v) % M;
        return hashCode;
    }

    /**
     * Divide the long number into two parts, use the higher part with the lower
     * part as two modular to compute hash code.
     */
    public int hashCode(long value) {
        return (((int)(value ^ (value >>> 32))) & 0x7fffffff) % M;
    }

    public int hashCode(double value) {
        return hashCode(Double.doubleToLongBits(value));
    }

    /**
     * For string, it is very similar with hashing on an array of integers.
     */
    public int hashCode(String value) {
        int hashCode = 0;
        for (int i = 0; i < value.length(); i++)
            hashCode = (hashCode * R + value.charAt(i)) % M;
        return hashCode;
    }
}
