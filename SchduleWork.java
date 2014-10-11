package interviewquestions.google;

import java.util.Arrays;

/**
 * There are at most eight servers in a data center. Each server has got a
 * capacity/memory limit. There can be at most 8 tasks that need to be scheduled
 * on those servers. Each task requires certain capacity/memory to run, and each
 * server can handle multiple tasks as long as the capacity limit is not hit.
 * Write a program to see if all of the given tasks can be scheduled or not on
 * the servers?
 * 
 * Ex: Servers capacity limits: 8, 16, 8, 32 Tasks capacity needs: 18, 4, 8, 4,
 * 6, 6, 8, 8 For this example, the program should say 'true'.
 * 
 * Ex2: Server capacity limits: 1, 3 Task capacity needs: 4 For this example,
 * program should return false.
 * 
 */
public class SchduleWork {
    public boolean canSchdule(int[] limit, int[] work) {
        Arrays.sort(limit);
        Arrays.sort(work);
        return findSchdule(limit, work, 0);
    }

    public boolean findSchdule(int[] limit, int[] work, int index) {
        if (index == work.length)
            return true;

        /** Check all slot one by one and if fit, search deeper. */
        for (int i = 0; i < limit.length; i++) {
            if (limit[i] < work[index])
                continue;
            limit[i] -= work[index];
            if (findSchdule(limit, work, index + 1))
                return true;
            limit[i] += work[index];
        }
        return false;
    }
}
