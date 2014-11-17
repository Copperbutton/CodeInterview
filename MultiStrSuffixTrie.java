package interviewquestions.facebook;

import java.util.*;

/**
 * Write a function that accepts two or more strings and returns the longest
 * common substring in all of them.
 */

public class MultiStrSuffixTrie {
    private static class Node {
        private Set<Integer> hasSuffixOf;
        private Map<String, Node> next;

        public Node() {
            hasSuffixOf = new HashSet<Integer>();
            next = new HashMap<String, Node>();
        }
    }

    private Node ROOT;
    private int count;

    public MultiStrSuffixTrie() {
        ROOT = new Node();
        count = 0;
    }

    public void put(String str) {
        if (str == null)
            return;

        for (int i = 0; i < str.length(); i++) {
            put(str, ROOT, i, count);
        }
        /** Root has suffix of every input string */
        ROOT.hasSuffixOf.add(count);
        count++;
    }

    private void put(String str, Node root, int index, int count) {
        /**
         * If reached end, put a terminator at it.
         */
        if (index == str.length()) {
            String terminator = "$" + count;
            root.next.put(terminator, new Node());
            return;
        }

        /**
         * Try to put the string into next level.
         */
        String next = Character.toString(str.charAt(index));
        if (!root.next.containsKey(next)) {
            root.next.put(next, new Node());
        }
        root.next.get(next).hasSuffixOf.add(count);
        put(str, root.next.get(next), index + 1, count);
    }

    public String longestCommonSubstring() {
        List<String> result = new ArrayList<String>();
        searchLongestCommonSubString(ROOT, new LinkedList<String>(), result);
        return result.isEmpty() ? "" : result.get(0);
    }

    private void searchLongestCommonSubString(Node root,
            LinkedList<String> path, List<String> result) {
        boolean end = true;
        for (String next : root.next.keySet()) {
            if (root.next.get(next).hasSuffixOf.size() == this.count) {
                end = false;
                path.add(next);
                searchLongestCommonSubString(root.next.get(next), path, result);
                path.removeLast();
            }
        }

        if (end) {
            StringBuilder builder = new StringBuilder();
            for (String str : path)
                builder.append(str);
            String pStr = builder.toString();
            if (result.isEmpty() || result.get(0).length() < pStr.length()) {
                result.clear();
                result.add(pStr);
            }
        }
    }

}
