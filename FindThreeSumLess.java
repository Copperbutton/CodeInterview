package interviewquestions.linkedin;

import java.util.*;

/**
 * Find three sum that less than target from given array.
 * 
 * @author zhangxiaokang
 */
public class FindThreeSumLess {
    public List<List<Integer>> findThreeSumLess(int[] array, int target) {
        List<List<Integer>> result = new ArrayList<List<Integer>> ();
        if (array == null || array.length < 3)
            return result;
        int len = array.length;
        int[][] sum = new int[len][len];
        /** Use a matrix to cache all sum values*/
        for (int i = 1; i < len - 1; i++) {
            for (int j = i + 1; j < len; j++) {
                sum[i][j] = array[i] + array[j];
            }
        }
        
        /** Search all possible combinations */
        for (int i = 0; i < len - 2; i++) {
            for (int j = i + 1; j < len - 1; j++) {
                for (int k = j + 1; k < len; k++) {
                    if (array[i] + sum[j][k] <= target) {
                        Integer[] comb = {array[i], array[j], array[k]};
                        List<Integer> list = Arrays.asList(comb);
                        if (!result.contains(list))
                            result.add(list);
                    }
                }
            }
        }
        return result;
    }
}
