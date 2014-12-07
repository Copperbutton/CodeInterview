package interviewquestions.facebook;

import java.util.*;

public class FindMissingNumbers {
    public List<Integer> findMissingNums(int[] num, int m) {
        List<Integer> missNums = new ArrayList<Integer> ();
        if (num == null || m == 0)
            return missNums;
        int len = num.length;
        int rightMost = len == 0 ? 0 : num[len - 1];
        int rightMiss = len == 0 ? m : m - (num[len - 1] - len);
        for (int i = 1; i <= rightMiss; i++) {
            missNums.add(rightMost + i);
        }
        
        missNums.addAll(findMissingNums(num, 0, len - 1, 0, m - rightMiss));
        Collections.sort(missNums);
        return missNums;
    }
    
    private List<Integer> findMissingNums(int[] num, int left, int right, int leftMiss, int currMiss) {
        List<Integer> missNums = new ArrayList<Integer> ();
        if (left > right || currMiss == leftMiss)
                return missNums;
        if (left == right) {
            for (int gap = 1; gap <= currMiss - leftMiss; gap ++) {
                missNums.add(num[left] - gap);
            }
            return missNums;
        }
        int mid = left + (right - left)/2;
        int gap = num[mid] - 1 - mid;
        missNums.addAll(findMissingNums(num, left, mid, leftMiss, gap));
        missNums.addAll(findMissingNums(num, mid + 1, right, gap, currMiss));
        return missNums;
    }

}
