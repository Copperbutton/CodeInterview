package interviewquestions.other;

/** Implements a symbol table based on triee. */
public class TrieeST<Value> {
    private static final int ASCIINUM = 256;

    private Node ROOT;
    private int keyNum;

    /**
     * Some useful tips when creating an internal class. You should make it
     * static so that 1) the internal class will not bound with the external
     * class. 2) the internal class can not access the non-static class on the
     * external class.
     */
    private static class Node {
        private Object value;
        private Node[] next = new Node[ASCIINUM];
    }

    public TrieeST() {
        this.ROOT = new Node();
        this.keyNum = 0;
    }

    public void put(String key, Value value) {
        if (key == null)
            return;
        put(key, ROOT, 0, value);
    }

    /**
     * The reference code make a creation of the new node at the beginning of
     * the put. Which I doubt if that works, since make it this way will not
     * change the reference value on the value above.
     */
    private void put(String key, Node root, int start, Value value) {
        if (start == key.length()) {
            if (root.value == null)
                keyNum++;
            root.value = value;
            return;
        }
        char next = key.charAt(start);
        if (root.next[next] == null)
            root.next[next] = new Node();
        put(key, root.next[next], start + 1, value);
    }

    public Value get(String key) {
        if (key == null)
            return null;

        return get(key, 0, ROOT);
    }

    public Value get(String key, int index, Node root) {
        if (root == null)
            return null;

        if (index == key.length())
            return (Value) root.value;

        char next = key.charAt(index);
        return get(key, index + 1, root.next[next]);
    }

    public boolean containsKey(String key) {
        return get(key) != null;
    }
}
