package interviewquestions.other;

import java.util.*;

/**
 * This is a suffix tree that can support at most 256 sub tree. This is a suffix
 * tree that only support one string at one time.
 */
public class SuffixTries {
    private static final int ASCIINUM = 256;

    /**
     * Use a node that includes 256 subtrees and a terminator to indicate
     * whether current node is the suffix of some node. The implementation use
     * an array to store the next array, which might not be very flexible. We
     * can use a hash map to store the sub nodes. Use a count field to store how
     * many sub trees under current not;
     * 
     */
    private static class Node {
        private Node[] next;
        private boolean termin;
        private int count;

        public Node(boolean termin) {
            this.next = new Node[ASCIINUM];
            this.termin = termin;
            this.count = 0;
        }
    }

    private Node ROOT;

    /** Starting from all index, put suffix in order into tries */
    public SuffixTries(String src) {
        if (src == null || src.length() == 0)
            return;

        /** Root has an extra terminator, so should have one more count */
        ROOT = new Node(true);
        ROOT.count = 1;
        for (int i = 0; i < src.length(); i++) {
            ROOT.count += putString(ROOT, src, i);
        }
    }

    /** If the string has come to an end, then put it into tries. */
    private int putString(Node root, String src, int index) {
        if (index == src.length()) {
            root.termin = true;
            /**
             * Pay attention to the creationg, needs to add count here since the
             * root.count += * below will not work on the final root node.
             */
            root.count++;
            return 1;
        }

        char ch = src.charAt(index);
        if (root.next[ch] == null) {
            root.next[ch] = new Node(false);
        }
        root.count += putString(root.next[ch], src, index + 1);
        return 1;
    }

    /**
     * The suffix tries support isSuffix query which can check if given traget
     * string is the suffix of the string.
     */
    public boolean isSuffix(String targt) {
        if (targt == null)
            return false;
        return search(ROOT, targt, 0, true);
    }

    public boolean isSubString(String targt) {
        if (targt == null)
            return false;
        return search(ROOT, targt, 0, false);
    }

    /**
     * Search if certain string appeared on the string.
     */
    public boolean search(Node root, String targt, int index, boolean suffix) {
        if (index == targt.length()) {
            return suffix ? root.termin : true;
        }

        char ch = targt.charAt(index);
        if (root.next[ch] == null)
            return false;

        return search(root.next[ch], targt, index + 1, suffix);
    }

    /**
     * Search for the number of repeats for a certain pattern; Return the number
     * of leaves under the last node.
     */
    public int numOfRepeat(String pattern) {
        if (pattern == null)
            return 0;

        return searchNumOfRepeat(ROOT, pattern, 0);
    }

    /** Should not return root directly */
    public int searchNumOfRepeat(Node root, String pattern, int index) {
        if (index == pattern.length()) {
            return root.count;
        }

        char ch = pattern.charAt(index);
        if (root.next[ch] == null)
            return 0;

        if (index == pattern.length() - 1)
            return root.count;

        return searchNumOfRepeat(root.next[ch], pattern, index + 1);
    }

    /**
     * Find the longest repeat on the string, this method required a traversal
     * of the tree.
     */
    public String largestRepeat() {
        List<String> longestRepeat = new ArrayList<String>();
        searchLongestRepeat(ROOT, new StringBuilder(), longestRepeat);
        return longestRepeat.size() > 0 ? longestRepeat.get(0) : "";
    }

    public void searchLongestRepeat(Node root, StringBuilder path,
            List<String> result) {
        if (root == null || root.count < 2) {
            if (root != null
                    && (result.size() == 0 || result.get(0).length() < path
                            .length())) {
                StringBuilder builder = new StringBuilder(path);
                if (builder.length() > 0)
                    builder.setLength(builder.length() - 1);
                result.clear();
                result.add(builder.toString());
            }
            return;
        }

        for (int i = 0; i < root.next.length; i++) {
            if (root.next[i] == null)
                continue;

            path.append((char) i);
            searchLongestRepeat(root.next[i], path, result);
            path.setLength(path.length() - 1);
        }
    }

}
