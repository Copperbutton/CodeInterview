package interviewquestions.other;

import java.util.*;
import java.io.*;

public class Quentile {
    public static void main(String args[]) throws Exception {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT */
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String s = in.readLine();
        try {
            int Q = Integer.parseInt(s);
            int M = Integer.parseInt(s = in.readLine());
            int N = 0;
            /** Read pair input line by line and update tree map */
            Map<Integer, Integer> pairs = new HashMap<Integer, Integer>();
            while (M-- > 0 && (s = in.readLine()) != null) {
                String[] tokens = s.split(" ");
                int v = Integer.parseInt(tokens[0]);
                int c = Integer.parseInt(tokens[1]);
                if (!pairs.containsKey(v))
                    pairs.put(v, c);
                else
                    pairs.put(v, pairs.get(v) + c);
                N += c;
            }

            /** Aggregrate counts */
            aggregatePairCount(pairs);
            /** Generate all indexes */
            List<Integer> indexes = generateIndexes(Q, N);
            /** Calculate quantile from tree map */
            List<Integer> result = caculateQuentile(pairs, indexes);

            /** Output results */
            for (int quentile : result)
                System.out.println(quentile);

        } catch (IndexOutOfBoundsException iobe) {
            // print error information
        }
    }

    /**
     * Aggregate pairs count, adding accumulated count into current. Since tree
     * Map is sorted, can finish this in O(n) time.
     */
    private static void aggregatePairCount(Map<Integer, Integer> pairs) {
        int count = 0;
        for (Integer key : pairs.keySet()) {
            count += pairs.get(key);
            pairs.put(key, count);
        }
    }

    /** Generate all indexes for */
    private static List<Integer> generateIndexes(int Q, int N) {
        List<Integer> indexes = new ArrayList<Integer>(Q - 1);
        for (int k = 1; k < Q; k++) {
            int index = (int) Math.ceil(N * k / Q);
            indexes.add(index);
        }
        return indexes;
    }

    /**
     * Calculate quentile: check if indexes lies in certain range, if so the
     * number must be the number on the range.
     */
    private static List<Integer> caculateQuentile(Map<Integer, Integer> pairs,
            List<Integer> indexes) {
        int prev = 0, curr = 0;
        int index = 0;
        List<Integer> result = new ArrayList<Integer>(indexes.size());
        for (Integer key : pairs.keySet()) {
            prev = curr;
            curr += pairs.get(key);
            /** Add all key that full fill requirement into result */
            while (index < indexes.size() && indexes.get(index) > prev
                    && indexes.get(index) <= curr) {
                result.add(key);
                index++;
            }
        }
        return result;
    }
}