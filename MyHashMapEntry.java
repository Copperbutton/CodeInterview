package interviewquestions.other;

public class MyHashMapEntry<K, V> {
    public K key;
    public V value;
    public MyHashMapEntry<K, V> next;
    
    public MyHashMapEntry(K key, V value) {
        this.key = key;
        this.value = value;
    }
    
    public MyHashMapEntry(K key, V value, MyHashMapEntry<K, V> next) {
        this.key = key;
        this.value = value;
        this.next = next;
    }
}