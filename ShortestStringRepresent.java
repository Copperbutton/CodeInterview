package interviewquestions.google;

/**
 * Use the shorest unique prefix to represent each word in the array input:
 * ["zebra", "dog", "duck",”dot”] output: {zebra: z, dog: do, duck: du}
 * 
 * [zebra, dog, duck, dove] {zebra:z, dog: dog, duck: du, dove: dov}
 * 
 * [bearcat, bear] {bearcat: bearc, bear: bear}
 */
public class ShortestStringRepresent {
    private static final int ASCII = 256;

    private static class Node {
        private int subPath = 0;
        private Node[] next = new Node[ASCII];
    }

    private Node ROOT;

    public ShortestStringRepresent() {
        ROOT = new Node();
    }

    public void put(String str) {
        if (str == null || str.length() == 0)
            return;
        put(str, 0, ROOT);
    }

    private void put(String str, int index, Node root) {
        if (index == str.length()) {
            root.subPath++;
            return;
        }

        char ch = str.charAt(index);
        if (root.next[ch] == null)
            root.next[ch] = new Node();
        put(str, index + 1, root.next[ch]);
        root.subPath++;
    }

    public String getShortestRepresent(String str) {
        if (str == null)
            return null;
        int end = getShortestRepresent(str, 0, ROOT);
        return end < 0 ? "" : str.substring(0, end);
    }

    private int getShortestRepresent(String str, int index, Node root) {
        if (index == str.length())
            return index;

        int ch = str.charAt(index);
        if (root.next[ch] == null)
            return -1;
        if (root.subPath == 1)
            return index++;
        return getShortestRepresent(str, index + 1, root.next[ch]);
    }
}
