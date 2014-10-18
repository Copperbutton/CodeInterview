package interviewquestions.google;

import java.util.*;

/**
 * Generate random events according to their possibilities.
 */
public class RandomEventGenerator {
    private static Random rand = new Random();
    private String[] events;
    private int[] board;
    private static int blcokSize = 100000;

    /**
     * Use a boarder array to record the boundaries.
     */
    public RandomEventGenerator(String[] events, double[] posibilities) {
        this.events = events;
        board = new int[events.length + 1];
        for (int i = 1; i < board.length; i++) {
            board[i] = (int) (posibilities[i - 1] * blcokSize) + board[i - 1];
        }
    }

    public String nextEvent() {
        int randInt = rand.nextInt(blcokSize);
        for (int i = 1; i < board.length; i++) {
            if (randInt < board[i])
                return events[i - 1];
        }
        return "";
    }
}
