package interviewquestions.google;

import java.util.*;

/**
 * You have k lists of sorted integers. Find the smallest range that includes at
 * least one number from each of the k lists.
 * 
 * For example, List 1: [4, 10, 15, 24, 26] List 2: [0, 9, 12, 20] List 3: [5,
 * 18, 22, 30]
 * 
 * The smallest range here would be [20, 24] as it contains 24 from list 1, 20
 * from list 2, and 22 from list 3.
 * 
 * @author zhangxiaokang
 */
public class MiniRangeOfKList {
    public List<Integer> findMiniRangeOfKList(List<List<Integer>> lists) {
        List<Integer> result = new ArrayList<Integer>(2);
        int size = lists.size();
        int[] foundSrc = new int[size];
        List<Node> mergedList = mergeKList(lists);
        for (int start = 0, end = 0, count = 0; end < mergedList.size(); end++) {
            Node endNode = mergedList.get(end);
            foundSrc[endNode.source]++;
            if (foundSrc[endNode.source] == 1)
                count++;
            if (count >= size) {
                Node startNode = mergedList.get(start);
                while (start < end && foundSrc[startNode.source] > 1) {
                    foundSrc[startNode.source]--;
                    start++;
                    startNode = mergedList.get(start);
                }
                int gap = endNode.val - startNode.val;
                if (result.size() == 0 || gap < result.get(1) - result.get(0)) {
                    result.clear();
                    result.add(startNode.val);
                    result.add(endNode.val);
                }
            }
        }
        return result;
    }

    public List<Node> mergeKList(List<List<Integer>> lists) {
        List<Node> mergedList = new ArrayList<Node>();
        for (int index = 0; index < lists.size(); index++) {
            insertList(mergedList, lists.get(index), index);
        }
        return mergedList;
    }

    public void insertList(List<Node> targt, List<Integer> src, int srcIndx) {
        int indxT = 0;
        for (int val : src) {
            while (indxT < targt.size() && val > targt.get(indxT).val)
                indxT++;
            targt.add(indxT++, new Node(val, srcIndx));
        }
    }
}
