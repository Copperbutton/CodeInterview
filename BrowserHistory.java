package interviewquestions.google;

import java.util.*;

public class BrowserHistory {
    class Node {
        String record;
        Node prev;
        Node next;

        public Node(String record) {
            this.record = record;
            prev = null;
            next = null;
        }
    }

    class DoubleLinkedList {
        private Node HEAD;
        private Node TAIL;
        private int size;
        
        DoubleLinkedList() {
            HEAD = new Node("");
            TAIL = new Node("");
            HEAD.next = TAIL;
            TAIL.prev = HEAD;
            size = 0;
        }

        public List<String> getTopNRecord(int N) {
            List<String> list = new ArrayList<String>();
            Node p = HEAD.next;
            for (int count = 0; count < N && p != TAIL; p = p.next, count++) {
                list.add(p.record);
            }
            return list;
        }

        public void remove(Node node) {
            if (node == HEAD || node == TAIL || getSize() == 0)
                return;
            node.prev.next = node.next;
            node.next.prev = node.prev;
            node.prev = null;
            node.next = null;
            size--;
        }

        public void addHead(Node node) {
            node.next = HEAD.next;
            HEAD.next.prev = node;
            node.prev = HEAD;
            HEAD.next = node;
            size++;
        }

        public void removeLast() {
            if (getSize() == 0)
                return;
            Node node = TAIL.prev;
            node.prev.next = TAIL;
            TAIL = node.prev;
            node.next = null;
            node.prev = null;
            size--;
        }

        public int getSize() {
            return size;
        }
    }

    private Map<String, Node> recordMap = new HashMap<String, Node>();
    private DoubleLinkedList history = new DoubleLinkedList();
    private int capacity;

    public BrowserHistory(int capaicty) {
        this.capacity = capacity;
    }

    public List<String> getHistory(int N) {
        return history.getTopNRecord(N);
    }

    public void addNewRecord(String newRecord) {
        if (recordMap.containsKey(newRecord)) {
            Node node = recordMap.get(newRecord);
            history.remove(node);
            history.addHead(node);
        } else {
            if (history.getSize() == this.capacity)
                history.removeLast();
            Node node = new Node(newRecord);
            history.addHead(node);
            recordMap.put(newRecord, node);
        }
    }
}
