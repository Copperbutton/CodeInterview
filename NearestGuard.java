package interviewquestions.google;

import java.util.*;

/**
 * Given a 2-D matrix represents the room, obstacle and guard like the following
 * (0 is room, B->obstacle, G-> Guard): 0 0 0 B G G B 0 0
 * 
 * calculate the steps from a room to nearest Guard and set the matrix, like
 * this 2 1 1 B G G B 1 1 Write the algorithm, with optimal solution.
 */
public class NearestGuard {
    class Pair {
        int x;
        int y;

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public void findNearestGurad(char[][] matrix) {
        if (matrix == null || matrix.length == 0)
            return;
        int ROW = matrix.length;
        int COL = matrix[0].length;
        Queue<Pair> queue = new LinkedList<Pair>();
        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COL; j++) {
                if (matrix[i][j] == 'G')
                    queue.offer(new Pair(i, j));
            }
        }

        /**
         * Why does this method work? Used BFS, each G point will visit only its
         * neighbor four step first. So that means step from G by one step at
         * one time. And who got the grid first, who is the closet.
         */
        while (!queue.isEmpty()) {
            Pair currPair = queue.poll();
            for (int i = -1; i < 2; i++) {
                for (int j = -1; j < 2; j++) {
                    int newX = currPair.x + i;
                    int newY = currPair.y + j;
                    if ((i == 0 || j == 0)
                            && !(i == 0 && j == 0)
                            && (newX >= 0 && newX < ROW && newY >= 0
                                    && newY < COL && matrix[newX][newY] == '0')) {
                        matrix[newX][newY] = matrix[currPair.x][currPair.y] == 'G' ? '1'
                                : (char) (matrix[currPair.x][currPair.y] + 1);
                        queue.offer(new Pair(newX, newY));
                    }
                }
            }
        }
    }

}
