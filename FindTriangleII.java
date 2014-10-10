package interviewquestions.linkedin;

import java.util.*;

/**
 * Find the combination list of all triangles.
 * 
 * @author zhangxiaokang
 */
public class FindTriangleII {
    public List<List<Integer>> findTriangleSets(int[] array) {
        List<List<Integer>> result = new ArrayList<List<Integer>> ();
        if (array == null || array.length < 3)
            return result;
        
        Arrays.sort(array);
        int len = array.length;
        
        /**
         * Use a matrix to cache all gaps.
         */
        int[][] gap = new int[len][len];
        for (int i = 1; i < len - 1; i++) {
            for (int j = i + 1; j < len; j++) {
                gap[i][j] = array[j] - array[i];
            }
        }
        
        /**
         * Search all combinations.
         */
        for (int i = 0; i < len - 2; i++) {
            for (int j = i + 1; j < len - 1; j++) {
                for (int k = j + 1; j < len; k++) {
                    if (array[i] > gap[j][k]) {
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
