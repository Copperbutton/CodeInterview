package interviewquestions.google;

import java.util.*;

/**
 * Any number can be composed of N = a^2 + b^2 + c^2 + d^2 + ....; Find the
 * composition that of the least length.
 */
public class LeastSquareComposition {
    public List<Integer> leastSquareCompos(int N) {
        if (N < 0)
            throw new IllegalArgumentException("");
        List<Integer> compos = new ArrayList<Integer>();
        compos = searchLeastCompo(N, (int) Math.sqrt(N), 0, N);
        return compos;
    }

    /**
     * 1. End used to indicate the beginning number, if started form square
     * root, will have duplicated path. 2. Depth and minDepth used to record the
     * depth that needs to go. Minimum depth will be updated according to the
     * last result. 3. Started from end to begin will ensure a best efficiency
     * since will cover most.
     */
    public List<Integer> searchLeastCompo(int N, int end, int depth,
            int minDepth) {
        List<Integer> compos = new ArrayList<Integer>();

        if (N == 0 || depth > minDepth)
            return compos;

        end = Math.min(end, (int) Math.sqrt(N));
        for (int num = end; num > 0; num--) {
            List<Integer> subComp = searchLeastCompo(N - num * num, num,
                    depth + 1, minDepth);
            subComp.add(num);
            if (compos.isEmpty() || compos.size() > subComp.size()) {
                compos = subComp;
                minDepth = compos.size();
            }
        }
        return compos;
    }
}
