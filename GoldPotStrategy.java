package interviewquestions.google;

import java.util.*;

public class GoldPotStrategy {
    public int winningPots(int[] pots) {
        if (pots == null || pots.length == 0)
            return 0;
        return winningPots(pots, 0, pots.length - 1);
    }

    /**
     * A has two choice, pots[start] or pots[end]. A will chose one from the two
     * that has the best result. But that is also depends on B. B can chose
     * pots[start + 1]/pots[end](if A chose pots[start]) or chose
     * pots[start]/pots[end - 1](if A chose pots[end]). And A will chose the
     * next one from the rest. Since B will also use the same strategy, which is
     * maximize B's choice. Then B will chose the one that result in the minimal
     * result of A. So we need to find the minimum result of next step.
     */
    public int winningPots(int[] pots, int start, int end) {
        if (start > end)
            return 0;
        int choseFirst = pots[start]
                + Math.min(winningPots(pots, start + 1, end - 1),
                        winningPots(pots, start + 2, end));
        int choseLast = pots[end]
                + Math.min(winningPots(pots, start + 1, end - 1),
                        winningPots(pots, start, end - 2));
        return Math.max(choseFirst, choseLast);
    }

    /**
     * Finding out the winning strategy.
     */
    public List<Integer> findWinningStrategy(int[] pots) {
        List<Integer> selection = new ArrayList<Integer>();
        winningPots(pots, 0, pots.length - 1, new LinkedList<Integer>(),
                selection, 0);
        selection.remove(0);
        return selection;
    }

    /**
     * This is wrong, if selecting in this way, you will get the largest result,
     * but this is not the result that both the user following the same
     * strategy. Since A will get the largest one but B will not let A do this.
     * B is trying to minimize the gap between the two.
     */
    public int winningPots(int[] pots, int start, int end,
            LinkedList<Integer> path, List<Integer> selection, int sum) {
        if (start > end) {
            if (selection.isEmpty() || selection.get(0) < sum) {
                selection.clear();
                selection.add(sum);
                selection.addAll(path);
            }
            return 0;
        }

        path.add(pots[start]);
        int choseFirst = pots[start]
                + Math.min(
                        winningPots(pots, start + 1, end - 1, path, selection,
                                sum + pots[start]),
                        winningPots(pots, start + 2, end, path, selection, sum
                                + pots[start]));
        path.removeLast();
        path.add(pots[end]);
        int choseLast = pots[end]
                + Math.min(
                        winningPots(pots, start + 1, end - 1, path, selection,
                                sum + pots[end]),
                        winningPots(pots, start, end - 2, path, selection, sum
                                + pots[end]));
        path.removeLast();
        return Math.max(choseFirst, choseLast);
    }

    public List<Integer> findWinningSelection(int[] pots) {
        LinkedList<Integer> selection = new LinkedList<Integer>();
        findSelection(pots, 0, pots.length - 1, selection);
        return selection;
    }

    /**
     * Since whether a path should be used or not is decided after return to the
     * root of the path. So we need to keep all the possible path and decide
     * which path should be kept after all the information has been gathered.
     */
    public int findSelection(int[] pots, int start, int end,
            LinkedList<Integer> path) {
        if (start > end)
            return 0;

        path.add(pots[start]);
        LinkedList<Integer> choseFirstFirst = new LinkedList<Integer>(path);
        int firstFirst = findSelection(pots, start + 2, end, choseFirstFirst);
        LinkedList<Integer> choseFirstLast = new LinkedList<Integer>(path);
        int firstLast = findSelection(pots, start + 1, end - 1, choseFirstLast);

        int choseFirst = pots[start] + Math.min(firstFirst, firstLast);
        LinkedList<Integer> first = firstFirst > firstLast ? choseFirstLast
                : choseFirstFirst;

        path.removeLast();
        path.add(pots[end]);
        LinkedList<Integer> choseLastFirst = new LinkedList<Integer>(path);
        int lastFist = findSelection(pots, start + 1, end - 1, choseLastFirst);
        LinkedList<Integer> choseLastLast = new LinkedList<Integer>(path);
        int lastLast = findSelection(pots, start, end - 2, choseLastLast);

        int choseLast = pots[end] + Math.min(lastFist, lastLast);
        LinkedList<Integer> last = lastFist > lastLast ? choseLastLast
                : choseLastFirst;

        path.clear();
        if (choseFirst > choseLast)
            path.addAll(first);
        else
            path.addAll(last);
        return Math.max(choseFirst, choseLast);
    }
}
