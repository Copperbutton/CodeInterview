/**
 * There is an array of 3-tuple, in the form of (a, 1, 5). The first element in
 * the tuple is the id, the second and third elements are both integers, and the
 * third is always larger than or equal to the second. Assume that the array is
 * sorted based on the second element of the tuple. Write a function that breaks
 * each of the 3-tuple into two 2-tuples like (a, 1) and (a, 5), and sort them
 * according to the integer. E.g. given (a, 1, 5), (b, 2, 4), (c, 7, 8), output
 * (a, 1), (b, 2), (b, 4), (a, 5), (c, 7), (c, 8).
 */
package interviewquestions.google;

import java.util.LinkedList;

public class SortThreeTuple {
    private class TwoTuple {
        public String ID;
        public Integer val;

        public TwoTuple(String ID, String val) {
            this.ID = ID;
            this.val = Integer.parseInt(val);
        }

        public String toString() {
            return ID + " " + val;
        }
    }

    public String[] sortTuple(String[] treeTuple) {
        LinkedList<TwoTuple> list = new LinkedList<TwoTuple>();
        int insertIndex = 0;
        for (int i = 0; i < treeTuple.length; i++) {
            String[] tokens = treeTuple[i].split(" ");
            TwoTuple tuple1 = new TwoTuple(tokens[0], tokens[1]);
            TwoTuple tuple2 = new TwoTuple(tokens[0], tokens[2]);
            insertIndex = insertTuple(list, tuple1, insertIndex);
            insertTuple(list, tuple2, insertIndex);
        }
        
        String[] result = new String[list.size()];
        for (int i = 0; i < list.size(); i ++)
            result[i] = list.get(i).toString();
        return result;
    }
    
    private int insertTuple(LinkedList<TwoTuple> list, TwoTuple newTuple, int index) {
        while (index < list.size()) {
            if (newTuple.val < list.get(index).val)
                break;
            index ++;
        }
        list.add(index, newTuple);
        return index;
    }

}
