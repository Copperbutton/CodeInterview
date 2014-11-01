package interviewquestions.google;

import java.util.*;

/***
 * This problem is the same as the schedule work problem. But this solution is
 * much faster. Time complexity: m task, n servers, findKnspack for every
 * server: C*m, where C is the capacity of the server, in total c * m * n.
 * Generate previous path: n, generate path m. so total time complexity is C * m
 * * n. But if C is very big, the time complexity can still be very big.
 */
public class SchduleWorkII {
    public boolean canArrangeTaskOnServer(int[] tasks, int[] servers) {
        if (tasks == null || servers == null
                || (servers.length == 0 && tasks.length != 0))
            return false;
        
        /** Sort is important for this algorithm: */
        Arrays.sort(tasks);
        Arrays.sort(servers);

        /** Print */
        System.out.println("Tasks: " + Arrays.toString(tasks));
        System.out.println("Servers: " + Arrays.toString(servers));

        int taskNum = tasks.length;
        boolean[] taskUsed = new boolean[taskNum];
        for (int capacity : servers) {
            Map<Integer, Integer> prev = genPrevMap(tasks, taskUsed);
            findKnspack(tasks, capacity, taskUsed, prev);
        }

        for (boolean used : taskUsed)
            if (!used)
                return false;
        
        return true;
        
    }

    /**
     * Here I used a hash map to track the previous task that has not been used.
     * Pay attention to the index, matrix is one based for DP reason, task is 0
     * based.
     */
    private Map<Integer, Integer> genPrevMap(int[] tasks, boolean[] taskUsed) {
        int prevIndex = 0;
        Map<Integer, Integer> prev = new HashMap<Integer, Integer>();
        for (int i = 0; i < tasks.length; i++) {
            if (taskUsed[i])
                continue;
            prev.put(i + 1, prevIndex);
            prevIndex = i + 1;
        }
        return prev;
    }

    private void findKnspack(int[] task, int capacity, boolean[] taskUsed,
            Map<Integer, Integer> prev) {
        int taskNum = task.length;

        /**
         * One problem, when you doing search, it is difficult to handle the
         * used tasks.
         */
        int[][] maxCap = new int[capacity + 1][taskNum + 1];
        for (int cap = 1; cap <= capacity; cap++) {
            for (int index = 1; index <= taskNum; index++) {
                if (taskUsed[index - 1])
                    continue;

                int currWeight = task[index - 1];
                int prevCol = prev.get(index);
                if (cap < currWeight)
                    maxCap[cap][index] = maxCap[cap][prevCol];
                else
                    maxCap[cap][index] = Math.max(maxCap[cap][prevCol],
                            currWeight + maxCap[cap - currWeight][prevCol]);
            }
        }

        /** Mark all tasks on the best path as used. */
        markKnspackPath(maxCap, task, taskUsed, prev);
    }

    private void markKnspackPath(int[][] maxCap, int[] task,
            boolean[] taskUsed, Map<Integer, Integer> prev) {
        List<Integer> path = new ArrayList<Integer>();
        int end = taskUsed.length;
        int cap = maxCap.length - 1;
        while (end > 0 && taskUsed[end - 1])
            end--;

        while (end > 0) {
            /** Compare the choice to decide which one is used */
            if (maxCap[cap][end] != maxCap[cap][end - 1]) {
                path.add(end);
                /** An important thing here is to try update the capacity. */
                cap -= task[end - 1];
            }
            end = prev.get(end);
        }

        for (int used : path)
            taskUsed[used - 1] = true;

        /** Print Path: */
        List<Integer> list = new ArrayList<Integer>();
        for (int index : path)
            list.add(task[index - 1]);
        System.out.println("Server " + (maxCap.length - 1) + " task"
                + list.toString());

    }
}
