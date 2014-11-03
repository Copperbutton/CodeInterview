package interviewquestions.google;

import java.util.*;

public class CircularBuffer {
    private int[] buffer;
    private int count;
    private int head;
    private int tail;

    /**
     * In order to complete a circular buffer, we need one empty slot than the
     * actually size.
     */
    public CircularBuffer(int capacity) {
        if (capacity <= 0)
            throw new IllegalArgumentException(
                    "Error: capacity must be positive.");
        this.buffer = new int[capacity + 1];
        this.head = 0;
        this.tail = 0;
    }

    /**
     * head will be the next position to insert, if its position + 1 equals
     * tail, buffer is full and we need to clear the tail slot. while actually
     * head is the position to insert, head + 1 position is always empty.
     */
    public void add(int newCount) {
        if ((head + 1) % buffer.length == tail) {
            count -= buffer[tail++];
            tail %= buffer.length;
        }
        buffer[head++] = newCount;
        count += newCount;
        head %= buffer.length;
    }

    public int getCount() {
        return this.count;
    }

    public void removeLast() {
        if (tail == head)
            throw new NoSuchElementException("Error: buffer empty.");
        count -= buffer[tail++];
        tail %= buffer.length;
    }

    public boolean isFull() {
        return (head + 1) % buffer.length == tail;
    }

    /**
     * This is used to measure whether an given count term has ended. We achieve
     * this by checking whether there the circular buffer has reached beginning.
     */
    public boolean isReachBeginning() {
        return head == buffer.length - 1;
    }
}
