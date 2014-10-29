package interviewquestions.google;

import java.util.*;

/**
 * Give a string, try to insert the minimal number of char into the string to
 * make it a palindrome. Find out the minimal insertion,
 */
public class MinimalPalindromeInsertion {
    public int findMiniPalindromeInsertion(String src) {
        if (src == null || src.length() < 2)
            return 0;
        int len = src.length();
        int[] minInsert = new int[len];
        for (int left = len - 1; left >= 0; left--) {
            int prevLeft = 0;
            for (int right = left; right < len; right++) {
                if (left + 1 >= right) {
                    prevLeft = minInsert[right];
                    minInsert[right] = src.charAt(left) == src.charAt(right) ? 0
                            : 1;
                } else {
                    int prev = minInsert[right];
                    minInsert[right] = src.charAt(left) == src.charAt(right) ? prevLeft
                            : Math.min(minInsert[right - 1], prev) + 1;
                    prevLeft = prev;
                }
            }
        }
        return minInsert[len - 1];
    }

    /*
     * Follow up: can you print the best insert palindrome.
     */
    public String findBestInsertPalindrome(String src) {
        if (src == null || src.length() < 2)
            return src;
        int len = src.length();
        int[][] matrix = new int[len][len];
        generateMinInsertMatrix(src, matrix);

        return searchMinInsertMatrix(src, matrix);
    }

    public void generateMinInsertMatrix(String src, int[][] matrix) {
        int len = src.length();
        for (int row = len - 1; row >= 0; row--) {
            for (int col = row; col < len; col++) {
                if (row + 1 >= col) {
                    matrix[row][col] = src.charAt(row) == src.charAt(col) ? 0
                            : 1;
                } else
                    matrix[row][col] = src.charAt(row) == src.charAt(col) ? matrix[row + 1][col - 1]
                            : Math.min(matrix[row + 1][col],
                                    matrix[row][col - 1]) + 1;
            }
        }
    }

    private static class Node implements Comparable<Node> {
        private int index;
        private char ch;

        public Node(int index, char ch) {
            this.index = index;
            this.ch = ch;
        }

        @Override
        public int compareTo(Node arg) {
            if (this.index >= arg.index)
                return 1;
            else
                return -1;
        }
    }

    private String searchMinInsertMatrix(String src, int[][] matrix) {
        int row = 0;
        int col = matrix[0].length - 1;
        int insertNum = matrix[0][col];
        Queue<Character> left = new LinkedList<Character>();
        PriorityQueue<Node> minHeap = new PriorityQueue<Node>();
        for (int count = insertNum; count > 0;) {
            if (src.charAt(row) == src.charAt(col)) {
                row = row + 1;
                col = col - 1;
            } else {
                /**
                 * Should be careful here: since we use >=, only one queue is
                 * enough.
                 */
                if (matrix[row + 1][col] >= matrix[row][col - 1]) {
                    if (row - 1 < 0)
                        left.offer(src.charAt(col));
                    else
                        minHeap.add(new Node(row - 1, src.charAt(col)));
                    col = col - 1;
                } else {
                    minHeap.add(new Node(col + 1, src.charAt(row)));
                    row = row + 1;
                }
                count--;
            }
        }

        StringBuilder builder = new StringBuilder();

        /** Put all the lefts into builder. */
        while (!left.isEmpty())
            builder.append(left.poll());

        /** Copy all chars into builder by their order. */
        for (int i = 0; i < src.length(); i++) {
            while (!minHeap.isEmpty() && minHeap.peek().index < i) {
                Node next = minHeap.poll();
                builder.append(next.ch);
            }
            builder.append(src.charAt(i));
        }

        while (!minHeap.isEmpty()) {
            Node next = minHeap.poll();
            builder.append(next.ch);
        }

        return builder.toString();
    }
}
