package interviewquestions.facebook;

import java.util.Arrays;

/**
 * Find kth smallest element from two sorted array
 */
public class KthSmallestElement {
    public int findKthSmallestElement(int[] A, int[] B, int K) {
        if (A.length > B.length)
            return findKthSmallestElement(B, A, K);

        if (A.length == 0)
            return B[K - 1];

        if (K == 1)
            return Math.min(A[0], B[0]);

        int indexA = Math.min(A.length, K / 2);
        int indexB = K - 1 - indexA;
        int Ai1 = (indexA == 0) ? Integer.MIN_VALUE : A[indexA - 1];
        int Ai = (indexA == A.length) ? Integer.MAX_VALUE : A[indexA];
        int Bi1 = (indexB == 0) ? Integer.MIN_VALUE : B[indexB - 1];
        int Bi = (indexB == B.length) ? Integer.MAX_VALUE : B[indexB];

        if (Ai1 < Bi && Bi < Ai)
            return Bi;
        if (Bi1 < Ai && Ai < Bi)
            return Ai;

        if (Ai < Bi)
            // Exclude indexA and below portion
            // Exclude indexB and above portion
            return findKthSmallestElement(
                    Arrays.copyOfRange(A, indexA + 1, A.length),
                    Arrays.copyOfRange(B, 0, indexB), K - 1 - indexA);
        else
            // Exclude indexA and above
            // Exclude indexB and below
            return findKthSmallestElement(Arrays.copyOfRange(A, 0, indexA),
                    Arrays.copyOfRange(B, indexB + 1, B.length), K - 1 - indexB);
    }
}
