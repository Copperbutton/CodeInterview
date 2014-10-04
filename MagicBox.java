package interviewquestions.palantir;

import java.io.*;
import java.util.*;

public class MagicBox {
    private static final char P = 'P';
    private static final char T = 'T';

    /** Generate every possible flip method */
    public Map<Set<Integer>, Integer> genFlipPatternMap(int num) {
        Map<Set<Integer>, Integer> filpPatternMap = new HashMap<Set<Integer>, Integer>();
        List<Set<Integer>> flipPattList = new ArrayList<Set<Integer>>();
        flipPattList.add(new HashSet<Integer>());

        int[] array = new int[num];
        for (int i = 0; i < num; i++)
            array[i] = i;
        for (int next : array) {
            int resultSize = flipPattList.size();
            for (int i = 0; i < resultSize; i++) {
                Set<Integer> newPattern = new HashSet<Integer>(
                        flipPattList.get(i));
                newPattern.add(next);
                if (!containsSymmPatt(flipPattList, newPattern, array))
                    flipPattList.add(newPattern);
            }
        }
        for (Set<Integer> flipPatternSet : flipPattList) {
            filpPatternMap.put(flipPatternSet, 0);
        }
        return filpPatternMap;
    }

    /**
     * Check if contains symmetric pattern: for example [0] <-> [1, 2] are
     * symmetric patterns
     */
    public boolean containsSymmPatt(List<Set<Integer>> pattList,
            Set<Integer> patt, int[] array) {
        for (Set<Integer> pattOne : pattList)
            if (isSymmPatt(pattOne, patt, array))
                return true;
        return false;
    }

    /**
     * Check is two pattern are symmetric.
     */
    public boolean isSymmPatt(Set<Integer> pattOne, Set<Integer> pattTwo,
            int[] array) {
        for (int col : array)
            if (!pattOne.contains(col) && !pattTwo.contains(col))
                return false;
        return true;
    }

    /** Flip according to the flip way and check if match */
    public int flipAndUpdate(char[] row, Set<Integer> flipPatt) {
        return isMatch(row, flipPatt) ? 1 : 0;
    }

    /** Check if row a match */
    public boolean isMatch(char[] row, Set<Integer> flipPatt) {
        if (row.length < 2)
            return true;
        int first = flipPatt.contains(0) ? row[0] : (row[0] == P ? T : P);
        for (int index = 1; index < row.length; index++) {
            if ((flipPatt.contains(index) && row[index] == first)
                    || (!flipPatt.contains(index) && row[index] != first))
                return false;
        }
        return true;
    }

    public static void main(String args[]) throws Exception {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT */
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        MagicBox solution = new MagicBox();
        String s = in.readLine();

        try {
            String[] head = s.split(" ");
            int ROW = Integer.parseInt(head[0]);
            int COL = Integer.parseInt(head[1]);
            Map<Set<Integer>, Integer> flipPatternMap = solution
                    .genFlipPatternMap(COL);
            int maxWishes = 0;
            while (ROW-- > 0 && (s = in.readLine()) != null) {
                char[] row = s.toCharArray();
                for (Set<Integer> flipPatternList : flipPatternMap.keySet()) {
                    int matchNum = flipPatternMap.get(flipPatternList)
                            + solution.flipAndUpdate(row, flipPatternList);
                    flipPatternMap.put(flipPatternList, matchNum);
                    maxWishes = maxWishes > matchNum ? maxWishes : matchNum;
                }
            }
            System.out.println(maxWishes);
        } catch (IndexOutOfBoundsException iobe) {
            // print error information
        }
    }
}