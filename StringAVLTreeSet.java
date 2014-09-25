package interviewquestions.other;

/**
 * This is a good practice of seeing the effect of extending a class.
 * 
 */
public class StringAVLTreeSet extends StdStringTreeSet {

    public StringAVLTreeSet() {
        super();
    }

    @Override
    public StringTreeNode add(StringTreeNode node, String value) {
        node = super.add(node, value);
        node = rebalance(node);
        return node;
    }

    public void print() {
        // To be implemented
    }

    @Override
    public StringTreeNode remove(StringTreeNode node, String value) {
        node = super.remove(node, value);
        node = rebalance(node);
        return node;
    }

    private StringTreeNode rebalance(StringTreeNode node) {
        int bf = balanceFactor(node);
        if (bf < -1) {
            if (balanceFactor(node.left) < 0) {
                node = rightRotate(node);
            } else {
                node.left = leftRotate(node.left);
                node = rightRotate(node);
            }
        } else if (bf > 1) {
            if (balanceFactor(node.right) > 0) {
                node = leftRotate(node);
            } else {
                node.right = rightRotate(node.right);
                node = leftRotate(node);
            }
        }
        return node;
    }

    private StringTreeNode leftRotate(StringTreeNode parent) {
        StringTreeNode rightLeft = parent.right.left;
        StringTreeNode newParent = parent.right;
        newParent.left = parent;
        parent.right = rightLeft;

        parent.height = computeHeight(parent);
        newParent.height = computeHeight(newParent);
        return newParent;
    }

    private StringTreeNode rightRotate(StringTreeNode parent) {
        StringTreeNode leftright = parent.left.right;
        StringTreeNode newParent = parent.left;
        newParent.right = parent;
        parent.left = leftright;

        parent.height = computeHeight(parent);
        newParent.height = computeHeight(newParent);
        return newParent;
    }
}
