package interviewquestions.linkedin;

import java.util.*;

/**
 * Implement a stack that support peekMax and popMax
 */
public class MaxStack {
    class DoubleLinkedListNode implements Comparable<DoubleLinkedListNode> {
        int val;
        DoubleLinkedListNode next;
        DoubleLinkedListNode prev;
        DoubleLinkedListNode(int val) {
            this.val = val;
            this.next = null;
            this.prev = null;
        }
        
        @Override
        public int compareTo(DoubleLinkedListNode arg) {
            return arg.val - this.val;
        } 
    }
    
    private DoubleLinkedListNode HEAD;
    private PriorityQueue<DoubleLinkedListNode> maxHeap;
    public MaxStack() {
        HEAD = new DoubleLinkedListNode(-1);
        maxHeap = new PriorityQueue<DoubleLinkedListNode> ();
    }
    
    public void push(int val) {
        DoubleLinkedListNode newNode = new DoubleLinkedListNode(val);
        newNode.next = HEAD.next;
        if (HEAD.next != null)
            HEAD.next.prev = newNode;
        newNode.prev = HEAD;
        HEAD.next = newNode;
        maxHeap.offer(newNode);
    }
    
    public int pop(){
        if (HEAD.next == null)
            throw new NoSuchElementException();
        DoubleLinkedListNode retNode = HEAD.next;
        HEAD.next = retNode.next;
        if (retNode.next != null)
            retNode.prev = HEAD;
        maxHeap.remove(retNode);
        return retNode.val;
    }
    
    public int peek() {
        if (HEAD.next == null)
            throw new NoSuchElementException();
        return HEAD.next.val;
    }
    
    public int peekMax() {
        if (maxHeap.isEmpty())
            throw new NoSuchElementException();
        return maxHeap.peek().val;
    }
    
    public int popMax() {
        if (maxHeap.isEmpty())
            throw new NoSuchElementException();
        DoubleLinkedListNode retNode = maxHeap.poll();
        retNode.prev.next = retNode.next;
        if (retNode.next != null)
            retNode.next.prev = retNode.prev;
        return retNode.val;
    }
}
