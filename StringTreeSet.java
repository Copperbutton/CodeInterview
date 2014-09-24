package interviewquestions.other;

public class StringTreeSet implements StringSet {
    private StringTreeNode root;
    private int size;

    public StringTreeSet() {
        root = null;
        size = 0;
    }

    /**
     * Check if the tree has already contains the value, if so, return. If not,
     * find the place to inert.
     */
    @Override
    public boolean add(String value) {
        if (contains(value))
            return false;
        StringTreeNode newNode = new StringTreeNode(value);
        int oldSize = size;
        return oldSize != add(newNode);
    }

    /**
     * If root is null, replace it with the new node, else find the place to
     * insert by recording the root.
     */
    private int add(StringTreeNode newNode) {
        if (root == null) {
            root = newNode;
            return ++size;
        }

        StringTreeNode parent = root;
        StringTreeNode tmp = root.data.compareTo(newNode.data) > 0 ? root.left
                : root.right;
        while (tmp != null) {
            if (tmp.data.compareTo(newNode.data) > 0) {
                parent = tmp;
                tmp = tmp.left;
            } else if (tmp.data.compareTo(newNode.data) < 0) {
                parent = tmp;
                tmp = tmp.right;
            }
        }
        if (parent.data.compareTo(newNode.data) > 0)
            parent.left = newNode;
        else
            parent.right = newNode;
        return ++size;
    }

    @Override
    public boolean contains(String value) {
        StringTreeNode tmp = root;
        while (tmp != null && tmp.data != null) {
            if (tmp.data.compareTo(value) == 0) {
                return true;
            } else if (tmp.data.compareTo(value) > 0) {
                tmp = tmp.left;
            } else if (tmp.data.compareTo(value) < 0) {
                tmp = tmp.right;
            }
        }
        return false;
    }

    @Override
    public void print() {
        // TODO Auto-generated method stub
    }

    @Override
    public boolean remove(String value) {
        if (!contains(value))
            return false;
        if (root.data.compareTo(value) == 0) {
            root = null;
            return true;
        }
        
        StringTreeNode parent = findParent(value);
        StringTreeNode node = parent.left.data.compareTo(value) == 0 ? parent.left : parent.right;
        if (node.left == null && node.right == null) {
            if (node == parent.left)
                parent.left = null;
            else
                parent.right = null;
        } else if (node.left == null) {
            if (node == parent.left) {
                parent.left = node.right;
            } else 
                parent.right = node.right;
        } else if (node.right == null) {
            if (node == parent.left) {
                parent.left = node.left;
            } else
                parent.right = node.left;
        } else {
            StringTreeNode rightMost = findRightMost(node.left);
            String data = node.data;
            node.data = rightMost.data;
            rightMost.data = data;
            remove(data);
        }
        return true;
    }
    
    private StringTreeNode findParent(String value) {
        StringTreeNode parent = root;
        StringTreeNode tmp = parent.data.compareTo(value) > 0 ? parent.left : parent.right;
        while (tmp != null) {
            if (tmp.data.compareTo(value) == 0) {
                break;
            } else if (tmp.data.compareTo(value) > 0) {
                parent = tmp;
                tmp = tmp.left;
            } else {
                parent = tmp;
                tmp = tmp.right;
            }
        }
        return tmp;
    }
    
    private StringTreeNode findRightMost(StringTreeNode node) {
        while (node.right != null) {
            node = node.right;
        }
        return node;
    }

    @Override
    public int size() {
        return size;
    }
}
