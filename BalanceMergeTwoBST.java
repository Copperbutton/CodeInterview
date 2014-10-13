package interviewquestions.google;

import java.util.*;

public class BalanceMergeTwoBST {
    public BinaryTreeNode<Integer> mergeTwoBST(BinaryTreeNode<Integer> root1,
            BinaryTreeNode<Integer> root2) {
        root1 = flatBST(root1);
        root2 = flatBST(root2);
        root1 = mergeTwoFlatBST(root1, root2);
        return constructBSTFromList(root1);
    }

    /**
     * Find the smallest which will be the return node Flat the tree to a single
     * linked list. This take O(n) time and constant space.
     */
    public BinaryTreeNode<Integer> flatBST(BinaryTreeNode<Integer> root) {
        BinaryTreeNode<Integer> ret = root;
        while (ret != null && ret.left != null)
            ret = ret.left;

        BinaryTreeNode<Integer> p = root;
        BinaryTreeNode<Integer> tmp = null;
        while (p != null) {
            if (p.left == null) {
                tmp = p.right;
                p.right = findInOrderNext(p);
                p = tmp;
                root = p;
            } else {
                tmp = findInOrderPrev(p);
                p = p.left;
                tmp.right = root;
                root.left = null;
                root = p;
            }
        }
        return ret;
    }

    public BinaryTreeNode<Integer> findInOrderNext(BinaryTreeNode<Integer> root) {
        root = root == null ? null : root.right;
        while (root != null && root.left != null)
            root = root.left;
        return root;
    }

    public BinaryTreeNode<Integer> findInOrderPrev(BinaryTreeNode<Integer> root) {
        root = (root == null) ? null : root.left;
        while (root != null && root.right != null)
            root = root.right;
        return root;
    }

    public BinaryTreeNode<Integer> flatBSTRecursively(
            BinaryTreeNode<Integer> root) {
        return flatBSTRecursively(root,
                new ArrayList<BinaryTreeNode<Integer>>());
    }

    /** Flat BST recursively */
    public BinaryTreeNode<Integer> flatBSTRecursively(
            BinaryTreeNode<Integer> root, List<BinaryTreeNode<Integer>> tail) {
        if (root == null) {
            tail.clear();
            tail.add(null);
            return null;
        }

        BinaryTreeNode<Integer> leftFlat = flatBSTRecursively(root.left, tail);
        if (leftFlat != null)
            tail.get(0).right = root;
        BinaryTreeNode<Integer> rightFlat = flatBSTRecursively(root.right, tail);
        root.right = rightFlat;
        root.left = null;
        if (rightFlat == null) {
            tail.clear();
            tail.add(root);
        }
        return leftFlat == null ? root : leftFlat;
    }

    /**
     * Merge Two flatten binary search tree into one, this takes O(n) time and
     * constant space
     */
    public BinaryTreeNode<Integer> mergeTwoFlatBST(
            BinaryTreeNode<Integer> root1, BinaryTreeNode<Integer> root2) {
        BinaryTreeNode<Integer> dummy = new BinaryTreeNode<Integer>(-1);
        dummy.right = root1;
        BinaryTreeNode<Integer> p1 = dummy;
        BinaryTreeNode<Integer> p2 = root2;
        while (p2 != null) {
            while (p1.right != null && p1.right.val < p2.val)
                p1 = p1.right;
            root2 = p2.right;
            p2.right = p1.right;
            p1.right = p2;
            p1 = p2;
            p2 = root2;
        }
        return dummy.right;
    }

    /**
     * Construct BST from bottom to top. First find length of list, then build
     * it up recursively.
     */
    public BinaryTreeNode<Integer> constructBSTFromList(
            BinaryTreeNode<Integer> root) {
        int len = 0;
        BinaryTreeNode<Integer> p = root;
        while (p != null) {
            p = p.right;
            len++;
        }
        List<BinaryTreeNode<Integer>> list = new ArrayList<BinaryTreeNode<Integer>>();
        list.add(root);
        return constructBSTFromList(list, 0, len - 1);
    }

    public BinaryTreeNode<Integer> constructBSTFromList(
            List<BinaryTreeNode<Integer>> list, int start, int end) {
        if (start > end)
            return null;
        int mid = start + (end - start) / 2;
        BinaryTreeNode<Integer> left = constructBSTFromList(list, start, mid - 1);
        BinaryTreeNode<Integer> root = list.get(0);
        root.left = left;
        list.clear();
        list.add(root.right);
        root.right = constructBSTFromList(list, mid + 1, end);
        return root;
    }
}
