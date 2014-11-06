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
        int index = index(key, data.length);
        while (data[index] != null && !data[index].key.equals(key)) {
            index++;
            index %= data.length;
        }
        return data[index] == null ? null : (Value) data[index].value;
    }

    /**
     * Find the slot on the table, if there has exitsted, then override, or find
     * the place to put.
     */
    public void put(Key key, Value value) {
        if (size >= data.length / 2)
            resize(data.length * 2);

        int index = index(key, data.length);
        while (data[index] != null && !data[index].key.equals(key)) {
            index = (index + 1) % data.length;
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
            int index = index((Key)node.key, newData.length);
            while (newData[index] != null) {
                index = (index + 1) % newData.length;
            }
            newData[index] = node;
        }
        data = newData;
    }

    private int index(Key key, int base) {
        return (key.hashCode() & 0x7fffffff) % base;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public int size() {
        return this.size;
    }

    /**
     * What to do with hasht able linear probing remove? to copy all values that
     * next to it back?
     */
    public void remove(Key key) {
        /** First step, check if given key on the table */
        if (!containsKey(key))
            return;

        /** Then find the key and set the array slot as empty. */
        int index = index(key, data.length);
        while (!data[index].key.equals(key))
            index = (index + 1) % data.length;
        data[index] = null;

        /** Copy all later data into current position. */
        index = (index + 1) % data.length;
        while (data[index] != null) {
            Node node = data[index];
            data[index] = null;
            /** Put data into new slot, this implementation is not efficient. */
            put((Key) node.key, (Value) node.value);
        }
        size--;
    }

    public boolean containsKey(Key key) {
        return get(key) != null;
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
