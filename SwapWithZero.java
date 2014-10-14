package interviewquestions.google;

/**
 * Rearrange an array using swap with 0.
 * 
 * You have two arrays src, tgt, containing two permutations of the numbers
 * 0..n-1. You would like to rearrange src so that it equals tgt. The only
 * allowed operations is Òswap a number with 0Ó, e.g. {1,0,2,3} -> {1,3,2,0}
 * (Òswap 3 with 0Ó). Write a program that prints to stdout the list of required
 * operations.
 * 
 * Practical application:
 * 
 * Imagine you have a parking place with n slots and n-1 cars numbered from
 * 1..n-1. The free slot is represented by 0 in the problem. If you want to
 * rearrange the cars, you can only move one car at a time into the empty slot,
 * which is equivalent to Òswap a number with 0Ó.
 * 
 * Example: src={1,0,2,3}; tgt={0,2,3,1};
 */

public class SwapWithZero {
    /** Swap a number with zero */
    public void swapZeroWith(int[] array, int n) {
        int index1 = indexOf(array, 0);
        int index2 = indexOf(array, n);
        int tmp = array[index1];
        array[index1] = array[index2];
        array[index2] = tmp;
    }

    public int indexOf(int[] array, int n) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == n)
                return i;
        }
        return -1;
    }

    public void swapToTarget(int[] src, int[] targt) {
        for (int i = 0; i < src.length; i++) {
            if (src[i] != targt[i] && targt[i] != 0) {
                if (src[i] != 0)
                    swapZeroWith(src, src[i]);
                swapZeroWith(src, targt[i]);
            }
        }
    }
}
