package interviewquestions.other;

/**
 * Implement a red black balance tree.
 * 
 */
public class RedBlackBST<Key extends Comparable<Key>, Value> {
    private class Node {
        private Key key;
        private Value value;
        private Node left, right;
        private boolean color;
        private int N;

        public Node(Key key, Value value, boolean color, int N) {
            this.key = key;
            this.value = value;
            this.color = color;
            this.N = N;
        }
    }

    private static final boolean RED = true;
    private static final boolean BLACK = false;
    private Node ROOT;

    /** Node helper functions */
    private boolean isRed(Node node) {
        if (node == null)
            return false;
        return node.color == RED;
    }

    public int size(Node node) {
        if (node == null)
            return 0;
        return node.N;
    }
    
    public boolean isEmpty() {
       return ROOT == null; 
    }

    /** Get key from the tree. */
    public Value get(Key key) {
        if (key == null)
            return null;
        return get(ROOT, key);
    }

    private Value get(Node node, Key key) {
        while (node != null) {
            int comp = key.compareTo(node.key);
            if (comp < 0)
                node = node.left;
            else if (comp > 0)
                node = node.right;
            else if (comp == 0)
                return node.value;
        }
        return null;
    }

    public boolean containsKey(Key key) {
        return get(key) != null;
    }

    /** Put a key value pair into the BST */
    public void put(Key key, Value value) {
        ROOT = put(ROOT, key, value);
    }

    private Node put(Node root, Key key, Value value) {
        if (root == null)
            root = new Node(key, value, RED, 1);

        /** Insert into tree according to comparasion */
        int comp = key.compareTo(root.key);
        if (comp < 0)
            root = put(root.left, key, value);
        else if (comp > 0)
            root = put(root.right, key, value);
        else
            root.value = value;

        /** rotate tree */
        /** right is red but left not right, lean direction wrong. */
        if (isRed(root.right) && !isRed(root.left))
            root = rotateLeft(root);

        /** Both left and left.left are red, try to rotate right */
        if (isRed(root.left) && isRed(root.left.left))
            root = rotateRight(root);

        /** If both left and right are red, then flip */
        if (isRed(root.left) && isRed(root.right))
            flipColor(root);

        root.N = size(root.left) + size(root.right) + 1;
        return root;
    }

    /** Delete operations. */
    public void delete(Key key) {
        if (!containsKey(key)) {
            System.err.println("Error: symbol table does not contain target key");
            return;
        }
        
        /** If both root and children are black, set the root red */
        if (isRed(ROOT.left) && isRed(ROOT.right))
            ROOT.color = RED;
        
        ROOT = delete(ROOT, key);
        if (!isEmpty()) ROOT.color = BLACK;
    }
    
    private Node delete(Node root, Key key) {
       /** Delete Operation no implemented */
        return root;
    }

    /**
     * Basic rotate and flip color operation Pay attention to the functions
     * signature here, it returns a Node, which is actually the new root of
     * subtree.
     */
    private Node rotateLeft(Node root) {
        Node right = root.right;
        root.right = right.left;
        right.left = root;
        right.color = root.color;
        root.color = RED;
        right.N = root.N;
        root.N = 1 + size(root.left) + size(root.right);
        return right;
    }

    private Node rotateRight(Node root) {
        Node left = root.left;
        root.left = left.right;
        left.right = root;

        left.color = root.color;
        root.color = RED;
        left.N = root.N;
        root.N = 1 + size(root.left) + size(root.right);
        return left;
    }

    private void flipColor(Node root) {
        root.color = RED;
        root.left.color = BLACK;
        root.right.color = BLACK;
    }
    
    private Node moveRedLeft(Node root) {
        flipColor(root);
        if (isRed(root.right.left)) {
            root.right = rotateRight(root.right);
            root = rotateLeft(root);
        }
        return root;
    }
    
    
    private Node moveRedRight(Node root) {
        flipColor(root);
        if (isRed(root.left.left)) {
            root = rotateRight(root);
        }
        return root;
    }
    
    private Node balance(Node root) {
        if (isRed(root.right) && !isRed(root.left))
            root = rotateLeft(root);
        
        if (isRed(root.left) && isRed(root.left))
            root = rotateRight(root);
        
        if (isRed(root.left) && isRed(root.right))
            flipColor(root);
        root.N = size(root.left) + size(root.right) + 1;
        
        return root;
     }
}
