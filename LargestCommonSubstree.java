package interviewquestions.medallia;

import java.util.Arrays;
import java.util.List;

/**
 * Question1 From medallia online test.
 * 
 * @author zhangxiaokang
 */
public class ReverseKList {

    public static class LinkedListNode {
        int val;
        LinkedListNode next;

        LinkedListNode(int node_value) {
            val = node_value;
            next = null;
        }
    }

    static LinkedListNode reverse(LinkedListNode list, int k) {
        if (k <= 0)
            throw new IllegalArgumentException("Error: Step k must be positive.");
        int listSize = getListSize(list);
        int firstBlock = listSize % k;
        LinkedListNode dummy = new LinkedListNode(-1);
        dummy.next = list;

        /** Go to end of first block */
        LinkedListNode prevBegin = dummy;
        LinkedListNode begin = list;
        while (firstBlock > 0 && begin != null) {
            prevBegin = prevBegin.next;
            begin = begin.next;
            firstBlock--;
        }

        /** Reverse block by k step*/
        LinkedListNode prevEnd = prevBegin;
        LinkedListNode end = begin;
        while (end != null) {
            for (int i = 0; i < k; i++) {
                prevEnd = prevEnd.next;
                end = end.next;
            }

            prevBegin.next = end;
            prevEnd.next = dummy.next;
            dummy.next = begin;
            if (prevBegin != dummy) {
                begin = prevBegin.next;
                prevEnd = prevBegin;
            } else {
                begin = end;
                prevBegin = prevEnd;
            }
        }

        return dummy.next;
    }

    private static int getListSize(LinkedListNode head) {
        int length = 0;
        while (head != null) {
            length++;
            head = head.next;
        }
        return length;
    }

    /**
     * @return {@link LinkedListNode} head of the linked list containing the
     *         given values
     */
    static LinkedListNode createFromList(List<Integer> list) {
        LinkedListNode head = null;
        LinkedListNode node = null;
        for (int val : list) {
            LinkedListNode prev = node;
            node = new LinkedListNode(val);
            if (head == null)
                head = node;

            if (prev != null)
                prev.next = node;
        }

        return head;
    }

    /** Asserts that two linked lists are equal */
    static void assertLinkedListsEqual(LinkedListNode root1,
            LinkedListNode root2) {
        LinkedListNode node1 = root1;
        LinkedListNode node2 = root2;
        while (node1 != null && node2 != null) {
            if (node1.val != node2.val)
                throw new AssertionError("Expected " + printLinkedList(root1)
                        + " but found " + printLinkedList(root2));
            node1 = node1.next;
            node2 = node2.next;
        }

        if (node1 == null ^ node2 == null)
            throw new AssertionError("Expected " + printLinkedList(root1)
                    + " but found " + printLinkedList(root2));
        System.out.println("Test Finished");
    }

    /** Prints the linked lists */
    static String printLinkedList(LinkedListNode head) {
        StringBuilder sb = new StringBuilder();
        LinkedListNode node = head;
        while (node != null) {
            if (node != head)
                sb.append(',');
            sb.append(node.val);
            node = node.next;
        }
        return sb.toString();
    }

    private static void basicTest() {
        LinkedListNode testLinkedList = createFromList(Arrays.asList(1, 2, 3,
                4, 5, 6, 7, 8, 9));
        LinkedListNode reversed = reverse(testLinkedList, 2);
        LinkedListNode expected = createFromList(Arrays.asList(8, 9, 6, 7, 4,
                5, 2, 3, 1));
        assertLinkedListsEqual(expected, reversed);
    }

    private static void moreTest_1() {
        LinkedListNode testLinkedList = createFromList(Arrays.asList(1, 2, 3,
                4, 5, 6, 7, 8, 9));
        LinkedListNode reversed = reverse(testLinkedList, 1);
        LinkedListNode expected = createFromList(Arrays.asList(9, 8, 7, 6, 5,
                4, 3, 2, 1));
        assertLinkedListsEqual(expected, reversed);
    }

    private static void moreTest_2() {
        LinkedListNode testLinkedList = createFromList(Arrays.asList(2, 3, 4,
                5, 6, 7, 8, 9));
        LinkedListNode reversed = reverse(testLinkedList, 2);
        LinkedListNode expected = createFromList(Arrays.asList(8, 9, 6, 7, 4,
                5, 2, 3));
        assertLinkedListsEqual(expected, reversed);
    }
    
    private static void moreTest_3() {
        LinkedListNode testLinkedList = createFromList(Arrays.asList(1, 2, 3, 4,
                5, 6, 7, 8, 9));
        LinkedListNode reversed = reverse(testLinkedList, 10);
        LinkedListNode expected = createFromList(Arrays.asList(1, 2, 3, 4,
                5, 6, 7, 8, 9));
        assertLinkedListsEqual(expected, reversed);
    }

    public static void main(String[] args) {
        basicTest();
        moreTest_1();
        moreTest_2();
        moreTest_3();
    }
}
