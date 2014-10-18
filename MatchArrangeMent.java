package interviewquestions.google;

import java.util.*;

/**
 * Arrange the matches so that on each round all team will get involved.
 */
public class MatchArrangeMent {
    public List<List<Integer>> arrangeMatch(int[] teams) {
        List<List<Integer>> arrangement = new ArrayList<List<Integer>>();
        if (teams == null || teams.length == 0)
            return arrangement;
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        boolean[] visited = new boolean[teams.length];
        LinkedList<Integer> path = new LinkedList<Integer>();

        /** To support both even number of teams or odd number of teams */
        if (teams.length % 2 == 0)
            arrangeMatch(teams, visited, 0, path, result);
        else {
            for (int i = 0; i < teams.length; i++) {
                path.add(teams[i]);
                visited[i] = true;
                arrangeMatch(teams, visited, 0, path, result);
                visited[i] = false;
                path.removeLast();
            }
        }
        return arrangement;
    }

    /**
     * Search right results. Here you don't need a count, we can use path to caculate numebr of items.
     */
    public void arrangeMatch(int[] teams, boolean[] visited, int first,
            LinkedList<Integer> path, List<List<Integer>> result) {
        if (path.size() == teams.length) {
            result.add(new ArrayList<Integer>(path));
            System.out.println(path);
            return;
        }

        while (first < teams.length && visited[first])
            first++;
        visited[first] = true;
        path.add(teams[first]);

        for (int second = first + 1; second < teams.length; second++) {
            if (visited[second])
                continue;
            visited[second] = true;
            path.add(teams[second]);
            arrangeMatch(teams, visited, first + 1, path, result);
            path.removeLast();
            visited[second] = false;
        }

        visited[first] = false;
        path.removeLast();
    }
}
