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

    public StringTreeNode add(StringTreeNode root, String value) {
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
        updateHeight(root);
        return root;
    }

    @Override
    public boolean contains(String value) {
        return contains(root, value);
    }

    public boolean contains(StringTreeNode root, String value) {
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

    public StringTreeNode remove(StringTreeNode root, String value) {
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
        updateHeight(root);
        return root;
    }

    public StringTreeNode findMin(StringTreeNode root) {
        if (root == null || (root.left == null))
            return root;
        StringTreeNode tmp = root.left;
        while (tmp.left != null)
            tmp = tmp.left;
        return tmp;
    }

    @Override
    public int size() {
        return numElements;
    }
    
    protected void updateHeight(StringTreeNode node) {
        if (node == null)
            return;
        if (node.left == null && node.right == null)
            node.height = 0;
        else if (node.right == null)
            node.height = node.left.height + 1;
        else if (node.left == null)
            node.height = node.right.height + 1;
        else
            node.height = Math.max(node.left.height + 1, node.right.height + 1);
    }

    protected static int computeHeight(StringTreeNode node) {
        if (node == null)
            return -1;
        return Math.max(computeHeight(node.left), computeHeight(node.right)) + 1;
    }

    protected static int balanceFactor(StringTreeNode node) {
        if (node == null)
            return 0;
        int left = (node.left == null) ? 0 : node.left.height + 1;
        int right = (node.right == null) ? 0 : node.right.height + 1;
        return right - left;
    }

    public boolean isBalanced() {
        return isBalanced(root);
    }

    private boolean isBalanced(StringTreeNode node) {
        if (node == null)
            return true;
        int bf = balanceFactor(node);
        if (bf > 1 || bf < -1)
            return false;
        return isBalanced(node.left) || isBalanced(node.right);
    }
}
