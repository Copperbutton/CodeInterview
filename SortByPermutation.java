package interviewquestions.google;

/**
 * The input is a sequence x1,x2,...,xn of integers in an arbitrary order, and
 * another sequence a1,a2,..,an of distinct integers from 1 to n (namely
 * a1,a2,...,an is a permutation of 1, 2,..., n). Both sequences are given as
 * arrays. Design an 0(n logn) algorithm to order the first sequence according
 * to the order imposed by the permutation. In other words, for each i, Xi
 * should appear in the position given in ai. For example, if x = 17, 5, 1,9,
 * and a = 3, 2, 4, 1, then the outcome should be x = 9, 5, 17, 1. The algorithm
 * should be in-place, so you cannot use an additional array.
 */
public class SortByPermutation {
    public void sortByPermutation(int[] array, int[] permute) {
        for (int i = 0; i < array.length; i++) {
            /**
             * Swap current index to where it should be until current index is
             * in place. O(n) time complexity.
             */
            while (permute[i] != i + 1) {
                swap(array, i, permute[i] - 1);
                swap(permute, i, permute[i] - 1);
            }
        }
    }

    public void swap(int[] array, int first, int second) {
        int tmp = array[first];
        array[first] = array[second];
        array[second] = tmp;
    }
}
