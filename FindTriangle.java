package interviewquestions.linkedin;

import java.util.Arrays;

/**
 * Find if given array integers can form an array.
 * 
 * @author zhangxiaokang
 */
public class FindTriangle {
    public boolean findTriangle(int[] array) {
        if (array == null || array.length < 3)
            return false;

        /** Sort array, and assume x < y < z */
        Arrays.sort(array);
        int len = array.length;

        /** Generate an array that record z - y */
        int[] gap = new int[len];
        for (int i = 0; i < len - 1; i++)
            gap[i] = array[i + 1] - array[i];

        /**
         * Search from back to front, as long as x > z - y, a triangle exist,
         * since z + x > y, y + z > x automatically correct.
         */
        for (int i = len - 3; i >= 0; i--) {
            if (array[i] > gap[i + 1])
                return true;
        }
        return false;
    }

}
