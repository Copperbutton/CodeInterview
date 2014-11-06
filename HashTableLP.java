package interviewquestions.other;

import java.util.*;

/**
 * Create a hash table with linear probing.
 */
public class HashTableLP<Key, Value> {
    private static final int DEFAULT_TABLE_SIZE = 37;

    /** It's also OK if we do not use a static class to represent the Node. */
    private static class Node {
        private static final int R = 17;
        private Object key;
        private Object value;
        private int hashCode;

        public Node(Object key, Object value) {
            this.key = key;
            this.value = value;
            this.hashCode = 0;
        }

        @Override
        public int hashCode() {
            if (hashCode != 0)
                hashCode = key.hashCode() * R + value.hashCode();
            return hashCode;
        }
    }

    private Node[] data;
    private int size;

    public HashTableLP() {
        this(DEFAULT_TABLE_SIZE);
    }

    public HashTableLP(int size) {
        if (size <= 0)
            throw new IllegalArgumentException(
                    "Error: table size must be positive");
        data = new Node[size];
    }

    /**
     * Search for all possible values. Until one of the value hit or null.
     * 
     */
    @SuppressWarnings("unchecked")
    public Value get(Key key) {
        int index = index(key);
        while (data[index] != null && !data[index].equals(key)) {
            index++;
            index %= data.length;
        }
        return data[index] == null ? null : (Value) data[index].value;
    }

    /** What if the hash table is full. */
    public void put(Key key, Value value) {
        if (size >= data.length / 2)
            resize(data.length * 2);

        int index = index(key);
        while (data[index] != null) {
            index++;
            index %= data.length;
        }
        data[index] = new Node(key, value);
        size++;
    }

    /**
     * Resize: 1) when to resize the hash table? 2) Do we need to re hash all
     * elements?
     */
    private void resize(int capacity) {
        Node[] newData = new Node[capacity];
        for (Node node : data) {
            if (node == null)
                continue;
            int index = (node.key.hashCode() & 0x7fffffff) % newData.length;
            while (newData[index] != null) {
                index++;
                index %= newData.length;
            }
            newData[index] = node;
        }
        data = newData;
    }

    private int index(Key key) {
        return (key.hashCode() & 0x7fffffff) % data.length;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public int size() {
        return this.size;
    }

    @SuppressWarnings("unchecked")
    public Iterable<Key> keySet() {
        List<Key> list = new ArrayList<Key>();
        for (Node node : data) {
            if (node != null)
                list.add((Key) node.key);
        }
        return list;
    }

}
