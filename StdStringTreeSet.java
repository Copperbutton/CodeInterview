package interviewquestions.other;

/**
 * This implementation is better than mine because it fully utilizes the search
 * feature of BST.
 */

public class StdStringTreeSet implements StringSet {
    private StringTreeNode root;
    private int numElements;

    public StdStringTreeSet() {
        root = null;
        numElements = 0;
    }

    @Override
    public boolean add(String value) {
        int oldSize = size();
        this.root = add(root, value);
        return oldSize != size();
    }

    private StringTreeNode add(StringTreeNode root, String value) {
        if (root == null) {
            root = new StringTreeNode(value);
            numElements++;
        } else if (root.data.compareTo(value) == 0) {
            return root;
        } else if (root.data.compareTo(value) > 0) {
            root.left = add(root.left, value);
        } else {
            root.right = add(root.right, value);
        }
        return root;
    }

    @Override
    public boolean contains(String value) {
        return contains(root, value);
    }

    private boolean contains(StringTreeNode root, String value) {
        if (root == null)
            return false;
        else if (root.data.compareTo(value) == 0) {
            return true;
        } else if (root.data.compareTo(value) > 0) {
            return contains(root.left, value);
        } else {
            return contains(root.right, value);
        }
    }

    @Override
    public void print() {
        // TODO Auto-generated method stub

    }

    @Override
    public boolean remove(String value) {
        int oldSize = size();
        root = remove(root, value);
        return oldSize == size();
    }

    private StringTreeNode remove(StringTreeNode root, String value) {
        if (root == null)
            return root;
        else if (root.data.compareTo(value) > 0) {
            root.left = remove(root.left, value);
        } else if (root.data.compareTo(value) < 0) {
            root.right = remove(root.right, value);
        } else {
            /** We find the data we want to remove */
            if (root.left != null && root.right != null) {
                root.data = findMin(root.right).data;
                root.right = remove(root.right, root.data);
            } else if (root.right != null) {
                root = root.right;
                numElements--;
            } else {
                root = root.left;
                numElements--;
            }
        }
        return root;
    }

    private StringTreeNode findMin(StringTreeNode root) {
        if (root == null || (root.left == null && root.right == null))
            return root;
        StringTreeNode tmp = root.left;
        while (tmp.right != null)
            tmp = tmp.right;
        return tmp;
    }

    @Override
    public int size() {
        return numElements;
    }

}
