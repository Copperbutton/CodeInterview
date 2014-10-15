package interviewquestions.google;

import java.util.*;

/**
 * Serach for the longest increasing sequence, the increasing numbers does no
 * have to be adjacent.
 */
public class LongestIncreasingSequence {
    public List<Integer> longestIncreasingSequence(int[][] matrix) {
        List<List<Integer>> maxPath = new ArrayList<List<Integer>>();
        if (matrix == null || matrix.length == 0)
            return maxPath.get(0);
        int ROW = matrix.length;
        int COL = matrix[0].length;
        boolean[][] visited = new boolean[ROW][COL];
        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COL; j++) {
                searchLIS(matrix, visited, i, j, matrix[i][j] - 1,
                        new LinkedList<Integer>(), maxPath);
            }
        }
        return maxPath.get(0);
    }

    public void searchLIS(int[][] matrix, boolean[][] visited, int x, int y,
            int prevNum, LinkedList<Integer> path, List<List<Integer>> result) {
        if (x < 0 || x >= matrix.length || y < 0 || y >= matrix[0].length
                || visited[x][y] || matrix[x][y] < prevNum) {
            /**
             * Add current path to result if result is empty or result size <
             * current path size.
             */
            if (result.size() == 0 || path.size() > result.get(0).size()) {
                result.clear();
                result.add(new ArrayList<Integer>(path));
            }
            return;
        }

        path.add(matrix[x][y]);
        visited[x][y] = true;
        searchLIS(matrix, visited, x + 1, y, matrix[x][y], path, result);
        searchLIS(matrix, visited, x - 1, y, matrix[x][y], path, result);
        searchLIS(matrix, visited, x, y + 1, matrix[x][y], path, result);
        searchLIS(matrix, visited, x, y - 1, matrix[x][y], path, result);
        visited[x][y] = false;
        path.removeLast();
    }
}
