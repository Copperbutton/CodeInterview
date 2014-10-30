package interviewquestions.google;

/**
 * Put a tree on an array, each value on the array was the index of its parent
 * node. delete the sub tree of a given nodes from the array.
 */

public class DeleteSubTreeFromArray {
    private final static int DELETED = -1;

    public void deleteSubTree(int[] parent, int root) {
        if(root < 0 || root > parent.length)
            throw new IndexOutOfBoundsException("Error: invalid index for parent");
        
        if (parent == null || parent.length == 0 )
            return;
        
        for (int i = 0; i < parent.length; i++) {
            if (isOnSubtree(parent, i, root))
                parent[i] = DELETED;
        }
        return;
    }

    /**
     * This solution is very similar with Union Find data structure.
     */
    public boolean isOnSubtree(int[] parent, int child, int root) {
        while (child != root && parent[child] != child
                && parent[child] != DELETED && parent[child] != root)
            child = parent[child];
        return child == root || parent[child] == root
                || parent[child] == DELETED;
    }
}
