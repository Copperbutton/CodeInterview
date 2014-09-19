package interviewquestions.other;

/**
 * Implement a hash map
 * 
 */
public class MyHashMap<K, V> {
    private MyHashMapEntry<K, V>[] data;
    private static int DEFAULT_CAPACITY = 11;
    private int size;

    public MyHashMap() {
        this(DEFAULT_CAPACITY);
    }

    @SuppressWarnings("unchecked")
    public MyHashMap(int tableSize) {
        if (tableSize <= 0)
            throw new IllegalArgumentException("Error: invalid table size.");
        data = new MyHashMapEntry[tableSize];
        size = 0;
    }

    public void put(K key, V value) {
        int keyIndex = hash(key);
        MyHashMapEntry<K, V> entry = data[keyIndex];
        while (entry != null) {
            /**
             * If some of the key is empty, them must be deleted, put value into
             * it. Or if key is equal, them override value.
             */
            if ((entry.key == null && entry.value == null)
                    || (entry.key != null && entry.key.equals(key))) {
                entry.key = key;
                entry.value = value;
                return;
            }
            entry = entry.next;
        }
        /**
         * If not found place to insert, them put value into the beginning of
         * the chain
         */
        data[keyIndex] = new MyHashMapEntry<K, V>(key, value, data[keyIndex]);
        size++;
    }

    public V get(K key) {
        int keyIndex = hash(key);
        MyHashMapEntry<K, V> entry = data[keyIndex];
        while (entry != null) {
            if (entry.key.equals(key))
                break;
            entry = entry.next;
        }
        return entry == null ? null : entry.value;
    }

    public void remove(K key) {
        int keyIndex = hash(key);
        MyHashMapEntry<K, V> entry = data[keyIndex];
        while (entry != null) {
            if (entry.key.equals(key)) {
                entry.key = null;
                entry.value = null;
                size--;
                break;
            }
            entry = entry.next;
        }
    }

    public boolean containsKey(K key) {
        int keyIndex = hash(key);
        MyHashMapEntry<K, V> entry = data[keyIndex];
        while (entry != null) {
            if (entry.key != null && entry.key.equals(key))
                break;
            entry = entry.next;
        }
        return entry != null;
    }

    private int hash(K key) {
        if (key == null)
            return 0;
        else
            return Math.abs(key.hashCode() % data.length);
    }

    public int getSize() {
        return size;
    }

}
