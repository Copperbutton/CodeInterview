package interviewquestions.google;

import java.util.*;

/**
 * A helper function that convert an array to a tree.
 * The tree organized in array as the following format:
 * 1, 2, 3, null, null => 1
 *                 / \
 *                2   3
 */
public class TreeTestHelper<T> {
    public BinaryTreeNode<T> convertToTree(T[] array) {
        int len = array.length;
        
        @SuppressWarnings("unchecked")
        BinaryTreeNode<T> treeNodes[] = new BinaryTreeNode[len];
        for (int i = 0; i < len; i++) {
            if (array[i] != null)
                treeNodes[i] = new BinaryTreeNode<T>(array[i]);
        }
        
        for (int i = 0; 2*i + 1 < len; i++) {
            if (treeNodes[i] == null)
                continue;
            treeNodes[i].left = treeNodes[2*i + 1];
            treeNodes[i].right = 2 * i + 2 < len ? treeNodes[2*i + 2] : null;
        }
        
        return treeNodes[0];
    }
    
    public List<T> preOrderTraversal(BinaryTreeNode<T> root) {
        List<T> list = new ArrayList<T> ();
        BinaryTreeNode<T> p = root;
        while (p != null) {
            if (p.left == null) {
                list.add(p.val);
                p = p.right;
            } else {
                BinaryTreeNode<T> tmp = p.left;
                while (tmp.right != null && tmp.right != p)
                    tmp = tmp.right;
                if (tmp.right == null) {
                    list.add(p.val);
                    tmp.right = p;
                    p = p.left;
                } else {
                    tmp.right = null;
                    p = p.right;
                }
            }
        }
        return list;
    }
    
    public List<T> inOrderTraversal(BinaryTreeNode<T> root) {
        List<T> list = new ArrayList<T> ();
        BinaryTreeNode<T> p = root;
        while (p != null) {
            if (p.left == null) {
                list.add(p.val);
                p = p.right;
            } else {
                BinaryTreeNode<T> tmp = p.left;
                while (tmp.right != null && tmp.right != p)
                    tmp = tmp.right;
                if (tmp.right == null) {
                    tmp.right = p;
                    p = p.left;
                } else {
                    list.add(p.val);
                    tmp.right = null;
                    p = p.right;
                }
            }
        }
        return list;
    }

}
