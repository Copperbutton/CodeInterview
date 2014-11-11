package interviewquestions.google;

import java.util.*;

public class KthBTNode {
    /**
     * Check if the target if current node, if not, try to go left with
     * deduction on K or try to go right with the deduction on K.
     */
    public int findInOrderKthNode(BTNode root, int K) {
        if (root == null)
            throw new IllegalArgumentException(
                    "Error: root node cannot be null");

        if (root.val < K)
            throw new NoSuchElementException(
                    "Error: target node not exists on the tree");

        if ((root.left != null && root.left.subNodes + 1 == K)
                || (root.left == null && K == 1))
            return root.val;
        else if (root.left != null && root.left.subNodes + 1 > K)
            return findInOrderKthNode(root.left, K);
        else
            return findInOrderKthNode(root.right, K
                    - (root.left == null ? 1 : 1 + root.left.subNodes));
    }

    /**
     * Find the target iteratively.
     */
    public int iterativeKthNode(BTNode root, int K) {
        if (root == null)
            throw new IllegalArgumentException(
                    "Error: root node cannot be null");

        if (root.val < K)
            throw new NoSuchElementException(
                    "Error: target node not exists on the tree");

        BTNode p = root;
        while (p != null) {
            if ((p.left != null && p.left.subNodes + 1 == K)
                    || (p.left == null && K == 1))
                return root.val;
            else if (p.left != null && p.left.subNodes + 1 > K)
                p = p.left;
            else {
                p = p.right;
                K = K - (p.left == null ? 1 : 1 + p.left.subNodes);
            }
        }
        return -1;
    }

}
