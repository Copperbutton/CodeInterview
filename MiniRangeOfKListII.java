package interviewquestions.google;

import java.util.*;

/**
 * This implementation is based on the algorithm from Careercup.
 * In this algorithm heap works as a sliding window.
 */
public class MiniRangeOfKListII {
    public List<Integer> findMiniRangeOfKList(List<List<Integer>> lists) {
        int size = lists.size();
        List<Integer> result = new ArrayList<Integer> (2);
        PriorityQueue<Node> heap = new PriorityQueue<Node> (size);
        int index = 0;
        for (List<Integer> list : lists) {
            heap.add(new Node(list.get(0), index++));
            list.remove(0);
        }

        int range = Integer.MAX_VALUE;
        while (true) {
            range = checkRange(heap, range, result);
            Node minNode = heap.poll();
            List<Integer> list = lists.get(minNode.source);
            /** Pay attention, this check should be here instead of below.
             *  Heap works as a window here, only break when the window cannot
             *  move forward.
             */
            if (list.isEmpty())
                break;
            heap.offer(new Node(list.get(0), minNode.source));
            list.remove(0);
            /**
             * Check empty should not be here:
             * if (list.isEmpty())
             *  break;
             */
            
        }
        return result;
    }
    
    /**They way to check and track the node needs to be refined. */
    private int checkRange(PriorityQueue<Node> heap, int oldLen, List<Integer> result) {
        Node[] nodes = new Node[heap.size()];
        heap.toArray(nodes);
        Node minNode = null;
        Node maxNode = null;
        for (Node node : nodes) {
            if (minNode == null || node.val < minNode.val)
                minNode = node;
            if (maxNode == null || node.val > maxNode.val)
                maxNode = node;
        }
        if (maxNode.val - minNode.val < oldLen) {
            result.clear();
            result.add(minNode.val);
            result.add(maxNode.val);
            oldLen = maxNode.val - minNode.val;
        }
        return oldLen;
    }
    
}
