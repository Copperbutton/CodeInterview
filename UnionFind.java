package interviewquestions.other;

import java.util.*;

/**
 * Implemented an generic union find data structure. Used a hash map instead of
 * an initial array to record the parent.
 */
public class UnionFind<T> {
    private T[] data;
    private int[] weight;
    private Map<T, Integer> parent;
    private int size;
    private static final int DEFAULT_SIZE = 10;

    public UnionFind() {
        this(DEFAULT_SIZE);
    }

    public UnionFind(int iniCap) {
        @SuppressWarnings("unchecked")
        final T[] newArray = (T[]) new Object[iniCap];
        data = newArray;
        parent = new HashMap<T, Integer>(iniCap);
        weight = new int[iniCap];
        size = 0;
    }

    /** Adding new data to union find, if size reach limit, doubel it */
    public void add(T val) {
        if (parent.containsKey(val))
            return;
        if (size == data.length) {
            data = Arrays.copyOf(data, size * 2);
            weight = Arrays.copyOf(weight, size * 2);
        }
        data[size] = val;
        parent.put(val, size);
        weight[size] = 1;
        size++;
    }

    /**
     * Union two values. Compare their weight, connect the smaller one to the
     * bigger one, then update their weight.
     */
    public void union(T val1, T val2) {
        int parent1 = root(val1);
        int parent2 = root(val2);
        if (parent1 == parent2)
            return;
        if (weight[parent1] > weight[parent2]) {
            weight[parent1] += weight[parent2];
            parent.put(data[parent2], parent1);
        } else {
            weight[parent2] += weight[parent1];
            parent.put(data[parent1], parent2);
        }
    }

    /**
     * Check if two values are connected by checking their root.
     */
    public boolean connected(T val1, T val2) {
        return root(val1) == root(val2);
    }

    /**
     * Count number of elements on the array.
     */
    public int count() {
        return size;
    }

    /**
     * Find root of a value. Compress the path on the process when the root more
     * than two layer by setting current parent to its grand parent.
     */
    private int root(T val) {
        if (!parent.containsKey(val))
            throw new NoSuchElementException("Value not found.");
        int parentIndex = parent.get(val);
        while (data[parentIndex] != val) {
            int grandParentIndex = parent.get(data[parentIndex]);
            if (grandParentIndex != parentIndex) {
                parent.put(val, grandParentIndex);
                weight[parentIndex]--;
            }

            val = data[grandParentIndex];
            parentIndex = parent.get(val);
        }
        return parentIndex;
    }
}
