package interviewquestions.other;

/**
 * CSE 373, Winter 2011, Jessica Miller
 * The StringTreeNode represents a single node in our StringTree.
 */
public class StringTreeNode {
    public String data;             // data stored at this node
    public StringTreeNode left;     // reference to left subtree
    public StringTreeNode right;    // reference to right subtree
    public int height;
    
    // Constructs a leaf node with the given data.
    public StringTreeNode(String data) {
        this(data, null, null);
    }

    // Constructs a leaf or branch node with the given data and links.
    public StringTreeNode(String data, StringTreeNode left, StringTreeNode right) {
        this.data = data;
        this.left = left;
        this.right = right;
        height = 0;
    }    
}
