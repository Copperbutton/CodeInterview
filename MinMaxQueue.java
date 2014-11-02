package interviewquestions.google;

/**
 * A queue that support constant query of min/max value.
 */
public interface MinMaxQueue<Key extends Comparable<Key>>{
    public void offer(Key key);
    
    public Key poll();
    
    public Key peek();
    
    public Key min();
    
    public Key max();
    
    public int size();
    
    public boolean isEmpty();
}
