package interviewquestions.google;

/**
 * This is an variant implementation of Design Timer. It has a much less memeory
 * usage than previous one.
 */
public class DesignTimerII {
    private static class Event {
        private long time;
        private Event next;

        public Event(long time) {
            this.time = time;
            this.next = null;
        }
    }

    private static final int NANO_IN_SECOND = 1000 * 1000 * 1000;
    private static final int SECONDS_IN_MINUTE = 60;
    private static final int MINUTES_IN_HOUR = 60;
    private static final int HOURS_IN_DAY = 24;

    private long secondStart;
    private Event start;
    private Event end;
    private int lastSecondCount;
    private CircularBuffer lastMinutes;
    private CircularBuffer lastHour;
    private CircularBuffer lastDay;

    public DesignTimerII() {
        this.lastMinutes = new CircularBuffer(SECONDS_IN_MINUTE);
        this.lastHour = new CircularBuffer(MINUTES_IN_HOUR);
        this.lastDay = new CircularBuffer(HOURS_IN_DAY);
    }

    public void increment() {
        Event now = new Event(System.nanoTime());
        if (secondStart == 0) {
            secondStart = now.time;
        }

        if (end == null) {
            start = now;
            end = now;
        } else {
            end.next = now;
            end = now;
        }
        lastSecondCount++;
        updateWindow(now.time);
    }

    private void updateWindow(long time) {
        /**
         * Since we have no idea how many events have taken place during the
         * time, we needs to calculate by their time event.
         */
        for (long nextSecond = secondStart + NANO_IN_SECOND; end != null
                && nextSecond < time; nextSecond += NANO_IN_SECOND) {
            int count = 0;
            while (start != null && start.time < nextSecond) {
                count++;
                start = start.next;
            }
            lastSecondCount -= count;
            secondStart = nextSecond;
            updateLastSecond(count);
            if (start == null)
                end = null;
        }
    }

    /**
     * In sum, the idea of this program is to count the longer time term based
     * the previous shorter time term. If previous time term reached one count,
     * then the current time term will begin accumulating.
     */
    private void updateLastSecond(int count) {
        if (this.lastMinutes.isReachBeginning()) {
            if (this.lastHour.isReachBeginning()) {
                this.lastDay.add(lastHour.getCount());
            }
            this.lastHour.add(this.lastMinutes.getCount());
        }
        this.lastMinutes.add(count);
    }

    public int getCountInLastSecond() {
        updateWindow(System.nanoTime());
        return this.lastSecondCount;
    }

    public int getCountInLastMinute() {
        return Math.max(getCountInLastSecond(), this.lastMinutes.getCount());
    }

    public int getCountInLastHour() {
        return Math.max(getCountInLastMinute(), this.lastHour.getCount());
    }

    public int getCountInLastDay() {
        return Math.max(getCountInLastHour(), this.lastDay.getCount());

    }

}
