package interviewquestions.other;

/**
 * Implement a sparse vector that support the dot operation with another vector.
 * The good point about sparse vector is that it's time complexity of
 * calculating only proportional to the number of non zero entries on the table.
 */
public class SparseVector {
    /** Sparse vector is based on hash table */
    private HashTableLP<Integer, Double> table;
    private final int N;

    public SparseVector(int N) {
        this.N = N;
        table = new HashTableLP<Integer, Double>();
    }

    /**
     * Notice that sparse vector should be able to clean it self when there are
     * unnecessary data put in.
     */
    public void put(int i, double value) {
        if (i < 0 || i >= N)
            throw new IndexOutOfBoundsException("Error: index out of bounds ");
        if (value == 0.0)
            table.remove(i);
        else
            table.put(i, value);
    }

    /**
     * Check if values is on the table, if not on the table, means that value is
     * 0.0.
     */
    public double get(int i) {
        if (i < 0 || i >= N)
            throw new IndexOutOfBoundsException("Error: index out of bouds");
        if (table.containsKey(i))
            return table.get(i);
        return 0.0;

    }

    /**
     * Return the number of non zero entries.
     */
    public int nnz() {
        return table.size();
    }

    /**
     * Doing dot operation with another vector.
     */
    public double dot(SparseVector vector) {
        if (vector.N != this.N)
            throw new IllegalArgumentException(
                    "Error: given argument does not match in length.");
        double sum = 0;
        if (vector.N < this.N) {
            for (int key : vector.table.keySet())
                if (this.table.containsKey(key))
                    sum += vector.get(key) * this.get(key);
        } else {
            for (int key : this.table.keySet())
                if (vector.table.containsKey(key))
                    sum += this.get(key) * vector.get(key);
        }
        return sum;
    }

    public double dot(double[] data) {
        if (data.length != this.N)
            throw new IllegalArgumentException(
                    "Error: given argument does not match in length.");
        double sum = 0;
        for (int key : this.table.keySet())
            sum += data[key] * this.table.get(key);
        return sum;
    }
}
