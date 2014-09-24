package interviewquestions.other;
/**
 * CSE 373, Winter 2011, Jessica Miller
 * An interface that defines the operations for a Set ADT for Strings.
 */
public interface StringSet {
    /** 
     * Adds the specified String to the Set if it is not already present.
     * @return true if this set did not already contain the String 
     */
    public boolean add(String value);

    /**
     * Returns true if the set contains the specified String.
     */
    public boolean contains(String value);
    
    /**
     * Prints the set in a tree-like format.
     */
    public void print();

    /**
     * Removes the specified String from this set if it is present.
     * @return true if this set contained the specified element 
     */
    public boolean remove(String value);
    
    /**
     * Returns the number of elements in this set (its cardinality)
     */
    public int size();
}