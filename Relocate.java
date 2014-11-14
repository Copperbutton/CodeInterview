package interviewquestions.facebook;

/**
 * WAP to modify the array such that arr[I] = arr[arr[I]]. Do this in place i.e.
 * with out using additional memory.
 * 
 * example : if a = {2,3,1,0} o/p = a = {1,0,3,2}
 * 
 * Note : The array contains 0 to n-1 integers.
 */
public class Relocate {
    public void relocate(int[] array) {
        if (array == null)
            return;

        /**
         * Since we can not use additional space, we need to encode the
         * array[array[i]] number on array[i] instead of override it directly,
         * because we will need array[i] later, the best way is that we can
         * retrieve the number array[i] and array[array[i]]from the encoded
         * number directly. One solution is to use A += (B % length) * length;
         * so that we can get A simply by A' % length, and get B by A'/length;
         * this is very similar of decimal number that put the one number on the
         * first digit, while put another number of the second digit.
         */
        for (int i = 0; i < array.length; i++) {
            array[i] += (array[array[i]] % array.length) * array.length;
        }

        for (int i = 0; i < array.length; i++) {
            array[i] /= array.length;
        }
    }
}
