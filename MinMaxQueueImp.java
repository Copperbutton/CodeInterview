package interviewquestions.google;

import java.util.*;

public class MinMaxQueueImp<Key extends Comparable<Key>> implements
        MinMaxQueue<Key> {
    private class Node {
        private Key key;
        private Node next;
        private Node prev;

        public Node(Key key) {
            this.key = key;
            this.next = null;
        }
    }

    private Node HEAD;
    private Node TAIL;
    private Node MinHEAD;
    private Node MinTAIL;
    private Node MaxHEAD;
    private Node MaxTAIL;
    private int size;

    public MinMaxQueueImp() {
    }

    @Override
    public void offer(Key key) {
        if (key == null)
            return;
        Node node = new Node(key);
        size++;
        if (HEAD == null && TAIL == null) {
            HEAD = node;
            TAIL = node;
        } else {
            TAIL.next = node;
            TAIL = node;
        }

        /*** Remove All values that greater than the */
        while (MinHEAD != null && key.compareTo(MinHEAD.key) < 0)
            MinHEAD = MinHEAD.next;
        Node minNode = new Node(key);
        /** Value on the minQueue will be non-decendent order. */
        if (MinHEAD == null) {
            MinHEAD = minNode;
            MinTAIL = minNode;
        } else {
            minNode.next = MinHEAD;
            MinHEAD = minNode;
        }

        /** Remove all nodes that less than current key */
        while (MaxHEAD != null && key.compareTo(MaxHEAD.key) > 0)
            MaxHEAD = MaxHEAD.next;
        Node maxNode = new Node(key);

        /**
         * Then put the value into queue, and the max queue will be
         * non-ascendent.
         */
        if (MaxHEAD == null) {
            MaxHEAD = maxNode;
            MaxTAIL = maxNode;
        } else {
            maxNode.next = MaxHEAD;
            MaxHEAD = maxNode;
        }
    }

    @Override
    public Key poll() {
        if (isEmpty())
            throw new NoSuchElementException("Error: queue empty.");
        Node node = HEAD;
        HEAD = HEAD.next;
        if (HEAD == null)
            TAIL = null;
        size--;
        if (MinTAIL.key.compareTo(node.key) == 0) {
            MinTAIL = findPrev(MinHEAD, MinTAIL);
            if (MinTAIL != null)
                MinTAIL.next = null;
            if (MinTAIL == null)
                MinHEAD = null;
        }

        if (MaxTAIL.key.compareTo(node.key) == 0) {
            MaxTAIL = findPrev(MaxHEAD, MaxTAIL);
            if (MaxTAIL != null)
                MaxTAIL.next = null;
            if (MaxTAIL == null)
                MaxHEAD = null;
        }
        return node.key;
    }

    private Node findPrev(Node head, Node tail) {
        if (head == null || tail == null || head == tail)
            return null;
        while (head.next != tail)
            head = head.next;
        return head;
    }

    @Override
    public Key peek() {
        return HEAD == null ? null : HEAD.key;
    }

    @Override
    public Key min() {
        return MinTAIL == null ? null : MinTAIL.key;
    }

    @Override
    public Key max() {
        return MaxTAIL == null ? null : MaxTAIL.key;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

}
