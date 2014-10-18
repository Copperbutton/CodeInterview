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
        List<List<Integer>> result = new ArrayList<List<Integer>> ();
        boolean[] visited = new boolean[teams.length];
        arrangeMatch(teams, visited, 0, 0, new LinkedList<Integer> (), result);
        return arrangement;
    }

    /**
     * Search right results.
     */
    public void arrangeMatch(int[] teams, boolean[] visited, int count, int first,
            LinkedList<Integer> path, List<List<Integer>> result) {
        if (count == teams.length) {
            result.add(new ArrayList<Integer> (path));
            System.out.println(path);
            return;
        }
        
        while(first < teams.length && visited[first])
            first++;
        visited[first] = true;
        path.add(teams[first]);
        
        for (int second = first + 1; second < teams.length; second++) {
            if (visited[second])
                continue;
            visited[second] = true;
            path.add(teams[second]);
            arrangeMatch(teams, visited, count + 2, first + 1, path, result);
            path.removeLast();
            visited[second] = false;
        }
        
        visited[first] = false;
        path.removeLast();
    }
}
