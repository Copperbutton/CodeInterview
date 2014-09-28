package interviewquestions.rocketfuel;

import java.io.*;
import java.util.*;

public class DanceFloor {
    public void maxDancePair(InputStream input) {
        Scanner scanner = new Scanner(input);
        scanner.nextLine();

        int maxPair = findPair(scanner.nextLine());
        System.out.println(maxPair);
    }

    private int findPair(String pairs) {
        String[] tokens = pairs.split("\\s+");
        int manPairs = Integer.parseInt(tokens[0]);
        int wmanPairs = Integer.parseInt(tokens[1]);
        int lacePairs = Integer.parseInt(tokens[2]) / 2;
        return Math.min(Math.min(manPairs, wmanPairs), lacePairs);
    }

    public static void main(String[] args) {
        DanceFloor sol = new DanceFloor();
        sol.maxDancePair(System.in);
    }
}
