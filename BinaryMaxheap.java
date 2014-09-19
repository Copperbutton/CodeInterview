package interviewquestions.other;

import java.util.Arrays;

/**
 * 
 * Implement a binary max heap.
 * 
 */

public class BinaryMaxHeap<T extends Comparable<T>> {
    private T[] data;
    private static int DEFAULT_CAPACITY = 10;
    private static int RESIZE_FACTOR = 2;
    private int size;

    @SuppressWarnings("unchecked")
    public BinaryMaxHeap() {
        data = (T[]) (new Comparable[DEFAULT_CAPACITY]);
        this.size = 0;
    }

    @SuppressWarnings("unchecked")
    public BinaryMaxHeap(int startCapacity) {
        if (startCapacity <= 0)
            throw new IllegalArgumentException(
                    "Error: Start Capacity must be positive.");
        data = (T[]) (new Comparable[startCapacity]);
        this.size = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void add(T value) {
        if (size == data.length)
            resize();
        data[size] = value;
        moveUp(data, size);
        size++;
    }

    public T peek() {
        if (isEmpty())
            throw new IllegalStateException();
        return data[0];
    }

    public T remove() {
        /** Do not check size here, since if empty, will throw state error */
        T ret = peek();
        data[0] = data[size - 1];
        data[size - 1] = null;
        moveDown(data, 0, size - 2);
        size--;
        return ret;
    }

    public int getSize() {
        return size;
    }

    public void resize() {
        data = Arrays.copyOf(data, data.length * RESIZE_FACTOR);
    }

    public void moveUp(T[] data, int index) {
        while (hasParent(index)
                && data[index].compareTo(data[getParent(index)]) > 0) {
            swap(data, index, getParent(index));
            index = getParent(index);
        }
    }

    public void moveDown(T[] data, int first, int last) {
        int childIndex = getLeftChild(first);
        while (childIndex <= last) {
            if (childIndex < last
                    && data[childIndex].compareTo(data[childIndex + 1]) < 0)
                childIndex++;
            if (data[first].compareTo(data[childIndex]) < 0) {
                swap(data, first, childIndex);
                first = childIndex;
                childIndex = getLeftChild(childIndex);
            } else {
                childIndex = last + 1;
            }
        }
    }

    public boolean hasParent(int index) {
        return index > 0;
    }

    public int getParent(int index) {
        return (index - 1) / 2;
    }

    public int getLeftChild(int index) {
        return index * 2 + 1;
    }

    public void swap(T[] data, int index1, int index2) {
        T tmp = data[index1];
        data[index1] = data[index2];
        data[index2] = tmp;
    }

}