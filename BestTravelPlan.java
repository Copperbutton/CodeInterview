package interviewquestions.google;

import java.util.*;

/**
 * Given a an array that represents the distance from hote to hotel. You
 * penality will be the total distance from last hotel to current hotel
 * substract 100 and do a power 2. Find a optimal trveraling plan to make the
 * peanlity minimal.
 */
public class BestTravelPlan {
    public int findBestTravelPlan(int[] distance) {
        if (distance == null || distance.length == 0)
            return 0;

        int hotelNum = distance.length + 1;
        int[] minPenFromHotel = new int[hotelNum];
        /**
         * Needs to fill the initial form so that later comparison will not
         * override it.
         */
        Arrays.fill(minPenFromHotel, Integer.MAX_VALUE);
        /**
         * Fill it with Integer.Max_VALUE is not a good idea, later comparison
         * will overflow.
         */
        /** Pay attention to plus, if not set as zero, will overflow!!! */
        minPenFromHotel[hotelNum - 1] = 0;
        for (int start = hotelNum - 1; start >= 0; start--) {
            for (int nextStop = start + 1; nextStop < hotelNum; nextStop++) {
                int currPen = (int) Math.pow(
                        getDistance(distance, start, nextStop) - 100, 2);
                minPenFromHotel[start] = Math.min(minPenFromHotel[start],
                        currPen + minPenFromHotel[nextStop]);
            }
        }
        return minPenFromHotel[0];
    }

    /**
     * Please notice that there is a logic error on this code,
     * minPenFromHotel[nextStop][hotelNum - 1] will make the question to a to
     * step questions. This is because of the confusion of its meaning,
     * minPenFromHotel[nextStop][hotelNum - 1] means the penality from nextstop
     * to the end, not the optimal penality that starts from nextStop.
     */
    public int findBestTravelPlanStop(int[] distance) {
        List<Integer> plan = new ArrayList<Integer>();
        int hotelNum = distance.length + 1;
        int[][] minPenFromHotel = new int[hotelNum][hotelNum];

        /** Fill the matrix with the maxValue of integer */
        for (int i = 0; i < hotelNum; i++) {
            for (int j = 0; j < hotelNum; j++)
                minPenFromHotel[i][j] = Integer.MAX_VALUE;
        }
        minPenFromHotel[hotelNum - 1][hotelNum - 1] = 0;

        for (int start = hotelNum - 1; start >= 0; start--) {
            minPenFromHotel[start][start] = 0;
            for (int nextStop = start + 1; nextStop < hotelNum; nextStop++) {
                int currPen = (int) Math.pow(
                        getDistance(distance, start, nextStop) - 100, 2);
                minPenFromHotel[start][nextStop] = Math.min(
                        minPenFromHotel[start][nextStop], currPen
                                + minPenFromHotel[nextStop][hotelNum - 1]);
            }
        }

        /** Search for the minimal distance */
        return minPenFromHotel[0][hotelNum - 1];
    }

    public int findBestTravelPlanStop2(int[] distance) {
        int hotelNum = distance.length + 1;
        int[][] minPenFromHotel = new int[hotelNum][hotelNum];

        /** Fill the matrix with the maxValue of integer */
        for (int i = 0; i < hotelNum; i++) {
            for (int j = 0; j < hotelNum; j++)
                minPenFromHotel[i][j] = Integer.MAX_VALUE;
        }
        minPenFromHotel[hotelNum - 1][hotelNum - 1] = 0;

        /**
         * What we care about if not how nextStop jump to the final, in order to
         * ignore that, we use the minPenFromHotel[start][start] to store the
         * optimal penality that starts from nextStop.
         */
        for (int start = hotelNum - 2; start >= 0; start--) {
            for (int nextStop = start + 1; nextStop < hotelNum; nextStop++) {
                int currPen = (int) Math.pow(
                        getDistance(distance, start, nextStop) - 100, 2);
                minPenFromHotel[start][nextStop] = Math.min(
                        minPenFromHotel[start][nextStop], currPen
                                + minPenFromHotel[nextStop][nextStop]);
                minPenFromHotel[start][start] = Math.min(
                        minPenFromHotel[start][start],
                        minPenFromHotel[start][nextStop]);
            }
        }

        /** Search for the minimal distance */
        return minPenFromHotel[0][0];
    }

    public List<Integer> findBestTravel(int[] distance) {
        List<Integer> path = new ArrayList<Integer>();
        int hotelNum = distance.length + 1;
        int[][] minPenFromHotel = new int[hotelNum][hotelNum];
        int[] nextOptimal = new int[hotelNum];

        /** Fill the matrix with the maxValue of integer */
        for (int i = 0; i < hotelNum; i++) {
            for (int j = 0; j < hotelNum; j++)
                minPenFromHotel[i][j] = Integer.MAX_VALUE;
        }
        minPenFromHotel[hotelNum - 1][hotelNum - 1] = 0;
        nextOptimal[hotelNum - 1] = hotelNum - 1;

        /**
         * What we care about if not how nextStop jump to the final, in order to
         * ignore that, we use the minPenFromHotel[start][start] to store the
         * optimal penality that starts from nextStop.
         */
        for (int start = hotelNum - 2; start >= 0; start--) {
            for (int nextStop = start + 1; nextStop < hotelNum; nextStop++) {
                int currPen = (int) Math.pow(
                        getDistance(distance, start, nextStop) - 100, 2);
                minPenFromHotel[start][nextStop] = Math.min(
                        minPenFromHotel[start][nextStop], currPen
                                + minPenFromHotel[nextStop][nextStop]);
                if (minPenFromHotel[start][start] > minPenFromHotel[start][nextStop]) {
                    minPenFromHotel[start][start] = minPenFromHotel[start][nextStop];
                    nextOptimal[start] = nextStop;
                }

            }
        }

        for (int index = 0; index < hotelNum; index++) {
            path.add(index);
            index = nextOptimal[index];
        }
        path.add(hotelNum - 1);
        return path;
    }

    /** We can also finish the computation with one array */
    public List<Integer> findBestTravel2(int[] distance) {
        if (distance == null || distance.length == 0)
            return null;

        List<Integer> path = new ArrayList<Integer>();
        int hotelNum = distance.length + 1;
        int[] minPenFromHotel = new int[hotelNum];
        int[] nextOptimal = new int[hotelNum];

        Arrays.fill(minPenFromHotel, Integer.MAX_VALUE);
        minPenFromHotel[hotelNum - 1] = 0;
        nextOptimal[hotelNum - 1] = hotelNum - 1;

        for (int start = hotelNum - 1; start >= 0; start--) {
            for (int nextStop = start + 1; nextStop < hotelNum; nextStop++) {
                int currPen = (int) Math.pow(
                        getDistance(distance, start, nextStop) - 100, 2);

                if (minPenFromHotel[start] > currPen
                        + minPenFromHotel[nextStop]) {
                    minPenFromHotel[start] = currPen
                            + minPenFromHotel[nextStop];
                    nextOptimal[start] = nextStop;
                }
            }
        }

        for (int index = 0; index < hotelNum; index++) {
            path.add(index);
            index = nextOptimal[index];
        }
        path.add(hotelNum - 1);
        return path;

    }

    public int getDistance(int[] distance, int start, int next) {
        int totalDist = 0;
        while (start < next)
            totalDist += distance[start++];
        return totalDist;
    }

}
