package interviewquestions.google;

/**
 * Event driven request counter. Use a linked list to record the counter of
 * minutes. Use four pointer to track the last event on each of the window.
 * There are two choice in this design update mechanism: 1) event driven
 * mechanism: update the window whenever there are requests comes, including
 * increment event and query requests. 2) Server mechanism: running in
 * independent thread the update all the window per second. This implementation
 * provides an accurate prediction, but have server memory problem.
 */
public class DesignTimer {
    private static class Node {
        private long time;
        private Node next;

        public Node(long time) {
            this.time = time;
            this.next = null;
        }
    }

    private static final long nanoToSecond = 1000 * 1000 * 1000;
    private static final long nanoToMinute = 60 * nanoToSecond;
    private static final long nanoToHour = 60 * nanoToMinute;
    private static final long nanoToDay = 24 * nanoToHour;

    private Node lastSecond;
    private Node lastMinute;
    private Node lastHour;
    private Node lastDay;
    private Node HEAD;

    private int lastSecondCount;
    private int lastMinuteCount;
    private int lastHourCount;
    private int lastDayCount;

    public DesignTimer() {
        this.lastSecond = null;
        this.lastMinute = null;
        this.lastHour = null;
        this.lastDay = null;
        this.HEAD = null;
        this.lastSecondCount = 0;
        this.lastMinuteCount = 0;
        this.lastHourCount = 0;
        this.lastDayCount = 0;
    }

    public void increment() {
        Node now = new Node(System.nanoTime());
        if (HEAD == null) {
            HEAD = now;
        } else {
            HEAD.next = now;
            HEAD = HEAD.next;
        }

        if (lastSecond == null) {
            lastSecond = HEAD;
            lastSecondCount = 0;
        }
        if (lastMinute == null) {
            lastMinute = HEAD;
            lastMinuteCount = 0;
        }
        if (lastHour == null) {
            lastHour = HEAD;
            lastHourCount = 0;
        }
        if (lastDay == null) {
            lastDay = HEAD;
            lastDayCount = 0;
        }
        updateCounts();
        updateWindows(now.time);

    }

    private void updateCounts() {
        lastSecondCount++;
        lastMinuteCount++;
        lastHourCount++;
        lastDayCount++;
    }

    private int count;
    private void updateWindows(long eventTime) {
        count = 0;
        lastSecond = updateWindow(lastSecond, eventTime, nanoToSecond);
        lastSecondCount -= count;
        
        count = 0;
        lastMinute = updateWindow(lastMinute, eventTime, nanoToMinute);
        lastMinuteCount -= count;
        
        count = 0;
        lastHour = updateWindow(lastHour, eventTime, nanoToHour);
        lastHourCount -= count;
        
        count = 0;
        lastDay = updateWindow(lastDay, eventTime, nanoToDay);
        lastDayCount -= count;
    }

    private Node updateWindow(Node windowStart, long currWindowEnd,
            long windLength) {
        while (windowStart != null
                && (currWindowEnd - windowStart.time > windLength)) {
            windowStart = windowStart.next;
            count++;
        }
        return windowStart;
    }

    public int getCountInLastSecond() {
        updateWindows(System.nanoTime());
        return lastSecondCount;
    }

    public int getCountInLastMinute() {
        updateWindows(System.nanoTime());
        return lastMinuteCount;
    }

    public int getCountInLastHour() {
        updateWindows(System.nanoTime());
        return lastHourCount;
    }

    public int getCountInLastDay() {
        updateWindows(System.nanoTime());
        return lastDayCount;
    }
}
